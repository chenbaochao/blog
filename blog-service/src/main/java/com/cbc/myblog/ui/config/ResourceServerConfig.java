package com.cbc.myblog.ui.config;


import feign.RequestInterceptor;
import lombok.AllArgsConstructor;
import org.springframework.boot.autoconfigure.security.oauth2.resource.ResourceServerProperties;
import org.springframework.boot.autoconfigure.web.ResourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.security.oauth2.client.feign.OAuth2FeignRequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.oauth2.client.DefaultOAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.token.grant.client.ClientCredentialsResourceDetails;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

@Configuration
@EnableConfigurationProperties
@EnableResourceServer
@EnableGlobalMethodSecurity(prePostEnabled = true)
@AllArgsConstructor
public class ResourceServerConfig {

    private final ResourceServerProperties sso;

    @Bean
    @ConfigurationProperties("security.oauth2.client")
    public ClientCredentialsResourceDetails clientCredentialsResourceDetails(){
        return new ClientCredentialsResourceDetails();
    }

    @Bean
    public RequestInterceptor oauth2FeignRequestInterceptor(){
        return new OAuth2FeignRequestInterceptor(new DefaultOAuth2ClientContext(),clientCredentialsResourceDetails());
    }

    @Bean
    public OAuth2RestTemplate oauth2RestTemplate(){
        return new OAuth2RestTemplate(clientCredentialsResourceDetails());
    }

}
