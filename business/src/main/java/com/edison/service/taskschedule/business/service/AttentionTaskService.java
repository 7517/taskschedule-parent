package com.edison.service.taskschedule.business.service;


import com.edison.saas.common.jpa.GenericCrudService;
import com.edison.service.taskschedule.business.dao.AttentionTaskDao;
import com.edison.service.taskschedule.business.dao.AttentionTaskSpecification;
import com.edison.service.taskschedule.business.domain.AttentionTask;
import com.edison.service.taskschedule.business.dto.AttentionTaskCondition;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;


@Service("attentionTaskService")
@Slf4j
public class AttentionTaskService extends GenericCrudService<AttentionTask, Long, AttentionTaskCondition, AttentionTaskDao> {
  
	@Override
	public Specification<AttentionTask> getSpecification(AttentionTaskCondition condition) {
		return new AttentionTaskSpecification(condition);
	}

	public Sort getDefaultSort(){

		Sort sort = new Sort(Sort.Direction.DESC, AttentionTask.PROPERTY_UID);
		return sort;
	}
	//根據用戶id查詢任務
	public List<Long> findTaskId(Long uid){
		List<Long> list = dao.findByUid(uid);
		return list;
	}
}