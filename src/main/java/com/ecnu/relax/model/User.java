package com.ecnu.relax.model;

public class User {
    private Integer userId;

    private String nickname;

    private String phone;

    private String password;

    private String realName;

    private Integer identity;

    private Double remainder;

    private String portrait;

    private Double relaxDegree;

    public User(){

    }
    public User(int userId){
        this.userId = userId;
    }

    public User(String nickname,String realname,String phone,String pwd) {
        this.nickname = nickname;
        this.phone = phone;
        this.password = pwd;
        this.realName = realname;
        this.identity = 0;
        this.remainder = 0.0;
        this.portrait = "1";
        this.relaxDegree = 80.0;
    }


    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname == null ? null : nickname.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName == null ? null : realName.trim();
    }

    public Integer getIdentity() {
        return identity;
    }

    public void setIdentity(Integer identity) {
        this.identity = identity;
    }

    public Double getRemainder() {
        return remainder;
    }

    public void setRemainder(Double remainder) {
        this.remainder = remainder;
    }

    public String getPortrait() {
        return portrait;
    }

    public void setPortrait(String portrait) {
        this.portrait = portrait == null ? null : portrait.trim();
    }

    public Double getRelaxDegree() {
        return relaxDegree;
    }

    public void setRelaxDegree(Double relaxDegree) {
        this.relaxDegree = relaxDegree;
    }
}