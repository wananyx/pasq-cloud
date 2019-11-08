package com.pasq.provider.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.pasq.provider.entity.SysRole;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Set;

/**
 * <p>
 * 后台角色表 Mapper 接口
 * </p>
 *
 * @author jst
 * @since 2019-11-04
 */
@Repository
public interface SysRoleMapper extends BaseMapper<SysRole> {

    Set<String> findPermsByRoleIds(@Param("roleIds") Set<Long> roleIds);
}
