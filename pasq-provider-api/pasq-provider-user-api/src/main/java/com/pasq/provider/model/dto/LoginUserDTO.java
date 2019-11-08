package com.pasq.provider.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pasq.base.mybatis.BaseEntity;
import lombok.Data;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.CollectionUtils;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Data
@ToString
public class LoginUserDTO extends BaseEntity implements UserDetails  {

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
     * 性别
     */
    private String sex;

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

    /**
     * 角色英文名集合
     */
    private Set<String> roles;

    /**
     * 权限标识集合
     */
    private Set<String> authority;

    @JsonIgnore
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> collection = new HashSet<>();

        if (!CollectionUtils.isEmpty(roles)) {
            roles.forEach(role -> {
                if (role.startsWith("ROLE_")) {
                    collection.add(new SimpleGrantedAuthority(role));
                } else {
                    collection.add(new SimpleGrantedAuthority("ROLE_" + role));
                }
            });
        }

        if (!CollectionUtils.isEmpty(authority)) {
            authority.forEach(per -> {
                collection.add(new SimpleGrantedAuthority(per));
            });
        }

        return collection;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return getEnabled();
    }
}
