package com.pasq.provider.api.hystrix;

import com.pasq.provider.api.TestFeignApi;
import org.springframework.stereotype.Component;

@Component
public class TestFeignApiHystrix implements TestFeignApi {
    @Override
    public String test() {
        return "testA有点问题";
    }
}
