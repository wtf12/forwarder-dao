package com.guguangming.forwarder.dao;

import com.moxie.commons.BaseJdbcUtils;
import com.moxie.commons.model.*;
import com.guguangming.forwarder.po.CompanySynopsisBannerPo;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Repository
public class CompanySynopsisBannerDao {
    private final static String TABLE_NAME = "company_synopsis_banner";
    private Map<String, String> dbMapping = new HashMap<>();
    @Resource(name = "templateForwarder")
    private JdbcTemplate template;

    @PostConstruct
    public void init() {
        dbMapping.put("id", "id");
        dbMapping.put("companySynopsisBannerImgUrl", "companySynopsisBannerImgUrl");
    }

    public boolean insert(CompanySynopsisBannerPo companySynopsisBanner) {
        JdbcResult jdbcResult = BaseJdbcUtils.getInsert(getTable(), companySynopsisBanner, dbMapping);
        return template.update(jdbcResult.getSql(), jdbcResult.getParams()) == 1;
    }

    public boolean insertIgnore(CompanySynopsisBannerPo companySynopsisBanner) {
        JdbcResult jdbcResult = BaseJdbcUtils.getInsertIgnore(getTable(), companySynopsisBanner, dbMapping);
        return template.update(jdbcResult.getSql(), jdbcResult.getParams()) == 1;
    }

    /**
     * @return true when insert
     */
    public boolean insertOrUpdate(CompanySynopsisBannerPo companySynopsisBanner) {
        JdbcResult jdbcResult = BaseJdbcUtils.getInsertOrUpdate(getTable(), companySynopsisBanner, dbMapping);
        return template.update(jdbcResult.getSql(), jdbcResult.getParams()) == 1;
    }

    public int batchInsert(List<CompanySynopsisBannerPo> companySynopsisBanners) {
        JdbcResult jdbcResult = BaseJdbcUtils.getBatchInsert(getTable(), companySynopsisBanners, dbMapping);
        return IntStream.of(template.batchUpdate(jdbcResult.getSql(), jdbcResult.getBatchParams())).sum();
    }

    public boolean update(CompanySynopsisBannerPo companySynopsisBanner) {
        JdbcResult jdbcResult = BaseJdbcUtils.getUpdate(getTable(), companySynopsisBanner, dbMapping, "id");
        return template.update(jdbcResult.getSql(), jdbcResult.getParams()) == 1;
    }

    public boolean patch(CompanySynopsisBannerPo companySynopsisBanner) {
        JdbcResult jdbcResult = BaseJdbcUtils.getPatch(getTable(), companySynopsisBanner, dbMapping, "id");
        return template.update(jdbcResult.getSql(), jdbcResult.getParams()) == 1;
    }

    public CompanySynopsisBannerPo get(Integer id) {
        JdbcResult jdbcResult = BaseJdbcUtils.getSelect(getTable(), Criteria.column("id").eq(id));
        try {
            Map<String, Object> dbRow = template.queryForMap(jdbcResult.getSql(), jdbcResult.getParams());
            return BaseJdbcUtils.dbRowToPo(dbRow, dbMapping, CompanySynopsisBannerPo.class);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public CompanySynopsisBannerPo getOrInsert(CompanySynopsisBannerPo companySynopsisBanner) {
        CompanySynopsisBannerPo po = this.get(companySynopsisBanner.getId());
        if (po == null) {
            if (!this.insertIgnore(companySynopsisBanner)) {
                return this.get(companySynopsisBanner.getId());
            }
            return companySynopsisBanner;
        }
        return po;
    }

    public PageResponse<CompanySynopsisBannerPo> getPage(PageRequest pageRequest) {
        JdbcResult jdbcResult = BaseJdbcUtils.getSelectForCount(getTable(), (Criteria) null);
        Integer total = template.queryForObject(jdbcResult.getSql(), jdbcResult.getParams(), Integer.class);
        if(total == 0) {
            return new PageResponse<>(0, null);
        }

        jdbcResult = BaseJdbcUtils.getSelect(getTable(), (Criteria) null, pageRequest);
        List<CompanySynopsisBannerPo> datas = template.queryForList(jdbcResult.getSql(), jdbcResult.getParams()).stream()
                .map(dbRow -> BaseJdbcUtils.dbRowToPo(dbRow, dbMapping, CompanySynopsisBannerPo.class))
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