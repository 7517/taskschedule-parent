package com.edison.service.taskschedule.business.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.*;
import javax.persistence.*;
import javax.validation.constraints.*;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;




/**
* 任务的值对象
*/
@ApiModel(value = "展现任务的值对象")
@Setter @Getter
public class TaskVO {

    @ApiModelProperty(value = "记录id")
    private Long id;


    @ApiModelProperty(value = "责任部门主任id")
    private String respDeptUid;


    /**结束时间*/
    @ApiModelProperty(value = "结束时间")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date endAt;


    @ApiModelProperty(value = "协助部门主任id")
    private String assDeptUid;


    @ApiModelProperty(value = "任务语音地址")
    private String taskVoice;


    @ApiModelProperty(value = "紧急等级")
    private Long emerLevel;


    @ApiModelProperty(value = "是否为长期任务")
    private Integer isLongterm;


    @ApiModelProperty(value = "协助部门")
    private String asstDeptId;


    @ApiModelProperty(value = "责任部门")
    private String respDeptId;


    @ApiModelProperty(value = "任务详情")
    private String taskDetails;


    /**提醒时间*/
    @ApiModelProperty(value = "提醒时间")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date reminderTime;


    @ApiModelProperty(value = "任务分类")
    private Long taskCatagory;


    /**开始时间*/
    @ApiModelProperty(value = "开始时间")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date beginAt;


    @ApiModelProperty(value = "任务被指派人")
    private String respUid;

    @ApiModelProperty(value = "倒计时")
    private String countdown;

    @ApiModelProperty(value = "任务最新进展")
    private String taskProgress;









    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

}