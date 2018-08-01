package com.cbc.myblog.account.domain;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.deser.std.StringDeserializer;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;

@Data
public class User {

    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    @NotEmpty
    private String username;

    @NotEmpty
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
