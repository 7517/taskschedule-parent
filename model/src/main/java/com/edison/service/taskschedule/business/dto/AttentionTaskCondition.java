package com.edison.service.taskschedule.business.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

@ApiModel(value = "查询关注的任务使用的DTO")
@Getter @Setter
public class AttentionTaskCondition {

	@ApiModelProperty(value = "用户id")
	private Long uid;
	@ApiModelProperty(value = "用户id最大值")
	private Long uidMax;
	@ApiModelProperty(value = "用户id最小值")
	private Long uidMin;
	@ApiModelProperty(value = "任务id")
	private Long taskId;
	@ApiModelProperty(value = "任务id最大值")
	private Long taskIdMax;
	@ApiModelProperty(value = "任务id最小值")
	private Long taskIdMin;





	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
