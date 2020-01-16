package com.shiro.demo.common;

import com.shiro.demo.entity.Perm;
import com.shiro.demo.entity.User;
import com.shiro.demo.service.IUserService;
import com.xiaoleilu.hutool.util.CollectionUtil;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @Description TODO
 * @Author Lenovo
 * @Date 2020/1/7 17:02
 * @Version 1.0
 */
@Slf4j
public class UserRealm extends AuthorizingRealm {

    private IUserService userService;

    public void setUserService(IUserService userService){
        this.userService = userService;
    }

    /**
     * @Description 权限定义
     * @Date 2020/1/7 17:13
     * @Author Lenovo
     * @Param principalCollection
     * @return org.apache.shiro.authz.AuthorizationInfo
     */

    @SneakyThrows
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        User  user = (User)principalCollection.getPrimaryPrincipal();
        if(user == null){
            throw new BussinessException(ErrorCode.USER_NOT_LOGIN);
        }

        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        Set<String> roles = new HashSet<>();
        Set<String> stringPermissions = new HashSet<>();
//        roles.add("USER");
//        stringPermissions.add("USER:DELETE");
//        stringPermissions.add("/user/queryUserInfo");
        /*
         * @Description 只对具体权限做校验，不对角色做校验
         *  通过用户Id，查询出用户所拥有的权限信息
         * @Date 2020/1/16 11:09
         * @Author Lenovo
         * @Param principalCollection
         * @return org.apache.shiro.authz.AuthorizationInfo
         */
        List<Perm> permList = userService.getPermssions(user.getId());

        log.info("==========query User permissions,size:{}===========",permList == null ? 0 : permList.size());
        if(CollectionUtil.isNotEmpty(permList)){
            for(Perm perm:permList){
                stringPermissions.add(perm.getPermUrl());
            }
        }

        info.setRoles(roles);
        info.setStringPermissions(stringPermissions);


        return info;
    }

    /**
     * @Description 身份校验
     * @Date 2020/1/7 17:13
     * @Author Lenovo
     * @Param null
     * @return null
     */
    @SneakyThrows
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException{
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        String userName = token.getUsername();

        //查询数据库获取用户信息
        User user = userService.getUserByUserName(userName);
        if(user == null){
            throw new UnknownAccountException();
        }

        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(user,user.getPassword().toCharArray(),getName());


        return authenticationInfo;
    }
}
