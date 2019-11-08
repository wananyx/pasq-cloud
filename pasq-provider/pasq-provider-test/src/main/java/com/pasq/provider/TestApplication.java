package com.pasq.provider;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableHystrix
@EnableFeignClients
@SpringCloudApplication
public class TestApplication {
    public static void main(String[] args) {
        SpringApplication.run(TestApplication.class,args);
    }
}
