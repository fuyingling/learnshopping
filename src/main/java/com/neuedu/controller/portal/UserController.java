package com.neuedu.controller.portal;


import com.neuedu.common.Const;
import com.neuedu.common.ServerResponse;
import com.neuedu.pojo.UserInfo;
import com.neuedu.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;

//登录

@RestController
@RequestMapping(value = "/portal/user/")
public class UserController {

    @Autowired
    IUserService userService;

    //根据用户名和密码登录
    @RequestMapping(value = "login.do")
    //获取session在形参里面加入HttpSession session
    public ServerResponse login(HttpSession session,String username, String password){

        ServerResponse serverResponse = userService.login(username, password);
       //如果接口调用成功了，userInfo才有值，把名字密码保存到session
        if (serverResponse.isSuccess()){//保存登录状态
                session.setAttribute(Const.CURRENTUSER,serverResponse.getData());//Key,Value形式，Value 是 userInfo
        }
        return serverResponse;//其他情况下返回
    }

    //注册
    @RequestMapping(value = "register.do")
    public ServerResponse register(UserInfo userInfo){

        return userService.register(userInfo);

    }


}
















