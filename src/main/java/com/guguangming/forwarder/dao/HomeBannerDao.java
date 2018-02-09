package com.guguangming.forwarder.dao;

import com.moxie.commons.BaseJdbcUtils;
import com.moxie.commons.model.*;
import com.guguangming.forwarder.po.HomeBannerPo;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Repository
public class HomeBannerDao {
    private final static String TABLE_NAME = "home_banner";
    private Map<String, String> dbMapping = new HashMap<>();
    @Resource(name = "templateForwarder")
    private JdbcTemplate template;

    @PostConstruct
    public void init() {
        dbMapping.put("id", "id");
        dbMapping.put("homeBannerImgUrl", "homeBannerImgUrl");
    }

    public boolean insert(HomeBannerPo homeBanner) {
        JdbcResult jdbcResult = BaseJdbcUtils.getInsert(getTable(), homeBanner, dbMapping);
        return template.update(jdbcResult.getSql(), jdbcResult.getParams()) == 1;
    }

    public boolean insertIgnore(HomeBannerPo homeBanner) {
        JdbcResult jdbcResult = BaseJdbcUtils.getInsertIgnore(getTable(), homeBanner, dbMapping);
        return template.update(jdbcResult.getSql(), jdbcResult.getParams()) == 1;
    }

    /**
     * @return true when insert
     */
    public boolean insertOrUpdate(HomeBannerPo homeBanner) {
        JdbcResult jdbcResult = BaseJdbcUtils.getInsertOrUpdate(getTable(), homeBanner, dbMapping);
        return template.update(jdbcResult.getSql(), jdbcResult.getParams()) == 1;
    }

    public int batchInsert(List<HomeBannerPo> homeBanners) {
        JdbcResult jdbcResult = BaseJdbcUtils.getBatchInsert(getTable(), homeBanners, dbMapping);
        return IntStream.of(template.batchUpdate(jdbcResult.getSql(), jdbcResult.getBatchParams())).sum();
    }

    public boolean update(HomeBannerPo homeBanner) {
        JdbcResult jdbcResult = BaseJdbcUtils.getUpdate(getTable(), homeBanner, dbMapping, "id");
        return template.update(jdbcResult.getSql(), jdbcResult.getParams()) == 1;
    }

    public boolean patch(HomeBannerPo homeBanner) {
        JdbcResult jdbcResult = BaseJdbcUtils.getPatch(getTable(), homeBanner, dbMapping, "id");
        return template.update(jdbcResult.getSql(), jdbcResult.getParams()) == 1;
    }

    public HomeBannerPo get(Integer id) {
        JdbcResult jdbcResult = BaseJdbcUtils.getSelect(getTable(), Criteria.column("id").eq(id));
        try {
            Map<String, Object> dbRow = template.queryForMap(jdbcResult.getSql(), jdbcResult.getParams());
            return BaseJdbcUtils.dbRowToPo(dbRow, dbMapping, HomeBannerPo.class);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public HomeBannerPo getOrInsert(HomeBannerPo homeBanner) {
        HomeBannerPo po = this.get(homeBanner.getId());
        if (po == null) {
            if (!this.insertIgnore(homeBanner)) {
                return this.get(homeBanner.getId());
            }
            return homeBanner;
        }
        return po;
    }

    public PageResponse<HomeBannerPo> getPage(PageRequest pageRequest) {
        JdbcResult jdbcResult = BaseJdbcUtils.getSelectForCount(getTable(), (Criteria) null);
        Integer total = template.queryForObject(jdbcResult.getSql(), jdbcResult.getParams(), Integer.class);
        if(total == 0) {
            return new PageResponse<>(0, null);
        }

        jdbcResult = BaseJdbcUtils.getSelect(getTable(), (Criteria) null, pageRequest);
        List<HomeBannerPo> datas = template.queryForList(jdbcResult.getSql(), jdbcResult.getParams()).stream()
                .map(dbRow -> BaseJdbcUtils.dbRowToPo(dbRow, dbMapping, HomeBannerPo.class))
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