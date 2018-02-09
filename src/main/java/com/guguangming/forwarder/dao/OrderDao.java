package com.guguangming.forwarder.dao;

import com.moxie.commons.BaseJdbcUtils;
import com.moxie.commons.model.*;
import com.guguangming.forwarder.po.OrderPo;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Repository
public class OrderDao {
    private final static String TABLE_NAME = "order";
    private Map<String, String> dbMapping = new HashMap<>();
    @Resource(name = "templateForwarder")
    private JdbcTemplate template;

    @PostConstruct
    public void init() {
        dbMapping.put("id", "id");
        dbMapping.put("billOfLadingNumber", "billOfLadingNumber");
        dbMapping.put("entrustTheNumber", "entrustTheNumber");
        dbMapping.put("jobNumber", "jobNumber");
        dbMapping.put("shipName", "shipName");
        dbMapping.put("voyage", "voyage");
        dbMapping.put("dockPlannerManifest", "dockPlannerManifest");
        dbMapping.put("dateOfSailing", "dateOfSailing");
        dbMapping.put("cutOfDate", "cutOfDate");
        dbMapping.put("shipCompanyName", "shipCompanyName");
        dbMapping.put("boxAndBoxQuantity", "boxAndBoxQuantity");
        dbMapping.put("portOfDischarge", "portOfDischarge");
        dbMapping.put("placeOfDelivery", "placeOfDelivery");
        dbMapping.put("operatorName", "operatorName");
        dbMapping.put("operatorPhone", "operatorPhone");
        dbMapping.put("operatorEmail", "operatorEmail");
        dbMapping.put("documentaryClerkName", "documentaryClerkName");
        dbMapping.put("documentaryClerkPhone", "documentaryClerkPhone");
        dbMapping.put("documentaryClerkEmail", "documentaryClerkEmail");
    }

    public boolean insert(OrderPo order) {
        JdbcResult jdbcResult = BaseJdbcUtils.getInsert(getTable(), order, dbMapping);
        return template.update(jdbcResult.getSql(), jdbcResult.getParams()) == 1;
    }

    public boolean insertIgnore(OrderPo order) {
        JdbcResult jdbcResult = BaseJdbcUtils.getInsertIgnore(getTable(), order, dbMapping);
        return template.update(jdbcResult.getSql(), jdbcResult.getParams()) == 1;
    }

    /**
     * @return true when insert
     */
    public boolean insertOrUpdate(OrderPo order) {
        JdbcResult jdbcResult = BaseJdbcUtils.getInsertOrUpdate(getTable(), order, dbMapping);
        return template.update(jdbcResult.getSql(), jdbcResult.getParams()) == 1;
    }

    public int batchInsert(List<OrderPo> orders) {
        JdbcResult jdbcResult = BaseJdbcUtils.getBatchInsert(getTable(), orders, dbMapping);
        return IntStream.of(template.batchUpdate(jdbcResult.getSql(), jdbcResult.getBatchParams())).sum();
    }

    public boolean update(OrderPo order) {
        JdbcResult jdbcResult = BaseJdbcUtils.getUpdate(getTable(), order, dbMapping, "id");
        return template.update(jdbcResult.getSql(), jdbcResult.getParams()) == 1;
    }

    public boolean patch(OrderPo order) {
        JdbcResult jdbcResult = BaseJdbcUtils.getPatch(getTable(), order, dbMapping, "id");
        return template.update(jdbcResult.getSql(), jdbcResult.getParams()) == 1;
    }

    public OrderPo get(Integer id) {
        JdbcResult jdbcResult = BaseJdbcUtils.getSelect(getTable(), Criteria.column("id").eq(id));
        try {
            Map<String, Object> dbRow = template.queryForMap(jdbcResult.getSql(), jdbcResult.getParams());
            return BaseJdbcUtils.dbRowToPo(dbRow, dbMapping, OrderPo.class);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public OrderPo getOrInsert(OrderPo order) {
        OrderPo po = this.get(order.getId());
        if (po == null) {
            if (!this.insertIgnore(order)) {
                return this.get(order.getId());
            }
            return order;
        }
        return po;
    }

    public PageResponse<OrderPo> getPage(PageRequest pageRequest) {
        JdbcResult jdbcResult = BaseJdbcUtils.getSelectForCount(getTable(), (Criteria) null);
        Integer total = template.queryForObject(jdbcResult.getSql(), jdbcResult.getParams(), Integer.class);
        if(total == 0) {
            return new PageResponse<>(0, null);
        }

        jdbcResult = BaseJdbcUtils.getSelect(getTable(), (Criteria) null, pageRequest);
        List<OrderPo> datas = template.queryForList(jdbcResult.getSql(), jdbcResult.getParams()).stream()
                .map(dbRow -> BaseJdbcUtils.dbRowToPo(dbRow, dbMapping, OrderPo.class))
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