package com.cbc.myblog.admin.domain;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class User {

    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    private String username;

    private String password;


    /**
     * 注册时间
     */
    private LocalDateTime created;


    /**
     * 最后更新时间
     */
    private LocalDateTime updated;

}
