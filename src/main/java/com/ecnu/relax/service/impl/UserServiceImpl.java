package com.ecnu.relax.service.impl;

import com.ecnu.relax.mapper.SpecialistTypeMapper;
import com.ecnu.relax.mapper.UserMapper;
import com.ecnu.relax.model.SpecialistTypeKey;
import com.ecnu.relax.model.User;
import com.ecnu.relax.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl extends BaseServiceImpl implements IUserService {

    @Autowired
    UserMapper userMapper;
    @Autowired
    SpecialistTypeMapper specialistTypeMapper;

    @Override
    public User login(String phone, String password) {
        int result = 0;
        User user = userMapper.selectByPhone(phone);
        if (user == null) {
            //该用户不存在
            user = new User(-1);
        } else if (!user.getPassword().equals(password)) {
            //学号和密码不匹配
            user = new User(0);
        } else {
            //登录成功
        }
        return user;
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

    @Override
    public User getCurrentUserInfo(int userId) {
        User user = userMapper.selectByPrimaryKey(userId);
        return user;
    }

    @Override
    public int editUserPassword(int userId,String curPwd,String newPwd){
        User user = userMapper.selectByPrimaryKey(userId);
        int result = 1;
        if(user.getPassword().equals(curPwd)) {
            user.setPassword(newPwd);
            userMapper.updateByPrimaryKey(user);
            result = 0;
        }
        return result;
    }

    @Override
    public List<Integer> getTypesBySpecialistId(String specialistId) {
        List<SpecialistTypeKey> specialistTypeKeys = specialistTypeMapper.getSpecialistTypes(Integer.parseInt(specialistId));
        List<Integer> result = new ArrayList<>();
        for (int i = 0;i<specialistTypeKeys.size();i++)
            result.add(specialistTypeKeys.get(i).getTypeId());
        return result;
    }
}

