package com.edison.service.report.config;

import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("com.edison.service.report.client")
@EnableFeignClients("com.edison.service.report.client.")
public class ReportClientConfig {

}