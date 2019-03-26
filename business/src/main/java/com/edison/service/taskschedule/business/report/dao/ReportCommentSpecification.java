package com.edison.service.taskschedule.business.report.dao;


import com.edison.service.report.business.report.dto.ReportCommentCondition;
import com.edison.service.taskschedule.business.report.domain.ReportComment;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class ReportCommentSpecification implements Specification<ReportComment>{

	private ReportCommentCondition condition;

	public ReportCommentSpecification(ReportCommentCondition condition){
		this.condition = condition;
	}

	@Override
	public Predicate toPredicate(Root<ReportComment> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
		List<Predicate> predicateList = new ArrayList<>();

		if(condition==null){
			return null;
		}

		tryAddReportIdPredicate(predicateList, root, cb);
		tryAddCommentContentPredicate(predicateList, root, cb);


		Predicate[] pre = new Predicate[predicateList.size()];
		pre = predicateList.toArray(pre);
		return cb.and(pre);
    }


	private void tryAddReportIdPredicate(List<Predicate> predicateList, Root<ReportComment> root, CriteriaBuilder cb){

		if (null != condition.getReportId() ) {
			predicateList.add(cb.equal(root.get(ReportComment.PROPERTY_REPORT_ID).as(Long.class), condition.getReportId()));
		}

		if (null != condition.getReportIdMax() ) {
			predicateList.add(cb.greaterThanOrEqualTo(root.get(ReportComment.PROPERTY_REPORT_ID).as(Long.class), condition.getReportIdMax()));
		}

		if (null != condition.getReportIdMin() ) {
			predicateList.add(cb.lessThan(root.get(ReportComment.PROPERTY_REPORT_ID).as(Long.class), condition.getReportIdMin()));
		}
	}
	private void tryAddCommentContentPredicate(List<Predicate> predicateList, Root<ReportComment> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getCommentContent())){
			predicateList.add(cb.like(root.get(ReportComment.PROPERTY_COMMENT_CONTENT).as(String.class), "%"+condition.getCommentContent()+"%"));
		}
	}
}


