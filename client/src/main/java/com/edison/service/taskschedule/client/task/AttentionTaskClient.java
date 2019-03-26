package com.edison.service.taskschedule.client.task;

import com.edison.saas.common.framework.web.controller.PageContent;
import com.edison.saas.common.framework.web.controller.RestResponse;
import com.edison.saas.common.framework.web.data.PageSearchRequest;
import com.edison.service.taskschedule.business.dto.AttentionTaskAddDto;
import com.edison.service.taskschedule.business.dto.AttentionTaskCondition;
import com.edison.service.taskschedule.business.dto.AttentionTaskEditDto;
import com.edison.service.taskschedule.business.vo.AttentionTaskVO;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;


/**
 * 关注的任务客户端
 * @author icode
 */
@FeignClient(name = "EDISON-TASKSCHEDULE-MICROSERVICE", path = "/taskschedule/task/attentiontask", decode404 = true)
public interface AttentionTaskClient {

    /**
     * 新增关注的任务
     * @param addDto
     * @return
     */
    @PostMapping
	RestResponse<AttentionTaskVO> add(@RequestBody AttentionTaskAddDto addDto);


    /**
    * 删除关注的任务
    * @param id
    */
    @DeleteMapping("/{id}")
    RestResponse<AttentionTaskVO> delete(@PathVariable("id") String id);

    /**
    * 更新关注的任务
    * @param id
    * @param editDto
    * @return
    */
    @PutMapping("/{id}")
    RestResponse<AttentionTaskVO> update(@PathVariable("id") String id, @RequestBody AttentionTaskEditDto editDto);

    /**
    * 根据ID查询关注的任务
    * @param id
    * @return
    */
    @GetMapping("/{id}")
    RestResponse<AttentionTaskVO> get(@PathVariable("id") String id);

    /**
    * 查询关注的任务列表
    * @param pageSearchRequest
    * @return
    */
    @PostMapping("/list")
    RestResponse<PageContent<AttentionTaskVO>> list(@RequestBody PageSearchRequest<AttentionTaskCondition> pageSearchRequest);

}
