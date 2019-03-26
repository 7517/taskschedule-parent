package com.edison.service.taskschedule.business.dto;

import com.edison.saas.common.framework.eo.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;


/**
 * 日程
 * @author icode
 */
@ApiModel(value = "新增日程使用的DTO")
@Setter @Getter
public class ScheduleAddDto extends BaseEntity<Long> {

    /**提醒时间*/
	@ApiModelProperty(value = "提醒时间", required = false)
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date reminderTime;

    /**日程详情*/
	@ApiModelProperty(value = "日程详情", required = true)
	private String scheduleDetails;

    /**被指派部门主任*/
	@ApiModelProperty(value = "被指派部门主任", required = false)
	private String designeeDeptUid;

    /**被指派人*/
	@ApiModelProperty(value = "被指派人", required = false)
	private String designeeUid;

    /**开始时间*/
	@ApiModelProperty(value = "开始时间", required = true)
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date beginAt;

    /**被指派人部门*/
	@ApiModelProperty(value = "被指派人部门", required = false)
	private String designeeDeptId;

    /**日程状态*/
	@ApiModelProperty(value = "日程状态", required = false)
	private Long scheduleStatus;

    /**结束时间*/
	@ApiModelProperty(value = "结束时间", required = true)
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date endAt;

    /**日程标题*/
	@ApiModelProperty(value = "日程标题", required = true)
	private String scheduleTitle;


	public Date getReminderTime(){
		return reminderTime;
	}
	public void setReminderTime(Date reminderTime) {
		this.reminderTime = reminderTime;
	}

	public String getScheduleDetails(){
		return scheduleDetails;
	}
	public void setScheduleDetails(String scheduleDetails) {
		this.scheduleDetails = scheduleDetails;
	}

	public String getDesigneeDeptUid(){
		return designeeDeptUid;
	}
	public void setDesigneeDeptUid(String designeeDeptUid) {
		this.designeeDeptUid = designeeDeptUid;
	}

	public String getDesigneeUid(){
		return designeeUid;
	}
	public void setDesigneeUid(String designeeUid) {
		this.designeeUid = designeeUid;
	}

	public Date getBeginAt(){
		return beginAt;
	}
	public void setBeginAt(Date beginAt) {
		this.beginAt = beginAt;
	}

	public String getDesigneeDeptId(){
		return designeeDeptId;
	}
	public void setDesigneeDeptId(String designeeDeptId) {
		this.designeeDeptId = designeeDeptId;
	}

	public Long getScheduleStatus(){
		return scheduleStatus;
	}
	public void setScheduleStatus(Long scheduleStatus) {
		this.scheduleStatus = scheduleStatus;
	}

	public Date getEndAt(){
		return endAt;
	}
	public void setEndAt(Date endAt) {
		this.endAt = endAt;
	}

	public String getScheduleTitle(){
		return scheduleTitle;
	}
	public void setScheduleTitle(String scheduleTitle) {
		this.scheduleTitle = scheduleTitle;
	}


	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}


}
