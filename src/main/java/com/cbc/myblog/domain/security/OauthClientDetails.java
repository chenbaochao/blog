package com.cbc.myblog.domain.security;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.cbc.myblog.domain.BaseDomain;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by cbc on 2017/11/13.
 */
@Data
@Accessors(chain=true)
@TableName("oauth_client_details")
@JsonIgnoreProperties(ignoreUnknown = true)
public class OauthClientDetails extends BaseDomain implements ClientDetails{

    private String clientId;

    @TableField("resource_ids")
    private String resourceIdsString;

    private String clientSecret;

    @TableField("scopes")
    private String scopesString;

    @TableField("authorized_grant_types")
    private String authorizedGrantTypeString;

    @TableField("web_server_redirect_uri")
    private String webServerRedirectUri;

    @TableField("authorities")
    private String authoritiesString;

    private Integer accessTokenValidity;

    private Integer refreshTokenValidity;

    @TableField("auto_approve_scopes")
    private String  autoApproveScopesString;

    @TableField("additional_information")
    private String additionalInformationString;

    @Override
    public String getClientId() {
        return this.clientId;
    }

    @JsonIgnore
    @Override
    public Set<String> getResourceIds() {
        if(StringUtils.isEmpty(resourceIdsString)){
            return Collections.emptySet();
        }
        return Arrays.stream(resourceIdsString.split(",")).collect(Collectors.toSet());
    }

    public void setResourceIds(Set<String> resourceIds){
        this.resourceIdsString = Optional
                                    .ofNullable(resourceIds)
                                    .map(u -> u.stream().collect(Collectors.joining(","))
                                    ).orElse(null);
    }

    @Override
    public boolean isSecretRequired() {
        return this.clientSecret!=null;
    }

    @Override
    public String getClientSecret() {
        return this.clientSecret;
    }

    @JsonIgnore
    @Override
    public boolean isScoped() {
        return this.getScope()!=null&&!this.getScope().isEmpty();
    }

    @JsonIgnore
    @Override
    public Set<String> getScope() {
        if(StringUtils.isEmpty(scopesString)){
            return Collections.emptySet();
        }
        return Arrays.stream(scopesString.split(",")).collect(Collectors.toSet());
    }

    public void setScope(Set<String> scopes){
        this.scopesString = Optional
                                .ofNullable(scopes)
                                .map(u -> u.stream().collect(Collectors.joining(",")))
                                .orElse(null);
    }

    @JsonIgnore
    @Override
    public Set<String> getAuthorizedGrantTypes() {
        if(StringUtils.isEmpty(authorizedGrantTypeString)){
            return Collections.emptySet();
        }
        return Arrays.stream(authorizedGrantTypeString.split(",")).collect(Collectors.toSet());
    }

    public void setAuthorizedGrantTypes(Set<String> authorizedGrantTypes){
        this.authorizedGrantTypeString = Optional
                                                .ofNullable(authorizedGrantTypes)
                                                .map(u->u.stream().collect(Collectors.joining(",")))
                                                .orElse(null);


    }

    @JsonIgnore
    @Override
    public Set<String> getRegisteredRedirectUri() {
        if(StringUtils.isEmpty(webServerRedirectUri)){
            return Collections.emptySet();
        }
        return Arrays.stream(webServerRedirectUri.split(",")).collect(Collectors.toSet());
    }

    public void setRegisteredRedirectUri(Set<String> registeredRedirectUris){
        this.webServerRedirectUri = Optional
                                        .ofNullable(registeredRedirectUris)
                                        .map(u->u.stream().collect(Collectors.joining(",")))
                                        .orElse(null);
    }

    @JsonIgnore
    @Override
    public Collection<GrantedAuthority> getAuthorities() {
        if(StringUtils.isEmpty(authoritiesString)){
            return Collections.emptySet();
        }
        return Arrays.stream(authoritiesString.split(",")).map(SimpleGrantedAuthority::new).collect(Collectors.toSet());
    }

    public void setAuthorities(Set<String> authorities){
        this.authoritiesString = Optional
                                        .ofNullable(authorities)
                                        .map(u->u.stream().collect(Collectors.joining(",")))
                                        .orElse(null);
    }

    @Override
    public Integer getAccessTokenValiditySeconds() {
        return this.accessTokenValidity;
    }

    @Override
    public Integer getRefreshTokenValiditySeconds() {
        return this.refreshTokenValidity;
    }

    @JsonIgnore
    @Override
    public boolean isAutoApprove(String scope) {
        if(this.getAutoApproveScopes() == null) {
            return false;
        } else {
            Iterator var2 = this.getAutoApproveScopes().iterator();

            String auto;
            do {
                if(!var2.hasNext()) {
                    return false;
                }
                auto = (String)var2.next();
            } while(!auto.equals("true") && !scope.matches(auto));

            return true;
        }
    }

    @JsonIgnore
    public Set<String> getAutoApproveScopes(){
        if(StringUtils.isEmpty(autoApproveScopesString)){
            return Collections.emptySet();
        }
        return Arrays.stream(autoApproveScopesString.split(",")).collect(Collectors.toSet());
    }

    public void setAutoApproveScopes(Set<String> autoApproveScopes){
        this.autoApproveScopesString = Optional
                                            .ofNullable(autoApproveScopes)
                                            .map(u->u.stream().collect(Collectors.joining(",")))
                                            .orElse(null);
    }

    @JsonIgnore
    @Override
    public Map<String, Object> getAdditionalInformation() {
        if(StringUtils.isEmpty(additionalInformationString)){
            return Collections.emptyMap();
        }else{
            Map<String, Object> result = new LinkedHashMap<>();
            JSONObject additionalInformation = JSON.parseObject(this.additionalInformationString);
            additionalInformation.keySet().forEach(
                    key->result.put(key,additionalInformation.get(key))
            );
            return result;
        }
    }

    public void setAdditionalInformation(Map<String, Object> additionalInformation){
        this.additionalInformationString = Optional
                                                .ofNullable(additionalInformation)
                                                .map(JSON::toJSONString)
                                                .orElse(null);
    }
}
