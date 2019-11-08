package com.pasq.provider.api.hystrix;

import com.pasq.provider.api.UserFeignApi;
import com.pasq.provider.model.dto.LoginUserDTO;
import org.springframework.stereotype.Component;

@Component
public class UserFeignApiHystrix implements UserFeignApi {

    @Override
    public LoginUserDTO findUsername(String username) {
        return null;
    }

    @Override
    public String test() {
        return " user有点问题 ";
    }
}
