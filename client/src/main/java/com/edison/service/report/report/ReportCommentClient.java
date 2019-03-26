package com.edison.service.report.report;

import com.edison.saas.common.framework.web.controller.PageContent;
import com.edison.saas.common.framework.web.controller.RestResponse;
import com.edison.saas.common.framework.web.data.PageSearchRequest;
import com.edison.service.report.business.report.dto.ReportCommentAddDto;
import com.edison.service.report.business.report.dto.ReportCommentCondition;
import com.edison.service.report.business.report.dto.ReportCommentEditDto;
import com.edison.service.report.business.report.vo.ReportCommentVO;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;


/**
 * 报告评论客户端
 * @author icode
 */
@FeignClient(name = "EDISON-REPORT-MICROSERVICE", path = "/report/report/reportcomment", decode404 = true)
public interface ReportCommentClient {

    /**
     * 新增报告评论
     * @param addDto
     * @return
     */
    @PostMapping
	RestResponse<ReportCommentVO> add(@RequestBody ReportCommentAddDto addDto);


    /**
    * 删除报告评论
    * @param id
    */
    @DeleteMapping("/{id}")
    RestResponse<ReportCommentVO> delete(@PathVariable("id") String id);

    /**
    * 更新报告评论
    * @param id
    * @param editDto
    * @return
    */
    @PutMapping("/{id}")
    RestResponse<ReportCommentVO> update(@PathVariable("id") String id, @RequestBody ReportCommentEditDto editDto);

    /**
    * 根据ID查询报告评论
    * @param id
    * @return
    */
    @GetMapping("/{id}")
    RestResponse<ReportCommentVO> get(@PathVariable("id") String id);

    /**
    * 查询报告评论列表
    * @param pageSearchRequest
    * @return
    */
    @PostMapping("/list")
    RestResponse<PageContent<ReportCommentVO>> list(@RequestBody PageSearchRequest<ReportCommentCondition> pageSearchRequest);

}
