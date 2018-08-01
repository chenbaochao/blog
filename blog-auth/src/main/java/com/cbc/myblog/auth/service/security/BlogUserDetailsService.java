package com.cbc.myblog.auth.service.security;

import com.cbc.myblog.auth.domain.User;
import com.cbc.myblog.auth.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class BlogUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<User> user = userRepository.findByUsername(username);
        if(!user.isPresent()){
            throw new UsernameNotFoundException(String.format("没有找到用户名为%s的用户信息!",username));
        }
        return user.get();
    }
}
