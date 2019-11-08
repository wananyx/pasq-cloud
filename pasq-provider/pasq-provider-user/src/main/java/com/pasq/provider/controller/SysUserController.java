package com.pasq.provider.controller;

import com.pasq.base.constant.Result;
import com.pasq.provider.entity.SysUser;
import com.pasq.provider.mapper.SysUserMapper;
import com.pasq.provider.model.dto.LoginUserDTO;
import com.pasq.provider.service.ISysUserService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author jst
 * @since 2019-11-04
 */
@Slf4j
@RestController
@RequestMapping("/sysUser")
public class SysUserController {

    @Autowired
    private ISysUserService userService;

    /**
     * 注册用户
     * @param user User实体类
     * @return
     */
//    @LogAnnotation(module = "用户操作")
    @ApiOperation(value = "注册新用户",notes = "注册新用户")
    @ApiImplicitParam(name = "user", value = "User实体类",required = true, dataType = "User")
    @PostMapping("/anon/register")
    public Result register(SysUser user){
        userService.register(user);
        log.info("创建账号 {}",user.getUsername());
        return Result.ok();
    }

    /**
     * 查询所有用户列表
     * @return
     */
//    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @ApiOperation(value = "查询所有用户列表",notes = "查询所有用户列表")
    @GetMapping("/anon/listUser")
    public Result listUser(Long current, Long size){
        List<SysUser> list = userService.listUser(current,size);
        return Result.ok("list",list);
    }


    /**
     * 根据用户名查询用户
     * @param username
     * @return
     */
    @GetMapping("/anon/findUsername")
    public LoginUserDTO findUsername(String username){
        LoginUserDTO user = userService.findUsername(username);
        return user;
    }

}
