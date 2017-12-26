package com.cbc.myblog.service;

import com.cbc.myblog.domain.User;
import com.cbc.myblog.domain.security.CustomUserDetails;
import com.cbc.myblog.persistence.UserMapper;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;

/**
 * Created by cbc on 2017/12/22.
 */
@Service
@Transactional
@AllArgsConstructor
public class UserService implements UserDetailsService{

    private final UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userMapper.selectByUsername(username);
        if(user ==null){
            throw new UsernameNotFoundException("Could not find the user '"+username + "'");
        }
        return new CustomUserDetails(user,new HashSet<>());
    }


}
