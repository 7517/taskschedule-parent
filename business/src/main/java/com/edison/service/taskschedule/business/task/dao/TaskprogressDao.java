package com.edison.service.taskschedule.business.task.dao;

import com.edison.saas.common.jpa.BaseDao;
import com.edison.service.taskschedule.business.task.domain.Task;
import com.edison.service.taskschedule.business.task.domain.Taskprogress;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * 任务进展的数据库操作
 * @author icode
 */
@Repository("taskprogressDao")
public interface TaskprogressDao extends BaseDao<Taskprogress, Long>{

    List<Taskprogress> findByTaskId(Long taskId);
    List<Taskprogress> findByTaskStatus(Long taskStatus);
    void deleteByTaskId(Long taskId);


    //返回最新一条进展
    @Query(nativeQuery=true,value = "select * from task_taskprogress t where t.task_id = ?1 order by t.create_at desc limit 1")
    Taskprogress findByCreate(Long taskId);

    //返回最新一条进展，包含任务状态
    @Query(nativeQuery = true,value = "select * from task_taskprogress t where t.task_id = ?1 and task_status = ?2 order by t.create_at desc limit 1")
    Taskprogress findByCreateAndStatus(Long taskId,Long taskStatus);
}
