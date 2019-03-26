package com.edison.service.taskschedule.business.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;


/**
* 关注的任务的值对象
*/
@ApiModel(value = "展现关注的任务的值对象")
@Setter @Getter
public class AttentionTaskVO {

    @ApiModelProperty(value = "记录id")
    private Long id;


    @ApiModelProperty(value = "用户id")
    private Long uid;


    @ApiModelProperty(value = "任务id")
    private Long taskId;




    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

}