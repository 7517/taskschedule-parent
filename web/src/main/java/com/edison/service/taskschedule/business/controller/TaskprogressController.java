package com.edison.service.taskschedule.business.controller;

import com.alibaba.fastjson.JSONArray;
import com.edison.saas.bootstrap.monitor.annotation.BusinessFuncMonitor;
import com.edison.saas.common.framework.exception.ResourceNotFoundException;
import com.edison.saas.common.framework.spring.DateConverter;
import com.edison.saas.common.framework.web.controller.PageContent;
import com.edison.saas.common.framework.web.data.PageRequest;
import com.edison.saas.common.framework.web.data.PageRequestConvert;
import com.edison.saas.common.framework.web.data.PageSearchRequest;
import com.edison.saas.common.framework.web.ExcelUtil;
import com.edison.service.taskschedule.business.dto.TaskprogressAddDto;
import com.edison.service.taskschedule.business.dto.TaskprogressCondition;
import com.edison.service.taskschedule.business.dto.TaskprogressEditDto;
import com.edison.service.taskschedule.business.vo.TaskprogressVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import com.edison.service.taskschedule.business.domain.Taskprogress;
import com.edison.service.taskschedule.business.service.TaskprogressService;
import com.edison.service.taskschedule.business.valid.TaskprogressValidator;

import org.apache.commons.collections4.CollectionUtils;
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
 * 管理任务进展
 * @author icode
 */
@Api(description = "任务进展", tags = "Taskprogress")
@RestController
@RequestMapping(value = "/taskprogress")
public class TaskprogressController {

	private static final Logger LOGGER = LoggerFactory.getLogger(TaskprogressController.class);


	@Autowired
	private TaskprogressService taskprogressService;



	@Autowired
	private TaskprogressValidator taskprogressValidator;

	@InitBinder
	public void initBinder(WebDataBinder webDataBinder){
		webDataBinder.addValidators(taskprogressValidator);
		webDataBinder.registerCustomEditor(Date.class, new DateConverter());
	}


  
	/**
	 * 新增（修改）任务进展 搁置/取消
	 * @param taskprogressAddDto
	 * @return
	 */
	@ApiOperation(value = "新增", notes = "新增任务进展", httpMethod = "POST")
	@PostMapping
	@ResponseStatus( HttpStatus.CREATED )
  	@BusinessFuncMonitor(value = "taskschedule.task.taskprogress.add")
	public TaskprogressVO add(@RequestBody @Valid TaskprogressAddDto taskprogressAddDto){
		Taskprogress taskprogress = new Taskprogress();
		BeanUtils.copyProperties(taskprogressAddDto, taskprogress);

		taskprogressService.add(taskprogress);

		return  initViewProperty(taskprogress);
	}

	/**
	 * 删除任务进展,id以逗号分隔
	 * @param idArray
	 */
	@ApiOperation(value = "删除", notes = "删除任务进展", httpMethod = "DELETE")
	@DeleteMapping(path="/{idArray}")
  	@BusinessFuncMonitor(value = "taskschedule.task.taskprogress.delete")
	public void delete(@PathVariable String idArray){

	    LOGGER.debug("delete taskprogress :{}", idArray);

		String[] ids = idArray.split(",");
		for (String id : ids ){
			taskprogressService.delete(Long.parseLong(id));
		}

	}

