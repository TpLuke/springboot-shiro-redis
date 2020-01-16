package com.shiro.demo.common;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.credential.SimpleCredentialsMatcher;
import org.apache.shiro.util.SimpleByteSource;

/**
 * @Description TODO
 * @Author Lenovo
 * @Date 2020/1/7 17:04
 * @Version 1.0
 */
public class BugCredentialsMatcher extends SimpleCredentialsMatcher {

    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
        //对前台传入的明文密码数据加密，根据自定义加密规则
        Object tockenCredential = new SimpleByteSource((char[])token.getCredentials());
        //从数据库获取的密码
        Object accunt = new SimpleByteSource((char[])info.getCredentials());
        //返回对比结果
        return equals(accunt, tockenCredential);
    }
}

