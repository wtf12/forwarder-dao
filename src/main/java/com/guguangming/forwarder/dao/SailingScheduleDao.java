package com.guguangming.forwarder.dao;

import com.moxie.commons.BaseJdbcUtils;
import com.moxie.commons.model.*;
import com.guguangming.forwarder.po.SailingSchedulePo;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Repository
public class SailingScheduleDao {
    private final static String TABLE_NAME = "sailing_schedule";
    private Map<String, String> dbMapping = new HashMap<>();
    @Resource(name = "templateForwarder")
    private JdbcTemplate template;

    @PostConstruct
    public void init() {
        dbMapping.put("id", "id");
        dbMapping.put("scheduleName", "scheduleName");
        dbMapping.put("scheduleSize", "scheduleSize");
        dbMapping.put("scheduleType", "scheduleType");
        dbMapping.put("scheduleUrl", "scheduleUrl");
    }

    public boolean insert(SailingSchedulePo sailingSchedule) {
        JdbcResult jdbcResult = BaseJdbcUtils.getInsert(getTable(), sailingSchedule, dbMapping);
        return template.update(jdbcResult.getSql(), jdbcResult.getParams()) == 1;
    }

    public boolean insertIgnore(SailingSchedulePo sailingSchedule) {
        JdbcResult jdbcResult = BaseJdbcUtils.getInsertIgnore(getTable(), sailingSchedule, dbMapping);
        return template.update(jdbcResult.getSql(), jdbcResult.getParams()) == 1;
    }

    /**
     * @return true when insert
     */
    public boolean insertOrUpdate(SailingSchedulePo sailingSchedule) {
        JdbcResult jdbcResult = BaseJdbcUtils.getInsertOrUpdate(getTable(), sailingSchedule, dbMapping);
        return template.update(jdbcResult.getSql(), jdbcResult.getParams()) == 1;
    }

    public int batchInsert(List<SailingSchedulePo> sailingSchedules) {
        JdbcResult jdbcResult = BaseJdbcUtils.getBatchInsert(getTable(), sailingSchedules, dbMapping);
        return IntStream.of(template.batchUpdate(jdbcResult.getSql(), jdbcResult.getBatchParams())).sum();
    }

    public boolean update(SailingSchedulePo sailingSchedule) {
        JdbcResult jdbcResult = BaseJdbcUtils.getUpdate(getTable(), sailingSchedule, dbMapping, "id");
        return template.update(jdbcResult.getSql(), jdbcResult.getParams()) == 1;
    }

    public boolean patch(SailingSchedulePo sailingSchedule) {
        JdbcResult jdbcResult = BaseJdbcUtils.getPatch(getTable(), sailingSchedule, dbMapping, "id");
        return template.update(jdbcResult.getSql(), jdbcResult.getParams()) == 1;
    }

    public SailingSchedulePo get(Integer id) {
        JdbcResult jdbcResult = BaseJdbcUtils.getSelect(getTable(), Criteria.column("id").eq(id));
        try {
            Map<String, Object> dbRow = template.queryForMap(jdbcResult.getSql(), jdbcResult.getParams());
            return BaseJdbcUtils.dbRowToPo(dbRow, dbMapping, SailingSchedulePo.class);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public SailingSchedulePo getOrInsert(SailingSchedulePo sailingSchedule) {
        SailingSchedulePo po = this.get(sailingSchedule.getId());
        if (po == null) {
            if (!this.insertIgnore(sailingSchedule)) {
                return this.get(sailingSchedule.getId());
            }
            return sailingSchedule;
        }
        return po;
    }

    public PageResponse<SailingSchedulePo> getPage(PageRequest pageRequest) {
        JdbcResult jdbcResult = BaseJdbcUtils.getSelectForCount(getTable(), (Criteria) null);
        Integer total = template.queryForObject(jdbcResult.getSql(), jdbcResult.getParams(), Integer.class);
        if(total == 0) {
            return new PageResponse<>(0, null);
        }

        jdbcResult = BaseJdbcUtils.getSelect(getTable(), (Criteria) null, pageRequest);
        List<SailingSchedulePo> datas = template.queryForList(jdbcResult.getSql(), jdbcResult.getParams()).stream()
                .map(dbRow -> BaseJdbcUtils.dbRowToPo(dbRow, dbMapping, SailingSchedulePo.class))
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