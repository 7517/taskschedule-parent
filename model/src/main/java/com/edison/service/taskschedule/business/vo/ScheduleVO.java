package com.edison.service.taskschedule.business.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;


/**
* 日程的值对象
*/
@ApiModel(value = "展现日程的值对象")
@Setter @Getter
public class ScheduleVO {

    @ApiModelProperty(value = "记录id")
    private Long id;


    /**提醒时间*/
    @ApiModelProperty(value = "提醒时间")
    private Date reminderTime;


    @ApiModelProperty(value = "日程详情")
    private String scheduleDetails;


    @ApiModelProperty(value = "被指派部门主任")
    private String designeeDeptUid;


    @ApiModelProperty(value = "被指派人")
    private String designeeUid;


    /**开始时间*/
    @ApiModelProperty(value = "开始时间")
    private Date beginAt;


    @ApiModelProperty(value = "被指派人部门")
    private String designeeDeptId;


    @ApiModelProperty(value = "日程状态")
    private Long scheduleStatus;


    /**结束时间*/
    @ApiModelProperty(value = "结束时间")
    private Date endAt;


    @ApiModelProperty(value = "日程标题")
    private String scheduleTitle;




    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

}