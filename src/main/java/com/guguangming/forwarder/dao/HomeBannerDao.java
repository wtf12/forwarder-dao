package com.guguangming.forwarder.dao;

import com.guguangming.forwarder.entity.HomeBannerEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class HomeBannerDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * 添加首页介绍轮播图片
     *
     * @param homeBannerEntity
     * @return
     */
    public boolean addHomeBanner(HomeBannerEntity homeBannerEntity) {
        String sql = "INSERT INTO home_banner(home_banner_img_id, home_banner_img_url) VALUES (?,?)";
        return jdbcTemplate.update(sql, homeBannerEntity) == 1;
    }

    /**
     * 查找所有首页轮播图片
     *
     * @param
     * @return
     */
    public HomeBannerEntity getHomeBanner() {
        String sql = "SELECT * FROM home_banner ";
        return jdbcTemplate.queryForObject(sql, HomeBannerEntity.class);
    }

    /**
     * 根据首页轮播编号查找首页轮播图片路径
     *
     * @param homeBannerImgId
     * @return
     */
    public HomeBannerEntity getHomeBannerByHomeBannerImgId(Integer homeBannerImgId) {
        String sql = "SELECT home_banner_img_url FROM home_banner WHERE home_banner_img_id =" + homeBannerImgId;
        return jdbcTemplate.queryForObject(sql, HomeBannerEntity.class);
    }

    /**
     * 根据首页轮播编号更新首页介绍轮播图片
     *
     * @param
     * @return
     */
    public boolean updateHomeBannerByHomeBannerImgId(String homeBannerImgUrl, Integer homeBannerImgId) {
        String sql = "UPDATE home_banner SET home_banner_img_url =" + homeBannerImgUrl + " WHERE home_banner_img_id =" + homeBannerImgId;
        return jdbcTemplate.update(sql) == 1;
    }

    /**
     * 根据首页轮播编号删除首页介绍轮播图片
     *
     * @param
     * @return
     */
    public boolean deleteHomeBannerByHomeBannerImgId(Integer homeBannerImgId) {
        String sql = "DELETE FROM home_banner WHERE home_banner_img_id =" + homeBannerImgId;
        return jdbcTemplate.update(sql) == 1;
    }
}