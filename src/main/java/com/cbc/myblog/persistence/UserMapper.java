package com.cbc.myblog.persistence;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.cbc.myblog.domain.User;
import com.cbc.myblog.domain.security.CustomUserDetails;

/**
 * Created by cbc on 2017/12/22.
 */
public interface UserMapper extends BaseMapper<User>{

    User selectUserByUsername(String username);

}
