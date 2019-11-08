package com.pasq.gateway.config;

import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @Author: JST
 * @Date: 2019/4/27 9:52
 */
@Configuration
@EnableOAuth2Sso//开启sso单点登陆
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();//禁用csrf
        http.headers().frameOptions().sameOrigin();//该页面可以在相同域名页面的 frame 中展示，防止网站被人嵌套
        http.cors();//允许跨域访问
    }
}
