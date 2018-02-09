package com.guguangming.forwarder.dao;

import com.moxie.commons.BaseJdbcUtils;
import com.moxie.commons.model.*;
import com.guguangming.forwarder.po.UserInfoPo;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Repository
public class UserInfoDao {
    private final static String TABLE_NAME = "user_info";
    private Map<String, String> dbMapping = new HashMap<>();
    @Resource(name = "templateForwarder")
    private JdbcTemplate template;

    @PostConstruct
    public void init() {
        dbMapping.put("id", "id");
        dbMapping.put("userName", "userName");
        dbMapping.put("phone", "phone");
        dbMapping.put("password", "password");
        dbMapping.put("securityQuestionType", "securityQuestionType");
        dbMapping.put("securityAnswer", "securityAnswer");
    }

    public boolean insert(UserInfoPo userInfo) {
        JdbcResult jdbcResult = BaseJdbcUtils.getInsert(getTable(), userInfo, dbMapping);
        return template.update(jdbcResult.getSql(), jdbcResult.getParams()) == 1;
    }

    public boolean insertIgnore(UserInfoPo userInfo) {
        JdbcResult jdbcResult = BaseJdbcUtils.getInsertIgnore(getTable(), userInfo, dbMapping);
        return template.update(jdbcResult.getSql(), jdbcResult.getParams()) == 1;
    }

    /**
     * @return true when insert
     */
    public boolean insertOrUpdate(UserInfoPo userInfo) {
        JdbcResult jdbcResult = BaseJdbcUtils.getInsertOrUpdate(getTable(), userInfo, dbMapping);
        return template.update(jdbcResult.getSql(), jdbcResult.getParams()) == 1;
    }

    public int batchInsert(List<UserInfoPo> userInfos) {
        JdbcResult jdbcResult = BaseJdbcUtils.getBatchInsert(getTable(), userInfos, dbMapping);
        return IntStream.of(template.batchUpdate(jdbcResult.getSql(), jdbcResult.getBatchParams())).sum();
    }

    public boolean update(UserInfoPo userInfo) {
        JdbcResult jdbcResult = BaseJdbcUtils.getUpdate(getTable(), userInfo, dbMapping, "id");
        return template.update(jdbcResult.getSql(), jdbcResult.getParams()) == 1;
    }

    public boolean patch(UserInfoPo userInfo) {
        JdbcResult jdbcResult = BaseJdbcUtils.getPatch(getTable(), userInfo, dbMapping, "id");
        return template.update(jdbcResult.getSql(), jdbcResult.getParams()) == 1;
    }

    public UserInfoPo get(Integer id) {
        JdbcResult jdbcResult = BaseJdbcUtils.getSelect(getTable(), Criteria.column("id").eq(id));
        try {
            Map<String, Object> dbRow = template.queryForMap(jdbcResult.getSql(), jdbcResult.getParams());
            return BaseJdbcUtils.dbRowToPo(dbRow, dbMapping, UserInfoPo.class);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public UserInfoPo getOrInsert(UserInfoPo userInfo) {
        UserInfoPo po = this.get(userInfo.getId());
        if (po == null) {
            if (!this.insertIgnore(userInfo)) {
                return this.get(userInfo.getId());
            }
            return userInfo;
        }
        return po;
    }

    public PageResponse<UserInfoPo> getPage(PageRequest pageRequest) {
        JdbcResult jdbcResult = BaseJdbcUtils.getSelectForCount(getTable(), (Criteria) null);
        Integer total = template.queryForObject(jdbcResult.getSql(), jdbcResult.getParams(), Integer.class);
        if(total == 0) {
            return new PageResponse<>(0, null);
        }

        jdbcResult = BaseJdbcUtils.getSelect(getTable(), (Criteria) null, pageRequest);
        List<UserInfoPo> datas = template.queryForList(jdbcResult.getSql(), jdbcResult.getParams()).stream()
                .map(dbRow -> BaseJdbcUtils.dbRowToPo(dbRow, dbMapping, UserInfoPo.class))
                .collect(Collectors.toList());
        return new PageResponse<>(total, datas);
    }

    public int delete(Integer id) {
        JdbcResult jdbcResult = BaseJdbcUtils.getDelete(getTable(), Criteria.column("id").eq(id));
        return template.update(jdbcResult.getSql(), jdbcResult.getParams());
    }

    private String getTable() {
        return TABLE_NAME;
    }
}