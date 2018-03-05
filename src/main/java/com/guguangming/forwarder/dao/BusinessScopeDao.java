package com.guguangming.forwarder.dao;

import com.guguangming.forwarder.entity.BusinessScopeEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;


/**
 *
 */
@Repository
public class BusinessScopeDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;


    /**
     * 添加公司业务范围信息
     *
     * @param businessScopeEntity
     * @return
     */
    public boolean addBusinessScope(BusinessScopeEntity businessScopeEntity) {
        String sql = "INSERT INTO business_scope(business_name, " +
                "describe_keyword, " +
                "business_content) VALUES(?,?,?)";
        return jdbcTemplate.update(sql, businessScopeEntity) == 1;
    }

    /**
     * 查找所有业务范围内容
     *
     * @return
     */
    public BusinessScopeEntity getBusinessScopeContent() {
        String sql = "SELECT * FROM business_scope";
        return jdbcTemplate.queryForObject(sql, BusinessScopeEntity.class);
    }

    /**
     * 通过业务名称查找业务范围内容
     *
     * @param businessName
     * @return
     */
    public BusinessScopeEntity getBusinessScopeContentByBusinessName(String businessName) {
        String sql = "SELECT * FROM business_scope WHERE business_name = '" + businessName + "'";
        return jdbcTemplate.queryForObject(sql, BusinessScopeEntity.class);
    }

    /**
     * 通过业务名称更新关键字描述
     *
     * @param describeKeyword
     * @param businessName
     * @return
     */
    public boolean updateDescribeKeywordByBusinessName(String describeKeyword, String businessName) {
        String sql = "UPDATE business_scope SET describe_keyword = '" + describeKeyword + "'"
                + " WHERE business_name = '" + businessName + "'";
        return jdbcTemplate.update(sql) == 1;
    }

    /**
     * 通过业务名称更新业务内容
     *
     * @param businessContent
     * @return
     */
    public boolean updateBusinessContentByBusinessName(String businessContent, String businessName) {
        String sql = "UPDATE business_scope SET business_content = '" + businessContent + "'"
                + " WHERE business_name = '" + businessName + "'";
        return jdbcTemplate.update(sql) == 1;
    }

    /**
     * 通过业务名称删除业务内容
     *
     * @param businessName
     * @return
     */
    public boolean deleteBusinessScopeByBusinessName(String businessName) {
        String sql = "DELETE FROM business_scope WHERE businessName = '" + businessName + "'";
        return jdbcTemplate.update(sql) == 1;
    }
}