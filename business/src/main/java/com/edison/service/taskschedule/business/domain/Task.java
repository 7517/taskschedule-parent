package com.edison.service.taskschedule.business.domain;

import com.edison.saas.common.jpa.BaseEntity;
import java.util.*;
import javax.persistence.*;
import javax.validation.constraints.*;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.validator.constraints.NotEmpty;



/**
 * 任务
 * @author icode
 */
@Entity()
@Table(name = "task_task")
//@DynamicUpdate
//@DynamicInsert
//@Where(clause="delete=0")
public class Task extends BaseEntity<Long>{

	public static final String PROPERTY_RESP_DEPT_UID = "respDeptUid";
	public static final String PROPERTY_END_AT = "endAt";
	public static final String PROPERTY_ASS_DEPT_UID = "assDeptUid";
	public static final String PROPERTY_TASK_VOICE = "taskVoice";
	public static final String PROPERTY_EMER_LEVEL = "emerLevel";
	public static final String PROPERTY_IS_LONGTERM = "isLongterm";
	public static final String PROPERTY_ASST_DEPT_ID = "asstDeptId";
	public static final String PROPERTY_RESP_DEPT_ID = "respDeptId";
	public static final String PROPERTY_TASK_DETAILS = "taskDetails";
	public static final String PROPERTY_REMINDER_TIME = "reminderTime";
	public static final String PROPERTY_TASK_CATAGORY = "taskCatagory";
	public static final String PROPERTY_BEGIN_AT = "beginAt";
	public static final String PROPERTY_RESP_UID = "respUid";
	public static final String PROPERTY_COUNTDOWN = "countdown";
	public static final String PROPERTY_ASST_DEPT_UID_NAME = "assDeptUidName";
	public static final String PROPERTY_RESP_DEPT_UID_NAME="respDeptUidName";
	public static final String PROPERTY_RESP_UID_NAME="respUidName";
	public static final String PROPERTY_CREATE_UID="createUid";
	public static final String PROPERTY_ID="id";



    @Id
    @Column(name = "id")
    private Long id;


    /**
    * 责任部门主任id
    * 
    */
    @Column(name = "resp_dept_uid", nullable = true, updatable = true)
	@Size(max = 255, message = "责任部门主任id超长，最多255个字符")
	private String respDeptUid;

    /**
    * 结束时间
    * 
    */
    @Column(name = "end_at", nullable = true, updatable = true)
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date endAt;

    /**
    * 协助部门主任id
    * 
    */
    @Column(name = "ass_dept_uid", nullable = true, updatable = true)
	@Size(max = 255, message = "协助部门主任id超长，最多255个字符")
	private String assDeptUid;

    /**
    * 任务语音地址
    * 
    */
    @Column(name = "task_voice", nullable = true, updatable = true)
	@Size(max = 255, message = "任务语音地址超长，最多255个字符")
	private String taskVoice;

    /**
    * 紧急等级
    * 
    */
    @Column(name = "emer_level", nullable = true, updatable = true)
	private Long emerLevel;

    /**
    * 是否为长期任务
    * 
    */
    @Column(name = "is_longterm", nullable = true, updatable = true)
	private Integer isLongterm;

    /**
    * 协助部门
    * 
    */
    @Column(name = "asst_dept_id", nullable = true, updatable = true)
	@Size(max = 255, message = "协助部门超长，最多255个字符")
	private String asstDeptId;

    /**
    * 责任部门
    * 
    */
    @Column(name = "resp_dept_id", nullable = true, updatable = true)
	@Size(max = 255, message = "责任部门超长，最多255个字符")
	private String respDeptId;

    /**
    * 任务详情
    * 
    */
    @Column(name = "task_details", nullable = true, updatable = true)
	@Size(max = 255, message = "任务详情超长，最多255个字符")
	private String taskDetails;

    /**
    * 提醒时间
    * 
    */
    @Column(name = "reminder_time", nullable = true, updatable = true)
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date reminderTime;

    /**
    * 任务分类
    * 
    */
    @Column(name = "task_catagory", nullable = true, updatable = true)
	private Long taskCatagory;

    /**
    * 开始时间
    * 
    */
    @Column(name = "begin_at", nullable = true, updatable = true)
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date beginAt;

