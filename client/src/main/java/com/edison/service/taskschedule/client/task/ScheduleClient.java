package com.edison.service.taskschedule.client.task;

import com.edison.saas.common.framework.web.controller.PageContent;
import com.edison.saas.common.framework.web.controller.RestResponse;
import com.edison.saas.common.framework.web.data.PageSearchRequest;

import com.edison.service.taskschedule.business.dto.ScheduleAddDto;
import com.edison.service.taskschedule.business.dto.ScheduleCondition;
import com.edison.service.taskschedule.business.dto.ScheduleEditDto;
import com.edison.service.taskschedule.business.vo.ScheduleVO;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;


/**
 * 日程客户端
 * @author icode
 */
@FeignClient(name = "EDISON-SCHEDULE-MICROSERVICE", path = "/schedule/schedule/schedule", decode404 = true)
public interface ScheduleClient {

    /**
     * 新增日程
     * @param addDto
     * @return
     */
    @PostMapping
	RestResponse<ScheduleVO> add(@RequestBody ScheduleAddDto addDto);


    /**
    * 删除日程
    * @param id
    */
    @DeleteMapping("/{id}")
    RestResponse<ScheduleVO> delete(@PathVariable("id") String id);

    /**
    * 更新日程
    * @param id
    * @param editDto
    * @return
    */
    @PutMapping("/{id}")
    RestResponse<ScheduleVO> update(@PathVariable("id") String id, @RequestBody ScheduleEditDto editDto);

    /**
    * 根据ID查询日程
    * @param id
    * @return
    */
    @GetMapping("/{id}")
    RestResponse<ScheduleVO> get(@PathVariable("id") String id);

    /**
    * 查询日程列表
    * @param pageSearchRequest
    * @return
    */
    @PostMapping("/list")
    RestResponse<PageContent<ScheduleVO>> list(@RequestBody PageSearchRequest<ScheduleCondition> pageSearchRequest);

}
