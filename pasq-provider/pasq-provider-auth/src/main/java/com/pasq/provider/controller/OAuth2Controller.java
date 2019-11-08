package com.pasq.provider.controller;

import com.pasq.provider.api.UserFeignApi;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.token.ConsumerTokenServices;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Author: JST
 * @Date: 2019/4/25 11:30
 */
@Slf4j
@RestController
@RequestMapping
public class OAuth2Controller {

    @Resource
    private UserFeignApi userFeignApi;
    @Resource
    private ConsumerTokenServices tokenServices;

    /**
     * 当前登陆用户信息<br>
     * security获取当前登录用户的方法是SecurityContextHolder.getContext().getAuthentication()<br>
     * 返回值是接口org.springframework.security.core.Authentication,又继承了Principal<br>
     * 这里的实现类是org.springframework.security.oauth2.provider.OAuth2Authentication<br>
     * @return
     */
    @GetMapping("/user-me")
    public Authentication principal() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        log.info("user-me:{}",authentication.getName());
        return authentication;
    }

    @PostMapping("/remove_token")
    public void removeToken(String access_token){
        boolean flag = tokenServices.revokeToken(access_token);
    }


    //下面注释掉的三个方法也都一样,这四个方法任选其一即可

//	@GetMapping("/user-me")
//	public Principal principal(Principal principal) {
//		log.debug("user-me:{}", principal.getName());
//		return principal;
//	}
//
//	@GetMapping("/user-me")
//	public Authentication principal(Authentication authentication) {
//		log.debug("user-me:{}", authentication.getName());
//		return authentication;
//	}
//
//	@GetMapping("/user-me")
//	public OAuth2Authentication principal(OAuth2Authentication authentication) {
//		log.debug("user-me:{}", authentication.getName());
//		return authentication;
//	}




}
