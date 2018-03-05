package com.guguangming.forwarder.dao;

import com.guguangming.forwarder.entity.CertificateEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;


@Repository
public class CertificateDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * 添加证书信息
     *
     * @param certificateEntity
     * @return
     */
    public boolean addCertificate(CertificateEntity certificateEntity) {
        String sql = "INSERT INTO certificate(certificate_name, " +
                "certificate_img_url, " +
                "certificate_synopsis, " +
                "certificate_time) VALUES (?, ?, ?, ?)";
        return jdbcTemplate.update(sql, certificateEntity) == 1;
    }

    /**
     * 查找所有证书信息
     *
     * @param
     * @return
     */
    public CertificateEntity getCertificate() {
        String sql = "SELECT * FROM certificate";
        return jdbcTemplate.queryForObject(sql, CertificateEntity.class);
    }

    /**
     * 通过证书名查找证书信息
     *
     * @param certificateName
     * @return
     */
    public CertificateEntity getCertificateByCertificateName(String certificateName) {
        String sql = "SELECT * FROM certificate WHERE certificate_name = '" + certificateName + "'";
        return jdbcTemplate.queryForObject(sql, CertificateEntity.class);
    }

    /**
     * 通过证书名更新证书信息
     *
     * @param certificateEntity
     * @return
     */
    public boolean updateCertificateByCertificateName(CertificateEntity certificateEntity) {
        String sql = "UPDATE certificate SET certificate_img_url = '" + certificateEntity.getCertificateImgUrl()
                + "', certificate_synopsis = '" + certificateEntity.getCertificateSynopsis()
                + "', certificate_time = '" + certificateEntity.getCertificateTime()
                + "' WHERE certificate_name = '" + certificateEntity.getCertificateName() + "'";
        return jdbcTemplate.update(sql) == 1;
    }

    /**
     * 通过证书名删除证书信息
     *
     * @param certificateName
     * @return
     */
    public boolean deleteCertificateByCertificateName(String certificateName) {
        String sql = "DELETE FROM certificate WHERE certificateName = '" + certificateName + "'";
        return jdbcTemplate.update(sql) == 1;
    }
}