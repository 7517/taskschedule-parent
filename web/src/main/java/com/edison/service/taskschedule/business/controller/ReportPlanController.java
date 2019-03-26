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
import com.edison.service.report.business.report.dto.ReportPlanAddDto;
import com.edison.service.report.business.report.dto.ReportPlanCondition;
import com.edison.service.report.business.report.dto.ReportPlanEditDto;
import com.edison.service.report.business.report.vo.ReportPlanVO;
import com.edison.service.taskschedule.business.report.domain.Report;
import com.edison.service.taskschedule.business.report.domain.ReportPlan;
import com.edison.service.taskschedule.business.report.service.ReportPlanService;
import com.edison.service.taskschedule.business.report.service.ReportService;
import com.edison.service.taskschedule.business.report.week.StartWeek;
import com.edison.service.taskschedule.report.report.valid.ReportPlanValidator;
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
 * 管理报告计划
 * @author icode
 */
@Api(description = "报告计划", tags = "ReportPlan")
@RestController
@RequestMapping(value = "/report/report/reportplan")
public class ReportPlanController {

	private static final Logger LOGGER = LoggerFactory.getLogger(ReportPlanController.class);


	@Autowired
	private ReportPlanService reportPlanService;

	@Autowired
	private StartWeek startWeek;
	@Autowired
	private ReportService reportService;



	@Autowired
	private ReportPlanValidator reportPlanValidator;

	@InitBinder
	public void initBinder(WebDataBinder webDataBinder){
		webDataBinder.addValidators(reportPlanValidator);
		webDataBinder.registerCustomEditor(Date.class, new DateConverter());
	}


  
	/**
	 * 新增报告计划
	 * @param reportPlanAddDto
	 * @return
	 */
	@ApiOperation(value = "新增", notes = "新增报告计划", httpMethod = "POST")
	@PostMapping
	@ResponseStatus( HttpStatus.CREATED )
  	@BusinessFuncMonitor(value = "report.report.reportplan.add")
	public ReportPlanVO add(@RequestBody @Valid ReportPlanAddDto reportPlanAddDto){
		ReportPlan reportPlan = new ReportPlan();
		BeanUtils.copyProperties(reportPlanAddDto, reportPlan);

		reportPlanService.add(reportPlan);

		return  initViewProperty(reportPlan);
	}

	/**
	 * 删除报告计划,id以逗号分隔
	 * @param idArray
	 */
	@ApiOperation(value = "删除", notes = "删除报告计划", httpMethod = "DELETE")
	@DeleteMapping(path="/{idArray}")
  	@BusinessFuncMonitor(value = "report.report.reportplan.delete")
	public void delete(@PathVariable String idArray){

	    LOGGER.debug("delete reportPlan :{}", idArray);

		String[] ids = idArray.split(",");
		for (String id : ids ){
			reportPlanService.delete(Long.parseLong(id));
		}

	}

