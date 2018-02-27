package com.guguangming.forwarder.dao;

import com.guguangming.forwarder.entity.CompanySynopsisBannerEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;


@Repository
public class CompanySynopsisBannerDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * 添加公司介绍轮播图片
     *
     * @param companySynopsisBannerEntity
     * @return
     */
    public boolean addCompanySynopsisBanner(CompanySynopsisBannerEntity companySynopsisBannerEntity) {
        String sql = "INSERT INTO company_synopsis_banner(company_synopsis_banner_img_url) VALUES (?)";
        return jdbcTemplate.update(sql, companySynopsisBannerEntity) == 1;
    }

    /**
     * 查找所有公司介绍轮播图片
     *
     * @param
     * @return
     */
    public CompanySynopsisBannerEntity getCompanySynopsisBanner() {
        String sql = "SELECT * FROM company_synopsis_banner";
        return jdbcTemplate.queryForObject(sql, CompanySynopsisBannerEntity.class);
    }

    /**
     * 根据公司介绍轮播图片编号查找公司介绍轮播图片路径
     *
     * @param companySynopsisBannerImgId
     * @return
     */
    public CompanySynopsisBannerEntity getCompanySynopsisBannerByCompanySynopsisBannerImgId(Integer companySynopsisBannerImgId) {
        String sql = "SELECT company_synopsis_banner_img_url FROM company_synopsis_banner WHERE company_synopsis_banner_img_id = " + companySynopsisBannerImgId;
        return jdbcTemplate.queryForObject(sql, CompanySynopsisBannerEntity.class);
    }

    /**
     * 根据编号更新公司介绍轮播图片路径
     *
     * @param
     * @return
     */
    public boolean updateCompanySynopsisBannerByCompanySynopsisBannerImgId(Integer companySynopsisBannerImgUrl, String companySynopsisBannerImgId) {
        String sql = "UPDATE company_synopsis_banner SET company_synopsis_banner_img_url = " + companySynopsisBannerImgUrl
                + " WHERE company_synopsis_banner_img_id = " + companySynopsisBannerImgId;
        return jdbcTemplate.update(sql) == 1;
    }

    /**
     * 根据编号删除公司介绍轮播图片
     *
     * @param companySynopsisBannerImgId
     * @return
     */
    public boolean deleteCompanySynopsisBannerById(Integer companySynopsisBannerImgId) {
        String sql = "DELETE FROM company_synopsis_banner WHERE company_synopsis_banner_img_id = " + companySynopsisBannerImgId;
        return jdbcTemplate.update(sql) == 1;
    }
}