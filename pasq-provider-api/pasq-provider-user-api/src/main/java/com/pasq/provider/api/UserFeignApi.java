package com.pasq.provider.api;

import com.pasq.provider.api.hystrix.UserFeignApiHystrix;
import com.pasq.provider.model.dto.LoginUserDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "pasq-provider-user",fallback = UserFeignApiHystrix.class)
public interface UserFeignApi {

    /**
     * 根据用户名查询用户
     * @param username
     * @return
     */
    @GetMapping("/sysUser/anon/findUsername")
    LoginUserDTO findUsername(@RequestParam("username") String username);

    @GetMapping("/test-user")
    String test();
}
