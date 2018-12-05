package com.neuedu.common;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * 封装返回前端的高复用对象
 * */

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)//意思是，转json的时候把空字段过滤掉不显示
public class ServerResponse<T> {
    //状态码
    private int status;
    //返回接口数据
    private T data;
    //接口提示信息
    private String msg;


    //私有的构造方法
    private ServerResponse() {
        this.status = status;
    }

    private ServerResponse(int status) {
        this.status = status;
    }

    private ServerResponse(int status, String msg) {
        this.status = status;
        this.msg = msg;
    }

    private ServerResponse(int status, String msg, T data ) {
        this.status = status;
        this.msg = msg;
        this.data = data;
    }



    /**
     * 判断接口是否调用成功
     * */
    @JsonIgnore//转json的时候把这个字段忽略掉
    public boolean isSuccess(){
        return this.status==Const.SUCCESS_CODE;
    }



    /**
     * 成功
     **/

    public static ServerResponse createServerResponseBySuccess(){

        return new ServerResponse(Const.SUCCESS_CODE);
    }

    public static ServerResponse createServerResponseBySuccess(String msg){

        return new ServerResponse(Const.SUCCESS_CODE,msg);
    }

    public static<T> ServerResponse createServerResponseBySuccess(String msg,T data){

        return new ServerResponse(Const.SUCCESS_CODE,msg,data);
    }


    /**
     * 失败
     **/


    public static ServerResponse createServerResponseByError(){

        return new ServerResponse(Const.SUCCESS_ERROR);
    }
//状态码和返回信息
    public static ServerResponse createServerResponseByError(String msg){

        return new ServerResponse(Const.SUCCESS_ERROR,msg);
    }
//状态码自定义
    public static ServerResponse createServerResponseByError(int status){

        return new ServerResponse(status);
    }

    public static ServerResponse createServerResponseByError(int status,String msg){

        return new ServerResponse(status,msg);
    }








    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
