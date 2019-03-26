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
 * 报告
 * @author icode
 */
@ApiModel(value = "修改报告使用的DTO")
@Setter @Getter
public class ReportEditDto {


	/**结束时间*/
	@ApiModelProperty(value = "结束时间", required = false)
	@Temporal(TemporalType.DATE)
	private Date endAt;


	/**开始时间*/
	@ApiModelProperty(value = "开始时间", required = false)
	@Temporal(TemporalType.DATE)
	private Date beginAt;


	/**抄送人名*/
	@ApiModelProperty(value = "抄送人名", required = false)
	private String ccUidName;


	/**附件id*/
	@ApiModelProperty(value = "附件id", required = false)
	private Long annexId;


	/**进度条*/
	@ApiModelProperty(value = "进度条", required = false)
	private String progressBar;


	/**报告状态*/
	@ApiModelProperty(value = "报告状态", required = false)
	private Long reportStatus;


	/**报告内容总结*/
	@ApiModelProperty(value = "报告内容总结", required = false)
	private String reportContent;


	/**报告类型*/
	@ApiModelProperty(value = "报告类型", required = false)
	private Long reportType;


	/**抄送部门id*/
	@ApiModelProperty(value = "抄送部门id", required = false)
	private String ccDeptId;


	/**提交人名*/
	@ApiModelProperty(value = "提交人名", required = false)
	private String subUidName;


	/**抄送部门主任id*/
	@ApiModelProperty(value = "抄送部门主任id", required = false)
	private String ccUid;


	/**提交部门id*/
	@ApiModelProperty(value = "提交部门id", required = false)
	private Long subDeptId;


	/**评阅人id*/
	@ApiModelProperty(value = "评阅人id", required = false)
	private String reviewer_uid;


	/**提交部门主任id*/
	@ApiModelProperty(value = "提交部门主任id", required = false)
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


	public String getReviewer_uid(){
		return reviewer_uid;
	}
	public void setReviewer_uid(String reviewer_uid) {
		this.reviewer_uid = reviewer_uid;
	}


	public Long getSubUid(){
		return subUid;
	}
	public void setSubUid(Long subUid) {
		this.subUid = subUid;
	}


	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
