package com.cbc.myblog.web;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * Created by cbc on 2017/12/27.
 */
@RestController
public class indexController {

  //  @PreAuthorize("hasRole('ROLE_ADMIN')")
/*    @GetMapping("/hello")
    public ResponseEntity hello(@AuthenticationPrincipal Principal principal, Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return ResponseEntity.ok(principal.getName());
    }*/
}
