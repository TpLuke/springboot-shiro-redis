package com.shiro.demo.common;

import lombok.Getter;
import lombok.Setter;

/**
 * @Description TODO
 * @Author Lenovo
 * @Date 2020/1/7 17:29
 * @Version 1.0
 */

public class BussinessException extends Exception implements CommonError{
    private int errorCode;
    private String errorMessage;
    private CommonError commonError;

    public BussinessException(CommonError commonError){
        super();
        this.commonError = commonError;
    }

    public BussinessException(CommonError commonError,String errorMsg){
        super();
        this.commonError = commonError;
        this.commonError.setErrorMessage(errorMessage);
    }


    @Override
    public int getErrorCode() {
        return this.commonError.getErrorCode();
    }

    @Override
    public String getErrorMessage() {
        return this.commonError.getErrorMessage();
    }

    @Override
    public CommonError setErrorMessage(String errorMessage) {
        return this.commonError.setErrorMessage(errorMessage);
    }
}
