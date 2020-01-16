package com.shiro.demo.service;

import com.shiro.demo.entity.Perm;
import com.shiro.demo.entity.User;

import java.util.List;

/**
 * @Description TODO
 * @Author Lenovo
 * @Date 2020/1/7 17:24
 * @Version 1.0
 */
public interface IUserService {
    public User getUserByUserName(String userName);

    public List<Perm> getPermssions(long userId);

    public User getUserById(long userId);

}