	/**
	 * 更新报告计划
	 * @param reportPlanEditDto
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "修改", notes = "修改产报告计划(修改全部字段,未传入置空)", httpMethod = "PUT")
	@PutMapping(path="/{id}")
  	@BusinessFuncMonitor(value = "report.report.reportplan.update")
	public ReportPlanVO update(@RequestBody @Valid ReportPlanEditDto reportPlanEditDto, @PathVariable Long id){
		ReportPlan reportPlan = new ReportPlan();
		BeanUtils.copyProperties(reportPlanEditDto, reportPlan);
		reportPlan.setId(id);
		reportPlanService.merge(reportPlan);

		ReportPlanVO vo = initViewProperty(reportPlan);
		return  vo;
	}

	/**
	 * 根据ID查询报告计划
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据ID查询报告计划", httpMethod = "GET")
	@GetMapping(path="/{id}")
  	@BusinessFuncMonitor(value = "report.report.reportplan.get")
	public ReportPlanVO get(@PathVariable Long id) {

		ReportPlan reportPlan = reportPlanService.find(id);
		if(reportPlan == null){
			throw new ResourceNotFoundException("找不到指定的报告计划，请检查ID");
		}
		ReportPlanVO vo = initViewProperty(reportPlan);
		return vo;
	}

	/**
	 * 查询报告计划列表
	 * @param pageSearchRequest
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据条件查询报告计划列表", httpMethod = "POST")
	@PostMapping(path="/list")
	@BusinessFuncMonitor(value = "report.report.reportplan.list")
	public PageContent<ReportPlanVO> list(@RequestBody PageSearchRequest<ReportPlanCondition> pageSearchRequest){

		PageRequest pageRequest = PageRequestConvert.convert(pageSearchRequest);
      
		Page<ReportPlan> page = reportPlanService.find(pageSearchRequest.getSearchCondition(), pageRequest);

		List<ReportPlanVO> voList = new ArrayList<>();
		for(ReportPlan reportPlan : page.getContent()){
			voList.add(initViewProperty(reportPlan));
		}

		PageContent<ReportPlanVO> pageContent = new PageContent<>(voList, page.getTotalElements());
		LOGGER.debug("page Size :{}, total:{}", pageContent.getContent().size() ,pageContent.getTotal());
		return pageContent;

	}

	/**
     * 导出报告计划列表
     * @param condition
     * @param response
     */
    @ApiOperation(value = "导出", notes = "根据条件导出报告计划列表", httpMethod = "POST")
    @RequestMapping(path="/export")
    public void export(ReportPlanCondition condition, HttpServletResponse response) throws UnsupportedEncodingException {

        PageSearchRequest<ReportPlanCondition> pageSearchRequest = new PageSearchRequest<>();
        pageSearchRequest.setPage(0);
        pageSearchRequest.setLimit(Integer.MAX_VALUE);
        pageSearchRequest.setSearchCondition(condition);

        PageContent<ReportPlanVO> content = this.list(pageSearchRequest);

        List<ReportPlanVO> voList = new ArrayList<>();
        if(CollectionUtils.isNotEmpty(content.getContent())){
            voList.addAll(content.getContent());
        }

        JSONArray jsonArray = new JSONArray();
        for(ReportPlanVO vo : voList){
            jsonArray.add(vo);
        }

        Map<String,String> headMap = new LinkedHashMap<>();

        headMap.put("annualPlanId" ,"年计划主键");
        headMap.put("progressBar" ,"进度条百分比");
        headMap.put("beginAt" ,"开始时间");
        headMap.put("expectedAt" ,"预计完成时间");
        headMap.put("planDetails" ,"计划内容");
        headMap.put("endId" ,"结束时间");
        headMap.put("planStatus" ,"计划状态");
        headMap.put("annexId" ,"附件主键");
        headMap.put("planSupplement" ,"计划补充说明");
        headMap.put("planType" ,"计划类型（周/年）");
        headMap.put("reportId" ,"报告表主键");

        String title = new String("报告计划");
        String fileName = new String(("报告计划_"+ DateFormatUtils.ISO_8601_EXTENDED_TIME_FORMAT.format(new Date())).getBytes("UTF-8"), "ISO-8859-1");
        ExcelUtil.downloadExcelFile(title, headMap, jsonArray, response, fileName);
    }

	private ReportPlanVO initViewProperty(ReportPlan reportPlan){

	    ReportPlanVO vo = new ReportPlanVO();
        BeanUtils.copyProperties(reportPlan, vo);


	    //初始化其他对象
        return vo;

	}

	/**
	 * 周报自动返回上周填写的下周计划  页面登录自动调用
	 * @return
	 */
	@GetMapping("/getReportPlan")
	public List<ReportPlan> getReportPlan(){
		Date startOfWeek = startWeek.getStartOfWeek();
		Date endOfWeek = startWeek.getEndOfWeek();
		List<ReportPlan> reportPlan = reportPlanService.getReportPlan(startOfWeek,endOfWeek);
		return reportPlan;
	}




	/**
	 * 查看关联的年度计划的周计划详情  1为周报计划 2为年报计划
	 * @param planId
	 * @param type
	 * @return
	 */
	@GetMapping("/findReportPlanDetails")
	public List<ReportPlanVO> findReportPlanDetails(Long planId,Long type){
		List<ReportPlanVO> list=new ArrayList<>();
		List<ReportPlan> reportPlanDetails = reportPlanService.findReportPlanDetails(planId, type);
		for (ReportPlan reportPlanDetail : reportPlanDetails) {

			Long reportId = reportPlanDetail.getReportId();
			Report report = reportService.find(reportId);
			Long subDeptId = report.getSubDeptId();
			ReportPlanVO reportPlanVO = initViewProperty(reportPlanDetail);
			reportPlanVO.setSubDeptId(subDeptId);
			list.add(reportPlanVO);
		}
		return list;
	}

    /**
	 * 工作报告详情
	 * @param reportId
	 * @param type
	 * @return
	 */
	@GetMapping("/findAnnualReportPlan")
	public Map<String,Object> findAnnualReportPlan(Long reportId,Long type){
		Map<String,Object> map=new HashMap<>();
		Report report = reportService.find(reportId);

		List<ReportPlan> byReportId = reportPlanService.findByReportId(reportId, type);
		map.put("报告",report);
		map.put("计划列表",byReportId);
		return map;
	}




}

