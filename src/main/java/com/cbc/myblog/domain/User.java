package com.cbc.myblog.domain;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.Accessors;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.lang.Nullable;

import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.io.Serializable;
import java.sql.Timestamp;
import java.time.ZonedDateTime;
import java.util.*;

/**
 * Created by cbc on 2017/12/21.
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@TableName("blog_user")
public class User implements Comparable,Serializable{


    private static final long serialVersionUID = -656075117215734549L;

    @TableId
    private Long id;

    @NotNull
    private String username;

    @JsonIgnore
    @NotNull
    private String password;

    // 昵称
    @NotNull
    private String nickname;

    // 头像
    private String avatar;

    // 加入时间
    @TableField(value = "join_time")
    private Date joinTime;

    @NotNull
    private String email;

    public User(String username, String password, String nickname,String email) {
        this.username = username;
        this.password = password;
        this.nickname = nickname;
        this.email = email;
    }

    /**
     * 初始化：头像 + 注册时间
     */
    public void init() {
        try {
            Document document = Jsoup.connect("http://www.woyaogexing.com/touxiang/new/").get();
            Elements elements = document.select("div.txList");
            int size = elements.size();
            List<String> avatars = new ArrayList<>(size);
            elements.forEach(element -> avatars.add(element.select("img.lazy").attr("src")));
            Random random = new Random();
            this.avatar = avatars.get(random.nextInt(size));
            this.joinTime = new Date();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString(){
        return JSON.toJSONString(this);
    }

    @Override
    public int compareTo(@Nullable Object o) {
        if(o instanceof User){
            return this.username.compareTo(((User) o).username);
        }
        return 0;
    }
}
