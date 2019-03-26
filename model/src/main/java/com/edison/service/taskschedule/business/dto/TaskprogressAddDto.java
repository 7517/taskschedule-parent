package com.edison.service.taskschedule.business.dto;

import com.edison.saas.common.framework.eo.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;


/**
 * 任务进展
 * @author icode
 */
@ApiModel(value = "新增任务进展使用的DTO")
@Setter @Getter
public class TaskprogressAddDto extends BaseEntity<Long> {

    /**语音地址*/
	@ApiModelProperty(value = "语音地址", required = false)
	private String progressVoice;

    /**任务主键id*/
	@ApiModelProperty(value = "任务主键id", required = false)
	private Long taskId;

    /**任务状态*/
	@ApiModelProperty(value = "任务状态", required = false)
	private Long taskStatus;

    /**任务进展*/
	@ApiModelProperty(value = "任务进展", required = false)
	private String taskProgress;

	/**任务进度条*/
	@ApiModelProperty(value = "任务进度条", required = false)
	private String progressBar;

	public String getProgressVoice(){
		return progressVoice;
	}
	public void setProgressVoice(String progressVoice) {
		this.progressVoice = progressVoice;
	}

	public Long getTaskId(){
		return taskId;
	}
	public void setTaskId(Long taskId) {
		this.taskId = taskId;
	}

	public Long getTaskStatus(){
		return taskStatus;
	}
	public void setTaskStatus(Long taskStatus) {
		this.taskStatus = taskStatus;
	}

	public String getTaskProgress(){
		return taskProgress;
	}
	public void setTaskProgress(String taskProgress) {
		this.taskProgress = taskProgress;
	}

	public String getProgressBar(){
		return progressBar;
	}
	public void setProgressBar(String progressBar) {
		this.progressBar = progressBar;
	}



	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
