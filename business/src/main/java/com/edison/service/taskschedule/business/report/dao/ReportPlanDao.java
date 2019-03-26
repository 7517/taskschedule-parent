package com.edison.service.taskschedule.business.report.dao;

import com.edison.saas.common.jpa.BaseDao;
import com.edison.service.taskschedule.business.report.domain.ReportPlan;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;


/**
 * 报告计划的数据库操作
 * @author icode
 */
@Repository("reportPlanDao")
public interface ReportPlanDao extends BaseDao<ReportPlan, Long>{

    @Query(" from ReportPlan where beginAt =?1 and endId =?2")
        List<ReportPlan> getReportPlan(Date begin,Date end);

    @Query("from ReportPlan where beginAt = ?1 and endId =?2 and createUid =?3")
    void deleteAllByDate(Date begin,Date end,Long uid);

    List<ReportPlan> findByAnnualPlanIdAndPlanTypeOrderByBeginAtDesc(Long planId,Long type);

    List<ReportPlan> findByReportIdAndPlanType(Long reportId,Long type);


    List<ReportPlan> findByReportId(Long reportId);
}
