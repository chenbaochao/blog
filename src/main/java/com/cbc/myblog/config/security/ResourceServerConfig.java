/*
package com.cbc.myblog.config.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;

*/
/**
 * Created by cbc on 2017/12/20.
 *//*

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    private static final String BLOG = "blog";

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        resources.resourceId(BLOG).stateless(true);
}

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
               // .anonymous().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                .and()
                .authorizeRequests()
                .antMatchers("/", "/webjars*/
/**", "/user/register", "/api/common*/
/**", "/image*/
/**","/assets*/
/**","/css*/
/**").permitAll()
                .antMatchers("/oauth*/
/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin().loginPage("/login").defaultSuccessUrl("/index").permitAll();
    }



}
*/
