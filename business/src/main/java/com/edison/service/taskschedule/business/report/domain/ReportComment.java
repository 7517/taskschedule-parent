package com.edison.service.taskschedule.business.report.domain;

import com.edison.saas.common.jpa.BaseEntity;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;


/**
 * 报告评论
 * @author icode
 */
@Entity()
@Table(name = "report_report_comment")
//@DynamicUpdate
//@DynamicInsert
//@Where(clause="delete=0")
public class ReportComment extends BaseEntity<Long>{

	public static final String PROPERTY_REPORT_ID = "reportId";
	public static final String PROPERTY_COMMENT_CONTENT = "commentContent";


    @Id
    @Column(name = "id")
    private Long id;


    /**
    * 报告的主键
    * 
    */
    @Column(name = "report_id", nullable = true, updatable = true)
	private Long reportId;

    /**
    * 评论内容
    * 
    */
    @Column(name = "comment_content", nullable = true, updatable = true)
	@Size(max = 255, message = "评论内容超长，最多255个字符")
	private String commentContent;

	public Long getReportId(){
		return reportId;
	}
	public void setReportId(Long reportId) {
		this.reportId = reportId;
	}

	public String getCommentContent(){
		return commentContent;
	}
	public void setCommentContent(String commentContent) {
		this.commentContent = commentContent;
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

