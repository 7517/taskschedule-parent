package com.edison.service.taskschedule.client.task;

import com.edison.saas.common.framework.web.controller.PageContent;
import com.edison.saas.common.framework.web.controller.RestResponse;
import com.edison.saas.common.framework.web.data.PageSearchRequest;
import com.edison.schedule.business.schedule.dto.ScheduleRecordAddDto;
import com.edison.schedule.business.schedule.dto.ScheduleRecordCondition;
import com.edison.schedule.business.schedule.dto.ScheduleRecordEditDto;
import com.edison.schedule.business.schedule.vo.ScheduleRecordVO;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;


/**
 * 日程记录客户端
 * @author icode
 */
@FeignClient(name = "EDISON-SCHEDULE-MICROSERVICE", path = "/schedule/schedule/schedulerecord", decode404 = true)
public interface ScheduleRecordClient {

    /**
     * 新增日程记录
     * @param addDto
     * @return
     */
    @PostMapping
	RestResponse<ScheduleRecordVO> add(@RequestBody ScheduleRecordAddDto addDto);


    /**
    * 删除日程记录
    * @param id
    */
    @DeleteMapping("/{id}")
    RestResponse<ScheduleRecordVO> delete(@PathVariable("id") String id);

    /**
    * 更新日程记录
    * @param id
    * @param editDto
    * @return
    */
    @PutMapping("/{id}")
    RestResponse<ScheduleRecordVO> update(@PathVariable("id") String id, @RequestBody ScheduleRecordEditDto editDto);

    /**
    * 根据ID查询日程记录
    * @param id
    * @return
    */
    @GetMapping("/{id}")
    RestResponse<ScheduleRecordVO> get(@PathVariable("id") String id);

    /**
    * 查询日程记录列表
    * @param pageSearchRequest
    * @return
    */
    @PostMapping("/list")
    RestResponse<PageContent<ScheduleRecordVO>> list(@RequestBody PageSearchRequest<ScheduleRecordCondition> pageSearchRequest);

}
