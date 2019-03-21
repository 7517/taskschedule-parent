package  com.edison.service.taskschedule.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan("com.edison.service.taskschedule.client")
@EnableFeignClients("com.edison.service.taskschedule.client")
public class TaskScheduleClientConfig {

}