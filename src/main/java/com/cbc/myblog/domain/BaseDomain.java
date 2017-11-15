package com.cbc.myblog.domain;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableLogic;
import com.baomidou.mybatisplus.enums.FieldFill;
import com.baomidou.mybatisplus.enums.FieldStrategy;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.time.ZonedDateTime;

/**
 * Created by cbc on 2017/11/10.
 */
@Data
@Accessors(chain = true)
public abstract class BaseDomain implements Serializable {

    @TableId
    @JsonSerialize(using = ToStringSerializer.class)
    protected Long id;

    @TableField(strategy = FieldStrategy.NOT_NULL,fill= FieldFill.INSERT)
    protected Boolean isEnabled;

    @TableField(strategy = FieldStrategy.NOT_NULL,fill= FieldFill.INSERT)
    @TableLogic
    protected Boolean isDeleted;

    @TableField(fill = FieldFill.INSERT)
    protected ZonedDateTime createdDate;

    @TableField(fill = FieldFill.INSERT)
    protected Long createdBy;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    protected ZonedDateTime lastUpdatedDate;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    protected Long lastUpdatedBy;

}
