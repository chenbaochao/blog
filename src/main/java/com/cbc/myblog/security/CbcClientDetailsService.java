package com.cbc.myblog.security;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.cbc.myblog.domain.security.OauthClientDetails;
import com.cbc.myblog.exception.OptimisticLockerException;
import com.cbc.myblog.persistence.ClientDetailsMapper;
import com.fasterxml.jackson.databind.util.BeanUtil;
import lombok.AllArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.BeanFactoryUtils;
import org.springframework.cglib.beans.BeanMap;
import org.springframework.context.annotation.Bean;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.provider.*;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;

/**
 * Created by cbc on 2017/11/13.
 */
@Slf4j
@Service
@AllArgsConstructor
public class CbcClientDetailsService extends ServiceImpl<ClientDetailsMapper, OauthClientDetails> implements ClientDetailsService {

    private  ClientDetailsMapper clientDetailsMapper;

    @Setter
    private final PasswordEncoder passwordEncoder = NoOpPasswordEncoder.getInstance() ;


    @Override
    public ClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException {
        return Optional.ofNullable(clientDetailsMapper.selectByClientId(clientId)).map(client -> {
            Assert.isTrue(client.getIsEnabled(), "client.detail.service.error.401.client.disabled");
            Assert.isTrue(client.getIsDeleted(),"client.detail.service.error.401.client.not.found");
            return client;
        }).orElseThrow(() -> new NoSuchClientException("No client with requested id: " + clientId));
    }

    public void addClientDetails(OauthClientDetails clientDetails){
            try{
                this.insert(clientDetails);
            }catch (DuplicateKeyException e){
                throw new ClientAlreadyExistsException("Client already exists: " + clientDetails.getClientId(),e);
            }
    }

    public void updateClientDetails(OauthClientDetails clientDetails) {
        try{
            boolean result = this.updateById(clientDetails);
            if(!result){
                throw new OptimisticLockerException("version number not same");
            }
        }catch (DuplicateKeyException e) {
            throw new ClientAlreadyExistsException("Client already exists: " + clientDetails.getClientId(), e);
        }

    }

    public void updateClientSecret(String clientId, String secret) {
        Boolean result = clientDetailsMapper.updateSecretByClientId(clientId, this.passwordEncoder.encode(secret));
        if(!result){
            throw new NoSuchClientException("No client found with id = " + clientId);
        }
    }

    public void removeClientDetails(String clientId) throws NoSuchClientException {
        Boolean result = clientDetailsMapper.deleteByClientId(clientId);
        if(!result){
            throw new NoSuchClientException("No client found with id = " + clientId);
        }
    }

    public List<OauthClientDetails> listClientDetails() {
        List<OauthClientDetails> result = this.selectList(
                new EntityWrapper<OauthClientDetails>()
                        .eq("is_deleted", false)
                        .eq("is_enabled", true));
        return result;
    }
}
