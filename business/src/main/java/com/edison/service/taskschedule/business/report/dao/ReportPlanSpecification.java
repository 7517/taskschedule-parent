package com.edison.service.taskschedule.business.report.dao;

import com.edison.service.report.business.report.dto.ReportPlanCondition;
import com.edison.service.taskschedule.business.report.domain.ReportPlan;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ReportPlanSpecification implements Specification<ReportPlan>{

	private ReportPlanCondition condition;

	public ReportPlanSpecification(ReportPlanCondition condition){
		this.condition = condition;
	}

	@Override
	public Predicate toPredicate(Root<ReportPlan> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
		List<Predicate> predicateList = new ArrayList<>();

		if(condition==null){
			return null;
		}

		tryAddAnnualPlanIdPredicate(predicateList, root, cb);
		tryAddProgressBarPredicate(predicateList, root, cb);
		tryAddBeginAtPredicate(predicateList, root, cb);
		tryAddExpectedAtPredicate(predicateList, root, cb);
		tryAddPlanDetailsPredicate(predicateList, root, cb);
		tryAddEndIdPredicate(predicateList, root, cb);
		tryAddPlanStatusPredicate(predicateList, root, cb);
		tryAddAnnexIdPredicate(predicateList, root, cb);
		tryAddPlanSupplementPredicate(predicateList, root, cb);
		tryAddPlanTypePredicate(predicateList, root, cb);
		tryAddReportIdPredicate(predicateList, root, cb);


		Predicate[] pre = new Predicate[predicateList.size()];
		pre = predicateList.toArray(pre);
		return cb.and(pre);
    }


	private void tryAddAnnualPlanIdPredicate(List<Predicate> predicateList, Root<ReportPlan> root, CriteriaBuilder cb){

		if (null != condition.getAnnualPlanId() ) {
			predicateList.add(cb.equal(root.get(ReportPlan.PROPERTY_ANNUAL_PLAN_ID).as(Long.class), condition.getAnnualPlanId()));
		}

		if (null != condition.getAnnualPlanIdMax() ) {
			predicateList.add(cb.greaterThanOrEqualTo(root.get(ReportPlan.PROPERTY_ANNUAL_PLAN_ID).as(Long.class), condition.getAnnualPlanIdMax()));
		}

		if (null != condition.getAnnualPlanIdMin() ) {
			predicateList.add(cb.lessThan(root.get(ReportPlan.PROPERTY_ANNUAL_PLAN_ID).as(Long.class), condition.getAnnualPlanIdMin()));
		}
	}
	private void tryAddProgressBarPredicate(List<Predicate> predicateList, Root<ReportPlan> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getProgressBar())){
			predicateList.add(cb.like(root.get(ReportPlan.PROPERTY_PROGRESS_BAR).as(String.class), "%"+condition.getProgressBar()+"%"));
		}
	}
	private void tryAddBeginAtPredicate(List<Predicate> predicateList, Root<ReportPlan> root, CriteriaBuilder cb){

		if (null != condition.getBeginAt() ) {
			predicateList.add(cb.equal(root.get(ReportPlan.PROPERTY_BEGIN_AT).as(Date.class), condition.getBeginAt()));
		}

		if (null != condition.getBeginAtStart() ) {
			predicateList.add(cb.greaterThanOrEqualTo(root.get(ReportPlan.PROPERTY_BEGIN_AT).as(Date.class), condition.getBeginAtStart()));
		}

		if (null != condition.getBeginAtEnd() ) {
			predicateList.add(cb.lessThan(root.get(ReportPlan.PROPERTY_BEGIN_AT).as(Date.class), condition.getBeginAtEnd()));
		}
	}
	private void tryAddExpectedAtPredicate(List<Predicate> predicateList, Root<ReportPlan> root, CriteriaBuilder cb){

		if (null != condition.getExpectedAt() ) {
			predicateList.add(cb.equal(root.get(ReportPlan.PROPERTY_EXPECTED_AT).as(Date.class), condition.getExpectedAt()));
		}

		if (null != condition.getExpectedAtStart() ) {
			predicateList.add(cb.greaterThanOrEqualTo(root.get(ReportPlan.PROPERTY_EXPECTED_AT).as(Date.class), condition.getExpectedAtStart()));
		}

		if (null != condition.getExpectedAtEnd() ) {
			predicateList.add(cb.lessThan(root.get(ReportPlan.PROPERTY_EXPECTED_AT).as(Date.class), condition.getExpectedAtEnd()));
		}
	}
	private void tryAddPlanDetailsPredicate(List<Predicate> predicateList, Root<ReportPlan> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getPlanDetails())){
			predicateList.add(cb.like(root.get(ReportPlan.PROPERTY_PLAN_DETAILS).as(String.class), "%"+condition.getPlanDetails()+"%"));
		}
	}
	private void tryAddEndIdPredicate(List<Predicate> predicateList, Root<ReportPlan> root, CriteriaBuilder cb){

		if (null != condition.getEndId() ) {
			predicateList.add(cb.equal(root.get(ReportPlan.PROPERTY_END_ID).as(Date.class), condition.getEndId()));
		}

		if (null != condition.getEndIdStart() ) {
			predicateList.add(cb.greaterThanOrEqualTo(root.get(ReportPlan.PROPERTY_END_ID).as(Date.class), condition.getEndIdStart()));
		}

		if (null != condition.getEndIdEnd() ) {
			predicateList.add(cb.lessThan(root.get(ReportPlan.PROPERTY_END_ID).as(Date.class), condition.getEndIdEnd()));
		}
	}
	private void tryAddPlanStatusPredicate(List<Predicate> predicateList, Root<ReportPlan> root, CriteriaBuilder cb){

		if (null != condition.getPlanStatus() ) {
			predicateList.add(cb.equal(root.get(ReportPlan.PROPERTY_PLAN_STATUS).as(Long.class), condition.getPlanStatus()));
		}

		if (null != condition.getPlanStatusMax() ) {
			predicateList.add(cb.greaterThanOrEqualTo(root.get(ReportPlan.PROPERTY_PLAN_STATUS).as(Long.class), condition.getPlanStatusMax()));
		}

		if (null != condition.getPlanStatusMin() ) {
			predicateList.add(cb.lessThan(root.get(ReportPlan.PROPERTY_PLAN_STATUS).as(Long.class), condition.getPlanStatusMin()));
		}
	}
	private void tryAddAnnexIdPredicate(List<Predicate> predicateList, Root<ReportPlan> root, CriteriaBuilder cb){

		if (null != condition.getAnnexId() ) {
			predicateList.add(cb.equal(root.get(ReportPlan.PROPERTY_ANNEX_ID).as(Long.class), condition.getAnnexId()));
		}

		if (null != condition.getAnnexIdMax() ) {
			predicateList.add(cb.greaterThanOrEqualTo(root.get(ReportPlan.PROPERTY_ANNEX_ID).as(Long.class), condition.getAnnexIdMax()));
		}

		if (null != condition.getAnnexIdMin() ) {
			predicateList.add(cb.lessThan(root.get(ReportPlan.PROPERTY_ANNEX_ID).as(Long.class), condition.getAnnexIdMin()));
		}
	}
	private void tryAddPlanSupplementPredicate(List<Predicate> predicateList, Root<ReportPlan> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getPlanSupplement())){
			predicateList.add(cb.like(root.get(ReportPlan.PROPERTY_PLAN_SUPPLEMENT).as(String.class), "%"+condition.getPlanSupplement()+"%"));
		}
	}
	private void tryAddPlanTypePredicate(List<Predicate> predicateList, Root<ReportPlan> root, CriteriaBuilder cb){

		if (null != condition.getPlanType() ) {
			predicateList.add(cb.equal(root.get(ReportPlan.PROPERTY_PLAN_TYPE).as(Long.class), condition.getPlanType()));
		}

		if (null != condition.getPlanTypeMax() ) {
			predicateList.add(cb.greaterThanOrEqualTo(root.get(ReportPlan.PROPERTY_PLAN_TYPE).as(Long.class), condition.getPlanTypeMax()));
		}

		if (null != condition.getPlanTypeMin() ) {
			predicateList.add(cb.lessThan(root.get(ReportPlan.PROPERTY_PLAN_TYPE).as(Long.class), condition.getPlanTypeMin()));
		}
	}
	private void tryAddReportIdPredicate(List<Predicate> predicateList, Root<ReportPlan> root, CriteriaBuilder cb){

		if (null != condition.getReportId() ) {
			predicateList.add(cb.equal(root.get(ReportPlan.PROPERTY_REPORT_ID).as(Long.class), condition.getReportId()));
		}

		if (null != condition.getReportIdMax() ) {
			predicateList.add(cb.greaterThanOrEqualTo(root.get(ReportPlan.PROPERTY_REPORT_ID).as(Long.class), condition.getReportIdMax()));
		}

		if (null != condition.getReportIdMin() ) {
			predicateList.add(cb.lessThan(root.get(ReportPlan.PROPERTY_REPORT_ID).as(Long.class), condition.getReportIdMin()));
		}

	}
}


