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
import com.edison.service.report.business.report.dto.ReportAddDto;
import com.edison.service.report.business.report.dto.ReportCondition;
import com.edison.service.report.business.report.dto.ReportEditDto;
import com.edison.service.report.business.report.vo.ReportVO;
import com.edison.service.taskschedule.business.report.domain.Report;
import com.edison.service.taskschedule.business.report.domain.ReportPlan;
import com.edison.service.taskschedule.business.report.service.ReportPlanService;
import com.edison.service.taskschedule.business.report.service.ReportService;
import com.edison.service.taskschedule.business.report.week.StartWeek;
import com.edison.service.taskschedule.report.report.valid.ReportValidator;
import com.edison.services.orgcenter.client.organization.OrgClient;
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
 * 管理报告
 * @author icode
 */
@Api(description = "报告", tags = "Report")
@RestController
@RequestMapping(value = "/report/report/report")
public class ReportController {

	private static final Logger LOGGER = LoggerFactory.getLogger(ReportController.class);


	@Autowired
	private ReportService reportService;
	@Autowired
	private ReportPlanService reportPlanService;
	@Autowired
	private StartWeek startWeek;
	/*@Autowired
    private OrgClient orgClient;*/



	@Autowired
	private ReportValidator reportValidator;

	@InitBinder
	public void initBinder(WebDataBinder webDataBinder){
		webDataBinder.addValidators(reportValidator);
		webDataBinder.registerCustomEditor(Date.class, new DateConverter());
	}


  
	/**
	 * 新增报告
	 * @param reportAddDto
	 * @return
	 */
	@ApiOperation(value = "新增", notes = "新增报告", httpMethod = "POST")
	@PostMapping
	@ResponseStatus( HttpStatus.CREATED )
  	@BusinessFuncMonitor(value = "report.report.report.add")
	public ReportVO add(@RequestBody @Valid ReportAddDto reportAddDto){
		Report report = new Report();
		BeanUtils.copyProperties(reportAddDto, report);




		reportService.add(report);

		return  initViewProperty(report);


	}

	/**
	 * 删除报告,id以逗号分隔
	 * @param idArray
	 */
	@ApiOperation(value = "删除", notes = "删除报告", httpMethod = "DELETE")
	@DeleteMapping(path="/{idArray}")
  	@BusinessFuncMonitor(value = "report.report.report.delete")
	public void delete(@PathVariable String idArray){

	    LOGGER.debug("delete report :{}", idArray);


        String[] ids = idArray.split(",");
		for (String id : ids ){
			reportService.delete(Long.parseLong(id));
		}

	}

