package com.edison.service.taskschedule.business.dao;

import com.edison.saas.common.jpa.BaseDao;
import com.edison.service.taskschedule.business.domain.AttentionTask;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * 关注的任务的数据库操作
 * @author icode
 */
@Repository("attentionTaskDao")
public interface AttentionTaskDao extends BaseDao<AttentionTask, Long>{
        List<Long> findByUid(Long uid);

}
