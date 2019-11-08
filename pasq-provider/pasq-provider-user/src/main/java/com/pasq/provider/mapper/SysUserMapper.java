package com.pasq.provider.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.pasq.provider.entity.SysRole;
import com.pasq.provider.entity.SysUser;
import org.springframework.stereotype.Repository;

import java.util.Set;

/**
 * <p>
 * 用户表 Mapper 接口
 * </p>
 *
 * @author jst
 * @since 2019-11-04
 */
@Repository
public interface SysUserMapper extends BaseMapper<SysUser> {

    Set<SysRole> findRolesByUserId(Long userId);
}
