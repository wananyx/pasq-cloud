package com.pasq.provider.controller;

import com.pasq.provider.api.TestFeignApi;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class TestController {

//    @Value("${user.json}")
    private String json = "user";

    @Resource
    private TestFeignApi testFeignApi;

    @GetMapping("/test-user")
    public String test(){
        String test = testFeignApi.test();
        return json + "+" + test;
    }

    @GetMapping("/test")
    public String t(){
        return "user";
    }
}
