package com.cbc.myblog.service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.toolkit.CollectionUtils;
import com.cbc.myblog.domain.Role;
import com.cbc.myblog.domain.User;
import com.cbc.myblog.domain.UserRole;
import com.cbc.myblog.domain.security.CustomUserDetails;
import com.cbc.myblog.persistence.RoleMapper;
import com.cbc.myblog.persistence.UserMapper;
import com.cbc.myblog.persistence.UserRoleMapper;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.naming.AuthenticationNotSupportedException;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by cbc on 2017/12/22.
 */
@Service
@Transactional
@AllArgsConstructor
public class UserService extends BaseService<UserMapper,User> implements UserDetailsService{

    private final UserMapper userMapper;

    private final RoleMapper roleMapper;

    private final UserRoleMapper userRoleMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userMapper.selectUserByUsername(username);
        if (null == user) {
            throw new UsernameNotFoundException("Could not find the user '" + username + "'");
        }
        List<UserRole> userRoles = userRoleMapper.selectList(new EntityWrapper<UserRole>().eq("user_id", user.getId()));
        List<Long> roleIds = userRoles.stream().map(UserRole::getRoleId).collect(Collectors.toList());
        List<Role> roles = roleMapper.selectBatchIds(roleIds);
        return new CustomUserDetails(user,roles);
    }


}
