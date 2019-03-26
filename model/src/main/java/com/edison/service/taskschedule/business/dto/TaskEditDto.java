package com.edison.service.taskschedule.business.dto;

import com.edison.saas.common.framework.eo.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.*;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;


/**
 * 任务
 * @author icode
 */
@ApiModel(value = "修改任务使用的DTO")
@Setter @Getter
public class TaskEditDto extends BaseEntity<Long> {


	/**责任部门主任id*/
	@ApiModelProperty(value = "责任部门主任id", required = false)
	private String respDeptUid;


	/**结束时间*/
	@ApiModelProperty(value = "结束时间", required = false)
	private Date endAt;


	/**协助部门主任id*/
	@ApiModelProperty(value = "协助部门主任id", required = false)
	private String assDeptUid;


	/**任务语音地址*/
	@ApiModelProperty(value = "任务语音地址", required = false)
	private String taskVoice;


	/**紧急等级*/
	@ApiModelProperty(value = "紧急等级", required = false)
	private Long emerLevel;


	/**是否为长期任务*/
	@ApiModelProperty(value = "是否为长期任务", required = false)
	private Integer isLongterm;


	/**协助部门*/
	@ApiModelProperty(value = "协助部门", required = false)
	private String asstDeptId;


	/**责任部门*/
	@ApiModelProperty(value = "责任部门", required = false)
	private String respDeptId;


	/**任务详情*/
	@ApiModelProperty(value = "任务详情", required = false)
	private String taskDetails;


	/**提醒时间*/
	@ApiModelProperty(value = "提醒时间", required = false)
	private Date reminderTime;


	/**任务分类*/
	@ApiModelProperty(value = "任务分类", required = false)
	private Long taskCatagory;


	/**开始时间*/
	@ApiModelProperty(value = "开始时间", required = false)
	private Date beginAt;


	/**任务被指派人*/
	@ApiModelProperty(value = "任务被指派人", required = false)
	private String respUid;

	/**任务被指派人*/
	@ApiModelProperty(value = "倒计时", required = false)
	private Long countdown;

	/**协助部门主任人名*/
	@ApiModelProperty(value = "协助部门主任人名", required = false)
	private String assDeptUidName;

	/**责任部门主任人名*/
	@ApiModelProperty(value = "责任部门主任人名", required = false)
	private String respDeptUidName;

	/**被指派人名*/
	@ApiModelProperty(value = "被指派人名", required = false)
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

	public Long getCountdown() {
		return countdown;
	}

	public void setCountdown(Long countdown) {
		this.countdown = countdown;
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

	public void setRespDeptUidName(String respDeptUidName) {
		this.respDeptUidName = respDeptUidName;
	}

	public String getRespUidName() {
		return respUidName;
	}

	public void setRespUidName(String respUidName) {
		this.respUidName = respUidName;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
