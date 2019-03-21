package com.edison.service.taskschedule.business.task.dao;

import com.edison.service.taskschedule.business.task.dto.TaskprogressCondition;
import com.edison.service.taskschedule.business.task.domain.Taskprogress;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;


import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.*;

public class TaskprogressSpecification implements Specification<Taskprogress>{

	private TaskprogressCondition condition;

	public TaskprogressSpecification(TaskprogressCondition condition){
		this.condition = condition;
	}

	@Override
	public Predicate toPredicate(Root<Taskprogress> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
		List<Predicate> predicateList = new ArrayList<>();

		if(condition==null){
			return null;
		}

		tryAddProgressVoicePredicate(predicateList, root, cb);
		tryAddTaskIdPredicate(predicateList, root, cb);
		tryAddTaskStatusPredicate(predicateList, root, cb);
		tryAddTaskProgressPredicate(predicateList, root, cb);
		tryAddProgressBarPredicate(predicateList, root, cb);


		Predicate[] pre = new Predicate[predicateList.size()];
		pre = predicateList.toArray(pre);
		return cb.and(pre);
    }


	private void tryAddProgressVoicePredicate(List<Predicate> predicateList, Root<Taskprogress> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getProgressVoice())){
			predicateList.add(cb.like(root.get(Taskprogress.PROPERTY_PROGRESS_VOICE).as(String.class), "%"+condition.getProgressVoice()+"%"));
		}
	}
	private void tryAddTaskIdPredicate(List<Predicate> predicateList, Root<Taskprogress> root, CriteriaBuilder cb){

		if (null != condition.getTaskId() ) {
			predicateList.add(cb.equal(root.get(Taskprogress.PROPERTY_TASK_ID).as(Long.class), condition.getTaskId()));
		}

		if (null != condition.getTaskIdMax() ) {
			predicateList.add(cb.greaterThanOrEqualTo(root.get(Taskprogress.PROPERTY_TASK_ID).as(Long.class), condition.getTaskIdMax()));
		}

		if (null != condition.getTaskIdMin() ) {
			predicateList.add(cb.lessThan(root.get(Taskprogress.PROPERTY_TASK_ID).as(Long.class), condition.getTaskIdMin()));
		}
	}
	private void tryAddTaskStatusPredicate(List<Predicate> predicateList, Root<Taskprogress> root, CriteriaBuilder cb){

		if (null != condition.getTaskStatus() ) {
			predicateList.add(cb.equal(root.get(Taskprogress.PROPERTY_TASK_STATUS).as(Long.class), condition.getTaskStatus()));
		}

		if (null != condition.getTaskStatusMax() ) {
			predicateList.add(cb.greaterThanOrEqualTo(root.get(Taskprogress.PROPERTY_TASK_STATUS).as(Long.class), condition.getTaskStatusMax()));
		}

		if (null != condition.getTaskStatusMin() ) {
			predicateList.add(cb.lessThan(root.get(Taskprogress.PROPERTY_TASK_STATUS).as(Long.class), condition.getTaskStatusMin()));
		}
	}
	private void tryAddTaskProgressPredicate(List<Predicate> predicateList, Root<Taskprogress> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getTaskProgress())){
			predicateList.add(cb.like(root.get(Taskprogress.PROPERTY_TASK_PROGRESS).as(String.class), "%"+condition.getTaskProgress()+"%"));
		}
	}
	private void tryAddProgressBarPredicate(List<Predicate> predicateList, Root<Taskprogress> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getProgressBar())){
			predicateList.add(cb.like(root.get(Taskprogress.PROPERTY_PROGRESS_BAR).as(String.class), "%"+condition.getProgressBar()+"%"));
		}

	}


}


