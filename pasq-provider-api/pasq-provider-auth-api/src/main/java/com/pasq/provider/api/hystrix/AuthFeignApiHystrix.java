package com.pasq.provider.api.hystrix;

import com.pasq.provider.api.AuthFeignApi;
import org.springframework.stereotype.Component;

@Component
public class AuthFeignApiHystrix implements AuthFeignApi {

    @Override
    public String test() {
        return " auth有问题 ";
    }
}
