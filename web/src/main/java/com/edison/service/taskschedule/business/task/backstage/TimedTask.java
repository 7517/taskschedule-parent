package com.edison.service.taskschedule.business.task.backstage;


import com.edison.service.taskschedule.business.task.domain.Task;
import com.edison.service.taskschedule.business.task.service.TaskService;
import com.edison.service.taskschedule.business.task.service.TaskprogressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
public class TimedTask {
        @Autowired
        private TaskService taskService;
        @Autowired
        private TaskprogressService taskprogressService;


     /*   @Scheduled(fixedRate = 3000)
        public void test(){
            List<Task> list = taskService.findAll(null);
            Date date =new Date();
            long time = date.getTime();
            for (Task task : list) {
                long time1 = task.getBeginAt().getTime();
                if (time-time1<0){
                    Long taskId = task.getId();
                    taskprogressService.findByTaskId(taskId)
                }

            }


        }
*/

}
