package com.neuedu.controller.backend;

import com.neuedu.common.Const;
import com.neuedu.common.ResponseCode;
import com.neuedu.common.ServerResponse;
import com.neuedu.pojo.UserInfo;
import com.neuedu.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping(value = "/manage/category/")
public class CategoryManageController {

    @Autowired
    ICategoryService iCategoryService;


    /**
     * 获取品类的子节点(平级)
     * */
@RequestMapping(value = "get_category.do")
    public ServerResponse get_category(HttpSession session, Integer categoryId){
       //想要获取子节点，必须管理员操作，从session里面把管理员信息拿到
        UserInfo userInfo = (UserInfo) session.getAttribute(Const.CURRENTUSER);
       //如果用户为空的话，那么说明用户未登陆
         if (userInfo==null){
             return ServerResponse.createServerResponseByError(ResponseCode.USER_NOT_LOGIN.getStatus(),ResponseCode.USER_NOT_LOGIN.getMsg());
         }
        //登陆的话要判断用户权限，是不是管理员
        if (userInfo.getRole()!=Const.USER_ROLE_ADMIN){
             return ServerResponse.createServerResponseByError(ResponseCode.NO_PRIVILEGE.getStatus(),ResponseCode.NO_PRIVILEGE.getMsg());
        }

    return iCategoryService.get_category(categoryId);
    }

    /**
     * 增加节点
     * */
    @RequestMapping(value = "add_category.do")
    public ServerResponse add_category(HttpSession session,
                                       //参数可传可不传，用这个注释，说明这个参数不是必须的，默认值是0，即使是数字也要加""
                                       @RequestParam(required = false,defaultValue = "0") Integer perentId,
                                       String categoryName){
        //想要获取子节点，必须管理员操作，从session里面把管理员信息拿到
        UserInfo userInfo = (UserInfo) session.getAttribute(Const.CURRENTUSER);
        //如果用户为空的话，那么说明用户未登陆
        if (userInfo==null){
            return ServerResponse.createServerResponseByError(ResponseCode.USER_NOT_LOGIN.getStatus(),ResponseCode.USER_NOT_LOGIN.getMsg());
        }
        //登陆的话要判断用户权限，是不是管理员
        if (userInfo.getRole()!=Const.USER_ROLE_ADMIN){
            return ServerResponse.createServerResponseByError(ResponseCode.NO_PRIVILEGE.getStatus(),ResponseCode.NO_PRIVILEGE.getMsg());
        }

        return iCategoryService.add_category(perentId,categoryName);
    }


    /**
     * 修改节点
     * */
    @RequestMapping(value = "set_category_name.do")
    public ServerResponse set_category_name(HttpSession session,
                                       Integer categoryId,
                                       String categoryName){
        //想要获取子节点，必须管理员操作，从session里面把管理员信息拿到
        UserInfo userInfo = (UserInfo) session.getAttribute(Const.CURRENTUSER);
        //如果用户为空的话，那么说明用户未登陆
        if (userInfo==null){
            return ServerResponse.createServerResponseByError(ResponseCode.USER_NOT_LOGIN.getStatus(),ResponseCode.USER_NOT_LOGIN.getMsg());
        }
        //登陆的话要判断用户权限，是不是管理员
        if (userInfo.getRole()!=Const.USER_ROLE_ADMIN){
            return ServerResponse.createServerResponseByError(ResponseCode.NO_PRIVILEGE.getStatus(),ResponseCode.NO_PRIVILEGE.getMsg());
        }

        return iCategoryService.set_category_name(categoryId,categoryName);
    }


    /**
     * 获取当前分类id及递归子节点categoryId
     * */
    @RequestMapping(value = "get_deep_category.do")
    public ServerResponse get_deep_category(HttpSession session,
                                            Integer categoryId){
        //想要获取子节点，必须管理员操作，从session里面把管理员信息拿到
        UserInfo userInfo = (UserInfo) session.getAttribute(Const.CURRENTUSER);
        //如果用户为空的话，那么说明用户未登陆
        if (userInfo==null){
            return ServerResponse.createServerResponseByError(ResponseCode.USER_NOT_LOGIN.getStatus(),ResponseCode.USER_NOT_LOGIN.getMsg());
        }
        //登陆的话要判断用户权限，是不是管理员
        if (userInfo.getRole()!=Const.USER_ROLE_ADMIN){
            return ServerResponse.createServerResponseByError(ResponseCode.NO_PRIVILEGE.getStatus(),ResponseCode.NO_PRIVILEGE.getMsg());
        }

        return iCategoryService.get_deep_category(categoryId);
    }



}
