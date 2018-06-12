package com.ecnu.relax.controller.api;

import com.ecnu.relax.model.User;
import com.ecnu.relax.dto.BaseJson;
import com.ecnu.relax.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController extends APIBaseController{
    @Autowired
    private IUserService userService;

    @RequestMapping(value="/login", method = RequestMethod.GET)
    public User login(@RequestParam("phone")String phone, @RequestParam("password") String password){
        String message;
        User user = userService.login(phone,password);
        int userId = user.getUserId();
        switch (userId){
            case -1:
                message = "没有该用户";
                break;
            case 0:
                message = "密码错误";
                break;
            default:
                //登录成功
                HttpSession session = request.getSession();
                session.setAttribute("userId", userId);
                message = "登录成功";
                break;
        }
        return user;
    }

    @RequestMapping(value="/register", method = RequestMethod.GET)
    public int register(@RequestParam("nickname")String nickname,@RequestParam("realname")String realname,@RequestParam("phone")String phone, @RequestParam("password") String password){
        int registerResult = 0;
        String message;
        registerResult = userService.register(nickname,realname,phone,password);
        switch (registerResult){
            case -1:
                message = "该手机号已经被注册";
                break;
            default:
                //注册成功
                HttpSession session = request.getSession();
                session.setAttribute("userId", registerResult);
                message = "注册成功";
                break;
        }
        return registerResult;
    }

    @RequestMapping(value="/getCurrentUserInfo", method = RequestMethod.GET)
    public User getCurrentUserInfo(@RequestParam("userId")Integer userId){
        User user = userService.getCurrentUserInfo(userId);
        return user;
    }

    @RequestMapping(value="/editUserPassword", method = RequestMethod.GET)
    public int editUserPassword(@RequestParam("userId")Integer userId,@RequestParam("curPwd")String curPwd,@RequestParam("newPwd")String newPwd){
        return userService.editUserPassword(userId,curPwd,newPwd);
    }

    @RequestMapping(value="/getTypes", method = RequestMethod.GET)
    public List<Integer> getTypes(@RequestParam("specialistId")String specialistId){
        List<Integer> result = new ArrayList<>();
        result = userService.getTypesBySpecialistId(specialistId);
        return result;
    }

}
