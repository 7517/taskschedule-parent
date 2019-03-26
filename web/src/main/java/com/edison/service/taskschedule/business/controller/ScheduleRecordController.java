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

import com.edison.service.taskschedule.business.dto.ScheduleRecordAddDto;
import com.edison.service.taskschedule.business.dto.ScheduleRecordCondition;
import com.edison.service.taskschedule.business.dto.ScheduleRecordEditDto;
import com.edison.service.taskschedule.business.domain.ScheduleRecord;
import com.edison.service.taskschedule.business.service.ScheduleRecordService;
import com.edison.service.taskschedule.business.valid.ScheduleRecordValidator;
import com.edison.service.taskschedule.business.vo.ScheduleRecordVO;
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
 * 管理日程记录
 * @author icode
 */
@Api(description = "日程记录", tags = "ScheduleRecord")
@RestController
@RequestMapping(value = "/schedule/schedule/schedulerecord")
public class ScheduleRecordController {

	private static final Logger LOGGER = LoggerFactory.getLogger(ScheduleRecordController.class);


	@Autowired
	private ScheduleRecordService scheduleRecordService;



	@Autowired
	private ScheduleRecordValidator scheduleRecordValidator;

	@InitBinder
	public void initBinder(WebDataBinder webDataBinder){
		webDataBinder.addValidators(scheduleRecordValidator);
		webDataBinder.registerCustomEditor(Date.class, new DateConverter());
	}


  
	/**
	 * 新增日程记录
	 * @param scheduleRecordAddDto
	 * @return
	 */
	@ApiOperation(value = "新增", notes = "新增日程记录", httpMethod = "POST")
	@PostMapping
	@ResponseStatus( HttpStatus.CREATED )
  	@BusinessFuncMonitor(value = "schedule.schedule.schedulerecord.add")
	public ScheduleRecordVO add(@RequestBody @Valid ScheduleRecordAddDto scheduleRecordAddDto){
		ScheduleRecord scheduleRecord = new ScheduleRecord();
		BeanUtils.copyProperties(scheduleRecordAddDto, scheduleRecord);

		scheduleRecordService.add(scheduleRecord);

		return  initViewProperty(scheduleRecord);
	}

	/**
	 * 删除日程记录,id以逗号分隔
	 * @param idArray
	 */
	@ApiOperation(value = "删除", notes = "删除日程记录", httpMethod = "DELETE")
	@DeleteMapping(path="/{idArray}")
  	@BusinessFuncMonitor(value = "schedule.schedule.schedulerecord.delete")
	public void delete(@PathVariable String idArray){

	    LOGGER.debug("delete scheduleRecord :{}", idArray);

		String[] ids = idArray.split(",");
		for (String id : ids ){
			scheduleRecordService.delete(Long.parseLong(id));
		}

	}

