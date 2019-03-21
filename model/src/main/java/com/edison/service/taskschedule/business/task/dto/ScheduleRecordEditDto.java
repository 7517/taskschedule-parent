package com.edison.service.taskschedule.business.task.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;


/**
 * 日程记录
 * @author icode
 */
@ApiModel(value = "修改日程记录使用的DTO")
@Setter @Getter
public class ScheduleRecordEditDto {


	/**日程主键id*/
	@ApiModelProperty(value = "日程主键id", required = false)
	private Long scheduleId;


	/**日程文本信息*/
	@ApiModelProperty(value = "日程文本信息", required = false)
	private String scheduleRecordText;


	/**日程语音地址*/
	@ApiModelProperty(value = "日程语音地址", required = false)
	private String scheduleRecordVoice;



	public Long getScheduleId(){
		return scheduleId;
	}
	public void setScheduleId(Long scheduleId) {
		this.scheduleId = scheduleId;
	}


	public String getScheduleRecordText(){
		return scheduleRecordText;
	}
	public void setScheduleRecordText(String scheduleRecordText) {
		this.scheduleRecordText = scheduleRecordText;
	}


	public String getScheduleRecordVoice(){
		return scheduleRecordVoice;
	}
	public void setScheduleRecordVoice(String scheduleRecordVoice) {
		this.scheduleRecordVoice = scheduleRecordVoice;
	}


	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
