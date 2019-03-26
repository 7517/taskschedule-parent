package com.edison.service.taskschedule.business.report.service;


import com.edison.saas.common.jpa.GenericCrudService;

import com.edison.service.report.business.report.dto.ReportPlanCondition;
import com.edison.service.taskschedule.business.report.dao.ReportPlanDao;
import com.edison.service.taskschedule.business.report.dao.ReportPlanSpecification;
import com.edison.service.taskschedule.business.report.domain.Report;
import com.edison.service.taskschedule.business.report.domain.ReportPlan;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


@Service("reportPlanService")
@Slf4j
public class ReportPlanService  extends GenericCrudService<ReportPlan, Long, ReportPlanCondition, ReportPlanDao> {
  
	@Override
	public Specification<ReportPlan> getSpecification(ReportPlanCondition condition) {
		return new ReportPlanSpecification(condition);
	}

	public Sort getDefaultSort(){

		Sort sort = new Sort(Sort.Direction.DESC, ReportPlan.PROPERTY_ANNUAL_PLAN_ID);
		return sort;
	}
		//根据日期查询周计划
	public List<ReportPlan> getReportPlan(Date begin, Date end){
		Date beginat = getDate(begin);
		Date endat = getDate(end);
		List<ReportPlan> reportPlan = dao.getReportPlan(beginat, endat);
		return reportPlan;
	}

	//删除时间段里的计划
	public void deleteByDate(Date begin,Date end,Long uid){
		Date beginat = getDate(begin);
		Date endat = getDate(end);
		dao.deleteAllByDate(begin,endat,uid);
	}

	//根据类型跟年计划查询关联的周计划
	public List<ReportPlan> findReportPlanDetails(Long planId,Long type){
		List<ReportPlan> byReportIdAndPlanType = dao.findByAnnualPlanIdAndPlanTypeOrderByBeginAtDesc(planId, type);
		return byReportIdAndPlanType;
	}

	public List<ReportPlan> findByReportId(Long reportId,Long type){
		List<ReportPlan> byReportIdAndPlanType = dao.findByReportIdAndPlanType(reportId, type);
		return byReportIdAndPlanType;
	}


		//格式化时间的方法
	public Date getDate(Date date){
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		String format = sdf.format(date);
		Date parse = null;
		try {
			parse = sdf.parse(format);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		Timestamp timestamp1=new Timestamp(parse.getTime());
		return timestamp1;
	}

	//根据报告id 查询本周计划
	public List<ReportPlan> findByReportId(Long reportId){
		List<ReportPlan> list = dao.findByReportId(reportId);
		return list;

	}

	//统计本周总结
	public Report setReportContent(Report report,List<ReportPlan> reportPlans){
		int a=0;
		int b=0;
		int c=0;
		for (ReportPlan reportPlan : reportPlans) {
			String progressBar = reportPlan.getProgressBar();
			//统计计划完成情况，a为计划总数，b为未完成的计划数量，c为完成数量

			if ("100%".equals(progressBar)){
				a++;
				c++;

			}else {
				a++;
				b++;
			}
		}
		String count=a+"";
		String undonenum=b+"";
		String completednum=c+"";
		report.setReportContent("计划总数有"+count+"条,未完成数量有"+undonenum+"条,已完成计划有"+completednum+"条");
		return report;
	}


}