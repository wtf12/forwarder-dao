package com.guguangming.forwarder.dao;

import com.moxie.commons.BaseJdbcUtils;
import com.moxie.commons.model.*;
import com.guguangming.forwarder.po.BusinessScopePo;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 *
 */
@Repository
public class BusinessScopeDao {
    private final static String TABLE_NAME = "business_scope";
    private Map<String, String> dbMapping = new HashMap<>();
    @Resource(name = "templateForwarder")
    private JdbcTemplate template;

    @PostConstruct
    public void init() {
        dbMapping.put("id", "id");
        dbMapping.put("businessName", "businessName");
        dbMapping.put("describeKeyword", "describeKeyword");
        dbMapping.put("businessContent", "businessContent");
    }

    public boolean insert(BusinessScopePo businessScope) {
        JdbcResult jdbcResult = BaseJdbcUtils.getInsert(getTable(), businessScope, dbMapping);
        return template.update(jdbcResult.getSql(), jdbcResult.getParams()) == 1;
    }

    public boolean insertIgnore(BusinessScopePo businessScope) {
        JdbcResult jdbcResult = BaseJdbcUtils.getInsertIgnore(getTable(), businessScope, dbMapping);
        return template.update(jdbcResult.getSql(), jdbcResult.getParams()) == 1;
    }

    /**
     * @return true when insert
     */
    public boolean insertOrUpdate(BusinessScopePo businessScope) {
        JdbcResult jdbcResult = BaseJdbcUtils.getInsertOrUpdate(getTable(), businessScope, dbMapping);
        return template.update(jdbcResult.getSql(), jdbcResult.getParams()) == 1;
    }

    public int batchInsert(List<BusinessScopePo> businessScopes) {
        JdbcResult jdbcResult = BaseJdbcUtils.getBatchInsert(getTable(), businessScopes, dbMapping);
        return IntStream.of(template.batchUpdate(jdbcResult.getSql(), jdbcResult.getBatchParams())).sum();
    }

    public boolean update(BusinessScopePo businessScope) {
        JdbcResult jdbcResult = BaseJdbcUtils.getUpdate(getTable(), businessScope, dbMapping, "id");
        return template.update(jdbcResult.getSql(), jdbcResult.getParams()) == 1;
    }

    public boolean patch(BusinessScopePo businessScope) {
        JdbcResult jdbcResult = BaseJdbcUtils.getPatch(getTable(), businessScope, dbMapping, "id");
        return template.update(jdbcResult.getSql(), jdbcResult.getParams()) == 1;
    }

    public BusinessScopePo get(Integer id) {
        JdbcResult jdbcResult = BaseJdbcUtils.getSelect(getTable(), Criteria.column("id").eq(id));
        try {
            Map<String, Object> dbRow = template.queryForMap(jdbcResult.getSql(), jdbcResult.getParams());
            return BaseJdbcUtils.dbRowToPo(dbRow, dbMapping, BusinessScopePo.class);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public BusinessScopePo getOrInsert(BusinessScopePo businessScope) {
        BusinessScopePo po = this.get(businessScope.getId());
        if (po == null) {
            if (!this.insertIgnore(businessScope)) {
                return this.get(businessScope.getId());
            }
            return businessScope;
        }
        return po;
    }

    public PageResponse<BusinessScopePo> getPage(PageRequest pageRequest) {
        JdbcResult jdbcResult = BaseJdbcUtils.getSelectForCount(getTable(), (Criteria) null);
        Integer total = template.queryForObject(jdbcResult.getSql(), jdbcResult.getParams(), Integer.class);
        if(total == 0) {
            return new PageResponse<>(0, null);
        }

        jdbcResult = BaseJdbcUtils.getSelect(getTable(), (Criteria) null, pageRequest);
        List<BusinessScopePo> datas = template.queryForList(jdbcResult.getSql(), jdbcResult.getParams()).stream()
                .map(dbRow -> BaseJdbcUtils.dbRowToPo(dbRow, dbMapping, BusinessScopePo.class))
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