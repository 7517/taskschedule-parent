package com.edison.service.taskschedule.business.report.dao;

import com.edison.saas.common.jpa.BaseDao;

import com.edison.service.taskschedule.business.report.domain.ReportComment;
import org.springframework.stereotype.Repository;


/**
 * 报告评论的数据库操作
 * @author icode
 */
@Repository("reportCommentDao")
public interface ReportCommentDao extends BaseDao<ReportComment, Long>{


}
