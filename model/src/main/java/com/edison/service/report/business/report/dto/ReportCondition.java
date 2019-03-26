package com.edison.service.report.business.report.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;
import java.util.List;

@ApiModel(value = "查询报告使用的DTO")
@Getter @Setter
public class ReportCondition {

	@ApiModelProperty(value = "结束时间")
	private Date endAt;
	@ApiModelProperty(value = "起始结束时间")
	private Date endAtStart;
	@ApiModelProperty(value = "结束结束时间")
	private Date endAtEnd;
	@ApiModelProperty(value = "开始时间")
	private Date beginAt;
	@ApiModelProperty(value = "起始开始时间")
	private Date beginAtStart;
	@ApiModelProperty(value = "结束开始时间")
	private Date beginAtEnd;
	@ApiModelProperty(value = "抄送人名")
	private String ccUidName;
	@ApiModelProperty(value = "附件id")
	private Long annexId;
	@ApiModelProperty(value = "附件id最大值")
	private Long annexIdMax;
	@ApiModelProperty(value = "附件id最小值")
	private Long annexIdMin;
	@ApiModelProperty(value = "进度条")
	private String progressBar;
	@ApiModelProperty(value = "报告状态")
	private Long reportStatus;
	@ApiModelProperty(value = "报告状态最大值")
	private Long reportStatusMax;
	@ApiModelProperty(value = "报告状态最小值")
	private Long reportStatusMin;
	@ApiModelProperty(value = "报告内容总结")
	private String reportContent;
	@ApiModelProperty(value = "报告类型")
	private Long reportType;
	@ApiModelProperty(value = "报告类型最大值")
	private Long reportTypeMax;
	@ApiModelProperty(value = "报告类型最小值")
	private Long reportTypeMin;
	@ApiModelProperty(value = "抄送部门id")
	private String ccDeptId;
	@ApiModelProperty(value = "提交人名")
	private String subUidName;
	@ApiModelProperty(value = "抄送部门主任id")
	private String ccUid;
	@ApiModelProperty(value = "提交部门id")
	private Long subDeptId;
	@ApiModelProperty(value = "提交部门id最大值")
	private Long subDeptIdMax;
	@ApiModelProperty(value = "提交部门id最小值")
	private Long subDeptIdMin;
	@ApiModelProperty(value = "评阅人id")
	private String reviewer_uid;
	@ApiModelProperty(value = "提交部门主任id")
	private Long subUid;
	@ApiModelProperty(value = "提交部门主任id最大值")
	private Long subUidMax;
	@ApiModelProperty(value = "提交部门主任id最小值")
	private Long subUidMin;

	@ApiModelProperty(value = "用于接收json对象")
	private String searchJson;





	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
