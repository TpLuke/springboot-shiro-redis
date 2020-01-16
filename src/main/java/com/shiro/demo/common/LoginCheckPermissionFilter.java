package com.shiro.demo.common;

import com.xiaoleilu.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authz.AuthorizationFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Description TODO
 * @Author Lenovo
 * @Date 2020/1/7 17:04
 * @Version 1.0
 */
@Slf4j
public class LoginCheckPermissionFilter extends AuthorizationFilter {

    @Override
    protected boolean isAccessAllowed(ServletRequest servletRequest, ServletResponse servletResponse, Object o) throws Exception {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        String url = req.getRequestURI();
        try{
            Subject subject = SecurityUtils.getSubject();
            boolean isPermitted = subject.isPermitted(url);
            return isPermitted;
        }catch (Exception e){
            log.error("check perssion error",e);
        }
        return false;
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws IOException {
        HttpServletResponse httpResponse = (HttpServletResponse)response;
        Subject subject = SecurityUtils.getSubject();
        if (subject.getPrincipal() == null){
            saveRequestAndRedirectToLogin(request,response);
        }else{
            CommonResponse response_ = CommonResponse.buildFail(ErrorCode.NO_PERMISSION);
            httpResponse.setCharacterEncoding("UTF-8");
            httpResponse.setContentType("application/json");
            httpResponse.getWriter().write(JSONUtil.toJsonStr(response_));
        }
        return false;
    }
}
