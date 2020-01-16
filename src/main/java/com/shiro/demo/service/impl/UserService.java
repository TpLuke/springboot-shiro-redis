package com.shiro.demo.service.impl;

import com.shiro.demo.dao.PermMapper;
import com.shiro.demo.dao.UserMapper;
import com.shiro.demo.entity.Perm;
import com.shiro.demo.entity.User;
import com.shiro.demo.service.IUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description TODO
 * @Author Lenovo
 * @Date 2020/1/7 17:24
 * @Version 1.0
 */
@Service
public class UserService implements IUserService {
    @Resource
    private UserMapper userMapper;
    @Resource
    private PermMapper permMapper;
    @Override
    public User getUserByUserName(String userName) {
        User user = userMapper.queryUserByName(userName);
        return user;
    }

    public List<Perm> getPermssions(long userId){
        List<Perm> perms = permMapper.selectByUser(userId);
        return perms;
    }

    @Override
    public User getUserById(long userId) {
        User user = userMapper.selectByPrimaryKey(userId);
        return user;
    }
}
