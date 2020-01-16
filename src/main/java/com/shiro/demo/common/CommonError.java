package com.shiro.demo.common;

/**
 * @Description TODO
 * @Author Lenovo
 * @Date 2020/1/7 17:55
 * @Version 1.0
 */
public interface CommonError {
    public int getErrorCode();
    public String getErrorMessage();
    public CommonError setErrorMessage(String errorMessage);
}
