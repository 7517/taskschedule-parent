package com.edison.service.taskschedule.business.service;



import com.edison.saas.common.jpa.GenericCrudService;
import com.edison.service.taskschedule.business.dto.ScheduleRecordCondition;
import com.edison.service.taskschedule.business.dao.ScheduleRecordDao;
import com.edison.service.taskschedule.business.dao.ScheduleRecordSpecification;
import com.edison.service.taskschedule.business.domain.ScheduleRecord;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;


@Service("scheduleRecordService")
@Slf4j
public class ScheduleRecordService  extends GenericCrudService<ScheduleRecord, Long, ScheduleRecordCondition, ScheduleRecordDao> {
  
	@Override
	public Specification<ScheduleRecord> getSpecification(ScheduleRecordCondition condition) {
		return new ScheduleRecordSpecification(condition);
	}

	public Sort getDefaultSort(){

		Sort sort = new Sort(Sort.Direction.DESC, ScheduleRecord.PROPERTY_SCHEDULE_ID);
		return sort;
	}

	public List<ScheduleRecord> findBySchedule(Long scheduleId){
		List<ScheduleRecord> byScheduleId = dao.findByScheduleId(scheduleId);
		return byScheduleId;
	}

	public void deleteByScheduleId(Long id){
		dao.deleteByScheduleId(id);
	}

}