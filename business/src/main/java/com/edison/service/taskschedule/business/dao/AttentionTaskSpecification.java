package com.edison.service.taskschedule.business.dao;

import com.edison.service.taskschedule.business.domain.AttentionTask;
import com.edison.service.taskschedule.business.dto.AttentionTaskCondition;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class AttentionTaskSpecification implements Specification<AttentionTask>{

	private AttentionTaskCondition condition;

	public AttentionTaskSpecification(AttentionTaskCondition condition){
		this.condition = condition;
	}

	@Override
	public Predicate toPredicate(Root<AttentionTask> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
		List<Predicate> predicateList = new ArrayList<>();

		if(condition==null){
			return null;
		}

		tryAddUidPredicate(predicateList, root, cb);
		tryAddTaskIdPredicate(predicateList, root, cb);


		Predicate[] pre = new Predicate[predicateList.size()];
		pre = predicateList.toArray(pre);
		return cb.and(pre);
    }


	private void tryAddUidPredicate(List<Predicate> predicateList, Root<AttentionTask> root, CriteriaBuilder cb){

		if (null != condition.getUid() ) {
			predicateList.add(cb.equal(root.get(AttentionTask.PROPERTY_UID).as(Long.class), condition.getUid()));
		}

		if (null != condition.getUidMax() ) {
			predicateList.add(cb.greaterThanOrEqualTo(root.get(AttentionTask.PROPERTY_UID).as(Long.class), condition.getUidMax()));
		}

		if (null != condition.getUidMin() ) {
			predicateList.add(cb.lessThan(root.get(AttentionTask.PROPERTY_UID).as(Long.class), condition.getUidMin()));
		}
	}
	private void tryAddTaskIdPredicate(List<Predicate> predicateList, Root<AttentionTask> root, CriteriaBuilder cb){

		if (null != condition.getTaskId() ) {
			predicateList.add(cb.equal(root.get(AttentionTask.PROPERTY_TASK_ID).as(Long.class), condition.getTaskId()));
		}

		if (null != condition.getTaskIdMax() ) {
			predicateList.add(cb.greaterThanOrEqualTo(root.get(AttentionTask.PROPERTY_TASK_ID).as(Long.class), condition.getTaskIdMax()));
		}

		if (null != condition.getTaskIdMin() ) {
			predicateList.add(cb.lessThan(root.get(AttentionTask.PROPERTY_TASK_ID).as(Long.class), condition.getTaskIdMin()));
		}
	}
}


