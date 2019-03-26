package com.edison.service.taskschedule.business.report.domain;

import com.edison.saas.common.jpa.BaseEntity;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Date;


/**
 * 报告计划
 * @author icode
 */
@Entity()
@Table(name = "report_report_plan")
//@DynamicUpdate
//@DynamicInsert
//@Where(clause="delete=0")
public class ReportPlan extends BaseEntity<Long>{

	public static final String PROPERTY_ANNUAL_PLAN_ID = "annualPlanId";
	public static final String PROPERTY_PROGRESS_BAR = "progressBar";
	public static final String PROPERTY_BEGIN_AT = "beginAt";
	public static final String PROPERTY_EXPECTED_AT = "expectedAt";
	public static final String PROPERTY_PLAN_DETAILS = "planDetails";
	public static final String PROPERTY_END_ID = "endId";
	public static final String PROPERTY_PLAN_STATUS = "planStatus";
	public static final String PROPERTY_ANNEX_ID = "annexId";
	public static final String PROPERTY_PLAN_SUPPLEMENT = "planSupplement";
	public static final String PROPERTY_PLAN_TYPE = "planType";
	public static final String PROPERTY_REPORT_ID = "reportId";


    @Id
    @Column(name = "id")
    private Long id;


    /**
    * 年计划主键
    * 
    */
    @Column(name = "annual_plan_id", nullable = true, updatable = true)
	private Long annualPlanId;

    /**
    * 进度条百分比
    * 
    */
    @Column(name = "progress_bar", nullable = true, updatable = true)
	@Size(max = 255, message = "进度条百分比超长，最多255个字符")
	private String progressBar;

    /**
    * 开始时间
    * 
    */
    @Column(name = "begin_at", nullable = true, updatable = true)
	@Temporal(TemporalType.DATE)
	private Date beginAt;

    /**
    * 预计完成时间
    * 
    */
    @Column(name = "expected_at", nullable = true, updatable = true)
	@Temporal(TemporalType.DATE)
	private Date expectedAt;

    /**
    * 计划内容
    * 
    */
    @Column(name = "plan_details", nullable = true, updatable = true)
	@Size(max = 255, message = "计划内容超长，最多255个字符")
	private String planDetails;

    /**
    * 结束时间
    * 
    */
    @Column(name = "end_id", nullable = true, updatable = true)
	@Temporal(TemporalType.DATE)
	private Date endId;

    /**
    * 计划状态
    * 
    */
    @Column(name = "plan_status", nullable = true, updatable = true)
	private Long planStatus;

    /**
    * 附件主键
    * 
    */
    @Column(name = "annex_id", nullable = true, updatable = true)
	private Long annexId;

    /**
    * 计划补充说明
    * 
    */
    @Column(name = "plan_supplement", nullable = true, updatable = true)
	@Size(max = 255, message = "计划补充说明超长，最多255个字符")
	private String planSupplement;

    /**
    * 计划类型（周/年）
    * 
    */
    @Column(name = "plan_type", nullable = true, updatable = true)
	private Long planType;

    /**
    * 报告表主键
    * 
    */
    @Column(name = "report_id", nullable = true, updatable = true)
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


	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}

