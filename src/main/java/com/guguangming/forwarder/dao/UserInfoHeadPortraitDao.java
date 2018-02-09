package com.guguangming.forwarder.dao;

import com.moxie.commons.BaseJdbcUtils;
import com.moxie.commons.model.*;
import com.guguangming.forwarder.po.UserInfoHeadPortraitPo;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Repository
public class UserInfoHeadPortraitDao {
    private final static String TABLE_NAME = "user_info_head_portrait";
    private Map<String, String> dbMapping = new HashMap<>();
    @Resource(name = "templateForwarder")
    private JdbcTemplate template;

    @PostConstruct
    public void init() {
        dbMapping.put("id", "id");
        dbMapping.put("phone", "phone");
        dbMapping.put("userHeadPortraitImgUrl", "userHeadPortraitImgUrl");
    }

    public boolean insert(UserInfoHeadPortraitPo userInfoHeadPortrait) {
        JdbcResult jdbcResult = BaseJdbcUtils.getInsert(getTable(), userInfoHeadPortrait, dbMapping);
        return template.update(jdbcResult.getSql(), jdbcResult.getParams()) == 1;
    }

    public boolean insertIgnore(UserInfoHeadPortraitPo userInfoHeadPortrait) {
        JdbcResult jdbcResult = BaseJdbcUtils.getInsertIgnore(getTable(), userInfoHeadPortrait, dbMapping);
        return template.update(jdbcResult.getSql(), jdbcResult.getParams()) == 1;
    }

    /**
     * @return true when insert
     */
    public boolean insertOrUpdate(UserInfoHeadPortraitPo userInfoHeadPortrait) {
        JdbcResult jdbcResult = BaseJdbcUtils.getInsertOrUpdate(getTable(), userInfoHeadPortrait, dbMapping);
        return template.update(jdbcResult.getSql(), jdbcResult.getParams()) == 1;
    }

    public int batchInsert(List<UserInfoHeadPortraitPo> userInfoHeadPortraits) {
        JdbcResult jdbcResult = BaseJdbcUtils.getBatchInsert(getTable(), userInfoHeadPortraits, dbMapping);
        return IntStream.of(template.batchUpdate(jdbcResult.getSql(), jdbcResult.getBatchParams())).sum();
    }

    public boolean update(UserInfoHeadPortraitPo userInfoHeadPortrait) {
        JdbcResult jdbcResult = BaseJdbcUtils.getUpdate(getTable(), userInfoHeadPortrait, dbMapping, "id");
        return template.update(jdbcResult.getSql(), jdbcResult.getParams()) == 1;
    }

    public boolean patch(UserInfoHeadPortraitPo userInfoHeadPortrait) {
        JdbcResult jdbcResult = BaseJdbcUtils.getPatch(getTable(), userInfoHeadPortrait, dbMapping, "id");
        return template.update(jdbcResult.getSql(), jdbcResult.getParams()) == 1;
    }

    public UserInfoHeadPortraitPo get(Integer id) {
        JdbcResult jdbcResult = BaseJdbcUtils.getSelect(getTable(), Criteria.column("id").eq(id));
        try {
            Map<String, Object> dbRow = template.queryForMap(jdbcResult.getSql(), jdbcResult.getParams());
            return BaseJdbcUtils.dbRowToPo(dbRow, dbMapping, UserInfoHeadPortraitPo.class);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public UserInfoHeadPortraitPo getOrInsert(UserInfoHeadPortraitPo userInfoHeadPortrait) {
        UserInfoHeadPortraitPo po = this.get(userInfoHeadPortrait.getId());
        if (po == null) {
            if (!this.insertIgnore(userInfoHeadPortrait)) {
                return this.get(userInfoHeadPortrait.getId());
            }
            return userInfoHeadPortrait;
        }
        return po;
    }

    public PageResponse<UserInfoHeadPortraitPo> getPage(PageRequest pageRequest) {
        JdbcResult jdbcResult = BaseJdbcUtils.getSelectForCount(getTable(), (Criteria) null);
        Integer total = template.queryForObject(jdbcResult.getSql(), jdbcResult.getParams(), Integer.class);
        if(total == 0) {
            return new PageResponse<>(0, null);
        }

        jdbcResult = BaseJdbcUtils.getSelect(getTable(), (Criteria) null, pageRequest);
        List<UserInfoHeadPortraitPo> datas = template.queryForList(jdbcResult.getSql(), jdbcResult.getParams()).stream()
                .map(dbRow -> BaseJdbcUtils.dbRowToPo(dbRow, dbMapping, UserInfoHeadPortraitPo.class))
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