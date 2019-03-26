package com.edison.service.taskschedule.business.report.domain;

import com.edison.saas.common.jpa.BaseEntity;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Date;


/**
 * 报告
 * @author icode
 */
@Entity()
@Table(name = "report_report")
//@DynamicUpdate
//@DynamicInsert
//@Where(clause="delete=0")
public class Report extends BaseEntity<Long>{

	public static final String PROPERTY_END_AT = "endAt";
	public static final String PROPERTY_BEGIN_AT = "beginAt";
	public static final String PROPERTY_CC_UID_NAME = "ccUidName";
	public static final String PROPERTY_ANNEX_ID = "annexId";
	public static final String PROPERTY_PROGRESS_BAR = "progressBar";
	public static final String PROPERTY_REPORT_STATUS = "reportStatus";
	public static final String PROPERTY_REPORT_CONTENT = "reportContent";
	public static final String PROPERTY_REPORT_TYPE = "reportType";
	public static final String PROPERTY_CC_DEPT_ID = "ccDeptId";
	public static final String PROPERTY_SUB_UID_NAME = "subUidName";
	public static final String PROPERTY_CC_UID = "ccUid";
	public static final String PROPERTY_SUB_DEPT_ID = "subDeptId";
	public static final String PROPERTY_REVIEWER_UID = "reviewerUid";
	public static final String PROPERTY_SUB_UID = "subUid";


    @Id
    @Column(name = "id")
    private Long id;


    /**
    * 结束时间
    * 
    */
    @Column(name = "end_at", nullable = true, updatable = true)
	@Temporal(TemporalType.DATE)
	private Date endAt;

    /**
    * 开始时间
    * 
    */
    @Column(name = "begin_at", nullable = true, updatable = true)
	@Temporal(TemporalType.DATE)
	private Date beginAt;

    /**
    * 抄送人名
    * 
    */
    @Column(name = "cc_uid_name", nullable = true, updatable = true)
	@Size(max = 255, message = "抄送人名超长，最多255个字符")
	private String ccUidName;

    /**
    * 附件id
    * 
    */
    @Column(name = "annex_id", nullable = true, updatable = true)
	private Long annexId;

    /**
    * 进度条
    * 
    */
    @Column(name = "progress_bar", nullable = true, updatable = true)
	@Size(max = 255, message = "进度条超长，最多255个字符")
	private String progressBar;

    /**
    * 报告状态
    * 
    */
    @Column(name = "report_status", nullable = true, updatable = true)
	private Long reportStatus;

    /**
    * 报告内容总结
    * 
    */
    @Column(name = "report_content", nullable = true, updatable = true)
	@Size(max = 255, message = "报告内容总结超长，最多255个字符")
	private String reportContent;

    /**
    * 报告类型
    * 
    */
    @Column(name = "report_type", nullable = true, updatable = true)
	private Long reportType;

    /**
    * 抄送部门id
    * 
    */
    @Column(name = "cc_dept_id", nullable = true, updatable = true)
	@Size(max = 255, message = "抄送部门id超长，最多255个字符")
	private String ccDeptId;

    /**
    * 提交人名
    * 
    */
    @Column(name = "sub_uid_name", nullable = true, updatable = true)
	@Size(max = 255, message = "提交人名超长，最多255个字符")
	private String subUidName;

    /**
    * 抄送部门主任id
    * 
    */
    @Column(name = "cc_uid", nullable = true, updatable = true)
	@Size(max = 255, message = "抄送部门主任id超长，最多255个字符")
	private String ccUid;

    /**
    * 提交部门id
    * 
    */
    @Column(name = "sub_dept_id", nullable = true, updatable = true)
	private Long subDeptId;

    /**
    * 评阅人id
    * 
    */
    @Column(name = "reviewer_uid", nullable = true, updatable = true)
	@Size(max = 255, message = "评阅人id超长，最多255个字符")
	private String reviewerUid;

    /**
    * 提交部门主任id
    * 
    */
    @Column(name = "sub_uid", nullable = true, updatable = true)
	private Long subUid;

	public Date getEndAt(){
		return endAt;
	}
	public void setEndAt(Date endAt) {
		this.endAt = endAt;
	}

	public Date getBeginAt(){
		return beginAt;
	}
	public void setBeginAt(Date beginAt) {
		this.beginAt = beginAt;
	}

	public String getCcUidName(){
		return ccUidName;
	}
	public void setCcUidName(String ccUidName) {
		this.ccUidName = ccUidName;
	}

	public Long getAnnexId(){
		return annexId;
	}
	public void setAnnexId(Long annexId) {
		this.annexId = annexId;
	}

	public String getProgressBar(){
		return progressBar;
	}
	public void setProgressBar(String progressBar) {
		this.progressBar = progressBar;
	}

	public Long getReportStatus(){
		return reportStatus;
	}
	public void setReportStatus(Long reportStatus) {
		this.reportStatus = reportStatus;
	}

	public String getReportContent(){
		return reportContent;
	}
	public void setReportContent(String reportContent) {
		this.reportContent = reportContent;
	}

	public Long getReportType(){
		return reportType;
	}
	public void setReportType(Long reportType) {
		this.reportType = reportType;
	}

	public String getCcDeptId(){
		return ccDeptId;
	}
	public void setCcDeptId(String ccDeptId) {
		this.ccDeptId = ccDeptId;
	}

	public String getSubUidName(){
		return subUidName;
	}
	public void setSubUidName(String subUidName) {
		this.subUidName = subUidName;
	}

	public String getCcUid(){
		return ccUid;
	}
	public void setCcUid(String ccUid) {
		this.ccUid = ccUid;
	}

	public Long getSubDeptId(){
		return subDeptId;
	}
	public void setSubDeptId(Long subDeptId) {
		this.subDeptId = subDeptId;
	}


	public String getReviewerUid() {
		return reviewerUid;
	}

	public void setReviewerUid(String reviewerUid) {
		this.reviewerUid = reviewerUid;
	}

	public Long getSubUid(){
		return subUid;
	}
	public void setSubUid(Long subUid) {
		this.subUid = subUid;
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

