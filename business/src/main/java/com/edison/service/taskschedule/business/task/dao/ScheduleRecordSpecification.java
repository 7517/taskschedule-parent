package com.edison.service.taskschedule.business.task.dao;

import com.edison.schedule.business.schedule.domain.ScheduleRecord;
import com.edison.schedule.business.schedule.dto.ScheduleRecordCondition;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class ScheduleRecordSpecification implements Specification<ScheduleRecord>{

	private ScheduleRecordCondition condition;

	public ScheduleRecordSpecification(ScheduleRecordCondition condition){
		this.condition = condition;
	}

	@Override
	public Predicate toPredicate(Root<ScheduleRecord> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
		List<Predicate> predicateList = new ArrayList<>();

		if(condition==null){
			return null;
		}

		tryAddScheduleIdPredicate(predicateList, root, cb);
		tryAddScheduleRecordTextPredicate(predicateList, root, cb);
		tryAddScheduleRecordVoicePredicate(predicateList, root, cb);


		Predicate[] pre = new Predicate[predicateList.size()];
		pre = predicateList.toArray(pre);
		return cb.and(pre);
    }


	private void tryAddScheduleIdPredicate(List<Predicate> predicateList, Root<ScheduleRecord> root, CriteriaBuilder cb){

		if (null != condition.getScheduleId() ) {
			predicateList.add(cb.equal(root.get(ScheduleRecord.PROPERTY_SCHEDULE_ID).as(Long.class), condition.getScheduleId()));
		}

		if (null != condition.getScheduleIdMax() ) {
			predicateList.add(cb.greaterThanOrEqualTo(root.get(ScheduleRecord.PROPERTY_SCHEDULE_ID).as(Long.class), condition.getScheduleIdMax()));
		}

		if (null != condition.getScheduleIdMin() ) {
			predicateList.add(cb.lessThan(root.get(ScheduleRecord.PROPERTY_SCHEDULE_ID).as(Long.class), condition.getScheduleIdMin()));
		}
	}
	private void tryAddScheduleRecordTextPredicate(List<Predicate> predicateList, Root<ScheduleRecord> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getScheduleRecordText())){
			predicateList.add(cb.like(root.get(ScheduleRecord.PROPERTY_SCHEDULE_RECORD_TEXT).as(String.class), "%"+condition.getScheduleRecordText()+"%"));
		}
	}
	private void tryAddScheduleRecordVoicePredicate(List<Predicate> predicateList, Root<ScheduleRecord> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getScheduleRecordVoice())){
			predicateList.add(cb.like(root.get(ScheduleRecord.PROPERTY_SCHEDULE_RECORD_VOICE).as(String.class), "%"+condition.getScheduleRecordVoice()+"%"));
		}
	}
}


