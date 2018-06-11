package com.ecnu.relax.service;

import java.util.List;

public interface IUserService extends BaseService{
    int login(String phone,String password);
    int register(String nickname,String realname,String phone,String password);
    List<Integer> getTypesBySpecialistId(String specialistId);
}
