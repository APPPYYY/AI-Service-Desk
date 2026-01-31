package com.sb.ai.servicedesk;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class ServiceDeskAiApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceDeskAiApplication.class, args);
    }

}
