package com.edison.service.taskschedule.business.dao;

import com.edison.saas.common.jpa.BaseDao;

import com.edison.service.taskschedule.business.domain.Schedule;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;


/**
 * 日程的数据库操作
 * @author icode
 */
@Repository("scheduleDao")
public interface ScheduleDao extends BaseDao<Schedule, Long>{

    @Query("from Schedule where beginAt > ?1 and beginAt <?2 and ( designeeUid like concat('%',?3,'%') or designeeDeptUid like concat('%',?3,'%') or createUid like concat('%',?3,'%'))")
    List<Schedule> findByBeginAt(Timestamp timestamp1, Timestamp timestamp2, String uid);
    Schedule findById(Long id);

}
