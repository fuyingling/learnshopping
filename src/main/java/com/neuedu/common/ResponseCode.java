package com.neuedu.common;
/**
 * 定义响应状态码
 * 定义成枚举类型
 * */
public enum ResponseCode {

    PARAM_EMPTY(2,"参数为空"),
    EXISTS_USERNAME(3,"用户名已经存在"),
    EXISTS_EMAIL(4,"邮箱已经存在"),



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
