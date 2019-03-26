package com.edison.service.taskschedule.business.valid;

import com.edison.saas.common.framework.web.data.PageSearchRequest;

import com.edison.service.taskschedule.business.dto.ScheduleRecordAddDto;
import com.edison.service.taskschedule.business.dto.ScheduleRecordCondition;
import com.edison.service.taskschedule.business.domain.ScheduleRecord;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Service
public class ScheduleRecordValidator implements Validator {

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
	    if(obj instanceof ScheduleRecordAddDto){
            this.validateScheduleRecordAddDto((ScheduleRecordAddDto)obj, errors);
        }
        if(obj instanceof PageSearchRequest){
            this.validateSearchDto((PageSearchRequest)obj, errors);
        }
	}
	
    public void validateSearchDto(PageSearchRequest<ScheduleRecordCondition> search, Errors errors) {
        if(search.getSearchCondition() == null){
            search.setSearchCondition(new ScheduleRecordCondition());
        }
    }

	/**
     * 实现Validator中的validate接口
     * @param scheduleRecord 日程记录
     * @param errors
     */
	public void validateScheduleRecordAddDto(ScheduleRecordAddDto scheduleRecord, Errors errors) {


		//把校验信息注册到Error的实现类里
		//验证必填

		//验证长度
		if(StringUtils.length(scheduleRecord.getScheduleRecordText()) > 255){
			errors.rejectValue(ScheduleRecord.PROPERTY_SCHEDULE_RECORD_TEXT,null,"日程文本信息最长255个字符");
		}
		if(StringUtils.length(scheduleRecord.getScheduleRecordVoice()) > 255){
			errors.rejectValue(ScheduleRecord.PROPERTY_SCHEDULE_RECORD_VOICE,null,"日程语音地址最长255个字符");
		}
	}
}