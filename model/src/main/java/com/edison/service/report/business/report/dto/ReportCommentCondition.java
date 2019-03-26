package com.edison.service.report.business.report.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

@ApiModel(value = "查询报告评论使用的DTO")
@Getter @Setter
public class ReportCommentCondition {

	@ApiModelProperty(value = "报告的主键")
	private Long reportId;
	@ApiModelProperty(value = "报告的主键最大值")
	private Long reportIdMax;
	@ApiModelProperty(value = "报告的主键最小值")
	private Long reportIdMin;
	@ApiModelProperty(value = "评论内容")
	private String commentContent;





	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
