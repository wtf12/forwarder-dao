package com.guguangming.forwarder.dao;

import com.moxie.commons.BaseJdbcUtils;
import com.moxie.commons.model.*;
import com.guguangming.forwarder.po.ProjectSuccessPo;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Repository
public class ProjectSuccessDao {
    private final static String TABLE_NAME = "project_success";
    private Map<String, String> dbMapping = new HashMap<>();
    @Resource(name = "templateForwarder")
    private JdbcTemplate template;

    @PostConstruct
    public void init() {
        dbMapping.put("id", "id");
        dbMapping.put("projectName", "projectName");
        dbMapping.put("projectImgUrl", "projectImgUrl");
        dbMapping.put("projectSynopsis", "projectSynopsis");
    }

    public boolean insert(ProjectSuccessPo projectSuccess) {
        JdbcResult jdbcResult = BaseJdbcUtils.getInsert(getTable(), projectSuccess, dbMapping);
        return template.update(jdbcResult.getSql(), jdbcResult.getParams()) == 1;
    }

    public boolean insertIgnore(ProjectSuccessPo projectSuccess) {
        JdbcResult jdbcResult = BaseJdbcUtils.getInsertIgnore(getTable(), projectSuccess, dbMapping);
        return template.update(jdbcResult.getSql(), jdbcResult.getParams()) == 1;
    }

    /**
     * @return true when insert
     */
    public boolean insertOrUpdate(ProjectSuccessPo projectSuccess) {
        JdbcResult jdbcResult = BaseJdbcUtils.getInsertOrUpdate(getTable(), projectSuccess, dbMapping);
        return template.update(jdbcResult.getSql(), jdbcResult.getParams()) == 1;
    }

    public int batchInsert(List<ProjectSuccessPo> projectSuccesss) {
        JdbcResult jdbcResult = BaseJdbcUtils.getBatchInsert(getTable(), projectSuccesss, dbMapping);
        return IntStream.of(template.batchUpdate(jdbcResult.getSql(), jdbcResult.getBatchParams())).sum();
    }

    public boolean update(ProjectSuccessPo projectSuccess) {
        JdbcResult jdbcResult = BaseJdbcUtils.getUpdate(getTable(), projectSuccess, dbMapping, "id");
        return template.update(jdbcResult.getSql(), jdbcResult.getParams()) == 1;
    }

    public boolean patch(ProjectSuccessPo projectSuccess) {
        JdbcResult jdbcResult = BaseJdbcUtils.getPatch(getTable(), projectSuccess, dbMapping, "id");
        return template.update(jdbcResult.getSql(), jdbcResult.getParams()) == 1;
    }

    public ProjectSuccessPo get(Integer id) {
        JdbcResult jdbcResult = BaseJdbcUtils.getSelect(getTable(), Criteria.column("id").eq(id));
        try {
            Map<String, Object> dbRow = template.queryForMap(jdbcResult.getSql(), jdbcResult.getParams());
            return BaseJdbcUtils.dbRowToPo(dbRow, dbMapping, ProjectSuccessPo.class);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public ProjectSuccessPo getOrInsert(ProjectSuccessPo projectSuccess) {
        ProjectSuccessPo po = this.get(projectSuccess.getId());
        if (po == null) {
            if (!this.insertIgnore(projectSuccess)) {
                return this.get(projectSuccess.getId());
            }
            return projectSuccess;
        }
        return po;
    }

    public PageResponse<ProjectSuccessPo> getPage(PageRequest pageRequest) {
        JdbcResult jdbcResult = BaseJdbcUtils.getSelectForCount(getTable(), (Criteria) null);
        Integer total = template.queryForObject(jdbcResult.getSql(), jdbcResult.getParams(), Integer.class);
        if(total == 0) {
            return new PageResponse<>(0, null);
        }

        jdbcResult = BaseJdbcUtils.getSelect(getTable(), (Criteria) null, pageRequest);
        List<ProjectSuccessPo> datas = template.queryForList(jdbcResult.getSql(), jdbcResult.getParams()).stream()
                .map(dbRow -> BaseJdbcUtils.dbRowToPo(dbRow, dbMapping, ProjectSuccessPo.class))
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