package com.edison.service.taskschedule.business.valid;

import com.edison.saas.common.framework.web.data.PageSearchRequest;
import com.edison.service.taskschedule.business.dto.AttentionTaskAddDto;
import com.edison.service.taskschedule.business.dto.AttentionTaskCondition;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Service
public class AttentionTaskValidator implements Validator {

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
	    if(obj instanceof AttentionTaskAddDto){
            this.validateAttentionTaskAddDto((AttentionTaskAddDto)obj, errors);
        }
        if(obj instanceof PageSearchRequest){
            this.validateSearchDto((PageSearchRequest)obj, errors);
        }
	}
	
    public void validateSearchDto(PageSearchRequest<AttentionTaskCondition> search, Errors errors) {
        if(search.getSearchCondition() == null){
            search.setSearchCondition(new AttentionTaskCondition());
        }
    }

	/**
     * 实现Validator中的validate接口
     * @param attentionTask 关注的任务
     * @param errors
     */
	public void validateAttentionTaskAddDto(AttentionTaskAddDto attentionTask, Errors errors) {


		//把校验信息注册到Error的实现类里
		//验证必填

		//验证长度
	}
}