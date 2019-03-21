package com.edison.service.taskschedule.business.task.controller;

import com.alibaba.fastjson.JSONArray;
import com.edison.saas.bootstrap.monitor.annotation.BusinessFuncMonitor;
import com.edison.saas.common.framework.exception.ResourceNotFoundException;
import com.edison.saas.common.framework.spring.DateConverter;
import com.edison.saas.common.framework.web.controller.PageContent;
import com.edison.saas.common.framework.web.data.PageRequest;
import com.edison.saas.common.framework.web.data.PageRequestConvert;
import com.edison.saas.common.framework.web.data.PageSearchRequest;
import com.edison.saas.common.framework.web.ExcelUtil;

import com.edison.service.taskschedule.business.task.domain.Task;
import com.edison.service.taskschedule.business.task.domain.Taskprogress;
import com.edison.service.taskschedule.business.task.service.TaskService;
import com.edison.service.taskschedule.business.task.service.TaskprogressService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import com.edison.service.taskschedule.business.task.dto.TaskCondition;
import com.edison.service.taskschedule.business.task.dto.TaskAddDto;
import com.edison.service.taskschedule.business.task.dto.TaskEditDto;

import com.edison.service.taskschedule.business.task.valid.TaskValidator;
import com.edison.service.taskschedule.business.task.vo.TaskVO;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.WebDataBinder;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import java.io.UnsupportedEncodingException;
import java.util.*;

/**
 * 管理任务
 * @author icode
 */
@Api(description = "任务", tags = "Task")
@RestController
@RequestMapping(value = "/task")
public class TaskController {

	private static final Logger LOGGER = LoggerFactory.getLogger(TaskController.class);


	@Autowired
	private TaskService taskService;
	@Autowired
    private TaskprogressService taskprogressService;



	@Autowired
	private TaskValidator taskValidator;

	@InitBinder
	public void initBinder(WebDataBinder webDataBinder){
		webDataBinder.addValidators(taskValidator);
		webDataBinder.registerCustomEditor(Date.class, new DateConverter());
	}


  
	/**
	 * 新增任务
	 * @param taskAddDto
	 * @return
	 */
	@ApiOperation(value = "新增", notes = "新增任务", httpMethod = "POST")
	@PostMapping("/addTask")
	@ResponseStatus( HttpStatus.CREATED )
  	@BusinessFuncMonitor(value = "taskschedule.task.task.add")
	public TaskVO add(@RequestBody @Valid TaskAddDto taskAddDto){
		Task task = new Task();
		BeanUtils.copyProperties(taskAddDto, task);

		taskService.add(task);

		return  initViewProperty(task);
	}

	/**
	 * 删除任务,id以逗号分隔
	 * @param idArray
	 */
	@ApiOperation(value = "删除", notes = "删除任务", httpMethod = "DELETE")
	@DeleteMapping("/deleteTask")
  	@BusinessFuncMonitor(value = "taskschedule.task.task.delete")
	public Map delete(Long id,Long uid){
	    Map<Boolean,String> map=new HashMap<>();

        Task task = taskService.find(id);

		Long createUid = task.getCreateUid();

		//判断用户是否为创建人
		boolean equals = uid.equals(createUid);
		if (equals){
            taskService.deleteById(id);
            taskprogressService.deleteByTaskId(id);
            map.put(true,"删除成功");
            return map;
        }else{
			map.put(false,"您没有这个操作权限");
			return map;
		}
    }

