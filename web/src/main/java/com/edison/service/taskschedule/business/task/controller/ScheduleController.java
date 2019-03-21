package com.edison.service.taskschedule.business.task.controller;

import com.alibaba.fastjson.JSONArray;
import com.edison.saas.bootstrap.monitor.annotation.BusinessFuncMonitor;
import com.edison.saas.common.framework.exception.ResourceNotFoundException;
import com.edison.saas.common.framework.spring.DateConverter;
import com.edison.saas.common.framework.web.ExcelUtil;
import com.edison.saas.common.framework.web.controller.PageContent;
import com.edison.saas.common.framework.web.data.PageRequest;
import com.edison.saas.common.framework.web.data.PageRequestConvert;
import com.edison.saas.common.framework.web.data.PageSearchRequest;
import com.edison.schedule.business.schedule.domain.Schedule;
import com.edison.schedule.business.schedule.dto.ScheduleAddDto;
import com.edison.schedule.business.schedule.dto.ScheduleCondition;
import com.edison.schedule.business.schedule.dto.ScheduleEditDto;
import com.edison.schedule.business.schedule.service.ScheduleService;
import com.edison.schedule.business.schedule.valid.ScheduleValidator;
import com.edison.schedule.business.schedule.vo.ScheduleVO;
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
 * 管理日程
 * @author icode
 */
@Api(description = "日程", tags = "Schedule")
@RestController
@RequestMapping(value = "/schedule")
public class ScheduleController {

	private static final Logger LOGGER = LoggerFactory.getLogger(ScheduleController.class);


	@Autowired
	private ScheduleService scheduleService;



	@Autowired
	private ScheduleValidator scheduleValidator;

	@InitBinder
	public void initBinder(WebDataBinder webDataBinder){
		webDataBinder.addValidators(scheduleValidator);
		webDataBinder.registerCustomEditor(Date.class, new DateConverter());
	}


  
	/**
	 * 新增日程 修改时间格式 time单位为分钟
	 * @param scheduleAddDto
	 * @return
	 */
	@ApiOperation(value = "新增", notes = "新增日程", httpMethod = "POST")
	@PostMapping
	@ResponseStatus( HttpStatus.CREATED )
  	@BusinessFuncMonitor(value = "schedule.schedule.schedule.add")
	public Map addSchedule(@RequestBody ScheduleAddDto scheduleAddDto,Long time){
		Schedule schedule=new Schedule();
		BeanUtils.copyProperties(scheduleAddDto,schedule);
		Map map = scheduleService.addSchedule(schedule, time);

		return map;

	}

	/**
	 * 删除日程,id以逗号分隔
	 * @param idArray
	 */
	@ApiOperation(value = "删除", notes = "删除日程", httpMethod = "DELETE")
	@DeleteMapping(path="/{idArray}")
  	@BusinessFuncMonitor(value = "schedule.schedule.schedule.delete")
	public void delete(@PathVariable String idArray){

	    LOGGER.debug("delete schedule :{}", idArray);

		String[] ids = idArray.split(",");
		for (String id : ids ){
			scheduleService.delete(Long.parseLong(id));
		}

	}

