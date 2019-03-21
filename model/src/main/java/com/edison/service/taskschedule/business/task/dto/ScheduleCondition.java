package com.edison.service.taskschedule.business.task.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

@ApiModel(value = "查询日程使用的DTO")
@Getter @Setter
public class ScheduleCondition {

	@ApiModelProperty(value = "提醒时间")
	private Date reminderTime;
	@ApiModelProperty(value = "起始提醒时间")
	private Date reminderTimeStart;
	@ApiModelProperty(value = "结束提醒时间")
	private Date reminderTimeEnd;
	@ApiModelProperty(value = "日程详情")
	private String scheduleDetails;
	@ApiModelProperty(value = "被指派部门主任")
	private String designeeDeptUid;
	@ApiModelProperty(value = "被指派人")
	private String designeeUid;
	@ApiModelProperty(value = "开始时间")
	private Date beginAt;
	@ApiModelProperty(value = "起始开始时间")
	private Date beginAtStart;
	@ApiModelProperty(value = "结束开始时间")
	private Date beginAtEnd;
	@ApiModelProperty(value = "被指派人部门")
	private String designeeDeptId;
	@ApiModelProperty(value = "日程状态")
	private Long scheduleStatus;
	@ApiModelProperty(value = "日程状态最大值")
	private Long scheduleStatusMax;
	@ApiModelProperty(value = "日程状态最小值")
	private Long scheduleStatusMin;
	@ApiModelProperty(value = "结束时间")
	private Date endAt;
	@ApiModelProperty(value = "起始结束时间")
	private Date endAtStart;
	@ApiModelProperty(value = "结束结束时间")
	private Date endAtEnd;
	@ApiModelProperty(value = "日程标题")
	private String scheduleTitle;





	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
