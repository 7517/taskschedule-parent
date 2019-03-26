package com.edison.service.taskschedule.client.task;

import com.edison.saas.common.framework.web.controller.PageContent;
import com.edison.saas.common.framework.web.controller.RestResponse;
import com.edison.saas.common.framework.web.data.PageSearchRequest;
import com.edison.service.taskschedule.business.dto.TaskprogressAddDto;
import com.edison.service.taskschedule.business.dto.TaskprogressCondition;
import com.edison.service.taskschedule.business.dto.TaskprogressEditDto;
import com.edison.service.taskschedule.business.vo.TaskprogressVO;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;


/**
 * 任务进展客户端
 * @author icode
 */
@FeignClient(name = "EDISON-TASKSCHEDULE-MICROSERVICE", path = "/taskschedule/task/taskprogress", decode404 = true)
public interface TaskprogressClient {

    /**
     * 新增任务进展
     * @param addDto
     * @return
     */
    @PostMapping
	RestResponse<TaskprogressVO> add(@RequestBody TaskprogressAddDto addDto);


    /**
    * 删除任务进展
    * @param id
    */
    @DeleteMapping("/{id}")
    RestResponse<TaskprogressVO> delete(@PathVariable("id") String id);

    /**
    * 更新任务进展
    * @param id
    * @param editDto
    * @return
    */
    @PutMapping("/{id}")
    RestResponse<TaskprogressVO> update(@PathVariable("id") String id, @RequestBody TaskprogressEditDto editDto);

    /**
    * 根据ID查询任务进展
    * @param id
    * @return
    */
    @GetMapping("/{id}")
    RestResponse<TaskprogressVO> get(@PathVariable("id") String id);

    /**
    * 查询任务进展列表
    * @param pageSearchRequest
    * @return
    */
    @PostMapping("/list")
    RestResponse<PageContent<TaskprogressVO>> list(@RequestBody PageSearchRequest<TaskprogressCondition> pageSearchRequest);

}
