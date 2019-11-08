package com.pasq.gateway.api.hystrix;

import com.pasq.gateway.api.AuthFeignApi;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class AuthFeignApiHystrix implements AuthFeignApi {
    @Override
    public Map<String, Object> getAccessToken(Map<String, Object> params) {
        return null;
    }

    @Override
    public void removeToken(String access_token) {

    }
}
