package com.neuedu.common;

import sun.security.util.Password;

/**
 * 定义响应状态码
 * 定义成枚举类型
 * */
public enum ResponseCode {

    PARAM_EMPTY(2,"参数为空"),
    EXISTS_USERNAME(3,"用户名已经存在"),
    EXISTS_EMAIL(4,"邮箱已经存在"),
    NOT_EXISTS_USERNAME(5,"用户名不存在"),
    USER_NOT_LOGIN(6,"用户未登录"),
    SECURITY_QUESTION_EMPTY(7,"密保问题为空"),
    ANSWER_ERROR(8,"答案错误"),
    PASSWORD_MODIFICATION_FAILED(9,"密码修改失败"),
    PASSWORD_MODIFICATION_SUCCESSFULLY(10,"密码修改成功"),
    NO_PRIVILEGE(11,"没有权限操作")

    ;

    private int status;
    private String msg;

    ResponseCode() {
    }

    ResponseCode(int status, String msg) {
        this.status = status;
        this.msg = msg;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
