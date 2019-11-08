package com.pasq.provider.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.pasq.provider.entity.SysUser;
import com.pasq.provider.model.dto.LoginUserDTO;

import java.util.List;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author jst
 * @since 2019-11-04
 */
public interface ISysUserService extends IService<SysUser> {

    List<SysUser> listUser(Long current, Long size);

    LoginUserDTO findUsername(String username);

    void register(SysUser user);
}
