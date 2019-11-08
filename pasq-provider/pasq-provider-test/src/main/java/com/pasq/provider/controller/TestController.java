package com.pasq.provider.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class TestController {

//    @Value("${test.json}")
    private String json = "test";

    @GetMapping("/test-A")
    public String test(){
        return "+"+json;
    }
}
