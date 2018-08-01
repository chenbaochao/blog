package com.cbc.myblog.ui.util;

import com.alibaba.fastjson.JSONObject;
import com.cbc.myblog.ui.domain.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.OAuth2Authentication;

import java.util.LinkedHashMap;
import java.util.Map;

public class CurrentUserUtil {
    private static CurrentUserUtil instance = new CurrentUserUtil();

    public static CurrentUserUtil getInstance() {
        return instance;
    }

    public User getUser() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Map<String,Object> details = (LinkedHashMap) ((OAuth2Authentication) authentication).getUserAuthentication().getDetails();
        LinkedHashMap<String,Object> principal = (LinkedHashMap<String,Object>)details.get("principal");
        String s = JSONObject.toJSONString(principal);
        User user = JSONObject.parseObject(s, User.class);
        return user;
    }
}