	/**
	 * 更新任务
	 * @param taskEditDto
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "修改", notes = "修改产任务(修改全部字段,未传入置空)", httpMethod = "PUT")
	@PutMapping("/transferTask")
  	@BusinessFuncMonitor(value = "taskschedule.task.task.update")
	public	TaskVO update(@RequestBody @Valid TaskEditDto taskEditDto, @PathVariable Long id){
		Task task = new Task();
		BeanUtils.copyProperties(taskEditDto, task);
		task.setId(id);
		taskService.merge(task);

		TaskVO vo = initViewProperty(task);
		return  vo;
	}

	/**
	 * 根据ID查询任务
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据ID查询任务", httpMethod = "GET")
	@GetMapping(path="/{id}")
  	@BusinessFuncMonitor(value = "taskschedule.task.task.get")
	public  TaskVO get(@PathVariable Long id) {

		Task task = taskService.find(id);
		if(task == null){
			throw new ResourceNotFoundException("找不到指定的任务，请检查ID");
		}
		TaskVO vo = initViewProperty(task);
		return vo;
	}

	/**
	 * 查询任务列表
	 * @param pageSearchRequest
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据条件查询任务列表", httpMethod = "POST")
	@PostMapping("/findTaskList")
	@BusinessFuncMonitor(value = "taskschedule.task.task.list")
	public PageContent<TaskVO> list(@RequestBody PageSearchRequest<TaskCondition> pageSearchRequest){

		Long taskStatus = pageSearchRequest.getSearchCondition().getTaskStatus();
		PageRequest pageRequest = PageRequestConvert.convert(pageSearchRequest);
		TaskCondition searchCondition = pageSearchRequest.getSearchCondition();
		Page<Task> page = taskService.find(searchCondition, pageRequest);
		List<TaskVO> voList = new ArrayList<>();
		for(Task task : page.getContent()){
			Long id = task.getId();

		}

		PageContent<TaskVO> pageContent = new PageContent<>(voList, page.getTotalElements());
		LOGGER.debug("page Size :{}, total:{}", pageContent.getContent().size() ,pageContent.getTotal());
		return pageContent;

	}

	/**
     * 导出任务列表
     * @param condition
     * @param response
     */
    @ApiOperation(value = "导出", notes = "根据条件导出任务列表", httpMethod = "POST")
    @RequestMapping(path="/export")
    public void export(TaskCondition condition, HttpServletResponse response) throws UnsupportedEncodingException {

        PageSearchRequest<TaskCondition> pageSearchRequest = new PageSearchRequest<>();
        pageSearchRequest.setPage(0);
        pageSearchRequest.setLimit(Integer.MAX_VALUE);
        pageSearchRequest.setSearchCondition(condition);

        PageContent<TaskVO> content = this.list(pageSearchRequest);

        List<TaskVO> voList = new ArrayList<>();
        if(CollectionUtils.isNotEmpty(content.getContent())){
            voList.addAll(content.getContent());
        }

        JSONArray jsonArray = new JSONArray();
        for(TaskVO vo : voList){
            jsonArray.add(vo);
        }

        Map<String,String> headMap = new LinkedHashMap<>();

        headMap.put("respDeptUid" ,"责任部门主任id");
        headMap.put("endAt" ,"结束时间");
        headMap.put("assDeptUid" ,"协助部门主任id");
        headMap.put("taskVoice" ,"任务语音地址");
        headMap.put("emerLevel" ,"紧急等级");
        headMap.put("isLongterm" ,"是否为长期任务");
        headMap.put("asstDeptId" ,"协助部门");
        headMap.put("respDeptId" ,"责任部门");
        headMap.put("taskDetails" ,"任务详情");
        headMap.put("reminderTime" ,"提醒时间");
        headMap.put("taskCatagory" ,"任务分类");
        headMap.put("beginAt" ,"开始时间");
        headMap.put("respUid" ,"任务被指派人");
        headMap.put("countdown" ,"倒计时");

        String title = new String("任务");
        String fileName = new String(("任务_"+ DateFormatUtils.ISO_8601_EXTENDED_TIME_FORMAT.format(new Date())).getBytes("UTF-8"), "ISO-8859-1");
        ExcelUtil.downloadExcelFile(title, headMap, jsonArray, response, fileName);
    }

	private TaskVO initViewProperty(Task task){

	    TaskVO vo = new TaskVO();
        BeanUtils.copyProperties(task, vo);


	    //初始化其他对象
        return vo;

	}
	/**
	 * @param date
	 * @return
	 * 根据日期查询任务
	 */
	@ApiOperation(value = "查询", notes = "根据日期查询当天任务", httpMethod = "POST")
	@PostMapping("/findByDate")
	public List<Map> findByDate(Date beginAt,String uid){

		List<Task> taskList = taskService.findByDate(beginAt,uid);
		List<Map> list=new ArrayList<>();
		Map<String,Object> map=new HashMap<>();
		for (Task task : taskList) {
			//String countdown = taskService.countdown(task.getId());
			TaskVO taskVO = initViewProperty(task);
			map.put(taskVO.getId().toString(),taskVO);
			list.add(map);
		}
		return list;
	}

	/**
	 *
	 * @param id
	 * @param respUid
	 * @return
	 */
	@ApiOperation(value = "修改", notes = "添加任务被指派人", httpMethod = "POST")
	@PostMapping("/updateRespUid")
	public TaskVO updateRespUid(Long id,String respUid){

		Task task = taskService.updateRespUid(id, respUid);
		TaskVO taskVO = initViewProperty(task);
		return taskVO;
	}

