package com.edison.service.taskschedule.business.task.dto;

import com.edison.saas.common.framework.eo.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.*;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

@ApiModel(value = "查询任务使用的DTO")
@Getter @Setter
public class TaskCondition extends BaseEntity<Long> {

	@ApiModelProperty(value = "责任部门主任id")
	private String respDeptUid;
	@ApiModelProperty(value = "结束时间")
	private Date endAt;
	@ApiModelProperty(value = "起始结束时间")
	private Date endAtStart;
	@ApiModelProperty(value = "结束结束时间")
	private Date endAtEnd;
	@ApiModelProperty(value = "协助部门主任id")
	private String assDeptUid;
	@ApiModelProperty(value = "任务语音地址")
	private String taskVoice;
	@ApiModelProperty(value = "紧急等级")
	private Long emerLevel;
	@ApiModelProperty(value = "紧急等级最大值")
	private Long emerLevelMax;
	@ApiModelProperty(value = "紧急等级最小值")
	private Long emerLevelMin;
	@ApiModelProperty(value = "是否为长期任务")
	private Integer isLongterm;
	@ApiModelProperty(value = "是否为长期任务最大值")
	private Integer isLongtermMax;
	@ApiModelProperty(value = "是否为长期任务最小值")
	private Integer isLongtermMin;
	@ApiModelProperty(value = "协助部门")
	private String asstDeptId;
	@ApiModelProperty(value = "责任部门")
	private String respDeptId;
	@ApiModelProperty(value = "任务详情")
	private String taskDetails;
	@ApiModelProperty(value = "提醒时间")
	private Date reminderTime;
	@ApiModelProperty(value = "起始提醒时间")
	private Date reminderTimeStart;
	@ApiModelProperty(value = "结束提醒时间")
	private Date reminderTimeEnd;
	@ApiModelProperty(value = "任务分类")
	private Long taskCatagory;
	@ApiModelProperty(value = "任务分类最大值")
	private Long taskCatagoryMax;
	@ApiModelProperty(value = "任务分类最小值")
	private Long taskCatagoryMin;
	@ApiModelProperty(value = "开始时间")
	private Date beginAt;
	@ApiModelProperty(value = "起始开始时间")
	private Date beginAtStart;
	@ApiModelProperty(value = "结束开始时间")
	private Date beginAtEnd;
	@ApiModelProperty(value = "任务被指派人")
	private String respUid;

	@ApiModelProperty(value = "倒计时")
	private Long countdown;

	@ApiModelProperty(value = "协助部门主任人名")
	private String assDeptUidName;

	@ApiModelProperty(value = "责任部门主任人名")
	private String respDeptUidName;

	@ApiModelProperty(value = "被指派人人名")
	private String respUidName;


	@ApiModelProperty(value = "搜索的条件")
	private String searchText;

	@ApiModelProperty(value = "任务的状态")
	private Long taskStatus;

	@ApiModelProperty(value = "用户的uid")
	private String uid;







	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
