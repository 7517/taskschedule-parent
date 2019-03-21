package com.edison.service.taskschedule;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.edison.saas.common.framework.web.controller.ApiResponseBodyHandler;
import com.edison.saas.common.framework.web.controller.RestApiExceptionHandler;
import com.spring4all.swagger.EnableSwagger2Doc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ExitCodeGenerator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Import({ApiResponseBodyHandler.class, RestApiExceptionHandler.class})
@EnableSwagger2Doc
@SpringBootApplication()
@EnableTransactionManagement
@EnableScheduling
public class EdisonTaskScheduleMicroService implements ExitCodeGenerator {

	public static void main(String[] args) {
		SpringApplication.run(EdisonTaskScheduleMicroService.class, args);

	}

	@Override
	public int getExitCode() {
		return 0;
	}


}