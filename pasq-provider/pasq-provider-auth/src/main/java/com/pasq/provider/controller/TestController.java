package com.pasq.provider.controller;

import com.pasq.provider.api.UserFeignApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping
public class TestController {

//    @Value("${auth.json}")
    private String json = "auth";

    @Resource
    private UserFeignApi userFeignApi;

    @GetMapping("/test-auth")
    public String test(){
        String test = userFeignApi.test();
        return json + "+" + test;
    }

}
