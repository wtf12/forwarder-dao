package com.guguangming.forwarder.dao;

import com.moxie.commons.BaseJdbcUtils;
import com.moxie.commons.model.*;
import com.guguangming.forwarder.po.NewsPo;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Repository
public class NewsDao {
    private final static String TABLE_NAME = "news";
    private Map<String, String> dbMapping = new HashMap<>();
    @Resource(name = "templateForwarder")
    private JdbcTemplate template;

    @PostConstruct
    public void init() {
        dbMapping.put("id", "id");
        dbMapping.put("newsTitle", "newsTitle");
        dbMapping.put("newsImgUrl", "newsImgUrl");
        dbMapping.put("newsContent", "newsContent");
        dbMapping.put("newsSynopsis", "newsSynopsis");
        dbMapping.put("newsSynopsisImgUrl", "newsSynopsisImgUrl");
        dbMapping.put("newsTime", "newsTime");
    }

    public boolean insert(NewsPo news) {
        JdbcResult jdbcResult = BaseJdbcUtils.getInsert(getTable(), news, dbMapping);
        return template.update(jdbcResult.getSql(), jdbcResult.getParams()) == 1;
    }

    public boolean insertIgnore(NewsPo news) {
        JdbcResult jdbcResult = BaseJdbcUtils.getInsertIgnore(getTable(), news, dbMapping);
        return template.update(jdbcResult.getSql(), jdbcResult.getParams()) == 1;
    }

    /**
     * @return true when insert
     */
    public boolean insertOrUpdate(NewsPo news) {
        JdbcResult jdbcResult = BaseJdbcUtils.getInsertOrUpdate(getTable(), news, dbMapping);
        return template.update(jdbcResult.getSql(), jdbcResult.getParams()) == 1;
    }

    public int batchInsert(List<NewsPo> newss) {
        JdbcResult jdbcResult = BaseJdbcUtils.getBatchInsert(getTable(), newss, dbMapping);
        return IntStream.of(template.batchUpdate(jdbcResult.getSql(), jdbcResult.getBatchParams())).sum();
    }

    public boolean update(NewsPo news) {
        JdbcResult jdbcResult = BaseJdbcUtils.getUpdate(getTable(), news, dbMapping, "id");
        return template.update(jdbcResult.getSql(), jdbcResult.getParams()) == 1;
    }

    public boolean patch(NewsPo news) {
        JdbcResult jdbcResult = BaseJdbcUtils.getPatch(getTable(), news, dbMapping, "id");
        return template.update(jdbcResult.getSql(), jdbcResult.getParams()) == 1;
    }

    public NewsPo get(Integer id) {
        JdbcResult jdbcResult = BaseJdbcUtils.getSelect(getTable(), Criteria.column("id").eq(id));
        try {
            Map<String, Object> dbRow = template.queryForMap(jdbcResult.getSql(), jdbcResult.getParams());
            return BaseJdbcUtils.dbRowToPo(dbRow, dbMapping, NewsPo.class);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public NewsPo getOrInsert(NewsPo news) {
        NewsPo po = this.get(news.getId());
        if (po == null) {
            if (!this.insertIgnore(news)) {
                return this.get(news.getId());
            }
            return news;
        }
        return po;
    }

    public PageResponse<NewsPo> getPage(PageRequest pageRequest) {
        JdbcResult jdbcResult = BaseJdbcUtils.getSelectForCount(getTable(), (Criteria) null);
        Integer total = template.queryForObject(jdbcResult.getSql(), jdbcResult.getParams(), Integer.class);
        if(total == 0) {
            return new PageResponse<>(0, null);
        }

        jdbcResult = BaseJdbcUtils.getSelect(getTable(), (Criteria) null, pageRequest);
        List<NewsPo> datas = template.queryForList(jdbcResult.getSql(), jdbcResult.getParams()).stream()
                .map(dbRow -> BaseJdbcUtils.dbRowToPo(dbRow, dbMapping, NewsPo.class))
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