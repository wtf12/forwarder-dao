package com.guguangming.forwarder.dao;

import com.guguangming.forwarder.entity.AdministratorInfoEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;


@Repository
public class AdministratorInfoDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * PC端后台登录信息检查
     *
     * @param administratorInfoEntity
     * @return
     */
    public boolean checkLoginInfo(AdministratorInfoEntity administratorInfoEntity) {
        String sql = "SELECT COUNT(1) FROM administrator_info WHERE administrator_id = "
                + administratorInfoEntity.getAdministratorId() + " AND administrator_name = '"
                + administratorInfoEntity.getAdministratorName() + "' AND password = '"
                + administratorInfoEntity.getPassword() + "'";
        return jdbcTemplate.queryForObject(sql,Integer.class) == 1;

    }

}