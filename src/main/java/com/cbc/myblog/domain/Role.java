package com.cbc.myblog.domain;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.springframework.security.core.GrantedAuthority;

/**
 * Created by cbc on 2017/12/27.
 */
@Data
@Accessors(chain=true)
@NoArgsConstructor
@TableName("blog_role")
public class Role  implements GrantedAuthority {

    @TableId
    private Long id;

    private String name;

    private String type;

    @Override
    public String getAuthority() {
        return this.type;
    }

    public Role(Long id, String name, String type) {
        this.id = id;
        this.name = name;
        this.type = type;
    }


}
