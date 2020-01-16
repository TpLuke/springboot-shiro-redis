package com.shiro.demo.common;

import lombok.Getter;
import lombok.Setter;

/**
 * @Description TODO
 * @Author Lenovo
 * @Date 2020/1/8 9:39
 * @Version 1.0
 */
@Setter
@Getter
public class CommonResponse {
    private int code;
    private String message;
    private Object data;


    public CommonResponse(int code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static CommonResponse buildFail(CommonError commonError){
        return new CommonResponse(commonError.getErrorCode(),commonError.getErrorMessage(),null);
    }

    public static CommonResponse buildFail(CommonError commonError,String errMsg){
        commonError.setErrorMessage(errMsg);
        return new CommonResponse(commonError.getErrorCode(),commonError.getErrorMessage(),null);
    }

    public static CommonResponse buildOk(Object data){
        return new CommonResponse(0,"请求成功",data);
    }
}
