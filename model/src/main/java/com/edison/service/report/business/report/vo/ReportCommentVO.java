package com.edison.service.report.business.report.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;


/**
* 报告评论的值对象
*/
@ApiModel(value = "展现报告评论的值对象")
@Setter @Getter
public class ReportCommentVO {

    @ApiModelProperty(value = "记录id")
    private Long id;


    @ApiModelProperty(value = "报告的主键")
    private Long reportId;


    @ApiModelProperty(value = "评论内容")
    private String commentContent;




    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

}