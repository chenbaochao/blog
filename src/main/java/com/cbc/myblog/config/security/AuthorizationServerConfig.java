package com.cbc.myblog.config.security;

import com.cbc.myblog.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;

import javax.sql.DataSource;
import java.util.concurrent.TimeUnit;

/**
 * Created by cbc on 2017/11/8.
 */
@Configuration
@AllArgsConstructor
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    private final  DataSource dataSource;

    private final AuthenticationManager authenticationManager;

    private final UserService userService;




    @Bean
    public ClientDetailsService clientDetailsService(){
        return new JdbcClientDetailsService(dataSource);
    }

    @Bean
    public TokenStore tokenStore(){
        return new JdbcTokenStore(dataSource);
    }


    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.
                withClientDetails(clientDetailsService());
    }



    //允许表单认证
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security.allowFormAuthenticationForClients();
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints
                .tokenStore(tokenStore())
                .authenticationManager(authenticationManager)
                .userDetailsService(userService);

        DefaultTokenServices tokenServices = new DefaultTokenServices();
        tokenServices.setClientDetailsService(endpoints.getClientDetailsService());
        tokenServices.setTokenStore(endpoints.getTokenStore());
        tokenServices.setSupportRefreshToken(true);
        tokenServices.setReuseRefreshToken(true);
        tokenServices.setTokenEnhancer(endpoints.getTokenEnhancer());
        tokenServices.setAccessTokenValiditySeconds((int)TimeUnit.DAYS.toSeconds(30));
        endpoints.tokenServices(tokenServices);
    }

}