	/**
	 * 更新日程记录
	 * @param scheduleRecordEditDto
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "修改", notes = "修改产日程记录(修改全部字段,未传入置空)", httpMethod = "PUT")
	@PutMapping(path="/{id}")
  	@BusinessFuncMonitor(value = "schedule.schedule.schedulerecord.update")
	public	ScheduleRecordVO update(@RequestBody @Valid ScheduleRecordEditDto scheduleRecordEditDto, @PathVariable Long id){
		ScheduleRecord scheduleRecord = new ScheduleRecord();
		BeanUtils.copyProperties(scheduleRecordEditDto, scheduleRecord);
		scheduleRecord.setId(id);
		scheduleRecordService.merge(scheduleRecord);

		ScheduleRecordVO vo = initViewProperty(scheduleRecord);
		return  vo;
	}

	/**
	 * 根据ID查询日程记录
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据ID查询日程记录", httpMethod = "GET")
	@GetMapping(path="/{id}")
  	@BusinessFuncMonitor(value = "schedule.schedule.schedulerecord.get")
	public  ScheduleRecordVO get(@PathVariable Long id) {

		ScheduleRecord scheduleRecord = scheduleRecordService.find(id);
		if(scheduleRecord == null){
			throw new ResourceNotFoundException("找不到指定的日程记录，请检查ID");
		}
		ScheduleRecordVO vo = initViewProperty(scheduleRecord);
		return vo;
	}

	/**
	 * 查询日程记录列表
	 * @param pageSearchRequest
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据条件查询日程记录列表", httpMethod = "POST")
	@PostMapping(path="/list")
	@BusinessFuncMonitor(value = "schedule.schedule.schedulerecord.list")
	public PageContent<ScheduleRecordVO> list(@RequestBody PageSearchRequest<ScheduleRecordCondition> pageSearchRequest){

		PageRequest pageRequest = PageRequestConvert.convert(pageSearchRequest);
      
		Page<ScheduleRecord> page = scheduleRecordService.find(pageSearchRequest.getSearchCondition(), pageRequest);

		List<ScheduleRecordVO> voList = new ArrayList<>();
		for(ScheduleRecord scheduleRecord : page.getContent()){
			voList.add(initViewProperty(scheduleRecord));
		}

		PageContent<ScheduleRecordVO> pageContent = new PageContent<>(voList, page.getTotalElements());
		LOGGER.debug("page Size :{}, total:{}", pageContent.getContent().size() ,pageContent.getTotal());
		return pageContent;

	}

	/**
     * 导出日程记录列表
     * @param condition
     * @param response
     */
    @ApiOperation(value = "导出", notes = "根据条件导出日程记录列表", httpMethod = "POST")
    @RequestMapping(path="/export")
    public void export(ScheduleRecordCondition condition, HttpServletResponse response) throws UnsupportedEncodingException {

        PageSearchRequest<ScheduleRecordCondition> pageSearchRequest = new PageSearchRequest<>();
        pageSearchRequest.setPage(0);
        pageSearchRequest.setLimit(Integer.MAX_VALUE);
        pageSearchRequest.setSearchCondition(condition);

        PageContent<ScheduleRecordVO> content = this.list(pageSearchRequest);

        List<ScheduleRecordVO> voList = new ArrayList<>();
        if(CollectionUtils.isNotEmpty(content.getContent())){
            voList.addAll(content.getContent());
        }

        JSONArray jsonArray = new JSONArray();
        for(ScheduleRecordVO vo : voList){
            jsonArray.add(vo);
        }

        Map<String,String> headMap = new LinkedHashMap<>();

        headMap.put("scheduleId" ,"日程主键id");
        headMap.put("scheduleRecordText" ,"日程文本信息");
        headMap.put("scheduleRecordVoice" ,"日程语音地址");

        String title = new String("日程记录");
        String fileName = new String(("日程记录_"+ DateFormatUtils.ISO_8601_EXTENDED_TIME_FORMAT.format(new Date())).getBytes("UTF-8"), "ISO-8859-1");
        ExcelUtil.downloadExcelFile(title, headMap, jsonArray, response, fileName);
    }

	private ScheduleRecordVO initViewProperty(ScheduleRecord scheduleRecord){

	    ScheduleRecordVO vo = new ScheduleRecordVO();
        BeanUtils.copyProperties(scheduleRecord, vo);


	    //初始化其他对象
        return vo;

	}

	/**
	 * 返回日程记录
	 * @param scheduleId
	 * @return
	 */
	@GetMapping("/findByScheduleId")
	public List<ScheduleRecordVO> findByScheduleId(@RequestParam Long scheduleId){
		List<ScheduleRecord> list = scheduleRecordService.findBySchedule(scheduleId);

		List<ScheduleRecordVO> scheduleRecordVOList =new ArrayList<>();
		for (ScheduleRecord scheduleRecord : list) {
			ScheduleRecordVO scheduleRecordVO = initViewProperty(scheduleRecord);
			scheduleRecordVOList.add(scheduleRecordVO);
		}

		return scheduleRecordVOList;
    }


}


