package com.pasq.provider.entity;

import com.pasq.base.mybatis.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 角色与菜单对应关系
 * </p>
 *
 * @author jst
 * @since 2019-11-04
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class SysRoleMenu extends BaseEntity {

    /**
     * 角色ID
     */
    private Long roleId;

    /**
     * 菜单ID
     */
    private Long menuId;


}
