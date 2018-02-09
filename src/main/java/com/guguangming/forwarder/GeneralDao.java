package com.guguangming.forwarder;

import com.google.common.collect.Lists;
import com.moxie.commons.BaseDaoUtils;
import com.moxie.commons.model.DbInfo;

import java.util.List;

/**
 * Created by FUFAN on 2018/2/8.
 */
public class GeneralDao {
    public static void main(String[] args) throws Exception {
        DbInfo dbInfo = new DbInfo("jdbc:mysql://localhost:3306/forwarder?zeroDateTimeBehavior=convertToNull&characterEncoding=UTF-8", "root", "123456");
        List<String> list = Lists.newArrayList(
                "user_info",
                "company_synopsis",
                "business_scope",
                "certificate",
                "project_success",
                "employees",
                "order",
                "check_notice",
                "important_announcement",
                "company_synopsis_banner",
                "home_banner",
                "news",
                "sailing_schedule",
                "user_info_head_portrait"
        );
        BaseDaoUtils.generateCode(dbInfo,list,"com.guguangming.forwarder");
    }
}
