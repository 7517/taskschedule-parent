package com.edison.service.taskschedule.business.dao;


import com.edison.service.taskschedule.business.domain.Schedule;
import com.edison.service.taskschedule.business.dto.ScheduleCondition;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ScheduleSpecification implements Specification<Schedule>{

	private ScheduleCondition condition;

	public ScheduleSpecification(ScheduleCondition condition){
		this.condition = condition;
	}

	@Override
	public Predicate toPredicate(Root<Schedule> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
		List<Predicate> predicateList = new ArrayList<>();

		if(condition==null){
			return null;
		}

		tryAddReminderTimePredicate(predicateList, root, cb);
		tryAddScheduleDetailsPredicate(predicateList, root, cb);
		tryAddDesigneeDeptUidPredicate(predicateList, root, cb);
		tryAddDesigneeUidPredicate(predicateList, root, cb);
		tryAddBeginAtPredicate(predicateList, root, cb);
		tryAddDesigneeDeptIdPredicate(predicateList, root, cb);
		tryAddScheduleStatusPredicate(predicateList, root, cb);
		tryAddEndAtPredicate(predicateList, root, cb);
		tryAddScheduleTitlePredicate(predicateList, root, cb);


		Predicate[] pre = new Predicate[predicateList.size()];
		pre = predicateList.toArray(pre);
		return cb.and(pre);
    }


	private void tryAddReminderTimePredicate(List<Predicate> predicateList, Root<Schedule> root, CriteriaBuilder cb){

		if (null != condition.getReminderTime() ) {
			predicateList.add(cb.equal(root.get(Schedule.PROPERTY_REMINDER_TIME).as(Date.class), condition.getReminderTime()));
		}

		if (null != condition.getReminderTimeStart() ) {
			predicateList.add(cb.greaterThanOrEqualTo(root.get(Schedule.PROPERTY_REMINDER_TIME).as(Date.class), condition.getReminderTimeStart()));
		}

		if (null != condition.getReminderTimeEnd() ) {
			predicateList.add(cb.lessThan(root.get(Schedule.PROPERTY_REMINDER_TIME).as(Date.class), condition.getReminderTimeEnd()));
		}
	}
	private void tryAddScheduleDetailsPredicate(List<Predicate> predicateList, Root<Schedule> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getScheduleDetails())){
			predicateList.add(cb.like(root.get(Schedule.PROPERTY_SCHEDULE_DETAILS).as(String.class), "%"+condition.getScheduleDetails()+"%"));
		}
	}
	private void tryAddDesigneeDeptUidPredicate(List<Predicate> predicateList, Root<Schedule> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getDesigneeDeptUid())){
			predicateList.add(cb.like(root.get(Schedule.PROPERTY_DESIGNEE_DEPT_UID).as(String.class), "%"+condition.getDesigneeDeptUid()+"%"));
		}
	}
	private void tryAddDesigneeUidPredicate(List<Predicate> predicateList, Root<Schedule> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getDesigneeUid())){
			predicateList.add(cb.like(root.get(Schedule.PROPERTY_DESIGNEE_UID).as(String.class), "%"+condition.getDesigneeUid()+"%"));
		}
	}
	private void tryAddBeginAtPredicate(List<Predicate> predicateList, Root<Schedule> root, CriteriaBuilder cb){

		if (null != condition.getBeginAt() ) {
			predicateList.add(cb.equal(root.get(Schedule.PROPERTY_BEGIN_AT).as(Date.class), condition.getBeginAt()));
		}

		if (null != condition.getBeginAtStart() ) {
			predicateList.add(cb.greaterThanOrEqualTo(root.get(Schedule.PROPERTY_BEGIN_AT).as(Date.class), condition.getBeginAtStart()));
		}

		if (null != condition.getBeginAtEnd() ) {
			predicateList.add(cb.lessThan(root.get(Schedule.PROPERTY_BEGIN_AT).as(Date.class), condition.getBeginAtEnd()));
		}
	}
	private void tryAddDesigneeDeptIdPredicate(List<Predicate> predicateList, Root<Schedule> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getDesigneeDeptId())){
			predicateList.add(cb.like(root.get(Schedule.PROPERTY_DESIGNEE_DEPT_ID).as(String.class), "%"+condition.getDesigneeDeptId()+"%"));
		}
	}
	private void tryAddScheduleStatusPredicate(List<Predicate> predicateList, Root<Schedule> root, CriteriaBuilder cb){

		if (null != condition.getScheduleStatus() ) {
			predicateList.add(cb.equal(root.get(Schedule.PROPERTY_SCHEDULE_STATUS).as(Long.class), condition.getScheduleStatus()));
		}

		if (null != condition.getScheduleStatusMax() ) {
			predicateList.add(cb.greaterThanOrEqualTo(root.get(Schedule.PROPERTY_SCHEDULE_STATUS).as(Long.class), condition.getScheduleStatusMax()));
		}

		if (null != condition.getScheduleStatusMin() ) {
			predicateList.add(cb.lessThan(root.get(Schedule.PROPERTY_SCHEDULE_STATUS).as(Long.class), condition.getScheduleStatusMin()));
		}
	}
	private void tryAddEndAtPredicate(List<Predicate> predicateList, Root<Schedule> root, CriteriaBuilder cb){

		if (null != condition.getEndAt() ) {
			predicateList.add(cb.equal(root.get(Schedule.PROPERTY_END_AT).as(Date.class), condition.getEndAt()));
		}

		if (null != condition.getEndAtStart() ) {
			predicateList.add(cb.greaterThanOrEqualTo(root.get(Schedule.PROPERTY_END_AT).as(Date.class), condition.getEndAtStart()));
		}

		if (null != condition.getEndAtEnd() ) {
			predicateList.add(cb.lessThan(root.get(Schedule.PROPERTY_END_AT).as(Date.class), condition.getEndAtEnd()));
		}
	}
	private void tryAddScheduleTitlePredicate(List<Predicate> predicateList, Root<Schedule> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getScheduleTitle())){
			predicateList.add(cb.like(root.get(Schedule.PROPERTY_SCHEDULE_TITLE).as(String.class), "%"+condition.getScheduleTitle()+"%"));
		}
	}
}


