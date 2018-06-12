package com.ecnu.relax.service;

import com.ecnu.relax.model.User;

import java.util.List;

public interface IUserService extends BaseService{
    User login(String phone,String password);
    int register(String nickname,String realname,String phone,String password);
    User getCurrentUserInfo(int userId);
    int editUserPassword(int userId,String curPwd,String newPwd);
    List<Integer> getTypesBySpecialistId(String specialistId);
}
