package com.pasq.provider.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pasq.provider.entity.SysRole;
import com.pasq.provider.entity.SysUser;
import com.pasq.provider.mapper.SysRoleMapper;
import com.pasq.provider.mapper.SysUserMapper;
import com.pasq.provider.model.dto.LoginUserDTO;
import com.pasq.provider.service.ISysUserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author jst
 * @since 2019-11-04
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements ISysUserService {

    @Autowired
    private SysUserMapper userMapper;

    @Autowired
    private SysRoleMapper roleMapper;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public List<SysUser> listUser(Long current, Long size) {
        IPage<SysUser> userPage = new Page<>(current,size);
        IPage<SysUser> sysUserIPage = userMapper.selectPage(userPage, null);
        return sysUserIPage.getRecords();
    }

    @Override
    public LoginUserDTO findUsername(String username) {
        if(StringUtils.isBlank(username)){
            throw new IllegalArgumentException("参数不能为空");
        }
        QueryWrapper<SysUser> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(SysUser::getUsername, username);
        List<SysUser> list = list(wrapper);
        //username唯一，所以list只可能有一个元素
        if(list.size()!=1){
            throw new IllegalArgumentException("该账号存在异常,请检查用户名是否重复");
        }

        SysUser user = list.get(0);
        LoginUserDTO loginUser = new LoginUserDTO();
        BeanUtils.copyProperties(user, loginUser);
        loginUser.setSex(Boolean.TRUE == user.getSex()? "男":"女");
        //根据用户id查询角色信息
        Set<SysRole> roles = userMapper.findRolesByUserId(user.getId());
        Set<String> roleCodes = roles.stream().map(SysRole::getCode).collect(Collectors.toSet());
        //如果分配了角色，则根据角色id集合查询权限信息
        if (roles.size() > 0) {
            //获取角色id集合
            Set<Long> roleIds = roles.parallelStream().map(SysRole::getId).collect(Collectors.toSet());
            Set<String> perms = roleMapper.findPermsByRoleIds(roleIds);
            loginUser.setRoles(roleCodes);
            loginUser.setAuthority(perms);
        }
        return loginUser;
    }



    @Override
    public void register(SysUser user) {
        QueryWrapper<SysUser> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(SysUser::getUsername,user.getUsername());
        List<SysUser> list = list(wrapper);
        if(list.size()!=0){
            throw new IllegalArgumentException("该用户名已存在");
        }
        if(StringUtils.isBlank(user.getPassword().trim())){
            throw new IllegalArgumentException("请按要求正确输入密码");
        }
        user.setCreated(LocalDateTime.now());
        //设置加密密码
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));

        int insert = userMapper.insert(user);
        //如果插入数量不为1则证明新增失败
        if (insert != 1) {
            throw new RuntimeException("新增失败");
        }
    }
}
