package com.edison.service.taskschedule.business.task.domain;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.persistence.Temporal;
import javax.persistence.Transient;

@Setter
@Getter
@ApiModel(value = "查询任务列表使用的对象")
public class TaskDetails {
    @ApiModelProperty(value = "任务详情")
    private String taskDetails;

    @ApiModelProperty(value = "任务进展")
    private String taskProgress;

    @ApiModelProperty(value = "倒计时")
    private String date;
    @ApiModelProperty(value = "进度条")
    private String progressBar;

    @Transient
    private String aaa;



    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }


}
