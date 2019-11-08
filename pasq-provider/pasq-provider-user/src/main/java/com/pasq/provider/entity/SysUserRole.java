package com.pasq.provider.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.pasq.base.mybatis.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 *
 * </p>
 *
 * @author jst
 * @since 2019-11-04
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("sys_user_role")
public class SysUserRole extends BaseEntity {

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 角色id
     */
    private Long roleId;


}
