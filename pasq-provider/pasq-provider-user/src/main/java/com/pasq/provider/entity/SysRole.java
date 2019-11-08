package com.pasq.provider.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.pasq.base.mybatis.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * <p>
 * 后台角色表
 * </p>
 *
 * @author jst
 * @since 2019-11-04
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class SysRole extends BaseEntity {

    @TableId(type = IdType.AUTO)
    private Long id;
    /**
     * 角色名英文
     */
    private String code;

    /**
     * 角色名
     */
    private String roleName;

    private LocalDateTime created;

    private LocalDateTime updated;


}
