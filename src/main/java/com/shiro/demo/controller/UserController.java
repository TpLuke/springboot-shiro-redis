package com.shiro.demo.controller;

import com.shiro.demo.common.BussinessException;
import com.shiro.demo.common.CommonResponse;
import com.shiro.demo.common.ErrorCode;
import com.shiro.demo.entity.User;
import com.shiro.demo.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.awt.*;

/**
 * @Description TODO
 * @Author Lenovo
 * @Date 2020/1/7 17:18
 * @Version 1.0
 */
@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {
    @Resource
    private IUserService userService;
    @PostMapping(value = "/login",consumes = {MediaType.APPLICATION_JSON_UTF8_VALUE},produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    @ResponseBody
    public CommonResponse ckeckLogin(String userName,String password, boolean rememberMe){
        try {
            UsernamePasswordToken token = new UsernamePasswordToken(userName,password,rememberMe);
            Subject subject = SecurityUtils.getSubject();
            subject.login(token);
        }catch(UnknownAccountException be){
            return CommonResponse.buildFail(ErrorCode.USER_NOT_EXSIT);
        }catch(IncorrectCredentialsException ice){
            return CommonResponse.buildFail(ErrorCode.USER_PASSWORD_ERROR);
        }catch(Exception e) {
            log.error("登录失败",e);
            return CommonResponse.buildFail(ErrorCode.SYSTEM_ERROR,"登录失败");
        }

        return CommonResponse.buildOk(null);
    }

    @GetMapping(value = "/logout")
    @ResponseBody
    public CommonResponse logout(){
        Subject subject = SecurityUtils.getSubject();
        if(subject.isAuthenticated()){
            subject.logout();
        }else{
            return CommonResponse.buildFail(ErrorCode.SYSTEM_ERROR,"非法操作");
        }
        return CommonResponse.buildOk(null);
    }

    @GetMapping(value="/queryUserInfo")
    @ResponseBody
    public CommonResponse querUserInfo(@RequestParam("id") Long id){

        User user = userService.getUserById(id);
        if(user == null){
            return CommonResponse.buildFail(ErrorCode.USER_NOT_EXSIT);
        }else{
            return CommonResponse.buildOk(user);
        }

    }
}
