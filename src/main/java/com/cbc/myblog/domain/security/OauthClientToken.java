package com.cbc.myblog.domain.security;

import com.baomidou.mybatisplus.annotations.TableName;
import com.cbc.myblog.domain.BaseDomain;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.security.core.token.Token;

/**
 * Created by cbc on 2017/11/13.
 */
@Data
@Accessors(chain = true)
@TableName("oauth_client_token")
public class OauthClientToken extends BaseDomain{

    private String tokenId;

    private String token;

}
