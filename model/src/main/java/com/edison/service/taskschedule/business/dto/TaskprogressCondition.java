package com.edison.service.taskschedule.business.dto;

import com.edison.saas.common.framework.eo.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

@ApiModel(value = "查询任务进展使用的DTO")
@Getter @Setter
public class TaskprogressCondition extends BaseEntity<Long> {

	@ApiModelProperty(value = "语音地址")
	private String progressVoice;
	@ApiModelProperty(value = "任务主键id")
	private Long taskId;
	@ApiModelProperty(value = "任务主键id最大值")
	private Long taskIdMax;
	@ApiModelProperty(value = "任务主键id最小值")
	private Long taskIdMin;
	@ApiModelProperty(value = "任务状态")
	private Long taskStatus;
	@ApiModelProperty(value = "任务状态最大值")
	private Long taskStatusMax;
	@ApiModelProperty(value = "任务状态最小值")
	private Long taskStatusMin;
	@ApiModelProperty(value = "任务进展")
	private String taskProgress;
	@ApiModelProperty(value = "任务进度条")
	private String progressBar;





	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
