package com.pasq.gateway.controller;

import com.pasq.gateway.api.AuthFeignApi;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.util.OAuth2Utils;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: JST
 * @Date: 2019/4/27 14:33
 */
@Slf4j
@RestController
@RequestMapping
public class TokenController {

    @Resource
    private AuthFeignApi authFeignApi;

    /**
     * 用户名密码登录
     * @param username
     * @param password
     * @return access_token和refresh_token
     * 生成的access_token可去 https://jwt.io/ 进行解码
     */
    @PostMapping("/sys/login")
    public Map<String,Object> login(String username,String password){
        Map<String, Object> params = new HashMap<>();
        params.put("username",username);
        params.put("password",password);
        params.put(OAuth2Utils.GRANT_TYPE,"password");
        params.put(OAuth2Utils.CLIENT_ID,"clientId");
        params.put("client_secret","secret");
        params.put(OAuth2Utils.SCOPE,"app");

        Map<String, Object> tokenInfo = authFeignApi.getAccessToken(params);
//        saveLoginLog(username, "用户名密码登陆");
        return tokenInfo;
    }

    /**
     * 用refresh_token刷新token
     * @param refresh_token
     * @return
     */
    @PostMapping("/sys/refresh")
    public Map<String,Object> refreshToken(String refresh_token){
        Map<String, Object> params = new HashMap<>();
        params.put(OAuth2Utils.GRANT_TYPE,"refresh_token");
        params.put(OAuth2Utils.CLIENT_ID,"system");
        params.put("client_secret","system");
        params.put(OAuth2Utils.SCOPE,"app");
        params.put("refresh_token",refresh_token);

        return authFeignApi.getAccessToken(params);
    }

    /**
     * 退出登陆
     * @param access_token
     * @param token
     */
    @GetMapping("/sys/logout")
    public void logout(String access_token, @RequestHeader(required = false,value = "Authorization") String token){
        if(StringUtils.isBlank(access_token)){
            if(StringUtils.isNotBlank(token)){
                access_token = token.substring(OAuth2AccessToken.BEARER_TYPE.length() + 1);
            }
        }
        authFeignApi.removeToken(access_token);
    }

}
