package com.cbc.myblog.auth.controller;


import com.cbc.myblog.auth.domain.User;
import com.cbc.myblog.auth.service.IUserService;
import com.cbc.myblog.auth.util.ResponseBean;
import com.cbc.myblog.auth.util.ResultUtil;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/user")
@AllArgsConstructor
public class UserController {

    private final IUserService userService;

    @GetMapping("/current")
    public Principal getUser(Principal principal){
        return principal;
    }


   // @PreAuthorize("#oauth2.hasScope('server')")
    @PostMapping
    public ResponseBean createUser( @RequestBody User user) {
        userService.create(user);
        return ResultUtil.success();
    }

}
