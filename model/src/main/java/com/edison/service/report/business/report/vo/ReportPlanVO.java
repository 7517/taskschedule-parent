package com.edison.service.report.business.report.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.Value;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;


/**
* 报告计划的值对象
*/
@ApiModel(value = "展现报告计划的值对象")
@Setter @Getter
public class ReportPlanVO {

    @ApiModelProperty(value = "记录id")
    private Long id;


    @ApiModelProperty(value = "年计划主键")
    private Long annualPlanId;


    @ApiModelProperty(value = "进度条百分比")
    private String progressBar;


    /**开始时间*/
    @ApiModelProperty(value = "开始时间")
    private Date beginAt;


    /**预计完成时间*/
    @ApiModelProperty(value = "预计完成时间")
    private Date expectedAt;


    @ApiModelProperty(value = "计划内容")
    private String planDetails;


    /**结束时间*/
    @ApiModelProperty(value = "结束时间")
    private Date endId;


    @ApiModelProperty(value = "计划状态")
    private Long planStatus;


    @ApiModelProperty(value = "附件主键")
    private Long annexId;


    @ApiModelProperty(value = "计划补充说明")
    private String planSupplement;


    @ApiModelProperty(value = "计划类型（周/年）")
    private Long planType;


    @ApiModelProperty(value = "报告表主键")
    private Long reportId;

    @ApiModelProperty(value ="部门id")
    private Long subDeptId;




    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

}