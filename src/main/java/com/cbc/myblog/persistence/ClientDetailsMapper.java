package com.cbc.myblog.persistence;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.cbc.myblog.domain.security.OauthClientDetails;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * Created by cbc on 2017/11/13.
 */
public interface ClientDetailsMapper extends BaseMapper<OauthClientDetails>{

    @Select("select * from oauth_client_details where client_id=#{clientId}")
    OauthClientDetails selectByClientId(String clientId);

    @Update("update oauth_client_details set client_secret =#{secret} where client_id =#{clientId}")
    Boolean updateSecretByClientId(String clicentId,String secret);

    @Delete("delete from oauth_client_details where client_id=#{clientId}")
    Boolean deleteByClientId(String clientId);
}
