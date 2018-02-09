package com.guguangming.forwarder.po;

import lombok.Data;

import com.moxie.commons.annotation.*;
import java.util.*;

@Data
public class UserInfoHeadPortraitPo {
    @PrimaryKey
    private Integer id;
    private String phone;
    private String userHeadPortraitImgUrl;
}