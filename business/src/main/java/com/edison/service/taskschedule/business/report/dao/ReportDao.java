package com.edison.service.taskschedule.business.report.dao;

import com.edison.saas.common.jpa.BaseDao;

import com.edison.service.taskschedule.business.report.domain.Report;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Date;
import java.util.List;


/**
 * 报告的数据库操作
 * @author icode
 */
@Repository("reportDao")
public interface ReportDao extends BaseDao<Report, Long>{
    @Query(" from Report where beginAt = ?1 and endAt = ?2 and createUid = ?3 and reportType = ?4 and reportType=?5 ")
    List<Report> findAllByDate(Date start,Date end,Long uid,Long type,Long deptId);

    @Query("from Report where beginAt = ?1 and endAt = ?2 and reportType = ?4")
    List<Report> findAllWeekReport(Date start,Date end,Long type);


    List<Report> findBySubUidAndReportTypeAndSubDeptId(Long uid,Long type,Long deptId);


    List<Report> findBySubUidAndReportType(Long uid,Long type);


    @Query("from Report where beginAt>?1 and endAt <?2 and subDeptId = ?3 ")
    List<Report> findDateReport(Date begin,Date end,Long deptId);

    List<Report> findByReportType(Long type);

}
