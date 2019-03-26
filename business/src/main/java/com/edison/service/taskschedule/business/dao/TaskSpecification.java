package com.edison.service.taskschedule.business.dao;

import com.edison.service.taskschedule.business.domain.Task;
import com.edison.service.taskschedule.business.dto.TaskCondition;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.*;

public class TaskSpecification implements Specification<Task>{

	private TaskCondition condition;

	public TaskSpecification(TaskCondition condition){
		this.condition = condition;
	}

	@Override
	public Predicate toPredicate(Root<Task> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
		List<Predicate> predicateList = new ArrayList<>();

		if(condition==null){
			return null;
		}

		tryAddRespDeptUidPredicate(predicateList, root, cb);
		tryAddEndAtPredicate(predicateList, root, cb);
		tryAddAssDeptUidPredicate(predicateList, root, cb);
		tryAddTaskVoicePredicate(predicateList, root, cb);
		tryAddEmerLevelPredicate(predicateList, root, cb);
		tryAddIsLongtermPredicate(predicateList, root, cb);
		tryAddAsstDeptIdPredicate(predicateList, root, cb);
		tryAddRespDeptIdPredicate(predicateList, root, cb);
		tryAddTaskDetailsPredicate(predicateList, root, cb);
		tryAddReminderTimePredicate(predicateList, root, cb);
		tryAddTaskCatagoryPredicate(predicateList, root, cb);
		tryAddBeginAtPredicate(predicateList, root, cb);
		tryAddRespUidPredicate(predicateList, root, cb);
		tryAddAsstDeptUidNamePredicate(predicateList,root,cb);
		tryAddRespDeptUidNamePredicate(predicateList,root,cb);
		tryAddRespUidNamePredicate(predicateList,root,cb);
		tryAddCountdownPredicate(predicateList,root,cb);
		tryAddCreateUidPredicate(predicateList,root,cb);
        tryAddSearchTextPredicate(predicateList,root,cb);
        tryAddUserIdPredicate(predicateList,root,cb);

		Predicate[] pre = new Predicate[predicateList.size()];
		pre = predicateList.toArray(pre);
		return cb.and(pre);
    }


