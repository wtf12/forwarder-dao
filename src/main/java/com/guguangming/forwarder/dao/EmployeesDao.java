package com.guguangming.forwarder.dao;

import com.moxie.commons.BaseJdbcUtils;
import com.moxie.commons.model.*;
import com.guguangming.forwarder.po.EmployeesPo;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Repository
public class EmployeesDao {
    private final static String TABLE_NAME = "employees";
    private Map<String, String> dbMapping = new HashMap<>();
    @Resource(name = "templateForwarder")
    private JdbcTemplate template;

    @PostConstruct
    public void init() {
        dbMapping.put("id", "id");
        dbMapping.put("employeesImgUrl", "employeesImgUrl");
        dbMapping.put("employeesSynopsis", "employeesSynopsis");
        dbMapping.put("employeesImgName", "employeesImgName");
    }

    public boolean insert(EmployeesPo employees) {
        JdbcResult jdbcResult = BaseJdbcUtils.getInsert(getTable(), employees, dbMapping);
        return template.update(jdbcResult.getSql(), jdbcResult.getParams()) == 1;
    }

    public boolean insertIgnore(EmployeesPo employees) {
        JdbcResult jdbcResult = BaseJdbcUtils.getInsertIgnore(getTable(), employees, dbMapping);
        return template.update(jdbcResult.getSql(), jdbcResult.getParams()) == 1;
    }

    /**
     * @return true when insert
     */
    public boolean insertOrUpdate(EmployeesPo employees) {
        JdbcResult jdbcResult = BaseJdbcUtils.getInsertOrUpdate(getTable(), employees, dbMapping);
        return template.update(jdbcResult.getSql(), jdbcResult.getParams()) == 1;
    }

    public int batchInsert(List<EmployeesPo> employeess) {
        JdbcResult jdbcResult = BaseJdbcUtils.getBatchInsert(getTable(), employeess, dbMapping);
        return IntStream.of(template.batchUpdate(jdbcResult.getSql(), jdbcResult.getBatchParams())).sum();
    }

    public boolean update(EmployeesPo employees) {
        JdbcResult jdbcResult = BaseJdbcUtils.getUpdate(getTable(), employees, dbMapping, "id");
        return template.update(jdbcResult.getSql(), jdbcResult.getParams()) == 1;
    }

    public boolean patch(EmployeesPo employees) {
        JdbcResult jdbcResult = BaseJdbcUtils.getPatch(getTable(), employees, dbMapping, "id");
        return template.update(jdbcResult.getSql(), jdbcResult.getParams()) == 1;
    }

    public EmployeesPo get(Integer id) {
        JdbcResult jdbcResult = BaseJdbcUtils.getSelect(getTable(), Criteria.column("id").eq(id));
        try {
            Map<String, Object> dbRow = template.queryForMap(jdbcResult.getSql(), jdbcResult.getParams());
            return BaseJdbcUtils.dbRowToPo(dbRow, dbMapping, EmployeesPo.class);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public EmployeesPo getOrInsert(EmployeesPo employees) {
        EmployeesPo po = this.get(employees.getId());
        if (po == null) {
            if (!this.insertIgnore(employees)) {
                return this.get(employees.getId());
            }
            return employees;
        }
        return po;
    }

    public PageResponse<EmployeesPo> getPage(PageRequest pageRequest) {
        JdbcResult jdbcResult = BaseJdbcUtils.getSelectForCount(getTable(), (Criteria) null);
        Integer total = template.queryForObject(jdbcResult.getSql(), jdbcResult.getParams(), Integer.class);
        if(total == 0) {
            return new PageResponse<>(0, null);
        }

        jdbcResult = BaseJdbcUtils.getSelect(getTable(), (Criteria) null, pageRequest);
        List<EmployeesPo> datas = template.queryForList(jdbcResult.getSql(), jdbcResult.getParams()).stream()
                .map(dbRow -> BaseJdbcUtils.dbRowToPo(dbRow, dbMapping, EmployeesPo.class))
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