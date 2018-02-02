package com.cbc.myblog.service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.cbc.myblog.domain.Role;
import com.cbc.myblog.domain.User;
import com.cbc.myblog.domain.UserRole;
import com.cbc.myblog.persistence.RoleMapper;
import com.cbc.myblog.persistence.UserMapper;
import com.cbc.myblog.persistence.UserRoleMapper;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * Created by cbc on 2017/12/28.
 */
@Service
@AllArgsConstructor
public class RegisterService extends BaseService<UserMapper,User>{

    private final PasswordEncoder passwordEncoder;

    private final RoleMapper roleMapper;

    private final UserRoleMapper userRoleMapper;

    public void createUser(User user) {
        user.init();
        String pwd = passwordEncoder.encode(user.getPassword());
        Role role = roleMapper.selectOne(new Role().setType("ROLE_USER"));
        user.setPassword(pwd);
        this.insert(user);
        userRoleMapper.insert(new UserRole().setRoleId(role.getId()).setUserId(user.getId()));
    }
}