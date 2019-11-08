package com.pasq.provider.service;

import com.pasq.provider.api.UserFeignApi;
import com.pasq.provider.model.dto.LoginUserDTO;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;

@Service("userDetailsService")
public class UserDetailServiceImpl implements UserDetailsService {

    @Resource
    private UserFeignApi userFeignApi;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        LoginUserDTO user = userFeignApi.findUsername(username);
        if(user==null){
            throw new AuthenticationCredentialsNotFoundException("用户不存在");
        } else if(!user.isEnabled()){
            throw new DisabledException("用户已作废");
        }
        return user;
    }
}
