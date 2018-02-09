package com.guguangming.forwarder.dao;

import com.moxie.commons.BaseJdbcUtils;
import com.moxie.commons.model.*;
import com.guguangming.forwarder.po.ImportantAnnouncementPo;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Repository
public class ImportantAnnouncementDao {
    private final static String TABLE_NAME = "important_announcement";
    private Map<String, String> dbMapping = new HashMap<>();
    @Resource(name = "templateForwarder")
    private JdbcTemplate template;

    @PostConstruct
    public void init() {
        dbMapping.put("id", "id");
        dbMapping.put("announcementName", "announcementName");
        dbMapping.put("announcementContent", "announcementContent");
        dbMapping.put("announcementTime", "announcementTime");
    }

    public boolean insert(ImportantAnnouncementPo importantAnnouncement) {
        JdbcResult jdbcResult = BaseJdbcUtils.getInsert(getTable(), importantAnnouncement, dbMapping);
        return template.update(jdbcResult.getSql(), jdbcResult.getParams()) == 1;
    }

    public boolean insertIgnore(ImportantAnnouncementPo importantAnnouncement) {
        JdbcResult jdbcResult = BaseJdbcUtils.getInsertIgnore(getTable(), importantAnnouncement, dbMapping);
        return template.update(jdbcResult.getSql(), jdbcResult.getParams()) == 1;
    }

    /**
     * @return true when insert
     */
    public boolean insertOrUpdate(ImportantAnnouncementPo importantAnnouncement) {
        JdbcResult jdbcResult = BaseJdbcUtils.getInsertOrUpdate(getTable(), importantAnnouncement, dbMapping);
        return template.update(jdbcResult.getSql(), jdbcResult.getParams()) == 1;
    }

    public int batchInsert(List<ImportantAnnouncementPo> importantAnnouncements) {
        JdbcResult jdbcResult = BaseJdbcUtils.getBatchInsert(getTable(), importantAnnouncements, dbMapping);
        return IntStream.of(template.batchUpdate(jdbcResult.getSql(), jdbcResult.getBatchParams())).sum();
    }

    public boolean update(ImportantAnnouncementPo importantAnnouncement) {
        JdbcResult jdbcResult = BaseJdbcUtils.getUpdate(getTable(), importantAnnouncement, dbMapping, "id");
        return template.update(jdbcResult.getSql(), jdbcResult.getParams()) == 1;
    }

    public boolean patch(ImportantAnnouncementPo importantAnnouncement) {
        JdbcResult jdbcResult = BaseJdbcUtils.getPatch(getTable(), importantAnnouncement, dbMapping, "id");
        return template.update(jdbcResult.getSql(), jdbcResult.getParams()) == 1;
    }

    public ImportantAnnouncementPo get(Integer id) {
        JdbcResult jdbcResult = BaseJdbcUtils.getSelect(getTable(), Criteria.column("id").eq(id));
        try {
            Map<String, Object> dbRow = template.queryForMap(jdbcResult.getSql(), jdbcResult.getParams());
            return BaseJdbcUtils.dbRowToPo(dbRow, dbMapping, ImportantAnnouncementPo.class);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public ImportantAnnouncementPo getOrInsert(ImportantAnnouncementPo importantAnnouncement) {
        ImportantAnnouncementPo po = this.get(importantAnnouncement.getId());
        if (po == null) {
            if (!this.insertIgnore(importantAnnouncement)) {
                return this.get(importantAnnouncement.getId());
            }
            return importantAnnouncement;
        }
        return po;
    }

    public PageResponse<ImportantAnnouncementPo> getPage(PageRequest pageRequest) {
        JdbcResult jdbcResult = BaseJdbcUtils.getSelectForCount(getTable(), (Criteria) null);
        Integer total = template.queryForObject(jdbcResult.getSql(), jdbcResult.getParams(), Integer.class);
        if(total == 0) {
            return new PageResponse<>(0, null);
        }

        jdbcResult = BaseJdbcUtils.getSelect(getTable(), (Criteria) null, pageRequest);
        List<ImportantAnnouncementPo> datas = template.queryForList(jdbcResult.getSql(), jdbcResult.getParams()).stream()
                .map(dbRow -> BaseJdbcUtils.dbRowToPo(dbRow, dbMapping, ImportantAnnouncementPo.class))
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