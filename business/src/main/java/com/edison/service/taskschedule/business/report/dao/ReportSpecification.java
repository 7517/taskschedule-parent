package com.edison.service.taskschedule.business.report.dao;


import com.edison.service.report.business.report.dto.ReportCondition;
import com.edison.service.taskschedule.business.report.domain.Report;
import org.apache.commons.collections.list.PredicatedList;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ReportSpecification implements Specification<Report>{

	private ReportCondition condition;

	public ReportSpecification(ReportCondition condition){
		this.condition = condition;
	}

	@Override
	public Predicate toPredicate(Root<Report> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
		List<Predicate> predicateList = new ArrayList<>();

		if(condition==null){
			return null;
		}

		tryAddEndAtPredicate(predicateList, root, cb);
		tryAddBeginAtPredicate(predicateList, root, cb);
		tryAddCcUidNamePredicate(predicateList, root, cb);
		tryAddAnnexIdPredicate(predicateList, root, cb);
		tryAddProgressBarPredicate(predicateList, root, cb);
		tryAddReportStatusPredicate(predicateList, root, cb);
		tryAddReportContentPredicate(predicateList, root, cb);
		tryAddReportTypePredicate(predicateList, root, cb);
		tryAddCcDeptIdPredicate(predicateList, root, cb);
		tryAddSubUidNamePredicate(predicateList, root, cb);
		tryAddCcUidPredicate(predicateList, root, cb);
		tryAddSubDeptIdPredicate(predicateList, root, cb);
		tryAddReviewer_uidPredicate(predicateList, root, cb);
		tryAddSubUidPredicate(predicateList, root, cb);
		tryAddfindMonthReport(predicateList, root, cb);


		Predicate[] pre = new Predicate[predicateList.size()];
		pre = predicateList.toArray(pre);
		return cb.and(pre);
    }


	private void tryAddEndAtPredicate(List<Predicate> predicateList, Root<Report> root, CriteriaBuilder cb){

		if (null != condition.getEndAt() ) {
			predicateList.add(cb.equal(root.get(Report.PROPERTY_END_AT).as(Date.class), condition.getEndAt()));
		}

		if (null != condition.getEndAtStart() ) {
			predicateList.add(cb.greaterThanOrEqualTo(root.get(Report.PROPERTY_END_AT).as(Date.class), condition.getEndAtStart()));
		}

		if (null != condition.getEndAtEnd() ) {
			predicateList.add(cb.lessThan(root.get(Report.PROPERTY_END_AT).as(Date.class), condition.getEndAtEnd()));
		}
	}
	private void tryAddBeginAtPredicate(List<Predicate> predicateList, Root<Report> root, CriteriaBuilder cb){

		if (null != condition.getBeginAt() ) {
			predicateList.add(cb.equal(root.get(Report.PROPERTY_BEGIN_AT).as(Date.class), condition.getBeginAt()));
		}

		if (null != condition.getBeginAtStart() ) {
			predicateList.add(cb.greaterThanOrEqualTo(root.get(Report.PROPERTY_BEGIN_AT).as(Date.class), condition.getBeginAtStart()));
		}

		if (null != condition.getBeginAtEnd() ) {
			predicateList.add(cb.lessThan(root.get(Report.PROPERTY_BEGIN_AT).as(Date.class), condition.getBeginAtEnd()));
		}
	}
	private void tryAddCcUidNamePredicate(List<Predicate> predicateList, Root<Report> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getCcUidName())){
			predicateList.add(cb.like(root.get(Report.PROPERTY_CC_UID_NAME).as(String.class), "%"+condition.getCcUidName()+"%"));
		}
	}
	private void tryAddAnnexIdPredicate(List<Predicate> predicateList, Root<Report> root, CriteriaBuilder cb){

		if (null != condition.getAnnexId() ) {
			predicateList.add(cb.equal(root.get(Report.PROPERTY_ANNEX_ID).as(Long.class), condition.getAnnexId()));
		}

		if (null != condition.getAnnexIdMax() ) {
			predicateList.add(cb.greaterThanOrEqualTo(root.get(Report.PROPERTY_ANNEX_ID).as(Long.class), condition.getAnnexIdMax()));
		}

		if (null != condition.getAnnexIdMin() ) {
			predicateList.add(cb.lessThan(root.get(Report.PROPERTY_ANNEX_ID).as(Long.class), condition.getAnnexIdMin()));
		}
	}
	private void tryAddProgressBarPredicate(List<Predicate> predicateList, Root<Report> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getProgressBar())){
			predicateList.add(cb.like(root.get(Report.PROPERTY_PROGRESS_BAR).as(String.class), "%"+condition.getProgressBar()+"%"));
		}
	}
	private void tryAddReportStatusPredicate(List<Predicate> predicateList, Root<Report> root, CriteriaBuilder cb){

		if (null != condition.getReportStatus() ) {
			predicateList.add(cb.equal(root.get(Report.PROPERTY_REPORT_STATUS).as(Long.class), condition.getReportStatus()));
		}

		if (null != condition.getReportStatusMax() ) {
			predicateList.add(cb.greaterThanOrEqualTo(root.get(Report.PROPERTY_REPORT_STATUS).as(Long.class), condition.getReportStatusMax()));
		}

		if (null != condition.getReportStatusMin() ) {
			predicateList.add(cb.lessThan(root.get(Report.PROPERTY_REPORT_STATUS).as(Long.class), condition.getReportStatusMin()));
		}
	}
	private void tryAddReportContentPredicate(List<Predicate> predicateList, Root<Report> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getReportContent())){
			predicateList.add(cb.like(root.get(Report.PROPERTY_REPORT_CONTENT).as(String.class), "%"+condition.getReportContent()+"%"));
		}
	}
	private void tryAddReportTypePredicate(List<Predicate> predicateList, Root<Report> root, CriteriaBuilder cb){

		if (null != condition.getReportType() ) {
			predicateList.add(cb.equal(root.get(Report.PROPERTY_REPORT_TYPE).as(Long.class), condition.getReportType()));
		}

		if (null != condition.getReportTypeMax() ) {
			predicateList.add(cb.greaterThanOrEqualTo(root.get(Report.PROPERTY_REPORT_TYPE).as(Long.class), condition.getReportTypeMax()));
		}

		if (null != condition.getReportTypeMin() ) {
			predicateList.add(cb.lessThan(root.get(Report.PROPERTY_REPORT_TYPE).as(Long.class), condition.getReportTypeMin()));
		}
	}
	private void tryAddCcDeptIdPredicate(List<Predicate> predicateList, Root<Report> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getCcDeptId())){
			predicateList.add(cb.like(root.get(Report.PROPERTY_CC_DEPT_ID).as(String.class), "%"+condition.getCcDeptId()+"%"));
		}
	}
	private void tryAddSubUidNamePredicate(List<Predicate> predicateList, Root<Report> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getSubUidName())){
			predicateList.add(cb.like(root.get(Report.PROPERTY_SUB_UID_NAME).as(String.class), "%"+condition.getSubUidName()+"%"));
		}
	}
	private void tryAddCcUidPredicate(List<Predicate> predicateList, Root<Report> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getCcUid())){
			predicateList.add(cb.like(root.get(Report.PROPERTY_CC_UID).as(String.class), "%"+condition.getCcUid()+"%"));
		}
	}
	private void tryAddSubDeptIdPredicate(List<Predicate> predicateList, Root<Report> root, CriteriaBuilder cb){

		if (null != condition.getSubDeptId() ) {
			predicateList.add(cb.equal(root.get(Report.PROPERTY_SUB_DEPT_ID).as(Long.class), condition.getSubDeptId()));
		}

		if (null != condition.getSubDeptIdMax() ) {
			predicateList.add(cb.greaterThanOrEqualTo(root.get(Report.PROPERTY_SUB_DEPT_ID).as(Long.class), condition.getSubDeptIdMax()));
		}

		if (null != condition.getSubDeptIdMin() ) {
			predicateList.add(cb.lessThan(root.get(Report.PROPERTY_SUB_DEPT_ID).as(Long.class), condition.getSubDeptIdMin()));
		}
	}
	private void tryAddReviewer_uidPredicate(List<Predicate> predicateList, Root<Report> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getReviewer_uid())){
			predicateList.add(cb.like(root.get(Report.PROPERTY_REVIEWER_UID).as(String.class), "%"+condition.getReviewer_uid()+"%"));
		}
	}
	private void tryAddSubUidPredicate(List<Predicate> predicateList, Root<Report> root, CriteriaBuilder cb){

		if (null != condition.getSubUid() ) {
			predicateList.add(cb.equal(root.get(Report.PROPERTY_SUB_UID).as(Long.class), condition.getSubUid()));
		}

		if (null != condition.getSubUidMax() ) {
			predicateList.add(cb.greaterThanOrEqualTo(root.get(Report.PROPERTY_SUB_UID).as(Long.class), condition.getSubUidMax()));
		}

		if (null != condition.getSubUidMin() ) {
			predicateList.add(cb.lessThan(root.get(Report.PROPERTY_SUB_UID).as(Long.class), condition.getSubUidMin()));
		}
	}

	private void tryAddfindMonthReport(List<Predicate> predicateList, Root<Report> root, CriteriaBuilder cb){
		if (null != condition.getBeginAt() ) {
			Predicate p1=cb.lessThanOrEqualTo(root.get(Report.PROPERTY_BEGIN_AT).as(Date.class), condition.getEndAt());
			Predicate p2=cb.greaterThanOrEqualTo(root.get(Report.PROPERTY_END_AT).as(Date.class), condition.getBeginAt());
			predicateList.add(cb.and(p1,p2));
		}
	}
}


