package com.edison.service.report.business.report.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;


/**
 * 报告计划
 * @author icode
 */
@ApiModel(value = "新增报告计划使用的DTO")
@Setter @Getter
public class ReportPlanAddDto {

    /**年计划主键*/
	@ApiModelProperty(value = "年计划主键", required = false)
	private Long annualPlanId;

    /**进度条百分比*/
	@ApiModelProperty(value = "进度条百分比", required = false)
	private String progressBar;

    /**开始时间*/
	@ApiModelProperty(value = "开始时间", required = false)
	@Temporal(TemporalType.DATE)
	private Date beginAt;

    /**预计完成时间*/
	@ApiModelProperty(value = "预计完成时间", required = false)
	@Temporal(TemporalType.DATE)
	private Date expectedAt;

    /**计划内容*/
	@ApiModelProperty(value = "计划内容", required = false)
	private String planDetails;

    /**结束时间*/
	@ApiModelProperty(value = "结束时间", required = false)
	@Temporal(TemporalType.DATE)
	private Date endId;

    /**计划状态*/
	@ApiModelProperty(value = "计划状态", required = false)
	private Long planStatus;

    /**附件主键*/
	@ApiModelProperty(value = "附件主键", required = false)
	private Long annexId;

    /**计划补充说明*/
	@ApiModelProperty(value = "计划补充说明", required = false)
	private String planSupplement;

    /**计划类型（周/年）*/
	@ApiModelProperty(value = "计划类型（周/年）", required = false)
	private Long planType;

    /**报告表主键*/
	@ApiModelProperty(value = "报告表主键", required = false)
	private Long reportId;


	public Long getAnnualPlanId(){
		return annualPlanId;
	}
	public void setAnnualPlanId(Long annualPlanId) {
		this.annualPlanId = annualPlanId;
	}

	public String getProgressBar(){
		return progressBar;
	}
	public void setProgressBar(String progressBar) {
		this.progressBar = progressBar;
	}

	public Date getBeginAt(){
		return beginAt;
	}
	public void setBeginAt(Date beginAt) {
		this.beginAt = beginAt;
	}

	public Date getExpectedAt(){
		return expectedAt;
	}
	public void setExpectedAt(Date expectedAt) {
		this.expectedAt = expectedAt;
	}

	public String getPlanDetails(){
		return planDetails;
	}
	public void setPlanDetails(String planDetails) {
		this.planDetails = planDetails;
	}

	public Date getEndId(){
		return endId;
	}
	public void setEndId(Date endId) {
		this.endId = endId;
	}

	public Long getPlanStatus(){
		return planStatus;
	}
	public void setPlanStatus(Long planStatus) {
		this.planStatus = planStatus;
	}

	public Long getAnnexId(){
		return annexId;
	}
	public void setAnnexId(Long annexId) {
		this.annexId = annexId;
	}

	public String getPlanSupplement(){
		return planSupplement;
	}
	public void setPlanSupplement(String planSupplement) {
		this.planSupplement = planSupplement;
	}

	public Long getPlanType(){
		return planType;
	}
	public void setPlanType(Long planType) {
		this.planType = planType;
	}

	public Long getReportId(){
		return reportId;
	}
	public void setReportId(Long reportId) {
		this.reportId = reportId;
	}


	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
