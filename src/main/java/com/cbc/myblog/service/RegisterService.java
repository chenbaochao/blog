package com.cbc.myblog.service;

import com.cbc.myblog.domain.User;
import com.cbc.myblog.persistence.UserMapper;
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

    public void createUser(User user) {
        user.init();
        String pwd = passwordEncoder.encode(user.getPassword());
        user.setPassword(pwd);
        this.insert(user);
    }
}
