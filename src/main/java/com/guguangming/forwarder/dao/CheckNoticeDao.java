package com.guguangming.forwarder.dao;

import com.moxie.commons.BaseJdbcUtils;
import com.moxie.commons.model.*;
import com.guguangming.forwarder.po.CheckNoticePo;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Repository
public class CheckNoticeDao {
    private final static String TABLE_NAME = "check_notice";
    private Map<String, String> dbMapping = new HashMap<>();
    @Resource(name = "templateForwarder")
    private JdbcTemplate template;

    @PostConstruct
    public void init() {
        dbMapping.put("id", "id");
        dbMapping.put("notice", "notice");
    }

    public boolean insert(CheckNoticePo checkNotice) {
        JdbcResult jdbcResult = BaseJdbcUtils.getInsert(getTable(), checkNotice, dbMapping);
        return template.update(jdbcResult.getSql(), jdbcResult.getParams()) == 1;
    }

    public boolean insertIgnore(CheckNoticePo checkNotice) {
        JdbcResult jdbcResult = BaseJdbcUtils.getInsertIgnore(getTable(), checkNotice, dbMapping);
        return template.update(jdbcResult.getSql(), jdbcResult.getParams()) == 1;
    }

    /**
     * @return true when insert
     */
    public boolean insertOrUpdate(CheckNoticePo checkNotice) {
        JdbcResult jdbcResult = BaseJdbcUtils.getInsertOrUpdate(getTable(), checkNotice, dbMapping);
        return template.update(jdbcResult.getSql(), jdbcResult.getParams()) == 1;
    }

    public int batchInsert(List<CheckNoticePo> checkNotices) {
        JdbcResult jdbcResult = BaseJdbcUtils.getBatchInsert(getTable(), checkNotices, dbMapping);
        return IntStream.of(template.batchUpdate(jdbcResult.getSql(), jdbcResult.getBatchParams())).sum();
    }

    public boolean update(CheckNoticePo checkNotice) {
        JdbcResult jdbcResult = BaseJdbcUtils.getUpdate(getTable(), checkNotice, dbMapping, "id");
        return template.update(jdbcResult.getSql(), jdbcResult.getParams()) == 1;
    }

    public boolean patch(CheckNoticePo checkNotice) {
        JdbcResult jdbcResult = BaseJdbcUtils.getPatch(getTable(), checkNotice, dbMapping, "id");
        return template.update(jdbcResult.getSql(), jdbcResult.getParams()) == 1;
    }

    public CheckNoticePo get(Integer id) {
        JdbcResult jdbcResult = BaseJdbcUtils.getSelect(getTable(), Criteria.column("id").eq(id));
        try {
            Map<String, Object> dbRow = template.queryForMap(jdbcResult.getSql(), jdbcResult.getParams());
            return BaseJdbcUtils.dbRowToPo(dbRow, dbMapping, CheckNoticePo.class);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public CheckNoticePo getOrInsert(CheckNoticePo checkNotice) {
        CheckNoticePo po = this.get(checkNotice.getId());
        if (po == null) {
            if (!this.insertIgnore(checkNotice)) {
                return this.get(checkNotice.getId());
            }
            return checkNotice;
        }
        return po;
    }

    public PageResponse<CheckNoticePo> getPage(PageRequest pageRequest) {
        JdbcResult jdbcResult = BaseJdbcUtils.getSelectForCount(getTable(), (Criteria) null);
        Integer total = template.queryForObject(jdbcResult.getSql(), jdbcResult.getParams(), Integer.class);
        if(total == 0) {
            return new PageResponse<>(0, null);
        }

        jdbcResult = BaseJdbcUtils.getSelect(getTable(), (Criteria) null, pageRequest);
        List<CheckNoticePo> datas = template.queryForList(jdbcResult.getSql(), jdbcResult.getParams()).stream()
                .map(dbRow -> BaseJdbcUtils.dbRowToPo(dbRow, dbMapping, CheckNoticePo.class))
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