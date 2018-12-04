package com.neuedu.controller;

import com.neuedu.pojo.UserInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
/*@RestController 往前端返回的是json文件类型的格式*/
@RestController
@RequestMapping(value = "/user/")
public class TestController {

    @RequestMapping(value = "login.do")
    public UserInfo login(){

        UserInfo userInfo = new UserInfo();
        userInfo.setId(1);
        userInfo.setUsername("张三");
        return userInfo;

    }




}
