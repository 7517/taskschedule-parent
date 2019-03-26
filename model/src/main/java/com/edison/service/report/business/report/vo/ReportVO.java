package com.edison.service.report.business.report.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;
import java.util.List;


/**
* 报告的值对象
*/
@ApiModel(value = "展现报告的值对象")
@Setter @Getter
public class ReportVO {

    @ApiModelProperty(value = "记录id")
    private Long id;


    /**结束时间*/
    @ApiModelProperty(value = "结束时间")
    private Date endAt;


    /**开始时间*/
    @ApiModelProperty(value = "开始时间")
    private Date beginAt;


    @ApiModelProperty(value = "抄送人名")
    private String ccUidName;


    @ApiModelProperty(value = "附件id")
    private Long annexId;


    @ApiModelProperty(value = "进度条")
    private String progressBar;


    @ApiModelProperty(value = "报告状态")
    private Long reportStatus;


    @ApiModelProperty(value = "报告内容总结")
    private String reportContent;


    @ApiModelProperty(value = "报告类型")
    private Long reportType;


    @ApiModelProperty(value = "抄送部门id")
    private String ccDeptId;


    @ApiModelProperty(value = "提交人名")
    private String subUidName;


    @ApiModelProperty(value = "抄送部门主任id")
    private String ccUid;


    @ApiModelProperty(value = "提交部门id")
    private Long subDeptId;


    @ApiModelProperty(value = "评阅人id")
    private String reviewer_uid;


    @ApiModelProperty(value = "提交部门主任id")
    private Long subUid;
    @ApiModelProperty(value = "本周计划")
    private List reportPlanVOList;
    @ApiModelProperty(value = "下周计划")
    private List nextReportPlanVOList;





    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

}