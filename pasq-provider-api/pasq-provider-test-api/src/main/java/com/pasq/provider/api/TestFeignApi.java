package com.pasq.provider.api;

import com.pasq.provider.api.hystrix.TestFeignApiHystrix;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(value = "pasq-provider-test",fallback = TestFeignApiHystrix.class)
public interface TestFeignApi {

    @GetMapping("/test-A")
    String test();
}
