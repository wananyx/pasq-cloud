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
 * 用户表
 * </p>
 *
 * @author jst
 * @since 2019-11-04
 */
@Data
//@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ToString
public class SysUser extends BaseEntity {

    @TableId(type = IdType.AUTO)
    private Long id;
    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 头像url
     */
    private String imgUrl;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 性别 1男 0女
     */
    private Boolean sex;

    /**
     * 状态（1有效,0无效）
     */
    private Boolean enabled;

    /**
     * 类型（暂未用）
     */
    private String type;

    /**
     * 创建时间
     */
    private LocalDateTime created;

    /**
     * 修改时间
     */
    private LocalDateTime updated;


}
