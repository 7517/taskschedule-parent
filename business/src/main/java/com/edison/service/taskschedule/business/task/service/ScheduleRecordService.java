package com.edison.service.taskschedule.business.task.service;


import com.edison.saas.common.jpa.GenericCrudService;
import com.edison.schedule.business.schedule.dao.ScheduleRecordDao;
import com.edison.schedule.business.schedule.dao.ScheduleRecordSpecification;
import com.edison.schedule.business.schedule.domain.ScheduleRecord;
import com.edison.schedule.business.schedule.dto.ScheduleRecordCondition;
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
		List<ScheduleRecord> list = dao.findByScheduleId(scheduleId);
		return list;
	}

	public void deleteByScheduleId(Long id){
		dao.deleteByScheduleId(id);
	}

}