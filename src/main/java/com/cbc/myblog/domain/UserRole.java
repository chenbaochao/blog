package com.cbc.myblog.domain;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * Created by cbc on 2017/12/28.
 */
@Data
@Accessors(chain = true)
@TableName("blog_user_role")
public class UserRole {

    @TableId
    private Long id;

    private Long userId;

    private Long roleId;

}
