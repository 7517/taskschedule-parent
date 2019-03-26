package com.edison.service.taskschedule.business.report.service;


import com.edison.saas.common.jpa.GenericCrudService;

import com.edison.service.report.business.report.dto.ReportCondition;

import com.edison.service.taskschedule.business.report.dao.ReportDao;
import com.edison.service.taskschedule.business.report.dao.ReportSpecification;
import com.edison.service.taskschedule.business.report.domain.Report;
import com.edison.service.taskschedule.business.report.week.StartWeek;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Service("reportService")
@Slf4j
public class ReportService  extends GenericCrudService<Report, Long, ReportCondition, ReportDao> {

	@Autowired
	private StartWeek startWeek;

	@Override
	public Specification<Report> getSpecification(ReportCondition condition) {
		return new ReportSpecification(condition);
	}

	public Sort getDefaultSort(){

		Sort sort = new Sort(Sort.Direction.DESC, Report.PROPERTY_END_AT);
		return sort;
	}
	//获取部门周报
	public List<Report> getWeekReport(Date start,Date end,Long uid,Long type,Long deptId){
		List<Report> allByDate = dao.findAllByDate(start, end, uid, type,deptId);
		return allByDate;
	}
	//获取全部周报
	public List<Report> getAllWeekReport(Date start,Date end,Long type){
		List<Report> allWeekReport = dao.findAllWeekReport(start,end,type);
		return allWeekReport;
	}

	//获取当月周报
	public List<Report> findMonthReport(Long uid,Long type,Long deptId){
		List<Report> list = dao.findBySubUidAndReportTypeAndSubDeptId(uid,type,deptId);
		Date date=new Date();
		int month = startWeek.getMonth(date);
		int year = startWeek.getYear(date);
		List<Report> reports=new ArrayList<>();
		for (Report report : list) {
			Date beginAt = report.getBeginAt();
			int month1 = startWeek.getMonth(beginAt);
			int year1 = startWeek.getYear(beginAt);
			if (month==month1&&year==year1){
				reports.add(report);
			}
		}
		return reports;
	}

	//查询全部当月月报
	public List<Report> findAllMonthReport(Long type){
		List<Report> list = dao.findByReportType(type);
		Date date=new Date();
		int month = startWeek.getMonth(date);
		int year = startWeek.getYear(date);
		List<Report> reports=new ArrayList<>();
		for (Report report : list) {
			Date beginAt = report.getBeginAt();
			int month1 = startWeek.getMonth(beginAt);
			int year1 = startWeek.getYear(beginAt);
			if (month==month1&&year==year1){
				reports.add(report);
			}
		}
		return reports;
	}

	//
	public List<Report> findAnnualReport(Long uid,Long type,Long deptId){
		Date date=new Date();
		int year = startWeek.getYear(date);
		List<Report> list=new ArrayList<>();
		List<Report> reports= dao.findBySubUidAndReportTypeAndSubDeptId(uid, type,deptId);
		for (Report report : reports) {
			Date beginAt = report.getBeginAt();
			int year1 = startWeek.getYear(beginAt);
			if (year==year1){
				list.add(report);
			}
		}
		return list;
	}

	public List<Report>findDateReport(Date begin,Date end,Long deptId){
		Date beginat = startWeek.formatDate(begin);
		Date endat = startWeek.formatDate(end);
		List<Report> dateReport = dao.findDateReport(beginat, endat, deptId);
		return dateReport;
	}

	//查询所有报告
	public List<Report> findAllReport(Long type){
		List<Report> byReportType = dao.findByReportType(type);
		return byReportType;
	}


}