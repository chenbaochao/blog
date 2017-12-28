package com.cbc.myblog.web;

import com.cbc.myblog.domain.User;
import com.cbc.myblog.service.RegisterService;
import com.cbc.myblog.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.security.Principal;

/**
 * Created by cbc on 2017/12/28.
 */
@RestController
@AllArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    private final RegisterService registerService;

    @PostMapping("/register")
    public ResponseEntity createUser(@RequestParam String username,@RequestParam String password,@RequestParam String nickname,@RequestParam String email){
        User user = new User(username, password, nickname, email);
        registerService.createUser(user);
        return ResponseEntity
                    .created(ServletUriComponentsBuilder
                                    .fromCurrentRequest()
                                    .path("/{id}")
                                    .buildAndExpand(user.getId())
                                    .toUri())
                    .body(user);
    }


    @GetMapping
    public ResponseEntity getCurrentUser(@AuthenticationPrincipal Principal principal){
        if(principal instanceof UserDetails){
            return ResponseEntity.ok((User)principal);
        }
        return ResponseEntity.ok(principal.getName());
    }
}
