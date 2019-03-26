package com.edison.service.taskschedule.business.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.persistence.*;
import javax.validation.constraints.*;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;




/**
* 任务进展的值对象
*/
@ApiModel(value = "展现任务进展的值对象")
@Setter @Getter
public class TaskprogressVO {

    @ApiModelProperty(value = "记录id")
    private Long id;


    @ApiModelProperty(value = "语音地址")
    private String progressVoice;


    @ApiModelProperty(value = "任务主键id")
    private Long taskId;


    @ApiModelProperty(value = "任务状态")
    private Long taskStatus;


    @ApiModelProperty(value = "任务进展")
    private String taskProgress;




    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

}