package com.guguangming.forwarder.entity;


public class UserInfoEntity {

    //用户编号
    private Integer userId;

    //用户名
    private String userName;

    //手机号
    private String phone;

    //密码
    private String password;

    //密保问题类型
    private Integer securityQuestionType;

    //密保答案
    private String securityAnswer;

    //用户头像路径
    private String userHeadPortraitImgUrl;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getSecurityQuestionType() {
        return securityQuestionType;
    }

    public void setSecurityQuestionType(Integer securityQuestionType) {
        this.securityQuestionType = securityQuestionType;
    }

    public String getSecurityAnswer() {
        return securityAnswer;
    }

    public void setSecurityAnswer(String securityAnswer) {
        this.securityAnswer = securityAnswer;
    }

    public String getUserHeadPortraitImgUrl() {
        return userHeadPortraitImgUrl;
    }

    public void setUserHeadPortraitImgUrl(String userHeadPortraitImgUrl) {
        this.userHeadPortraitImgUrl = userHeadPortraitImgUrl;
    }
}