package com.guguangming.forwarder.dao;

import com.guguangming.forwarder.entity.CompanySynopsisEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class CompanySynopsisDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * 查找公司简介
     *
     * @return
     */
    public CompanySynopsisEntity getCompanySynopsis() {
        String sql = "SELECT synopsis FROM company_synopsis WHERE id = 1";
        return jdbcTemplate.queryForObject(sql, CompanySynopsisEntity.class);
    }

    /**
     * 更新公司简介
     *
     * @param synopsis
     * @return
     */
    public boolean updateCompanySynopsis(String synopsis) {
        String sql = "UPDATE company_synopsis SET synopsis = " + synopsis + " WHERE id = 1";
        return jdbcTemplate.update(sql) == 1;
    }
}