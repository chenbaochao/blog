package com.cbc.myblog.domain;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.annotations.TableId;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.internal.Nullable;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.*;

/**
 * Created by cbc on 2017/12/21.
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
public class User implements Comparable{

    @TableId
    private Long id;

    private String username;

    @JsonIgnore
    private String password;

    // 昵称
    private String nickname;

    // 头像
    private String avatar;

    // 加入时间
    private Date joinTime;

    public User(String username, String password, String nickname) {
        this.username = username;
        this.password = password;
        this.nickname = nickname;
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