	/**
	 * 更新任务进展
	 * @param taskprogressEditDto
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "修改", notes = "修改产任务进展(修改全部字段,未传入置空)", httpMethod = "PUT")
	@PutMapping("/update")
  	@BusinessFuncMonitor(value = "taskschedule.task.taskprogress.update")
	public	TaskprogressVO update(@RequestBody TaskprogressEditDto taskprogressEditDto){
		Taskprogress taskprogress = new Taskprogress();
		BeanUtils.copyProperties(taskprogressEditDto, taskprogress);
		taskprogressService.merge(taskprogress);

		TaskprogressVO vo = initViewProperty(taskprogress);
		return  vo;
	}

	/**
	 * 根据ID查询任务进展
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据ID查询任务进展", httpMethod = "GET")
	@GetMapping(path="/{id}")
  	@BusinessFuncMonitor(value = "taskschedule.task.taskprogress.get")
	public  TaskprogressVO get(@PathVariable Long id) {

		Taskprogress taskprogress = taskprogressService.find(id);
		if(taskprogress == null){
			throw new ResourceNotFoundException("找不到指定的任务进展，请检查ID");
		}
		TaskprogressVO vo = initViewProperty(taskprogress);
		return vo;
	}

	/**
	 * 查询任务进展列表
	 * @param pageSearchRequest
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据条件查询任务进展列表", httpMethod = "POST")
	@PostMapping(path="/list")
	@BusinessFuncMonitor(value = "taskschedule.task.taskprogress.list")
	public PageContent<TaskprogressVO> list(@RequestBody PageSearchRequest<TaskprogressCondition> pageSearchRequest){

		PageRequest pageRequest = PageRequestConvert.convert(pageSearchRequest);
      
		Page<Taskprogress> page = taskprogressService.find(pageSearchRequest.getSearchCondition(), pageRequest);

		List<TaskprogressVO> voList = new ArrayList<>();
		for(Taskprogress taskprogress : page.getContent()){
			voList.add(initViewProperty(taskprogress));
		}

		PageContent<TaskprogressVO> pageContent = new PageContent<>(voList, page.getTotalElements());
		LOGGER.debug("page Size :{}, total:{}", pageContent.getContent().size() ,pageContent.getTotal());
		return pageContent;

	}

	/**
     * 导出任务进展列表
     * @param condition
     * @param response
     */
    @ApiOperation(value = "导出", notes = "根据条件导出任务进展列表", httpMethod = "POST")
    @RequestMapping(path="/export")
    public void export(TaskprogressCondition condition, HttpServletResponse response) throws UnsupportedEncodingException {

        PageSearchRequest<TaskprogressCondition> pageSearchRequest = new PageSearchRequest<>();
        pageSearchRequest.setPage(0);
        pageSearchRequest.setLimit(Integer.MAX_VALUE);
        pageSearchRequest.setSearchCondition(condition);

        PageContent<TaskprogressVO> content = this.list(pageSearchRequest);

        List<TaskprogressVO> voList = new ArrayList<>();
        if(CollectionUtils.isNotEmpty(content.getContent())){
            voList.addAll(content.getContent());
        }

        JSONArray jsonArray = new JSONArray();
        for(TaskprogressVO vo : voList){
            jsonArray.add(vo);
        }

        Map<String,String> headMap = new LinkedHashMap<>();

        headMap.put("progressVoice" ,"语音地址");
        headMap.put("taskId" ,"任务主键id");
        headMap.put("taskStatus" ,"任务状态");
        headMap.put("taskProgress" ,"任务进展");
		headMap.put("progressBar" ,"任务进度条");

        String title = new String("任务进展");
        String fileName = new String(("任务进展_"+ DateFormatUtils.ISO_8601_EXTENDED_TIME_FORMAT.format(new Date())).getBytes("UTF-8"), "ISO-8859-1");
        ExcelUtil.downloadExcelFile(title, headMap, jsonArray, response, fileName);
    }

	private TaskprogressVO initViewProperty(Taskprogress taskprogress){

	    TaskprogressVO vo = new TaskprogressVO();
        BeanUtils.copyProperties(taskprogress, vo);


	    //初始化其他对象
        return vo;

	}
/**
 * 根据任务id查询任务进展
 * @param taskid
 * @return
 */
@ApiOperation(value = "查询", notes = "根据任务id查询任务进展", httpMethod = "GET")
@GetMapping("/findByTaskId")
public List<TaskprogressVO> findByTaskId(@RequestParam Long taskId){
	List<TaskprogressVO> list=new ArrayList<>();

	List<Taskprogress> taskIdList = taskprogressService.findByTaskId(taskId);
	for (Taskprogress taskprogress : taskIdList) {
		TaskprogressVO taskprogressVO = initViewProperty(taskprogress);
		list.add(taskprogressVO);
	}
	return list;
}



	/**
	 * 修改任务状态
	 * @param taskprogressAddDto
	 * @param status
	 * @return
	 */

	@ApiOperation(value = "修改", notes = "修改任务状态", httpMethod = "POST")
	@PostMapping("/updateProgressStatus")
	public Taskprogress updateProgressStatus(@RequestParam Long taskId,@RequestParam Long status){
		Taskprogress tp = taskprogressService.updateProgressStatus(taskId, status);
		return tp;
	}
}