	/**
	 * 更新报告
	 * @param reportEditDto
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "修改", notes = "修改产报告(修改全部字段,未传入置空)", httpMethod = "PUT")
	@PutMapping(path="/{id}")
  	@BusinessFuncMonitor(value = "report.report.report.update")
	public ReportVO update(@RequestBody @Valid ReportEditDto reportEditDto, @PathVariable Long id){
		Report report = new Report();
		BeanUtils.copyProperties(reportEditDto, report);
		report.setId(id);
		reportService.merge(report);

		ReportVO vo = initViewProperty(report);
		return  vo;
	}

	/**
	 * 根据ID查询报告
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据ID查询报告", httpMethod = "GET")
	@GetMapping(path="/{id}")
  	@BusinessFuncMonitor(value = "report.report.report.get")
	public ReportVO get(@PathVariable Long id) {

		Report report = reportService.find(id);
		if(report == null){
			throw new ResourceNotFoundException("找不到指定的报告，请检查ID");
		}
		ReportVO vo = initViewProperty(report);
		return vo;
	}

	/**
	 * 查询报告列表
	 * @param pageSearchRequest
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据条件查询报告列表", httpMethod = "POST")
	@PostMapping(path="/list")
	@BusinessFuncMonitor(value = "report.report.report.list")
	public PageContent<ReportVO> list(@RequestBody PageSearchRequest<ReportCondition> pageSearchRequest){

		PageRequest pageRequest = PageRequestConvert.convert(pageSearchRequest);
      
		Page<Report> page = reportService.find(pageSearchRequest.getSearchCondition(), pageRequest);

		List<ReportVO> voList = new ArrayList<>();
		for(Report report : page.getContent()){
			voList.add(initViewProperty(report));
		}

		PageContent<ReportVO> pageContent = new PageContent<>(voList, page.getTotalElements());
		LOGGER.debug("page Size :{}, total:{}", pageContent.getContent().size() ,pageContent.getTotal());
		return pageContent;

	}

	/**
     * 导出报告列表
     * @param condition
     * @param response
     */
    @ApiOperation(value = "导出", notes = "根据条件导出报告列表", httpMethod = "POST")
    @RequestMapping(path="/export")
    public void export(ReportCondition condition, HttpServletResponse response) throws UnsupportedEncodingException {

        PageSearchRequest<ReportCondition> pageSearchRequest = new PageSearchRequest<>();
        pageSearchRequest.setPage(0);
        pageSearchRequest.setLimit(Integer.MAX_VALUE);
        pageSearchRequest.setSearchCondition(condition);

        PageContent<ReportVO> content = this.list(pageSearchRequest);

        List<ReportVO> voList = new ArrayList<>();
        if(CollectionUtils.isNotEmpty(content.getContent())){
            voList.addAll(content.getContent());
        }

        JSONArray jsonArray = new JSONArray();
        for(ReportVO vo : voList){
            jsonArray.add(vo);
        }

        Map<String,String> headMap = new LinkedHashMap<>();

        headMap.put("endAt" ,"结束时间");
        headMap.put("beginAt" ,"开始时间");
        headMap.put("ccUidName" ,"抄送人名");
        headMap.put("annexId" ,"附件id");
        headMap.put("progressBar" ,"进度条");
        headMap.put("reportStatus" ,"报告状态");
        headMap.put("reportContent" ,"报告内容总结");
        headMap.put("reportType" ,"报告类型");
        headMap.put("ccDeptId" ,"抄送部门id");
        headMap.put("subUidName" ,"提交人名");
        headMap.put("ccUid" ,"抄送部门主任id");
        headMap.put("subDeptId" ,"提交部门id");
        headMap.put("reviewer_uid" ,"评阅人id");
        headMap.put("subUid" ,"提交部门主任id");

        String title = new String("报告");
        String fileName = new String(("报告_"+ DateFormatUtils.ISO_8601_EXTENDED_TIME_FORMAT.format(new Date())).getBytes("UTF-8"), "ISO-8859-1");
        ExcelUtil.downloadExcelFile(title, headMap, jsonArray, response, fileName);
    }

	private ReportVO initViewProperty(Report report){

	    ReportVO vo = new ReportVO();
        BeanUtils.copyProperties(report, vo);


	    //初始化其他对象
        return vo;

	}


    /**
     * 新增周报
     * @param reportAddDto
     * @param listWeek
     * @param listNextWeek
     * @return
     */
    @PostMapping("/addWeekReport")
	public Map addWeekReport(@RequestBody@Valid ReportAddDto reportAddDto, @RequestBody  List<ReportPlan> listWeek,@RequestBody List<ReportPlan> listNextWeek){
		Report report = new Report();
		BeanUtils.copyProperties(reportAddDto, report);
		Map<Boolean,String> map=new HashMap<>();
		try{
			reportService.add(report);
			Long id = report.getId();
			Date begin = report.getBeginAt();
			Date endat = report.getEndAt();

			//添加本周计划前，先删除上周填写的本周计划
			for (ReportPlan reportPlan : listWeek) {
				Date beginAt = reportPlan.getBeginAt();
				Date endAt = reportPlan.getEndId();
				Long createUid = reportPlan.getCreateUid();
				reportPlanService.deleteByDate(beginAt,endAt,createUid);
				break;
			}
			//添加本周的计划
			for (ReportPlan reportPlan : listWeek) {
				reportPlan.setReportId(id);
				reportPlan.setBeginAt(begin);
				report.setEndAt(endat);
				reportPlanService.add(reportPlan);
			}
			//添加下周计划
			for (ReportPlan reportPlan : listNextWeek) {
				Date startOfNextWeek = startWeek.getStartOfNextWeek();
				Date endOfNextWeek = startWeek.getEndOfNextWeek();
				reportPlan.setBeginAt(startOfNextWeek);
				reportPlan.setEndId(endOfNextWeek);
				reportPlanService.add(reportPlan);
			}
			map.put(true,"新增成功");
			return map;
		}catch (Exception e){
			map.put(false,"添加失败");
			return map;
		}
	}

