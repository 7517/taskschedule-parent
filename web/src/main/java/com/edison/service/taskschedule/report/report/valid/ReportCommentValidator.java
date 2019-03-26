package com.edison.service.taskschedule.report.report.valid;

import com.edison.saas.common.framework.web.data.PageSearchRequest;

import com.edison.service.report.business.report.dto.ReportCommentAddDto;
import com.edison.service.report.business.report.dto.ReportCommentCondition;
import com.edison.service.taskschedule.business.report.domain.ReportComment;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Service
public class ReportCommentValidator implements Validator {

	/**
	 * 判断支持的JavaBean类型
	 * @param aClass
	 * @return
	 */
	@Override
	public boolean supports(Class<?> aClass) {
		return true;
	}

	/**
	 * 实现Validator中的validate接口
	 * @param obj
	 * @param errors
	 */
	@Override
	public void validate(Object obj, Errors errors) {
	    if(obj instanceof ReportCommentAddDto){
            this.validateReportCommentAddDto((ReportCommentAddDto)obj, errors);
        }
        if(obj instanceof PageSearchRequest){
            this.validateSearchDto((PageSearchRequest)obj, errors);
        }
	}
	
    public void validateSearchDto(PageSearchRequest<ReportCommentCondition> search, Errors errors) {
        if(search.getSearchCondition() == null){
            search.setSearchCondition(new ReportCommentCondition());
        }
    }

	/**
     * 实现Validator中的validate接口
     * @param reportComment 报告评论
     * @param errors
     */
	public void validateReportCommentAddDto(ReportCommentAddDto reportComment, Errors errors) {


		//把校验信息注册到Error的实现类里
		//验证必填

		//验证长度
		if(StringUtils.length(reportComment.getCommentContent()) > 255){
			errors.rejectValue(ReportComment.PROPERTY_COMMENT_CONTENT,null,"评论内容最长255个字符");
		}
	}
}