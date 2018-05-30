package com.ecnu.relax.service.impl;

import com.ecnu.relax.mapper.UserMapper;
import com.ecnu.relax.model.User;
import com.ecnu.relax.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends BaseServiceImpl implements IUserService {

    @Autowired
    UserMapper userMapper;

    @Override
    public int login(String phone, String password) {
        int result = 0;
        User user = userMapper.selectByPhone(phone);
        if (user == null) {
            //该用户不存在
            result = -1;
        } else if (!user.getPassword().equals(password)) {
            //学号和密码不匹配
            result = 0;
        } else {
            //登录成功
            result = user.getUserId();
        }
        return result;
    }

    @Override
    public int register(String nickname,String realname,String phone, String password) {
        int result = 0;
        User user = userMapper.selectByPhone(phone);
        if (user != null) {
            //已经被注册
            result = -1;
        } else {
            user = new User(nickname,realname,phone,password);
            userMapper.insertSelective(user);
            result = user.getUserId();
        }
        return result;
    }
}

