package com.neuedu.service.impl;

import com.neuedu.common.Const;
import com.neuedu.common.ResponseCode;
import com.neuedu.common.ServerResponse;
import com.neuedu.dao.UserInfoMapper;
import com.neuedu.pojo.UserInfo;
import com.neuedu.service.IUserService;
import com.neuedu.utils.MD5Utils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//交给容器处理
@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    UserInfoMapper userInfoMapper;


    //注册接口
    @Override
    public ServerResponse register(UserInfo userInfo) {
        //step1:参数非空校验
        if (userInfo==null){
            return ServerResponse.createServerResponseByError(ResponseCode.PARAM_EMPTY.getStatus(),ResponseCode.PARAM_EMPTY.getMsg());
        }
        //step2:判断用户名是否已经存在
        //先拿到用户名
        String username = userInfo.getUsername();
        //校验用户名
        int count =userInfoMapper.checkUsername(username);
        if (count>0){//说明用户名已经存在
            return ServerResponse.createServerResponseByError(ResponseCode.EXISTS_USERNAME.getStatus(),ResponseCode.EXISTS_USERNAME.getMsg());
        }
        //step3:判断邮箱是否已经存在
               int result = userInfoMapper.checkEmail(userInfo.getEmail());
                    if (result>0){//邮箱已经存在
                       return ServerResponse.createServerResponseByError(ResponseCode.EXISTS_EMAIL.getStatus(),ResponseCode.EXISTS_EMAIL.getMsg());
                    }
        //step4:注册
        userInfo.setRole(Const.USER_ROLE_CUSTOMER);//普通用户
        //对注册的密码进行加密
        userInfo.setPassword(MD5Utils.getMD5Code(userInfo.getPassword()));
        int insert_result = userInfoMapper.insert(userInfo);

        //step5:返回结果

        if (insert_result>0){
            return ServerResponse.createServerResponseBySuccess("注册成功");
        }
        return ServerResponse.createServerResponseByError("注册失败");



    }


    //登录操作
    @Override
    public ServerResponse login(String username, String password) {

        //step1:参数的非空校验，传过来的参数不能是空的
            if (StringUtils.isBlank(username)){
                return ServerResponse.createServerResponseByError("用户名不能为空");
            }
            if (StringUtils.isBlank(password)){
                return ServerResponse.createServerResponseByError("密码不能为空");
            }
        //step2:检查username是否存在
          int result=  userInfoMapper.checkUsername(username);
            if (result<=0){//用户名不存在
                return ServerResponse.createServerResponseByError("用户名不存在");
            }
         //step3:根据用户名和密码查询用户
          UserInfo userInfo =  userInfoMapper.selectUserByUsernameAndPassword(username, MD5Utils.getMD5Code(password));
            if (userInfo==null){//用户名存在还是查询不到，说明密码是错的
                return ServerResponse.createServerResponseByError("密码错误");
            }
         //step4:处理结果并返回
            userInfo.setPassword("");//密码不能返回给前端，所以返回个空字符串
        return ServerResponse.createServerResponseBySuccess(null,userInfo);


    }
}