    /**
     * 新增年报
     * @param reportAddDto
     * @param list
     * @return
     */
    @PostMapping("/addAnnualReport")
	public Map addAnnualReport(@RequestBody ReportAddDto reportAddDto, List<ReportPlan> list){
		Report report = new Report();
		BeanUtils.copyProperties(reportAddDto, report);
		Map<Boolean,String> map=new HashMap<>();
		try{
            reportService.add(report);
            Long id = report.getId();
            Date begin = report.getBeginAt();
            Date endat = report.getEndAt();
            for (ReportPlan reportPlan : list) {
                reportPlan.setReportId(id);
                reportPlan.setBeginAt(begin);
                reportPlan.setEndId(endat);
                reportPlanService.add(reportPlan);
            }
            map.put(true,"添加成功");
            return map;
        }catch (Exception e){
		    map.put(false,"添加失败");
		    return map;
        }
	}

    /**
     * 查询本周周报
     * @param uid
     * @return
     */
    @GetMapping("/findThisWeekReport")
  public PageContent<ReportVO> findThisWeekReport(@RequestBody PageSearchRequest<ReportCondition> pageSearchRequest){
        PageRequest pageRequest = PageRequestConvert.convert(pageSearchRequest);
        ReportCondition searchCondition = pageSearchRequest.getSearchCondition();
        Date startOfWeek = startWeek.getStartOfWeek();
        Date endOfWeek = startWeek.getEndOfWeek();
        searchCondition.setBeginAt(startOfWeek);
        searchCondition.setEndAt(endOfWeek);
        Page<Report> page = reportService.find(pageSearchRequest.getSearchCondition(), pageRequest);
        List<ReportVO> voList = new ArrayList<>();

        for(Report report : page.getContent()){
            voList.add(initViewProperty(report));
        }

        PageContent<ReportVO> pageContent = new PageContent<>(voList, page.getTotalElements());
        LOGGER.debug("page Size :{}, total:{}", pageContent.getContent().size() ,pageContent.getTotal());
        return pageContent;

     /*  List<ReportVO> list = new ArrayList<>();
        if (deptId!=33333) {
            if (type != 333) {
                Date startOfWeek = startWeek.getStartOfWeek();
                Date endOfWeek = startWeek.getEndOfWeek();
                List<Report> weekReport = reportService.getWeekReport(startOfWeek, endOfWeek, uid, type,deptId);


                for (Report report : weekReport) {
                    ReportVO reportVO = initViewProperty(report);
                    list.add(reportVO);

                }
                return list;
            } else {
                List<ReportVO> annualReport = findAnnualReport(uid, type, deptId);
                return annualReport;
            }
        }else {
            Date startOfWeek = startWeek.getStartOfWeek();
            Date endOfWeek = startWeek.getEndOfWeek();
            List<Report> allWeekReport = reportService.getAllWeekReport(startOfWeek, endOfWeek, type);
            for (Report report : allWeekReport) {
                ReportVO reportVO = initViewProperty(report);
                list.add(reportVO);
            }
        return list;
        }*/

  }

    /**
     * 查询上周周报
     * @param uid
     * @return
     */
    @GetMapping("/findLastWeekReport")
  public PageContent<ReportVO> findLastWeekReport(@RequestBody PageSearchRequest<ReportCondition> pageSearchRequest){
        PageRequest pageRequest = PageRequestConvert.convert(pageSearchRequest);
        ReportCondition searchCondition = pageSearchRequest.getSearchCondition();
        Date startOfLastWeek = startWeek.getStartOfLastWeek();
        Date endOfLastWeek = startWeek.getEndOfLastWeek();
        searchCondition.setBeginAt(startOfLastWeek);
        searchCondition.setEndAt(endOfLastWeek);
        Page<Report> page = reportService.find(pageSearchRequest.getSearchCondition(), pageRequest);
        List<ReportVO> voList = new ArrayList<>();

        for(Report report : page.getContent()){
            voList.add(initViewProperty(report));
        }

        PageContent<ReportVO> pageContent = new PageContent<>(voList, page.getTotalElements());
        LOGGER.debug("page Size :{}, total:{}", pageContent.getContent().size() ,pageContent.getTotal());
        return pageContent;

       /* List<ReportVO> list = new ArrayList<>();
        //判断部门是否为局长所属部门
        if (deptId!=33333) {
            //判断报告的类型
            if (type != 333) {
                Date startOfLastWeek = startWeek.getStartOfLastWeek();
                Date endOfLastWeek = startWeek.getEndOfLastWeek();

                List<Report> weekReport = reportService.getWeekReport(startOfLastWeek, endOfLastWeek, uid, type,deptId);
                for (Report report : weekReport) {
                    ReportVO reportVO = initViewProperty(report);
                    list.add(reportVO);

                }
                return list;
            } else {
                List<ReportVO> annualReport = findAnnualReport(uid, type, deptId);
                return annualReport;
            }
        }else {
            Date startOfLastWeek = startWeek.getStartOfLastWeek();
            Date endOfLastWeek = startWeek.getEndOfLastWeek();
            List<Report> allWeekReport = reportService.getAllWeekReport(startOfLastWeek, endOfLastWeek, type);
            for (Report report : allWeekReport) {
                ReportVO reportVO = initViewProperty(report);
                list.add(reportVO);
            }
            return list;
        }*/

  }


