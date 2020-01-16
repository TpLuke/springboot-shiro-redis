package com.shiro.demo.common;

import lombok.Getter;
import lombok.Setter;

/**
 * @Description TODO
 * @Author Lenovo
 * @Date 2020/1/7 17:33
 * @Version 1.0
 */

public enum ErrorCode implements CommonError{

    //通用异常码
    SYSTEM_ERROR(1000,"服务器异常"),
    //无权限
    NO_PERMISSION(1001,"无操作权限"),

    //用户类型异常码
    USER_NOT_LOGIN(10000,"未登录"),
    USER_NOT_EXSIT(10001,"用户不存在"),
    USER_PASSWORD_ERROR(10002,"用户名密码错误")

    ;
    private int errorCode;
    private String errorMessage;

    ErrorCode(int errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }
    public int getErrorCode(){
        return this.errorCode;
    }
    public String getErrorMessage(){
        return this.errorMessage;
    }

    @Override
    public CommonError setErrorMessage(String errorMessage) {
         this.errorMessage = errorMessage;
         return this;
    }
}
