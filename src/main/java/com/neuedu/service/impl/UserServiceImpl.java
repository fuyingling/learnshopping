package com.neuedu.service.impl;

import com.neuedu.common.Const;
import com.neuedu.common.ResponseCode;
import com.neuedu.common.ServerResponse;
import com.neuedu.common.TokenCache;
import com.neuedu.dao.UserInfoMapper;
import com.neuedu.pojo.UserInfo;
import com.neuedu.service.IUserService;
import com.neuedu.utils.MD5Utils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.UUID;

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
        //int count =userInfoMapper.checkUsername(username);
       /* if (count>0){//说明用户名已经存在
            return ServerResponse.createServerResponseByError(ResponseCode.EXISTS_USERNAME.getStatus(),ResponseCode.EXISTS_USERNAME.getMsg());
        }*/
        ServerResponse serverResponse = check_valid(username,Const.USERNAME);
        if (!serverResponse.isSuccess()){
            return ServerResponse.createServerResponseByError(ResponseCode.EXISTS_USERNAME.getStatus(),ResponseCode.EXISTS_USERNAME.getMsg());
        }

        //step3:判断邮箱是否已经存在
              /* int result = userInfoMapper.checkEmail(userInfo.getEmail());
                    if (result>0){//邮箱已经存在
                       return ServerResponse.createServerResponseByError(ResponseCode.EXISTS_EMAIL.getStatus(),ResponseCode.EXISTS_EMAIL.getMsg());
                    }*/
        ServerResponse email_serverResponse = check_valid(userInfo.getEmail(),Const.EMAIL);
        if (!email_serverResponse.isSuccess()){
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
        //step2:检查username是否存在,调用下面的方法
         /* int result=  userInfoMapper.checkUsername(username);
            if (result<=0){//用户名不存在
                return ServerResponse.createServerResponseByError("用户名不存在");
            }*/

        ServerResponse serverResponse = check_valid(username,Const.USERNAME);
        if (serverResponse.isSuccess()){
            return ServerResponse.createServerResponseByError(ResponseCode.NOT_EXISTS_USERNAME.getStatus(),ResponseCode.NOT_EXISTS_USERNAME.getMsg());
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

    /***
     * 检查用户名或邮箱是否存在
     *
     * */
    @Override
    public ServerResponse check_valid(String str, String type) {
       //step1:参数非空校验
        if (StringUtils.isBlank(str)||StringUtils.isBlank(type)){
            return ServerResponse.createServerResponseByError("参数不能为空");
        }
        //step2:判断用户名或者邮箱是否存在
            if (type.equals(Const.USERNAME)){
           int username_result= userInfoMapper.checkUsername(str);
           if (username_result>0){//用户名已经存在
               return ServerResponse.createServerResponseByError(ResponseCode.EXISTS_USERNAME.getStatus(),ResponseCode.EXISTS_USERNAME.getMsg());
           }
           return ServerResponse.createServerResponseBySuccess("成功");
        }else if(type.equals(Const.EMAIL)){
                int email_result= userInfoMapper.checkEmail(str);
                if (email_result>0){//邮箱已经存在
                    return ServerResponse.createServerResponseByError(ResponseCode.EXISTS_EMAIL.getStatus(),ResponseCode.EXISTS_EMAIL.getMsg());
                }
                return ServerResponse.createServerResponseBySuccess("成功");
        }
        //step3:返回结果
        return ServerResponse.createServerResponseByError("type参数传递有误");
    }


    //根据用户名找回密保问题接口
    @Override
    public ServerResponse forget_get_question(String username) {
       //step1:参数的非空校验 username==null||username.equals("")
        if (StringUtils.isBlank(username)){
                return ServerResponse.createServerResponseByError("用户名不能为空");
        }
        //step2:校验用户名username
            int result =userInfoMapper.checkUsername(username);
            if (result==0){
                return ServerResponse.createServerResponseByError(ResponseCode.NOT_EXISTS_USERNAME.getStatus(),ResponseCode.NOT_EXISTS_USERNAME.getMsg());
            }
        //step3:查找密保问题
          String question = userInfoMapper.selectQuestByUsername(username);
            if (StringUtils.isBlank(question)){
                return ServerResponse.createServerResponseByError(ResponseCode.SECURITY_QUESTION_EMPTY.getStatus(),ResponseCode.SECURITY_QUESTION_EMPTY.getMsg());
            }
            //step4:返回结果
        return ServerResponse.createServerResponseBySuccess(null,question);
    }

    //提交问题答案接口
    @Override
    public ServerResponse forget_check_answer(String username, String question, String answer) {
        //step1:参数校验
        if (StringUtils.isBlank(username)||StringUtils.isBlank(question)||StringUtils.isBlank(answer)){
            return ServerResponse.createServerResponseByError(ResponseCode.PARAM_EMPTY.getStatus(),ResponseCode.PARAM_EMPTY.getMsg());
        }

        //step2:根据username,question,answer查询，校验答案
        int count = userInfoMapper.selectByUsernameAndQuestAndAnswer(username, question, answer);
        if (count<=0){
            return ServerResponse.createServerResponseByError(ResponseCode.ANSWER_ERROR.getStatus(),ResponseCode.ANSWER_ERROR.getMsg());
        }
        //服务端生成token保存并将token返回给客户端，返回用户的唯一标识--->     username--->token

        //UUID随机生成的唯一的字符串
        String user_token=UUID.randomUUID().toString();
        TokenCache.put(username, user_token);
        //或者
        /*
        * String user_token = UUID.randomUUID().toString();
        * TokenCache.put(username,user_token);
        *
        * */

        //step3:返回结果
        return ServerResponse.createServerResponseBySuccess(null,user_token);
    }

    //忘记密码的重设密码接口
    @Override
    public ServerResponse forget_reset_password(String username, String passwordNew,String forgetToken) {
       //step1:参数的非空校验
        if (username==null||username.equals("")){
            return ServerResponse.createServerResponseByError(ResponseCode.PARAM_EMPTY.getStatus(),ResponseCode.PARAM_EMPTY.getMsg());
        }
        if (passwordNew==null||passwordNew.equals("")){
            return ServerResponse.createServerResponseByError(ResponseCode.PARAM_EMPTY.getStatus(),ResponseCode.PARAM_EMPTY.getMsg());
        }
        if (forgetToken==null||forgetToken.equals("")){
            return ServerResponse.createServerResponseByError(ResponseCode.PARAM_EMPTY.getStatus(),ResponseCode.PARAM_EMPTY.getMsg());
        }

        //step3:校验token
        String token =TokenCache.get(username);
        if (token==null||token.equals("")){
            return ServerResponse.createServerResponseByError("token不存在或者过期");
        }
        if (!token.equals(forgetToken)){
            return ServerResponse.createServerResponseByError("token不一致,请不要修改别人的密码哦");
        }

        //step2:更新密码
int count =userInfoMapper.updatepasswordByUsername(username, MD5Utils.getMD5Code(passwordNew));
        if (count<=0){
            return ServerResponse.createServerResponseByError("密码修改失败");
        }

        //step3:返回结果

        return ServerResponse.createServerResponseBySuccess();
    }

    @Override
    public ServerResponse reset_password(UserInfo userInfo, String passwordOld, String passwordNew) {
      //step1:参数的非空校验
        if(passwordOld==null || passwordOld.equals("")){
            return ServerResponse.createServerResponseByError(ResponseCode.PARAM_EMPTY.getStatus(),ResponseCode.PARAM_EMPTY.getMsg());
        }
        if (passwordNew==null || passwordNew.equals("")){
            return ServerResponse.createServerResponseByError(ResponseCode.PARAM_EMPTY.getStatus(),ResponseCode.PARAM_EMPTY.getMsg());
        }


        //step2:校验旧密码是否正确
        UserInfo userInfoOld =  userInfoMapper.selectUserByUsernameAndPassword(userInfo.getUsername(),MD5Utils.getMD5Code(passwordOld));
        if (userInfoOld==null){
            return ServerResponse.createServerResponseByError("旧密码错误");
        }

        //step3:修改密码
      int count =  userInfoMapper.updatepasswordByUsername(userInfo.getUsername(),MD5Utils.getMD5Code(passwordNew));

        //step4:返回结果
            if (count<=0){
                return ServerResponse.createServerResponseByError(ResponseCode.PASSWORD_MODIFICATION_FAILED.getStatus(),ResponseCode.PASSWORD_MODIFICATION_FAILED.getMsg());
            }


        return ServerResponse.createServerResponseBySuccess(ResponseCode.PASSWORD_MODIFICATION_SUCCESSFULLY.getMsg());
    }


}