	private void tryAddRespDeptUidPredicate(List<Predicate> predicateList, Root<Task> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getRespDeptUid())){
			predicateList.add(cb.like(root.get(Task.PROPERTY_RESP_DEPT_UID).as(String.class), "%"+condition.getRespDeptUid()+"%"));
		}
	}
	private void tryAddEndAtPredicate(List<Predicate> predicateList, Root<Task> root, CriteriaBuilder cb){

		if (null != condition.getEndAt() ) {
			predicateList.add(cb.equal(root.get(Task.PROPERTY_END_AT).as(Date.class), condition.getEndAt()));
		}

		if (null != condition.getEndAtStart() ) {
			predicateList.add(cb.greaterThanOrEqualTo(root.get(Task.PROPERTY_END_AT).as(Date.class), condition.getEndAtStart()));
		}

		if (null != condition.getEndAtEnd() ) {
			predicateList.add(cb.lessThan(root.get(Task.PROPERTY_END_AT).as(Date.class), condition.getEndAtEnd()));
		}
	}
	private void tryAddAssDeptUidPredicate(List<Predicate> predicateList, Root<Task> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getAssDeptUid())){
			predicateList.add(cb.like(root.get(Task.PROPERTY_ASS_DEPT_UID).as(String.class), "%"+condition.getAssDeptUid()+"%"));
		}
	}
	private void tryAddTaskVoicePredicate(List<Predicate> predicateList, Root<Task> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getTaskVoice())){
			predicateList.add(cb.like(root.get(Task.PROPERTY_TASK_VOICE).as(String.class), "%"+condition.getTaskVoice()+"%"));
		}
	}
	private void tryAddEmerLevelPredicate(List<Predicate> predicateList, Root<Task> root, CriteriaBuilder cb){

		if (null != condition.getEmerLevel() ) {
			predicateList.add(cb.equal(root.get(Task.PROPERTY_EMER_LEVEL).as(Long.class), condition.getEmerLevel()));
		}

		if (null != condition.getEmerLevelMax() ) {
			predicateList.add(cb.greaterThanOrEqualTo(root.get(Task.PROPERTY_EMER_LEVEL).as(Long.class), condition.getEmerLevelMax()));
		}

		if (null != condition.getEmerLevelMin() ) {
			predicateList.add(cb.lessThan(root.get(Task.PROPERTY_EMER_LEVEL).as(Long.class), condition.getEmerLevelMin()));
		}
	}
	private void tryAddIsLongtermPredicate(List<Predicate> predicateList, Root<Task> root, CriteriaBuilder cb){

		if (null != condition.getIsLongterm() ) {
			predicateList.add(cb.equal(root.get(Task.PROPERTY_IS_LONGTERM).as(Integer.class), condition.getIsLongterm()));
		}

		if (null != condition.getIsLongtermMax() ) {
			predicateList.add(cb.greaterThanOrEqualTo(root.get(Task.PROPERTY_IS_LONGTERM).as(Integer.class), condition.getIsLongtermMax()));
		}

		if (null != condition.getIsLongtermMin() ) {
			predicateList.add(cb.lessThan(root.get(Task.PROPERTY_IS_LONGTERM).as(Integer.class), condition.getIsLongtermMin()));
		}
	}
	private void tryAddAsstDeptIdPredicate(List<Predicate> predicateList, Root<Task> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getAsstDeptId())){
			predicateList.add(cb.like(root.get(Task.PROPERTY_ASST_DEPT_ID).as(String.class), "%"+condition.getAsstDeptId()+"%"));
		}
	}
	private void tryAddRespDeptIdPredicate(List<Predicate> predicateList, Root<Task> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getRespDeptId())){
			predicateList.add(cb.like(root.get(Task.PROPERTY_RESP_DEPT_ID).as(String.class), "%"+condition.getRespDeptId()+"%"));
		}
	}
	private void tryAddTaskDetailsPredicate(List<Predicate> predicateList, Root<Task> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getTaskDetails())){
			predicateList.add(cb.like(root.get(Task.PROPERTY_TASK_DETAILS).as(String.class), "%"+condition.getTaskDetails()+"%"));
		}
	}
	private void tryAddReminderTimePredicate(List<Predicate> predicateList, Root<Task> root, CriteriaBuilder cb){

		if (null != condition.getReminderTime() ) {
			predicateList.add(cb.equal(root.get(Task.PROPERTY_REMINDER_TIME).as(Date.class), condition.getReminderTime()));
		}

		if (null != condition.getReminderTimeStart() ) {
			predicateList.add(cb.greaterThanOrEqualTo(root.get(Task.PROPERTY_REMINDER_TIME).as(Date.class), condition.getReminderTimeStart()));
		}

		if (null != condition.getReminderTimeEnd() ) {
			predicateList.add(cb.lessThan(root.get(Task.PROPERTY_REMINDER_TIME).as(Date.class), condition.getReminderTimeEnd()));
		}
	}
	private void tryAddTaskCatagoryPredicate(List<Predicate> predicateList, Root<Task> root, CriteriaBuilder cb){

		if (null != condition.getTaskCatagory() ) {
			predicateList.add(cb.equal(root.get(Task.PROPERTY_TASK_CATAGORY).as(Long.class), condition.getTaskCatagory()));
		}

		if (null != condition.getTaskCatagoryMax() ) {
			predicateList.add(cb.greaterThanOrEqualTo(root.get(Task.PROPERTY_TASK_CATAGORY).as(Long.class), condition.getTaskCatagoryMax()));
		}

		if (null != condition.getTaskCatagoryMin() ) {
			predicateList.add(cb.lessThan(root.get(Task.PROPERTY_TASK_CATAGORY).as(Long.class), condition.getTaskCatagoryMin()));
		}
	}
	private void tryAddBeginAtPredicate(List<Predicate> predicateList, Root<Task> root, CriteriaBuilder cb){

		if (null != condition.getBeginAt() ) {
			predicateList.add(cb.equal(root.get(Task.PROPERTY_BEGIN_AT).as(Date.class), condition.getBeginAt()));
		}

		if (null != condition.getBeginAtStart() ) {
			predicateList.add(cb.greaterThanOrEqualTo(root.get(Task.PROPERTY_BEGIN_AT).as(Date.class), condition.getBeginAtStart()));
		}

		if (null != condition.getBeginAtEnd() ) {
			predicateList.add(cb.lessThan(root.get(Task.PROPERTY_BEGIN_AT).as(Date.class), condition.getBeginAtEnd()));
		}
	}
	private void tryAddRespUidPredicate(List<Predicate> predicateList, Root<Task> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getRespUid())){
			predicateList.add(cb.like(root.get(Task.PROPERTY_RESP_UID).as(String.class), "%"+condition.getRespUid()+"%"));
		}
	}
	private void tryAddAsstDeptUidNamePredicate(List<Predicate> predicateList, Root<Task> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getAssDeptUidName())){
			predicateList.add(cb.like(root.get(Task.PROPERTY_ASST_DEPT_UID_NAME).as(String.class), "%"+condition.getAssDeptUidName()+"%"));
		}
	}
	private void tryAddRespUidNamePredicate(List<Predicate> predicateList, Root<Task> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getRespUidName())){
			predicateList.add(cb.like(root.get(Task.PROPERTY_RESP_UID_NAME).as(String.class), "%"+condition.getRespUidName()+"%"));
		}
	}

	private void tryAddRespDeptUidNamePredicate(List<Predicate> predicateList, Root<Task> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getRespDeptUidName())){
			predicateList.add(cb.like(root.get(Task.PROPERTY_RESP_DEPT_UID_NAME).as(String.class), "%"+condition.getRespDeptUidName()+"%"));
		}
	}
	private void tryAddCountdownPredicate(List<Predicate> predicateList, Root<Task> root, CriteriaBuilder cb){
		if(null !=(condition.getCountdown())){
			predicateList.add(cb.like(root.get(Task.PROPERTY_COUNTDOWN).as(String.class), "%"+condition.getRespDeptUidName()+"%"));
		}
	}
	private void tryAddCreateUidPredicate(List<Predicate> predicateList, Root<Task> root, CriteriaBuilder cb){
		if(null !=(condition.getCreateUid())){
			predicateList.add(cb.equal(root.get(Task.PROPERTY_CREATE_UID).as(Long.class), condition.getCreateUid()));
		}
	}
	private void tryAddTaskIdPredicate(List<Predicate> predicateList, Root<Task> root, CriteriaBuilder cb){
		if(null !=(condition.getTaskId())){
			predicateList.add(cb.equal(root.get(Task.PROPERTY_ID).as(Long.class), condition.getTaskId()));
		}
	}

	private void tryAddSearchTextPredicate(List<Predicate> predicateList, Root<Task> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getSearchText())){
			Predicate p1=cb.like(root.get(Task.PROPERTY_ASST_DEPT_UID_NAME).as(String.class), "%"+condition.getSearchText()+"%");
			Predicate p2=cb.like(root.get(Task.PROPERTY_RESP_UID_NAME).as(String.class), "%"+condition.getSearchText()+"%");
			Predicate p3=cb.like(root.get(Task.PROPERTY_RESP_DEPT_UID_NAME).as(String.class), "%"+condition.getSearchText()+"%");
			Predicate p4=cb.like(root.get(Task.PROPERTY_TASK_DETAILS).as(String.class), "%"+condition.getSearchText()+"%");
            predicateList.add(cb.or(p1,p2,p3,p4));
		}


	}
    private void tryAddUserIdPredicate(List<Predicate> predicateList, Root<Task> root, CriteriaBuilder cb){
        if(StringUtils.isNotEmpty(condition.getUid())){
            Predicate p1=cb.like(root.get(Task.PROPERTY_RESP_UID).as(String.class),"%"+ condition.getUid()+"%");
            Predicate p2=cb.like(root.get(Task.PROPERTY_ASS_DEPT_UID).as(String.class),"%"+ condition.getUid()+"%");
            Predicate p3=cb.like(root.get(Task.PROPERTY_RESP_DEPT_UID).as(String.class),"%"+ condition.getUid()+"%");
            predicateList.add(cb.or(p1,p2,p3));
        }
    }


}


