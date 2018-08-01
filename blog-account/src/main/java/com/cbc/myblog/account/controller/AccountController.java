package com.cbc.myblog.account.controller;

import com.cbc.myblog.account.client.AuthServiceClient;
import com.cbc.myblog.account.domain.User;
import com.cbc.myblog.account.util.ResponseBean;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/user")
public class AccountController {


    private final AuthServiceClient authServiceClient;

    public AccountController(AuthServiceClient authServiceClient) {
        this.authServiceClient = authServiceClient;
    }

    @PostMapping
    public ResponseBean createUser(@Valid User user){
        return authServiceClient.createUser(user);
    }
}
