package com.edison.service.report.report;

import com.edison.saas.common.framework.web.controller.PageContent;
import com.edison.saas.common.framework.web.controller.RestResponse;
import com.edison.saas.common.framework.web.data.PageSearchRequest;
import com.edison.service.report.business.report.dto.ReportAddDto;
import com.edison.service.report.business.report.dto.ReportCondition;
import com.edison.service.report.business.report.dto.ReportEditDto;
import com.edison.service.report.business.report.vo.ReportVO;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;


/**
 * 报告客户端
 * @author icode
 */
@FeignClient(name = "EDISON-REPORT-MICROSERVICE", path = "/report/report/report", decode404 = true)
public interface ReportClient {

    /**
     * 新增报告
     * @param addDto
     * @return
     */
    @PostMapping
	RestResponse<ReportVO> add(@RequestBody ReportAddDto addDto);


    /**
    * 删除报告
    * @param id
    */
    @DeleteMapping("/{id}")
    RestResponse<ReportVO> delete(@PathVariable("id") String id);

    /**
    * 更新报告
    * @param id
    * @param editDto
    * @return
    */
    @PutMapping("/{id}")
    RestResponse<ReportVO> update(@PathVariable("id") String id, @RequestBody ReportEditDto editDto);

    /**
    * 根据ID查询报告
    * @param id
    * @return
    */
    @GetMapping("/{id}")
    RestResponse<ReportVO> get(@PathVariable("id") String id);

    /**
    * 查询报告列表
    * @param pageSearchRequest
    * @return
    */
    @PostMapping("/list")
    RestResponse<PageContent<ReportVO>> list(@RequestBody PageSearchRequest<ReportCondition> pageSearchRequest);

}
