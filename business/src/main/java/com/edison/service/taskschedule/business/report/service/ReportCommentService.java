package com.edison.service.taskschedule.business.report.service;


import com.edison.saas.common.jpa.GenericCrudService;
import com.edison.service.report.business.report.dto.ReportCommentCondition;
import com.edison.service.taskschedule.business.report.dao.ReportCommentDao;
import com.edison.service.taskschedule.business.report.dao.ReportCommentSpecification;
import com.edison.service.taskschedule.business.report.domain.ReportComment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;


@Service("reportCommentService")
@Slf4j
public class ReportCommentService  extends GenericCrudService<ReportComment, Long, ReportCommentCondition, ReportCommentDao> {
  
	@Override
	public Specification<ReportComment> getSpecification(ReportCommentCondition condition) {
		return new ReportCommentSpecification(condition);
	}

	public Sort getDefaultSort(){

		Sort sort = new Sort(Sort.Direction.DESC, ReportComment.PROPERTY_REPORT_ID);
		return sort;
	}
}