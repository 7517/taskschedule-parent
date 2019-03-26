package com.edison.service.taskschedule.business.valid;

import com.edison.saas.common.framework.web.data.PageSearchRequest;
import com.edison.service.taskschedule.business.dto.TaskAddDto;
import com.edison.service.taskschedule.business.dto.TaskCondition;
import com.edison.service.taskschedule.business.domain.Task;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Service
public class TaskValidator implements Validator {

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
	    if(obj instanceof TaskAddDto){
            this.validateTaskAddDto((TaskAddDto)obj, errors);
        }
        if(obj instanceof PageSearchRequest){
            this.validateSearchDto((PageSearchRequest)obj, errors);
        }
	}
	
    public void validateSearchDto(PageSearchRequest<TaskCondition> search, Errors errors) {
        if(search.getSearchCondition() == null){
            search.setSearchCondition(new TaskCondition());
        }
    }

	/**
     * 实现Validator中的validate接口
     * @param task 任务
     * @param errors
     */
	public void validateTaskAddDto(TaskAddDto task, Errors errors) {


		//把校验信息注册到Error的实现类里
		//验证必填

		//验证长度
		if(StringUtils.length(task.getRespDeptUid()) > 255){
			errors.rejectValue(Task.PROPERTY_RESP_DEPT_UID,null,"责任部门主任id最长255个字符");
		}
		if(StringUtils.length(task.getAssDeptUid()) > 255){
			errors.rejectValue(Task.PROPERTY_ASS_DEPT_UID,null,"协助部门主任id最长255个字符");
		}
		if(StringUtils.length(task.getTaskVoice()) > 255){
			errors.rejectValue(Task.PROPERTY_TASK_VOICE,null,"任务语音地址最长255个字符");
		}
		if(StringUtils.length(task.getAsstDeptId()) > 255){
			errors.rejectValue(Task.PROPERTY_ASST_DEPT_ID,null,"协助部门最长255个字符");
		}
		if(StringUtils.length(task.getRespDeptId()) > 255){
			errors.rejectValue(Task.PROPERTY_RESP_DEPT_ID,null,"责任部门最长255个字符");
		}
		if(StringUtils.length(task.getTaskDetails()) > 255){
			errors.rejectValue(Task.PROPERTY_TASK_DETAILS,null,"任务详情最长255个字符");
		}
		if(StringUtils.length(task.getRespUid()) > 255){
			errors.rejectValue(Task.PROPERTY_RESP_UID,null,"任务被指派人最长255个字符");
		}

	}
}