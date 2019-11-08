package com.pasq.provider.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.pasq.base.mybatis.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * <p>
 * 菜单管理
 * </p>
 *
 * @author jst
 * @since 2019-11-04
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ToString
public class SysMenu extends BaseEntity {

    @TableId(value = "menu_id", type = IdType.AUTO)
    private Long menuId;

    /**
     * 父菜单ID，一级菜单为0
     */
    private Long parentId;

    /**
     * 菜单名称
     */
    private String name;

    /**
     * 菜单URL
     */
    private String url;

    /**
     * 授权(多个用逗号分隔，如：user:list,user:create)
     */
    private String perms;

    /**
     * 类型   0：目录   1：菜单   2：按钮
     */
    private Integer type;

    /**
     * 菜单图标
     */
    private String icon;

    /**
     * 排序
     */
    private Integer orderNum;

    /**
     * 创建时间
     */
    private LocalDateTime created;

    /**
     * 修改时间
     */
    private LocalDateTime updated;


}
