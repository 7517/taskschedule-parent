package com.edison.service.report.report;

import com.edison.saas.common.framework.web.controller.PageContent;
import com.edison.saas.common.framework.web.controller.RestResponse;
import com.edison.saas.common.framework.web.data.PageSearchRequest;
import com.edison.service.report.business.report.dto.ReportPlanAddDto;
import com.edison.service.report.business.report.dto.ReportPlanCondition;
import com.edison.service.report.business.report.dto.ReportPlanEditDto;
import com.edison.service.report.business.report.vo.ReportPlanVO;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;


/**
 * 报告计划客户端
 * @author icode
 */
@FeignClient(name = "EDISON-REPORT-MICROSERVICE", path = "/report/report/reportplan", decode404 = true)
public interface ReportPlanClient {

    /**
     * 新增报告计划
     * @param addDto
     * @return
     */
    @PostMapping
	RestResponse<ReportPlanVO> add(@RequestBody ReportPlanAddDto addDto);


    /**
    * 删除报告计划
    * @param id
    */
    @DeleteMapping("/{id}")
    RestResponse<ReportPlanVO> delete(@PathVariable("id") String id);

    /**
    * 更新报告计划
    * @param id
    * @param editDto
    * @return
    */
    @PutMapping("/{id}")
    RestResponse<ReportPlanVO> update(@PathVariable("id") String id, @RequestBody ReportPlanEditDto editDto);

    /**
    * 根据ID查询报告计划
    * @param id
    * @return
    */
    @GetMapping("/{id}")
    RestResponse<ReportPlanVO> get(@PathVariable("id") String id);

    /**
    * 查询报告计划列表
    * @param pageSearchRequest
    * @return
    */
    @PostMapping("/list")
    RestResponse<PageContent<ReportPlanVO>> list(@RequestBody PageSearchRequest<ReportPlanCondition> pageSearchRequest);

}
