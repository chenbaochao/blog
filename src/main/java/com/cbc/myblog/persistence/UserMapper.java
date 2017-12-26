package com.cbc.myblog.persistence;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.cbc.myblog.domain.User;
import org.apache.ibatis.annotations.Select;

/**
 * Created by cbc on 2017/12/22.
 */
public interface UserMapper extends BaseMapper<User>{

    @Select("select * from user where username=#{username}")
    User selectByUsername(String username);

}
