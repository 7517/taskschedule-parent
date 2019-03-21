package com.edison.service.taskschedule.client.task;

import com.edison.saas.common.framework.web.controller.PageContent;
import com.edison.saas.common.framework.web.controller.RestResponse;
import com.edison.saas.common.framework.web.data.PageSearchRequest;
import com.edison.service.taskschedule.business.task.dto.TaskAddDto;
import com.edison.service.taskschedule.business.task.dto.TaskCondition;
import com.edison.service.taskschedule.business.task.dto.TaskEditDto;
import com.edison.service.taskschedule.business.task.vo.TaskVO;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;


/**
 * 任务客户端
 * @author icode
 */
@FeignClient(name = "EDISON-TASKSCHEDULE-MICROSERVICE", path = "/taskschedule/task/task", decode404 = true)
public interface TaskClient {

    /**
     * 新增任务
     * @param addDto
     * @return
     */
    @PostMapping
	RestResponse<TaskVO> add(@RequestBody TaskAddDto addDto);


    /**
    * 删除任务
    * @param id
    */
    @DeleteMapping("/{id}")
    RestResponse<TaskVO> delete(@PathVariable("id") String id);

    /**
    * 更新任务
    * @param id
    * @param editDto
    * @return
    */
    @PutMapping("/{id}")
    RestResponse<TaskVO> update(@PathVariable("id") String id, @RequestBody TaskEditDto editDto);

    /**
    * 根据ID查询任务
    * @param id
    * @return
    */
    @GetMapping("/{id}")
    RestResponse<TaskVO> get(@PathVariable("id") String id);

    /**
    * 查询任务列表
    * @param pageSearchRequest
    * @return
    */
    @PostMapping("/list")
    RestResponse<PageContent<TaskVO>> list(@RequestBody PageSearchRequest<TaskCondition> pageSearchRequest);

}
