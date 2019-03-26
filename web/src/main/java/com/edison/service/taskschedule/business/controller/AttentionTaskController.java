package com.edison.service.taskschedule.business.controller;

import com.alibaba.fastjson.JSONArray;
import com.edison.saas.bootstrap.monitor.annotation.BusinessFuncMonitor;
import com.edison.saas.common.framework.exception.ResourceNotFoundException;
import com.edison.saas.common.framework.spring.DateConverter;
import com.edison.saas.common.framework.web.ExcelUtil;
import com.edison.saas.common.framework.web.controller.PageContent;
import com.edison.saas.common.framework.web.data.PageRequest;
import com.edison.saas.common.framework.web.data.PageRequestConvert;
import com.edison.saas.common.framework.web.data.PageSearchRequest;
import com.edison.service.taskschedule.business.domain.AttentionTask;
import com.edison.service.taskschedule.business.dto.AttentionTaskAddDto;
import com.edison.service.taskschedule.business.dto.AttentionTaskCondition;
import com.edison.service.taskschedule.business.dto.AttentionTaskEditDto;
import com.edison.service.taskschedule.business.dto.TaskCondition;
import com.edison.service.taskschedule.business.service.AttentionTaskService;

import com.edison.service.taskschedule.business.valid.AttentionTaskValidator;
import com.edison.service.taskschedule.business.vo.AttentionTaskVO;
import com.edison.service.taskschedule.business.vo.TaskVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.UnsupportedEncodingException;
import java.util.*;

/**
 * 管理关注的任务
 * @author icode
 */
@Api(description = "关注的任务", tags = "AttentionTask")
@RestController
@RequestMapping(value = "/taskschedule/task/attentiontask")
public class AttentionTaskController {

	private static final Logger LOGGER = LoggerFactory.getLogger(AttentionTaskController.class);


	@Autowired
	private AttentionTaskService attentionTaskService;



	@Autowired
	private AttentionTaskValidator attentionTaskValidator;

	@InitBinder
	public void initBinder(WebDataBinder webDataBinder){
		webDataBinder.addValidators(attentionTaskValidator);
		webDataBinder.registerCustomEditor(Date.class, new DateConverter());
	}


  
	/**
	 * 新增关注的任务
	 * @param attentionTaskAddDto
	 * @return
	 */
	@ApiOperation(value = "新增", notes = "新增关注的任务", httpMethod = "POST")
	@PostMapping
	@ResponseStatus( HttpStatus.CREATED )
  	@BusinessFuncMonitor(value = "taskschedule.task.attentiontask.add")
	public AttentionTaskVO add(@RequestBody @Valid AttentionTaskAddDto attentionTaskAddDto){
		AttentionTask attentionTask = new AttentionTask();
		BeanUtils.copyProperties(attentionTaskAddDto, attentionTask);

		attentionTaskService.add(attentionTask);

		return  initViewProperty(attentionTask);
	}

	/**
	 * 删除关注的任务,id以逗号分隔
	 * @param idArray
	 */
	@ApiOperation(value = "删除", notes = "删除关注的任务", httpMethod = "DELETE")
	@DeleteMapping(path="/{idArray}")
  	@BusinessFuncMonitor(value = "taskschedule.task.attentiontask.delete")
	public void delete(@PathVariable String idArray){

	    LOGGER.debug("delete attentionTask :{}", idArray);

		String[] ids = idArray.split(",");
		for (String id : ids ){
			attentionTaskService.delete(Long.parseLong(id));
		}

	}

	/**
	 * 更新关注的任务
	 * @param attentionTaskEditDto
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "修改", notes = "修改产关注的任务(修改全部字段,未传入置空)", httpMethod = "PUT")
	@PutMapping(path="/{id}")
  	@BusinessFuncMonitor(value = "taskschedule.task.attentiontask.update")
	public	AttentionTaskVO update(@RequestBody @Valid AttentionTaskEditDto attentionTaskEditDto, @PathVariable Long id){
		AttentionTask attentionTask = new AttentionTask();
		BeanUtils.copyProperties(attentionTaskEditDto, attentionTask);
		attentionTask.setId(id);
		attentionTaskService.merge(attentionTask);

		AttentionTaskVO vo = initViewProperty(attentionTask);
		return  vo;
	}

	/**
	 * 根据ID查询关注的任务
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据ID查询关注的任务", httpMethod = "GET")
	@GetMapping(path="/{id}")
  	@BusinessFuncMonitor(value = "taskschedule.task.attentiontask.get")
	public  AttentionTaskVO get(@PathVariable Long id) {

		AttentionTask attentionTask = attentionTaskService.find(id);
		if(attentionTask == null){
			throw new ResourceNotFoundException("找不到指定的关注的任务，请检查ID");
		}
		AttentionTaskVO vo = initViewProperty(attentionTask);
		return vo;
	}

	/**
	 * 查询关注的任务列表
	 * @param pageSearchRequest
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据条件查询关注的任务列表", httpMethod = "POST")
	@PostMapping(path="/list")
	@BusinessFuncMonitor(value = "taskschedule.task.attentiontask.list")
	public PageContent<AttentionTaskVO> list(@RequestBody PageSearchRequest<AttentionTaskCondition> pageSearchRequest){

		PageRequest pageRequest = PageRequestConvert.convert(pageSearchRequest);
      
		Page<AttentionTask> page = attentionTaskService.find(pageSearchRequest.getSearchCondition(), pageRequest);

		List<AttentionTaskVO> voList = new ArrayList<>();
		for(AttentionTask attentionTask : page.getContent()){
			voList.add(initViewProperty(attentionTask));
		}

		PageContent<AttentionTaskVO> pageContent = new PageContent<>(voList, page.getTotalElements());
		LOGGER.debug("page Size :{}, total:{}", pageContent.getContent().size() ,pageContent.getTotal());
		return pageContent;

	}

	/**
     * 导出关注的任务列表
     * @param condition
     * @param response
     */
    @ApiOperation(value = "导出", notes = "根据条件导出关注的任务列表", httpMethod = "POST")
    @RequestMapping(path="/export")
    public void export(AttentionTaskCondition condition, HttpServletResponse response) throws UnsupportedEncodingException {

        PageSearchRequest<AttentionTaskCondition> pageSearchRequest = new PageSearchRequest<>();
        pageSearchRequest.setPage(0);
        pageSearchRequest.setLimit(Integer.MAX_VALUE);
        pageSearchRequest.setSearchCondition(condition);

        PageContent<AttentionTaskVO> content = this.list(pageSearchRequest);

        List<AttentionTaskVO> voList = new ArrayList<>();
        if(CollectionUtils.isNotEmpty(content.getContent())){
            voList.addAll(content.getContent());
        }

        JSONArray jsonArray = new JSONArray();
        for(AttentionTaskVO vo : voList){
            jsonArray.add(vo);
        }

        Map<String,String> headMap = new LinkedHashMap<>();

        headMap.put("uid" ,"用户id");
        headMap.put("taskId" ,"任务id");

        String title = new String("关注的任务");
        String fileName = new String(("关注的任务_"+ DateFormatUtils.ISO_8601_EXTENDED_TIME_FORMAT.format(new Date())).getBytes("UTF-8"), "ISO-8859-1");
        ExcelUtil.downloadExcelFile(title, headMap, jsonArray, response, fileName);
    }

	private AttentionTaskVO initViewProperty(AttentionTask attentionTask){

	    AttentionTaskVO vo = new AttentionTaskVO();
        BeanUtils.copyProperties(attentionTask, vo);


	    //初始化其他对象
        return vo;

	}



}

