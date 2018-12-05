package com.neuedu.service.impl;

import com.neuedu.common.ServerResponse;
import com.neuedu.dao.UserInfoMapper;
import com.neuedu.pojo.UserInfo;
import com.neuedu.service.IUserService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//交给容器处理
@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    UserInfoMapper userInfoMapper;


    @Override
    public ServerResponse register(UserInfo userInfo) {

        //返回的影响的行数，大于0说明成功，等于0说明不成功
        int count = userInfoMapper.insert(userInfo);
        if (count>0){
            return ServerResponse.createServerResponseBySuccess();
        }
        return ServerResponse.createServerResponseByError();
    }

    @Override
    public ServerResponse login(String username, String password) {

        //step1:参数的非空校验，传过来的参数不能是空的
        /*if (username==null||username.equals("")){
            return ServerResponse.createServerResponseByError("用户名不能为空");
        }
        if (password==null||password.equals("")){
            return ServerResponse.createServerResponseByError("密码不能为空");
        }*/
//用阿帕奇的lang包
        if (StringUtils.isBlank(username)){
            return ServerResponse.createServerResponseByError("用户名不能为空");
        }
        if (StringUtils.isBlank(password)){
            return ServerResponse.createServerResponseByError("密码不能为空");
        }

        //step2:检查username是否存在
        int result =userInfoMapper.checkUsername(username);
        if (result<=0){//用户名不存在
            return ServerResponse.createServerResponseByError("用户名不存在");
        }

        //step3:根据用户名和密码查询用户
            UserInfo userInfo = userInfoMapper.selectUserByUsernameAndPassword(username,password);
            if (userInfo==null){//密码错误
                return ServerResponse.createServerResponseByError("密码错误");
            }

        //step4:处理结果并返回
        userInfo.setPassword("");//密码不能返回给前端，所以置空
            return ServerResponse.createServerResponseBySuccess(null,userInfo);

    }
}
