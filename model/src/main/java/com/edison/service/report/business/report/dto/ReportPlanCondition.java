package com.edison.service.report.business.report.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

@ApiModel(value = "查询报告计划使用的DTO")
@Getter @Setter
public class ReportPlanCondition {

	@ApiModelProperty(value = "年计划主键")
	private Long annualPlanId;
	@ApiModelProperty(value = "年计划主键最大值")
	private Long annualPlanIdMax;
	@ApiModelProperty(value = "年计划主键最小值")
	private Long annualPlanIdMin;
	@ApiModelProperty(value = "进度条百分比")
	private String progressBar;
	@ApiModelProperty(value = "开始时间")
	private Date beginAt;
	@ApiModelProperty(value = "起始开始时间")
	private Date beginAtStart;
	@ApiModelProperty(value = "结束开始时间")
	private Date beginAtEnd;
	@ApiModelProperty(value = "预计完成时间")
	private Date expectedAt;
	@ApiModelProperty(value = "起始预计完成时间")
	private Date expectedAtStart;
	@ApiModelProperty(value = "结束预计完成时间")
	private Date expectedAtEnd;
	@ApiModelProperty(value = "计划内容")
	private String planDetails;
	@ApiModelProperty(value = "结束时间")
	private Date endId;
	@ApiModelProperty(value = "起始结束时间")
	private Date endIdStart;
	@ApiModelProperty(value = "结束结束时间")
	private Date endIdEnd;
	@ApiModelProperty(value = "计划状态")
	private Long planStatus;
	@ApiModelProperty(value = "计划状态最大值")
	private Long planStatusMax;
	@ApiModelProperty(value = "计划状态最小值")
	private Long planStatusMin;
	@ApiModelProperty(value = "附件主键")
	private Long annexId;
	@ApiModelProperty(value = "附件主键最大值")
	private Long annexIdMax;
	@ApiModelProperty(value = "附件主键最小值")
	private Long annexIdMin;
	@ApiModelProperty(value = "计划补充说明")
	private String planSupplement;
	@ApiModelProperty(value = "计划类型（周/年）")
	private Long planType;
	@ApiModelProperty(value = "计划类型（周/年）最大值")
	private Long planTypeMax;
	@ApiModelProperty(value = "计划类型（周/年）最小值")
	private Long planTypeMin;
	@ApiModelProperty(value = "报告表主键")
	private Long reportId;
	@ApiModelProperty(value = "报告表主键最大值")
	private Long reportIdMax;
	@ApiModelProperty(value = "报告表主键最小值")
	private Long reportIdMin;





	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