    /**
    * 任务被指派人
    * 
    */
    @Column(name = "resp_uid", nullable = true, updatable = true)
	@Size(max = 255, message = "任务被指派人超长，最多255个字符")
	private String respUid;

	/**
	 * 倒计时
	 *
	 */
	@Column(name = "countdown", nullable = true, updatable = true)

	private Long countdown;

	/**
	 * 倒计时
	 *
	 */
	@Column(name = "ass_dept_uid_name", nullable = true, updatable = true)
	@Size(max = 255, message = "协助部门主任人名，最多255个字符")
	private String assDeptUidName;
	/**
	 * 责任部门主任人名
	 *
	 */
	@Column(name = "resp_dept_uid_name", nullable = true, updatable = true)
	@Size(max = 255, message = "责任部门主任人名，最多255个字符")
	private String respDeptUidName;

	/**
	 * 任务被指派人
	 *
	 */
	@Column(name = "resp_uid_name", nullable = true, updatable = true)
	@Size(max = 255, message = "任务被指派人名，最多255个字符")
	private String respUidName;



	public String getRespDeptUid(){
		return respDeptUid;
	}
	public void setRespDeptUid(String respDeptUid) {
		this.respDeptUid = respDeptUid;
	}

	public Date getEndAt(){
		return endAt;
	}
	public void setEndAt(Date endAt) {
		this.endAt = endAt;
	}

	public String getAssDeptUid(){
		return assDeptUid;
	}
	public void setAssDeptUid(String assDeptUid) {
		this.assDeptUid = assDeptUid;
	}

	public String getTaskVoice(){
		return taskVoice;
	}
	public void setTaskVoice(String taskVoice) {
		this.taskVoice = taskVoice;
	}

	public Long getEmerLevel(){
		return emerLevel;
	}
	public void setEmerLevel(Long emerLevel) {
		this.emerLevel = emerLevel;
	}

	public Integer getIsLongterm(){
		return isLongterm;
	}
	public void setIsLongterm(Integer isLongterm) {
		this.isLongterm = isLongterm;
	}

	public String getAsstDeptId(){
		return asstDeptId;
	}
	public void setAsstDeptId(String asstDeptId) {
		this.asstDeptId = asstDeptId;
	}

	public String getRespDeptId(){
		return respDeptId;
	}
	public void setRespDeptId(String respDeptId) {
		this.respDeptId = respDeptId;
	}

	public String getTaskDetails(){
		return taskDetails;
	}
	public void setTaskDetails(String taskDetails) {
		this.taskDetails = taskDetails;
	}

	public Date getReminderTime(){
		return reminderTime;
	}
	public void setReminderTime(Date reminderTime) {
		this.reminderTime = reminderTime;
	}

	public Long getTaskCatagory(){
		return taskCatagory;
	}
	public void setTaskCatagory(Long taskCatagory) {
		this.taskCatagory = taskCatagory;
	}

	public Date getBeginAt(){
		return beginAt;
	}
	public void setBeginAt(Date beginAt) {
		this.beginAt = beginAt;
	}

	public String getRespUid(){
		return respUid;
	}
	public void setRespUid(String respUid) {
		this.respUid = respUid;
	}


	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	public Long getCountdown(){
		return countdown;
	}
	public void setCountdown(Long countdown) {
		this.countdown =countdown;
	}

	public String getAsstDeptUidName(){
		return assDeptUidName;
	}
	public void setAsstDeptUidName(String assDeptUidName) {
		this.assDeptUid = assDeptUidName;
	}

	public void setRespDeptUidName(String respUidName) {
		this.respDeptUidName = respUidName;
	}public String getPropertyRespDeptUidName(){
		return respDeptUidName;
	}

	public void setRespUidName(String respUidName) {
		this.respDeptUidName = respUidName;
	}
	public String getRespUidName(){
		return respUidName;
	}


	public String getAssDeptUidName() {
		return assDeptUidName;
	}

	public void setAssDeptUidName(String assDeptUidName) {
		this.assDeptUidName = assDeptUidName;
	}

	public String getRespDeptUidName() {
		return respDeptUidName;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}

