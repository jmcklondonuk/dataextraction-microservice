/*
 * Developed by Jack McKenzie, MSc(Dist) on 5/27/19 2:14 AM.
 * Last modified 5/27/19 2:11 AM.
 * Copyright (c) 2019. All rights reserved.
 */

package com.embedit.microservice.dataextractor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = "com.embedit")
@SpringBootApplication
@EnableDiscoveryClient
public class DataExtractorApplication {
    public static void main(String[] args) {
        SpringApplication.run(DataExtractorApplication.class, args);
    }
}
