package com.ecnu.relax.service;

import com.ecnu.relax.model.User;

public interface IUserService extends BaseService{
    int login(String phone,String password);
    int register(String nickname,String realname,String phone,String password);
    User getCurrentUserInfo(int userId);
    int editUserPassword(int userId,String curPwd,String newPwd);
}
