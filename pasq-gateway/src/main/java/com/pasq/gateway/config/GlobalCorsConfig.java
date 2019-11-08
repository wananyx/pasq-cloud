package com.pasq.gateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * @Author: JST
 * @Date: 2019/4/27 9:26
 * 跨域配置
 *
 * 页面访问域名和后端接口地址的域名不一致时,会先发起一个OPTIONS的试探请求
 * 因同源策略将导致无法进行跨域访问,,要设置这个配置
 *
 */
@Configuration
public class GlobalCorsConfig {

    @Bean
    public CorsFilter corsFilter(){
        CorsConfiguration config = new CorsConfiguration();
        //允许cookie跨域使用
        config.setAllowCredentials(true);
        //允许的跨域域名,这里表示所有域名,真实开发中要写公司所用域名,下同
        config.addAllowedOrigin("*");
        //允许的头信息
        config.addAllowedHeader("*");
        //允许的http方法
        config.addAllowedMethod("*");
        //预检请求的缓存时间（秒）,即在这个时间段里,对于相同的跨域请求不会再预检了
        config.setMaxAge(18000L);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**",config);

        return new CorsFilter(source);
    }

}
