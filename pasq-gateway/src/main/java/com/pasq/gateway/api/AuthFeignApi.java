package com.pasq.gateway.api;

import com.pasq.gateway.api.hystrix.AuthFeignApiHystrix;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@FeignClient(value = "pasq-provider-auth",fallback = AuthFeignApiHystrix.class)
public interface AuthFeignApi {

    /**
     * 获取access_token
     * 这是spring-security-oauth2底层的接口，类TokenEndpoint
     * @param params
     * @return
     *
     * @see org.springframework.security.oauth2.provider.endpoint.TokenEndpoint
     */
    @PostMapping("/oauth/token")
    Map<String,Object> getAccessToken(@RequestParam Map<String, Object> params);

    @DeleteMapping("remove_token")
    void removeToken(@RequestParam("access_token") String access_token);


}
