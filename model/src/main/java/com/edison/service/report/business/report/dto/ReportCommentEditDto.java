package com.edison.service.report.business.report.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;


/**
 * 报告评论
 * @author icode
 */
@ApiModel(value = "修改报告评论使用的DTO")
@Setter @Getter
public class ReportCommentEditDto {


	/**报告的主键*/
	@ApiModelProperty(value = "报告的主键", required = false)
	private Long reportId;


	/**评论内容*/
	@ApiModelProperty(value = "评论内容", required = false)
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


	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
