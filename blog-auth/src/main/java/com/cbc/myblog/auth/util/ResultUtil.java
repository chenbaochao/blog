package com.cbc.myblog.auth.util;

import com.cbc.myblog.auth.domain.User;
import com.sun.org.apache.bcel.internal.generic.NEW;
import lombok.Data;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;

/**
 * Created by cbc on 2018/3/30.
 */
public class ResultUtil {

    public static ResponseBean success(Object o){
        ResponseBean responseBean = new ResponseBean();
        responseBean.setCode(200);
        responseBean.setMsg("成功！");
        responseBean.setData(o);
        return responseBean;
    }

    public static ResponseBean success(){
        return success(null);
    };

    public static ResponseBean error(Integer code,String msg){
        ResponseBean responseBean = new ResponseBean();
        responseBean.setCode(code);
        responseBean.setMsg(msg);
        return responseBean;
    }

    public static void main(String[] args) {
        PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        String encode = encoder.encode("blog-account");
        System.out.println(encode);



    }


}
