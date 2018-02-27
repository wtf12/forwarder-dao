package com.guguangming.forwarder.dao;

import com.guguangming.forwarder.entity.UserInfoEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UserInfoDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * 移动端添加用户信息
     *
     * @param userInfoEntity
     * @return
     */
    public boolean addUserInfo(UserInfoEntity userInfoEntity) {
        String sql = "INSERT INTO user_info(user_name," +
                "phone," +
                "password," +
                "security_question_type," +
                "security_answer) VALUES (?,?,?,?,?)";
        return jdbcTemplate.update(sql, userInfoEntity) == 1;
    }

    /**
     * 查所有用户信息
     *
     * @return
     */
    public UserInfoEntity getUserInfo() {
        String sql = "SELECT * FROM user_info";
        return jdbcTemplate.queryForObject(sql, UserInfoEntity.class);
    }

    /**
     * 根据用户id查用户信息
     *
     * @param userId
     * @return
     */
    public UserInfoEntity getUserInfoByUserId(Integer userId) {
        String sql = "SELECT * FROM user_info WHERE user_id =" + userId;
        return jdbcTemplate.queryForObject(sql, UserInfoEntity.class);
    }

    /**
     * 根据手机号查用户信息
     *
     * @param phone
     * @return
     */
    public UserInfoEntity getUserInfoByPhone(String phone) {
        String sql = "SELECT * FROM user_info WHERE phone =" + phone;
        return jdbcTemplate.queryForObject(sql, UserInfoEntity.class);
    }

    /**
     * 根据手机号查用户头像路径
     *
     * @param phone
     * @return
     */
    public UserInfoEntity getUserHeadPortraitImgUrlByPhone(String phone) {
        String sql = "SELECT user_head_portrait_img_url FROM user_info WHERE phone =" + phone;
        return jdbcTemplate.queryForObject(sql, UserInfoEntity.class);
    }


    /**
     * 移动端注册时检查手机号是否已注册过
     *
     * @param phone
     * @return
     */
    public boolean checkSameByPhone(String phone) {
        String sql = "SELECT COUNT(1) FROM user_info WHERE phone =" + phone;
        return jdbcTemplate.update(sql) == 1;
    }

    /**
     * 移动端登录检验
     *
     * @param userInfoEntity
     * @return
     */
    public boolean checkLoginInfo(UserInfoEntity userInfoEntity) {
        String sql = "SELECT COUNT(1) FROM user_info WHERE phone =" + userInfoEntity.getPhone()
                + " && password =" + userInfoEntity.getPassword();
        return jdbcTemplate.update(sql) == 1;
    }

    /**
     * 移动端忘记密码时密保问题校验
     *
     * @param securityQuestionType
     * @param securityAnswer
     * @return
     */
    public boolean checkSecurityQuestion(Integer securityQuestionType, String securityAnswer) {
        String sql = "SELECT COUNT(1) FROM user_info WHERE security_question_type =" + securityQuestionType
                + " && security_answer =" + securityAnswer;
        return jdbcTemplate.update(sql) == 1;
    }

    /**
     * 移动端修改密码时对原密码校验
     *
     * @param phone
     * @param password
     * @return
     */
    public boolean checkPasswordWhenChange(String phone, String password) {
        String sql = "SELECT COUNT(1) FROM user_info WHERE phone =" + phone + " && password =" + password;
        return jdbcTemplate.update(sql) == 1;
    }

    /**
     * 移动端根据手机号修改密码
     *
     * @param password
     * @return
     */
    public boolean updatePasswordByUserId(String password, String phone) {
        String sql = "UPDATE user_info SET password =" + password + " WHERE phone =" + phone;
        return jdbcTemplate.update(sql) == 1;
    }

    /**
     * 移动端根据手机号修改用户名
     *
     * @param userName
     * @param phone
     * @return
     */
    public boolean updateUserNameByUserId(String userName, String phone) {
        String sql = "UPDATE user_info SET user_name =" + userName + " WHERE phone =" + phone;
        return jdbcTemplate.update(sql) == 1;
    }

    /**
     * 移动端根据手机号修改用户头像路径
     *
     * @param userHeadPortraitImgUrl
     * @param phone
     * @return
     */
    public boolean updateUserHeadPortraitImgUrlByPhone(String userHeadPortraitImgUrl, String phone) {
        String sql = "UPDATE user_info SET user_head_portrait_img_url =" + userHeadPortraitImgUrl + " WHERE phone =" + phone;
        return jdbcTemplate.update(sql) == 1;
    }
}