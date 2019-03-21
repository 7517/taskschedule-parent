package com.edison.service.taskschedule.business.task.dao;

import com.edison.saas.common.jpa.BaseDao;
import com.edison.service.taskschedule.business.task.domain.Task;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;


/**
 * 任务的数据库操作
 * @author icode
 */
@Repository("taskDao")
public interface TaskDao extends BaseDao<Task, Long>{
    @Query("from Task where beginAt > ?1 and beginAt <?2 and ( respDeptUid like concat('%',?3,'%') or assDeptUid like concat('%',?3,'%') or respUid like concat('%',?3,'%'))")
    List<Task> findByBeginAt(Timestamp timestamp1,Timestamp timestamp2,String uid);
    Task findById(Long id);

    void deleteById(Long id);

    Task findByIdAndRespUid(Long id,Long respUid);
    @Query(" from Task where createUid like concat('%',?1,'%')")
    List<Task> findByCreateUid(Long uid);



    @Query(" from Task where respDeptUid like concat('%',?1,'%') or assDeptUid like concat('%',?1,'%') or respUid like concat('%',?1,'%') ")
    List<Task> findMyTask(String uid);


}
