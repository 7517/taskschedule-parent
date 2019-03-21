package com.edison.service.taskschedule.business.task.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;


/**
* 日程记录的值对象
*/
@ApiModel(value = "展现日程记录的值对象")
@Setter @Getter
public class ScheduleRecordVO {

    @ApiModelProperty(value = "记录id")
    private Long id;


    @ApiModelProperty(value = "日程主键id")
    private Long scheduleId;


    @ApiModelProperty(value = "日程文本信息")
    private String scheduleRecordText;


    @ApiModelProperty(value = "日程语音地址")
    private String scheduleRecordVoice;




    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

}