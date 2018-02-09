package com.guguangming.forwarder.dao;

import com.moxie.commons.BaseJdbcUtils;
import com.moxie.commons.model.*;
import com.guguangming.forwarder.po.CertificatePo;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Repository
public class CertificateDao {
    private final static String TABLE_NAME = "certificate";
    private Map<String, String> dbMapping = new HashMap<>();
    @Resource(name = "templateForwarder")
    private JdbcTemplate template;

    @PostConstruct
    public void init() {
        dbMapping.put("id", "id");
        dbMapping.put("certificateName", "certificateName");
        dbMapping.put("certificateImgUrl", "certificateImgUrl");
        dbMapping.put("certificateSynopsis", "certificateSynopsis");
    }

    public boolean insert(CertificatePo certificate) {
        JdbcResult jdbcResult = BaseJdbcUtils.getInsert(getTable(), certificate, dbMapping);
        return template.update(jdbcResult.getSql(), jdbcResult.getParams()) == 1;
    }

    public boolean insertIgnore(CertificatePo certificate) {
        JdbcResult jdbcResult = BaseJdbcUtils.getInsertIgnore(getTable(), certificate, dbMapping);
        return template.update(jdbcResult.getSql(), jdbcResult.getParams()) == 1;
    }

    /**
     * @return true when insert
     */
    public boolean insertOrUpdate(CertificatePo certificate) {
        JdbcResult jdbcResult = BaseJdbcUtils.getInsertOrUpdate(getTable(), certificate, dbMapping);
        return template.update(jdbcResult.getSql(), jdbcResult.getParams()) == 1;
    }

    public int batchInsert(List<CertificatePo> certificates) {
        JdbcResult jdbcResult = BaseJdbcUtils.getBatchInsert(getTable(), certificates, dbMapping);
        return IntStream.of(template.batchUpdate(jdbcResult.getSql(), jdbcResult.getBatchParams())).sum();
    }

    public boolean update(CertificatePo certificate) {
        JdbcResult jdbcResult = BaseJdbcUtils.getUpdate(getTable(), certificate, dbMapping, "id");
        return template.update(jdbcResult.getSql(), jdbcResult.getParams()) == 1;
    }

    public boolean patch(CertificatePo certificate) {
        JdbcResult jdbcResult = BaseJdbcUtils.getPatch(getTable(), certificate, dbMapping, "id");
        return template.update(jdbcResult.getSql(), jdbcResult.getParams()) == 1;
    }

    public CertificatePo get(Integer id) {
        JdbcResult jdbcResult = BaseJdbcUtils.getSelect(getTable(), Criteria.column("id").eq(id));
        try {
            Map<String, Object> dbRow = template.queryForMap(jdbcResult.getSql(), jdbcResult.getParams());
            return BaseJdbcUtils.dbRowToPo(dbRow, dbMapping, CertificatePo.class);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public CertificatePo getOrInsert(CertificatePo certificate) {
        CertificatePo po = this.get(certificate.getId());
        if (po == null) {
            if (!this.insertIgnore(certificate)) {
                return this.get(certificate.getId());
            }
            return certificate;
        }
        return po;
    }

    public PageResponse<CertificatePo> getPage(PageRequest pageRequest) {
        JdbcResult jdbcResult = BaseJdbcUtils.getSelectForCount(getTable(), (Criteria) null);
        Integer total = template.queryForObject(jdbcResult.getSql(), jdbcResult.getParams(), Integer.class);
        if(total == 0) {
            return new PageResponse<>(0, null);
        }

        jdbcResult = BaseJdbcUtils.getSelect(getTable(), (Criteria) null, pageRequest);
        List<CertificatePo> datas = template.queryForList(jdbcResult.getSql(), jdbcResult.getParams()).stream()
                .map(dbRow -> BaseJdbcUtils.dbRowToPo(dbRow, dbMapping, CertificatePo.class))
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