	/**
	 * 更新日程
	 * @param scheduleEditDto
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "修改", notes = "修改产日程(修改全部字段,未传入置空)", httpMethod = "PUT")
	@PutMapping(path="/{id}")
  	@BusinessFuncMonitor(value = "schedule.schedule.schedule.update")
	public	ScheduleVO update(@RequestBody @Valid ScheduleEditDto scheduleEditDto, @PathVariable Long id){
		Schedule schedule = new Schedule();
		BeanUtils.copyProperties(scheduleEditDto, schedule);
		schedule.setId(id);
		scheduleService.merge(schedule);

		ScheduleVO vo = initViewProperty(schedule);
		return  vo;
	}

	/**
	 * 根据ID查询日程
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据ID查询日程", httpMethod = "GET")
	@GetMapping(path="/{id}")
  	@BusinessFuncMonitor(value = "schedule.schedule.schedule.get")
	public  ScheduleVO get(@PathVariable Long id) {

		Schedule schedule = scheduleService.find(id);
		if(schedule == null){
			throw new ResourceNotFoundException("找不到指定的日程，请检查ID");
		}
		ScheduleVO vo = initViewProperty(schedule);
		return vo;
	}

	/**
	 * 查询日程列表
	 * @param pageSearchRequest
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据条件查询日程列表", httpMethod = "POST")
	@PostMapping(path="/list")
	@BusinessFuncMonitor(value = "schedule.schedule.schedule.list")
	public PageContent<ScheduleVO> list(@RequestBody PageSearchRequest<ScheduleCondition> pageSearchRequest){

		PageRequest pageRequest = PageRequestConvert.convert(pageSearchRequest);
      
		Page<Schedule> page = scheduleService.find(pageSearchRequest.getSearchCondition(), pageRequest);

		List<ScheduleVO> voList = new ArrayList<>();
		for(Schedule schedule : page.getContent()){
			voList.add(initViewProperty(schedule));
		}

		PageContent<ScheduleVO> pageContent = new PageContent<>(voList, page.getTotalElements());
		LOGGER.debug("page Size :{}, total:{}", pageContent.getContent().size() ,pageContent.getTotal());
		return pageContent;

	}

	/**
     * 导出日程列表
     * @param condition
     * @param response
     */
    @ApiOperation(value = "导出", notes = "根据条件导出日程列表", httpMethod = "POST")
    @RequestMapping(path="/export")
    public void export(ScheduleCondition condition, HttpServletResponse response) throws UnsupportedEncodingException {

        PageSearchRequest<ScheduleCondition> pageSearchRequest = new PageSearchRequest<>();
        pageSearchRequest.setPage(0);
        pageSearchRequest.setLimit(Integer.MAX_VALUE);
        pageSearchRequest.setSearchCondition(condition);

        PageContent<ScheduleVO> content = this.list(pageSearchRequest);

        List<ScheduleVO> voList = new ArrayList<>();
        if(CollectionUtils.isNotEmpty(content.getContent())){
            voList.addAll(content.getContent());
        }

        JSONArray jsonArray = new JSONArray();
        for(ScheduleVO vo : voList){
            jsonArray.add(vo);
        }

        Map<String,String> headMap = new LinkedHashMap<>();

        headMap.put("reminderTime" ,"提醒时间");
        headMap.put("scheduleDetails" ,"日程详情");
        headMap.put("designeeDeptUid" ,"被指派部门主任");
        headMap.put("designeeUid" ,"被指派人");
        headMap.put("beginAt" ,"开始时间");
        headMap.put("designeeDeptId" ,"被指派人部门");
        headMap.put("scheduleStatus" ,"日程状态");
        headMap.put("endAt" ,"结束时间");
        headMap.put("scheduleTitle" ,"日程标题");

        String title = new String("日程");
        String fileName = new String(("日程_"+ DateFormatUtils.ISO_8601_EXTENDED_TIME_FORMAT.format(new Date())).getBytes("UTF-8"), "ISO-8859-1");
        ExcelUtil.downloadExcelFile(title, headMap, jsonArray, response, fileName);
    }

	private ScheduleVO initViewProperty(Schedule schedule){

	    ScheduleVO vo = new ScheduleVO();
        BeanUtils.copyProperties(schedule, vo);


	    //初始化其他对象
        return vo;

	}



	/**
	 * @param date
	 * @return
	 * 根据日期查询日程
	 */
	@GetMapping("/findByDate")
	public List<Map> findByDate(Date beginAt,String uid){
		ScheduleVO scheduleVO=new ScheduleVO();
		List<Schedule> scheduleList = scheduleService.findByDate(beginAt,uid);
		List<Map> list=new ArrayList<>();
		for (Schedule schedule : scheduleList) {
			Map<String,Object> map=new HashMap<>();

			map.put(schedule.getId().toString(),initViewProperty(schedule));
			list.add(map);
		}
		return list;
	}

	/**
	 *查看日程详情
	 * @param id
	 * @return
	 */
	@GetMapping("/findScheduleDetails")
	public ScheduleVO findScheduleDetails(Long id){
		Schedule scheduleDetails = scheduleService.findScheduleDetails(id);
		ScheduleVO scheduleVO = initViewProperty(scheduleDetails);
		return scheduleVO;

	}

	/**
	 * 删除日程
	 * @param id
	 */

	public ScheduleVO deleteSchedule(@RequestBody ScheduleEditDto scheduleEditDto,Long time,Long id){
		Schedule schedule=new Schedule();
		BeanUtils.copyProperties(scheduleEditDto,schedule);
		Schedule schedule1 = scheduleService.updateSchedule(schedule, id, time);
		ScheduleVO scheduleVO = initViewProperty(schedule1);
		return scheduleVO;
	}

}