	/**
	 *
	 * @param id
	 * @param respUid
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据用户与任务id查看任务详情", httpMethod = "GET")
	@GetMapping("/personTask")
	public TaskVO personTask(Long id,String respUid){

		Task task = taskService.prosonTask(id, respUid);
		TaskVO taskVO = initViewProperty(task);
		return taskVO;
	}

	/**
	 * 设置任务提醒时间
	 */
	@ApiOperation(value = "查询", notes = "设置任务提醒时间", httpMethod = "GET")
	@GetMapping("/updateTaskReminderTime")
	public TaskVO updateTaskReminderTime(Long id,Date date){
		Map<Boolean,String> map=new HashMap<>();
		Task task = taskService.updateReminderTime(id, date);
        TaskVO taskVO = initViewProperty(task);
        return taskVO;

    }


	/**
	 * 查询我的任务列表 app  全部则状态为空
	 * @param pageSearchRequest
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "查询我的任务APP", httpMethod = "POST")
	@PostMapping("/findMyTaskListApp")
	public PageContent<TaskVO> findMyTaskListApp(@RequestBody PageSearchRequest<TaskCondition> pageSearchRequest){
		TaskCondition searchCondition = pageSearchRequest.getSearchCondition();
		//serchText接受搜索输入框的内容
		String searchText = searchCondition.getSearchText();
		String uid = searchCondition.getUid();
		PageRequest pageRequest = PageRequestConvert.convert(pageSearchRequest);
		List<TaskVO> voList = new ArrayList<>();
		//如果输入框的内容不为空，将内容赋值给人名，详情
		if (StringUtils.isNotEmpty(searchText)){
			searchCondition.setAssDeptUidName(searchText);
			searchCondition.setRespDeptUidName(searchText);
			searchCondition.setRespUidName(searchText);
			searchCondition.setTaskDetails(searchText);
		}
		//uid为必传属性 将uid赋值给协助部门uid，主责部门uid以及被指派人uid
		searchCondition.setAssDeptUid(uid);
		searchCondition.setRespDeptUid(uid);
		searchCondition.setRespUid(uid);
		Page<Task> tasks = taskService.find(searchCondition, pageRequest);
		Long taskStatus = searchCondition.getTaskStatus();
		if (taskStatus!=null){
			for (Task task : tasks) {
				Long taskId = task.getId();
				Taskprogress taskprogress = taskprogressService.findByCteateAndStatus(taskId, taskStatus);
				TaskVO taskVO = initViewProperty(task);
				//如果查询到任务进展 则赋值
				if (taskprogress!=null){
					taskVO.setTaskProgress(taskprogress.getTaskProgress());
				}
				voList.add(taskVO);
			}
		}else {
			for (Task task : tasks) {
				Taskprogress taskprogress = taskprogressService.findByCreate(task.getId());
				TaskVO taskVO = initViewProperty(task);
				if (taskprogress!=null){
					taskVO.setTaskProgress(taskprogress.getTaskProgress());
				}
				voList.add(taskVO);
			}

		}
		PageContent<TaskVO> pageContent = new PageContent<>(voList, tasks.getTotalElements());
		LOGGER.debug("page Size :{}, total:{}", pageContent.getContent().size() ,pageContent.getTotal());
		return pageContent;
	}




	/**
	 * 查询我发布的任务 app  全部则状态为空
	 * @param pageSearchRequest
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "查询我创建的任务APP", httpMethod = "POST")
	@PostMapping("/findCrateTaskListApp")
	public PageContent<TaskVO> findCrateTaskListApp(@RequestBody PageSearchRequest<TaskCondition> pageSearchRequest){
		TaskCondition searchCondition = pageSearchRequest.getSearchCondition();
		String searchText = searchCondition.getSearchText();
		String uid = searchCondition.getUid();
		long l = Long.parseLong(uid);
		PageRequest pageRequest = PageRequestConvert.convert(pageSearchRequest);
		List<TaskVO> voList = new ArrayList<>();
		if (StringUtils.isNotEmpty(searchText)){
			searchCondition.setAssDeptUidName(searchText);
			searchCondition.setRespDeptUidName(searchText);
			searchCondition.setRespUidName(searchText);
			searchCondition.setTaskDetails(searchText);
		}
		searchCondition.setCreateUid(l);
		Page<Task> tasks = taskService.find(searchCondition, pageRequest);
		Long taskStatus = searchCondition.getTaskStatus();
		if (taskStatus!=null){
			for (Task task : tasks) {
				Long taskId = task.getId();
				Taskprogress taskprogress = taskprogressService.findByCteateAndStatus(taskId, taskStatus);
				TaskVO taskVO = initViewProperty(task);
				if (taskprogress!=null){
					taskVO.setTaskProgress(taskprogress.getTaskProgress());
				}

				voList.add(taskVO);
			}
		}else {
			for (Task task : tasks) {
				Taskprogress taskprogress = taskprogressService.findByCreate(task.getId());
				TaskVO taskVO = initViewProperty(task);
				if (taskprogress!=null){
					taskVO.setTaskProgress(taskprogress.getTaskProgress());
				}
				voList.add(taskVO);
			}

		}
		PageContent<TaskVO> pageContent = new PageContent<>(voList, tasks.getTotalElements());
		LOGGER.debug("page Size :{}, total:{}", pageContent.getContent().size() ,pageContent.getTotal());
		return pageContent;
	}


	/**
	 * 查询我的任务 pc  全部则状态为空
	 * @param pageSearchRequest
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "查询我的任务PC", httpMethod = "POST")
	@PostMapping("/findMyTaskListPC")
	public PageContent<TaskVO> findMyTaskListPC(@RequestBody PageSearchRequest<TaskCondition> pageSearchRequest){
		TaskCondition searchCondition = pageSearchRequest.getSearchCondition();
		String uid = searchCondition.getUid();
		PageRequest pageRequest = PageRequestConvert.convert(pageSearchRequest);
		List<TaskVO> voList = new ArrayList<>();
		searchCondition.setAssDeptUid(uid);
		searchCondition.setRespDeptUid(uid);
		searchCondition.setRespUid(uid);
		Page<Task> tasks = taskService.find(searchCondition, pageRequest);
		Long taskStatus = searchCondition.getTaskStatus();
		if (taskStatus!=null){
			for (Task task : tasks) {
				Long taskId = task.getId();
				Taskprogress taskprogress = taskprogressService.findByCteateAndStatus(taskId, taskStatus);
				TaskVO taskVO = initViewProperty(task);
				if (taskprogress!=null) {
					taskVO.setTaskProgress(taskprogress.getTaskProgress());
				}
				voList.add(taskVO);
			}
		}else {
			for (Task task : tasks) {
				Taskprogress taskprogress = taskprogressService.findByCreate(task.getId());
				TaskVO taskVO = initViewProperty(task);
				if (taskprogress!=null) {
					taskVO.setTaskProgress(taskprogress.getTaskProgress());
				}
				voList.add(taskVO);
			}
		}
		PageContent<TaskVO> pageContent = new PageContent<>(voList, tasks.getTotalElements());
		LOGGER.debug("page Size :{}, total:{}", pageContent.getContent().size() ,pageContent.getTotal());
		return pageContent;
	}

	/**
	 * 查看我发布的任务 PC 状态为全部则 status为空
	 * @param pageSearchRequest
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "查询我创建的任务PC", httpMethod = "POST")

	@PostMapping("/findCrateTaskListPC")
	public PageContent<TaskVO> findCrateTaskListPC(@RequestBody PageSearchRequest<TaskCondition> pageSearchRequest){
		TaskCondition searchCondition = pageSearchRequest.getSearchCondition();
		String uid = searchCondition.getUid();
		long l = Long.parseLong(uid);
		PageRequest pageRequest = PageRequestConvert.convert(pageSearchRequest);
		List<TaskVO> voList = new ArrayList<>();
		searchCondition.setCreateUid(l);
		Page<Task> tasks = taskService.find(searchCondition, pageRequest);
		Long taskStatus = searchCondition.getTaskStatus();
		if (taskStatus!=null){
			for (Task task : tasks) {
				Long taskId = task.getId();
				Taskprogress taskprogress = taskprogressService.findByCteateAndStatus(taskId, taskStatus);
				TaskVO taskVO = initViewProperty(task);
				if (taskprogress!=null) {
					taskVO.setTaskProgress(taskprogress.getTaskProgress());
				}
				voList.add(taskVO);
			}
		}else {
			for (Task task : tasks) {
				Taskprogress taskprogress = taskprogressService.findByCreate(task.getId());
				TaskVO taskVO = initViewProperty(task);
				if (taskprogress!=null) {
					taskVO.setTaskProgress(taskprogress.getTaskProgress());
				}
				voList.add(taskVO);
			}

		}
		PageContent<TaskVO> pageContent = new PageContent<>(voList, tasks.getTotalElements());
		LOGGER.debug("page Size :{}, total:{}", pageContent.getContent().size() ,pageContent.getTotal());
		return pageContent;
	}

}

