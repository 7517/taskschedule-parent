package com.edison.service.taskschedule.business.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

@ApiModel(value = "查询日程记录使用的DTO")
@Getter @Setter
public class ScheduleRecordCondition {

	@ApiModelProperty(value = "日程主键id")
	private Long scheduleId;
	@ApiModelProperty(value = "日程主键id最大值")
	private Long scheduleIdMax;
	@ApiModelProperty(value = "日程主键id最小值")
	private Long scheduleIdMin;
	@ApiModelProperty(value = "日程文本信息")
	private String scheduleRecordText;
	@ApiModelProperty(value = "日程语音地址")
	private String scheduleRecordVoice;





	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
