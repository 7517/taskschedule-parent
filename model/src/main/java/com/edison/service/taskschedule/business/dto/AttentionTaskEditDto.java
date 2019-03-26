package com.edison.service.taskschedule.business.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;


/**
 * 关注的任务
 * @author icode
 */
@ApiModel(value = "修改关注的任务使用的DTO")
@Setter @Getter
public class AttentionTaskEditDto {


	/**用户id*/
	@ApiModelProperty(value = "用户id", required = false)
	private Long uid;


	/**任务id*/
	@ApiModelProperty(value = "任务id", required = false)
	private Long taskId;



	public Long getUid(){
		return uid;
	}
	public void setUid(Long uid) {
		this.uid = uid;
	}


	public Long getTaskId(){
		return taskId;
	}
	public void setTaskId(Long taskId) {
		this.taskId = taskId;
	}


	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
