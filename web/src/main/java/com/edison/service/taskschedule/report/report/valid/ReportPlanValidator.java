package com.edison.service.taskschedule.report.report.valid;

import com.edison.saas.common.framework.web.data.PageSearchRequest;

import com.edison.service.report.business.report.dto.ReportPlanAddDto;
import com.edison.service.report.business.report.dto.ReportPlanCondition;
import com.edison.service.taskschedule.business.report.domain.ReportPlan;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Service
public class ReportPlanValidator implements Validator {

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
	    if(obj instanceof ReportPlanAddDto){
            this.validateReportPlanAddDto((ReportPlanAddDto)obj, errors);
        }
        if(obj instanceof PageSearchRequest){
            this.validateSearchDto((PageSearchRequest)obj, errors);
        }
	}
	
    public void validateSearchDto(PageSearchRequest<ReportPlanCondition> search, Errors errors) {
        if(search.getSearchCondition() == null){
            search.setSearchCondition(new ReportPlanCondition());
        }
    }

	/**
     * 实现Validator中的validate接口
     * @param reportPlan 报告计划
     * @param errors
     */
	public void validateReportPlanAddDto(ReportPlanAddDto reportPlan, Errors errors) {


		//把校验信息注册到Error的实现类里
		//验证必填

		//验证长度
		if(StringUtils.length(reportPlan.getProgressBar()) > 255){
			errors.rejectValue(ReportPlan.PROPERTY_PROGRESS_BAR,null,"进度条百分比最长255个字符");
		}
		if(StringUtils.length(reportPlan.getPlanDetails()) > 255){
			errors.rejectValue(ReportPlan.PROPERTY_PLAN_DETAILS,null,"计划内容最长255个字符");
		}
		if(StringUtils.length(reportPlan.getPlanSupplement()) > 255){
			errors.rejectValue(ReportPlan.PROPERTY_PLAN_SUPPLEMENT,null,"计划补充说明最长255个字符");
		}
	}
}