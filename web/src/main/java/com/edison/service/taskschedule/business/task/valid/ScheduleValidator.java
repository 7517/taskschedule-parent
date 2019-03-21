package com.edison.service.taskschedule.business.task.valid;

import com.edison.saas.common.framework.web.data.PageSearchRequest;
import com.edison.schedule.business.schedule.domain.Schedule;
import com.edison.schedule.business.schedule.dto.ScheduleAddDto;
import com.edison.schedule.business.schedule.dto.ScheduleCondition;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Service
public class ScheduleValidator implements Validator {

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
	    if(obj instanceof ScheduleAddDto){
            this.validateScheduleAddDto((ScheduleAddDto)obj, errors);
        }
        if(obj instanceof PageSearchRequest){
            this.validateSearchDto((PageSearchRequest)obj, errors);
        }
	}
	
    public void validateSearchDto(PageSearchRequest<ScheduleCondition> search, Errors errors) {
        if(search.getSearchCondition() == null){
            search.setSearchCondition(new ScheduleCondition());
        }
    }

	/**
     * 实现Validator中的validate接口
     * @param schedule 日程
     * @param errors
     */
	public void validateScheduleAddDto(ScheduleAddDto schedule, Errors errors) {


		//把校验信息注册到Error的实现类里
		//验证必填

		//验证长度
		if(StringUtils.length(schedule.getScheduleDetails()) > 255){
			errors.rejectValue(Schedule.PROPERTY_SCHEDULE_DETAILS,null,"日程详情最长255个字符");
		}
		if(StringUtils.length(schedule.getDesigneeDeptUid()) > 255){
			errors.rejectValue(Schedule.PROPERTY_DESIGNEE_DEPT_UID,null,"被指派部门主任最长255个字符");
		}
		if(StringUtils.length(schedule.getDesigneeUid()) > 255){
			errors.rejectValue(Schedule.PROPERTY_DESIGNEE_UID,null,"被指派人最长255个字符");
		}
		if(StringUtils.length(schedule.getDesigneeDeptId()) > 255){
			errors.rejectValue(Schedule.PROPERTY_DESIGNEE_DEPT_ID,null,"被指派人部门最长255个字符");
		}
		if(StringUtils.length(schedule.getScheduleTitle()) > 255){
			errors.rejectValue(Schedule.PROPERTY_SCHEDULE_TITLE,null,"日程标题最长255个字符");
		}
	}
}