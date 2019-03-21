package com.edison.service.taskschedule.business.task.dao;

import com.edison.saas.common.jpa.BaseDao;
import com.edison.schedule.business.schedule.domain.ScheduleRecord;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * 日程记录的数据库操作
 * @author icode
 */
@Repository("scheduleRecordDao")
public interface ScheduleRecordDao extends BaseDao<ScheduleRecord, Long>{

    List<ScheduleRecord> findByScheduleId(Long scheduleId);
    void deleteByScheduleId(Long id);

}
