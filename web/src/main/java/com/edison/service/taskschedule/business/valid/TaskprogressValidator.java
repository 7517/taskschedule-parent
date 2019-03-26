package com.edison.service.taskschedule.business.valid;

import com.edison.saas.common.framework.web.data.PageSearchRequest;
import com.edison.service.taskschedule.business.dto.TaskprogressAddDto;
import com.edison.service.taskschedule.business.dto.TaskprogressCondition;
import com.edison.service.taskschedule.business.domain.Taskprogress;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Service
public class TaskprogressValidator implements Validator {

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
	    if(obj instanceof TaskprogressAddDto){
            this.validateTaskprogressAddDto((TaskprogressAddDto)obj, errors);
        }
        if(obj instanceof PageSearchRequest){
            this.validateSearchDto((PageSearchRequest)obj, errors);
        }
	}
	
    public void validateSearchDto(PageSearchRequest<TaskprogressCondition> search, Errors errors) {
        if(search.getSearchCondition() == null){
            search.setSearchCondition(new TaskprogressCondition());
        }
    }

	/**
     * 实现Validator中的validate接口
     * @param taskprogress 任务进展
     * @param errors
     */
	public void validateTaskprogressAddDto(TaskprogressAddDto taskprogress, Errors errors) {


		//把校验信息注册到Error的实现类里
		//验证必填

		//验证长度
		if(StringUtils.length(taskprogress.getProgressVoice()) > 255){
			errors.rejectValue(Taskprogress.PROPERTY_PROGRESS_VOICE,null,"语音地址最长255个字符");
		}
		if(StringUtils.length(taskprogress.getTaskProgress()) > 255){
			errors.rejectValue(Taskprogress.PROPERTY_TASK_PROGRESS,null,"任务进展最长255个字符");
		}
		if(StringUtils.length(taskprogress.getProgressBar()) > 255){
			errors.rejectValue(Taskprogress.PROPERTY_PROGRESS_BAR,null,"任务进度条最长255个字符");
		}
	}
}