    /**
     * 查询当月周报
     * @param uid
     * @return
     */
    @GetMapping("/findMonthReport")
    public PageContent<ReportVO> findMonthReport(@RequestBody PageSearchRequest<ReportCondition> pageSearchRequest){
        PageRequest pageRequest = PageRequestConvert.convert(pageSearchRequest);
        ReportCondition searchCondition = pageSearchRequest.getSearchCondition();
        Date startOfMonth = startWeek.getStartOfMonth();
        Date endOfMonth = startWeek.getEndOfMonth();
        searchCondition.setBeginAt(startOfMonth);
        searchCondition.setEndAt(endOfMonth);

        Page<Report> page = reportService.find(pageSearchRequest.getSearchCondition(), pageRequest);
        List<ReportVO> voList = new ArrayList<>();

        for(Report report : page.getContent()){
            voList.add(initViewProperty(report));
        }

        PageContent<ReportVO> pageContent = new PageContent<>(voList, page.getTotalElements());
        LOGGER.debug("page Size :{}, total:{}", pageContent.getContent().size() ,pageContent.getTotal());
        return pageContent;

       /* List<ReportVO> list = new ArrayList<>();
        if (deptId!=33333) {
            if (type != 333) {

                List<Report> monthReport = reportService.findMonthReport(uid, type, deptId);
                for (Report report : monthReport) {
                    ReportVO reportVO = initViewProperty(report);
                    list.add(reportVO);
                }
                return list;
            } else {
                List<ReportVO> annualReport = findAnnualReport(uid, type, deptId);
                return annualReport;
            }
        }else{
            List<Report> allMonthReport = reportService.findAllMonthReport(type);
            for (Report report : allMonthReport) {
                ReportVO reportVO = initViewProperty(report);
                list.add(reportVO);
            }
            return list;
        }*/
    }


    /**
     *查询部门年度计划
     * @param uid
     * @param type
     * @return
     */
    @GetMapping("/findAnnualRepor")
    public PageContent<ReportVO> findAnnualReport(@RequestBody PageSearchRequest<ReportCondition> pageSearchRequest /*Long uid,Long type,Long deptId*/){
        PageRequest pageRequest = PageRequestConvert.convert(pageSearchRequest);
        //ReportCondition searchCondition = pageSearchRequest.getSearchCondition();
        Page<Report> page = reportService.find(pageSearchRequest.getSearchCondition(), pageRequest);
        List<ReportVO> voList = new ArrayList<>();

        for(Report report : page.getContent()){
            voList.add(initViewProperty(report));
        }

        PageContent<ReportVO> pageContent = new PageContent<>(voList, page.getTotalElements());
        LOGGER.debug("page Size :{}, total:{}", pageContent.getContent().size() ,pageContent.getTotal());
        return pageContent;


        /*if (deptId!=33333){
            List<ReportVO> list=new ArrayList<>();
            List<Report> annualReport = reportService.findAnnualReport(uid, type,deptId);
            for (Report report : annualReport) {
                ReportVO reportVO = initViewProperty(report);
                list.add(reportVO);
            }
            return list;
        }else {
            List<ReportVO> allAnnualReport = findAllAnnualReport(type);
            return allAnnualReport;
        }*/

    }


