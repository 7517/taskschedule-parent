package com.edison.service.taskschedule.report.report.valid;

import com.edison.saas.common.framework.web.data.PageSearchRequest;

import com.edison.service.report.business.report.dto.ReportAddDto;
import com.edison.service.report.business.report.dto.ReportCondition;
import com.edison.service.taskschedule.business.report.domain.Report;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Service
public class ReportValidator implements Validator {

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
	    if(obj instanceof ReportAddDto){
            this.validateReportAddDto((ReportAddDto)obj, errors);
        }
        if(obj instanceof PageSearchRequest){
            this.validateSearchDto((PageSearchRequest)obj, errors);
        }
	}
	
    public void validateSearchDto(PageSearchRequest<ReportCondition> search, Errors errors) {
        if(search.getSearchCondition() == null){
            search.setSearchCondition(new ReportCondition());
        }
    }

	/**
     * 实现Validator中的validate接口
     * @param report 报告
     * @param errors
     */
	public void validateReportAddDto(ReportAddDto report, Errors errors) {


		//把校验信息注册到Error的实现类里
		//验证必填

		//验证长度
		if(StringUtils.length(report.getCcUidName()) > 255){
			errors.rejectValue(Report.PROPERTY_CC_UID_NAME,null,"抄送人名最长255个字符");
		}
		if(StringUtils.length(report.getProgressBar()) > 255){
			errors.rejectValue(Report.PROPERTY_PROGRESS_BAR,null,"进度条最长255个字符");
		}
		if(StringUtils.length(report.getReportContent()) > 255){
			errors.rejectValue(Report.PROPERTY_REPORT_CONTENT,null,"报告内容总结最长255个字符");
		}
		if(StringUtils.length(report.getCcDeptId()) > 255){
			errors.rejectValue(Report.PROPERTY_CC_DEPT_ID,null,"抄送部门id最长255个字符");
		}
		if(StringUtils.length(report.getSubUidName()) > 255){
			errors.rejectValue(Report.PROPERTY_SUB_UID_NAME,null,"提交人名最长255个字符");
		}
		if(StringUtils.length(report.getCcUid()) > 255){
			errors.rejectValue(Report.PROPERTY_CC_UID,null,"抄送部门主任id最长255个字符");
		}
		if(StringUtils.length(report.getReviewer_uid()) > 255){
			errors.rejectValue(Report.PROPERTY_REVIEWER_UID,null,"评阅人id最长255个字符");
		}
	}
}