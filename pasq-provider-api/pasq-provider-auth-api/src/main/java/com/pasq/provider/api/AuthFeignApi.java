package com.pasq.provider.api;

import com.pasq.provider.api.hystrix.AuthFeignApiHystrix;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@FeignClient(value = "pasq-provider-auth",fallback = AuthFeignApiHystrix.class)
public interface AuthFeignApi {

    @GetMapping("/test-auth")
    String test();

}
