package com.cbc.myblog.account.client;

import com.cbc.myblog.account.domain.User;
import com.cbc.myblog.account.util.ResponseBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @author cbc
 */
@Component
@FeignClient(name = "blog-auth")
public interface AuthServiceClient {

    @PostMapping(value = "/uaa/user",consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    ResponseBean createUser(User user);


}