    /*//查询所有年度计划
    public List<ReportVO> findAllAnnualReport(Long type){
        List<ReportVO> list=new ArrayList<>();
        List<Report> allReport = reportService.findAllReport(type);
        for (Report report : allReport) {
            ReportVO reportVO = initViewProperty(report);
            list.add(reportVO);
        }
        return list;
    }*/

	/**
	 * 查询周报  设置VO里面的属性
	 * @param begin
	 * @param end
	 * @param deptId
	 * @return
	 */
    public PageContent<ReportVO> findDateReport (@RequestBody PageSearchRequest<ReportCondition> pageSearchRequest  /*Date begin,Date end,Long deptId,Long type*/) {

        PageRequest pageRequest = PageRequestConvert.convert(pageSearchRequest);
        ReportCondition searchCondition = pageSearchRequest.getSearchCondition();
        Date beginAt = reportPlanService.getDate(searchCondition.getBeginAt());
        Date endAt = reportPlanService.getDate(searchCondition.getEndAt());
        searchCondition.setBeginAt(beginAt);
        searchCondition.setEndAt(endAt);
        Page<Report> page = reportService.find(pageSearchRequest.getSearchCondition(), pageRequest);
        List<ReportVO> voList = new ArrayList<>();

        for(Report report : page.getContent()){
            Map<String, Object> map = new HashMap<>();
            Long id = report.getId();
            List<ReportPlan> byReportId = reportPlanService.findByReportId(id);
            ReportVO reportVO = initViewProperty(report);
            reportPlanService.setReportContent(report, byReportId);
            reportVO.setReportPlanVOList(byReportId);
            Date startOfNextWeek = startWeek.getStartOfNextWeek();
            Date endOfNextWeek = startWeek.getEndOfNextWeek();
            List<ReportPlan> reportPlan = reportPlanService.getReportPlan(startOfNextWeek, endOfNextWeek);
            reportVO.setNextReportPlanVOList(reportPlan);

            voList.add(reportVO);
            //voList.add(initViewProperty(report));
        }

        PageContent<ReportVO> pageContent = new PageContent<>(voList, page.getTotalElements());
        LOGGER.debug("page Size :{}, total:{}", pageContent.getContent().size() ,pageContent.getTotal());
        return pageContent;

       /* List<Map> list = new ArrayList<>();
        List<Report> dateReport = reportService.findDateReport(begin, end, deptId);
        //判断部门id是否为局长所属部门，如果是 查询全部  33333为局长部门id
		if (deptId !=33333) {

			for (Report report : dateReport) {
				Map<String, Object> map = new HashMap<>();
				Long id = report.getId();
				List<ReportPlan> byReportId = reportPlanService.findByReportId(id);
				reportPlanService.setReportContent(report, byReportId);

				Date startOfNextWeek = startWeek.getStartOfNextWeek();
				Date endOfNextWeek = startWeek.getEndOfNextWeek();
				List<ReportPlan> reportPlan = reportPlanService.getReportPlan(startOfNextWeek, endOfNextWeek);

				map.put("本周周报", report);
				map.put("本周计划", byReportId);
				map.put("下周计划", reportPlan);
				list.add(map);
			}
			return list;
		}else{
            List<Report> allWeekReport = reportService.findAllReport(type);
            for (Report report : allWeekReport) {
                Map<String, Object> map = new HashMap<>();
                Long id = report.getId();
                List<ReportPlan> byReportId = reportPlanService.findByReportId(id);
                reportPlanService.setReportContent(report, byReportId);

                Date startOfNextWeek = startWeek.getStartOfNextWeek();
                Date endOfNextWeek = startWeek.getEndOfNextWeek();
                List<ReportPlan> reportPlan = reportPlanService.getReportPlan(startOfNextWeek, endOfNextWeek);

                map.put("本周周报", report);
                map.put("本周计划", byReportId);
                map.put("下周计划", reportPlan);
                list.add(map);
            }
            return list;
        }*/
	}

    //todo
	public List<Report> findCountReport(Date begin,Date end,Long type,String name,Long tid){
        List<Long>subList =new ArrayList<>();
        Date beginat = reportPlanService.getDate(begin);
        Date endat = reportPlanService.getDate(end);
        List<Report> allWeekReport = reportService.getAllWeekReport(beginat, endat, type);
        for (Report report : allWeekReport) {
            Long subDeptId = report.getSubDeptId();
            subList.add(subDeptId);

        }



        return null;

    }

}

