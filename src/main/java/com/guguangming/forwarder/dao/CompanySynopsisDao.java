package com.guguangming.forwarder.dao;

import com.moxie.commons.BaseJdbcUtils;
import com.moxie.commons.model.*;
import com.guguangming.forwarder.po.CompanySynopsisPo;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Repository
public class CompanySynopsisDao {
    private final static String TABLE_NAME = "company_synopsis";
    private Map<String, String> dbMapping = new HashMap<>();
    @Resource(name = "templateForwarder")
    private JdbcTemplate template;

    @PostConstruct
    public void init() {
        dbMapping.put("id", "id");
        dbMapping.put("synopsis", "synopsis");
    }

    public boolean insert(CompanySynopsisPo companySynopsis) {
        JdbcResult jdbcResult = BaseJdbcUtils.getInsert(getTable(), companySynopsis, dbMapping);
        return template.update(jdbcResult.getSql(), jdbcResult.getParams()) == 1;
    }

    public boolean insertIgnore(CompanySynopsisPo companySynopsis) {
        JdbcResult jdbcResult = BaseJdbcUtils.getInsertIgnore(getTable(), companySynopsis, dbMapping);
        return template.update(jdbcResult.getSql(), jdbcResult.getParams()) == 1;
    }

    /**
     * @return true when insert
     */
    public boolean insertOrUpdate(CompanySynopsisPo companySynopsis) {
        JdbcResult jdbcResult = BaseJdbcUtils.getInsertOrUpdate(getTable(), companySynopsis, dbMapping);
        return template.update(jdbcResult.getSql(), jdbcResult.getParams()) == 1;
    }

    public int batchInsert(List<CompanySynopsisPo> companySynopsiss) {
        JdbcResult jdbcResult = BaseJdbcUtils.getBatchInsert(getTable(), companySynopsiss, dbMapping);
        return IntStream.of(template.batchUpdate(jdbcResult.getSql(), jdbcResult.getBatchParams())).sum();
    }

    public boolean update(CompanySynopsisPo companySynopsis) {
        JdbcResult jdbcResult = BaseJdbcUtils.getUpdate(getTable(), companySynopsis, dbMapping, "id");
        return template.update(jdbcResult.getSql(), jdbcResult.getParams()) == 1;
    }

    public boolean patch(CompanySynopsisPo companySynopsis) {
        JdbcResult jdbcResult = BaseJdbcUtils.getPatch(getTable(), companySynopsis, dbMapping, "id");
        return template.update(jdbcResult.getSql(), jdbcResult.getParams()) == 1;
    }

    public CompanySynopsisPo get(Integer id) {
        JdbcResult jdbcResult = BaseJdbcUtils.getSelect(getTable(), Criteria.column("id").eq(id));
        try {
            Map<String, Object> dbRow = template.queryForMap(jdbcResult.getSql(), jdbcResult.getParams());
            return BaseJdbcUtils.dbRowToPo(dbRow, dbMapping, CompanySynopsisPo.class);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public CompanySynopsisPo getOrInsert(CompanySynopsisPo companySynopsis) {
        CompanySynopsisPo po = this.get(companySynopsis.getId());
        if (po == null) {
            if (!this.insertIgnore(companySynopsis)) {
                return this.get(companySynopsis.getId());
            }
            return companySynopsis;
        }
        return po;
    }

    public PageResponse<CompanySynopsisPo> getPage(PageRequest pageRequest) {
        JdbcResult jdbcResult = BaseJdbcUtils.getSelectForCount(getTable(), (Criteria) null);
        Integer total = template.queryForObject(jdbcResult.getSql(), jdbcResult.getParams(), Integer.class);
        if(total == 0) {
            return new PageResponse<>(0, null);
        }

        jdbcResult = BaseJdbcUtils.getSelect(getTable(), (Criteria) null, pageRequest);
        List<CompanySynopsisPo> datas = template.queryForList(jdbcResult.getSql(), jdbcResult.getParams()).stream()
                .map(dbRow -> BaseJdbcUtils.dbRowToPo(dbRow, dbMapping, CompanySynopsisPo.class))
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