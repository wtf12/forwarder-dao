package com.guguangming.forwarder.po;

import lombok.Data;

import com.moxie.commons.annotation.*;
import java.util.*;

@Data
public class UserInfoPo {
    @PrimaryKey
    private Integer id;
    private String userName;
    private String phone;
    private String password;
    private Integer securityQuestionType;
    private String securityAnswer;
}