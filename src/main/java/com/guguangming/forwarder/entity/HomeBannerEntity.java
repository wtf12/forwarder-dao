package com.guguangming.forwarder.entity;

public class HomeBannerEntity {

    //编号
    private Integer id;

    //首页图片轮播编号
    private Integer homeBannerImgId;

    //首页图片轮播路径
    private String homeBannerImgUrl;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getHomeBannerImgId() {
        return homeBannerImgId;
    }

    public void setHomeBannerImgId(Integer homeBannerImgId) {
        this.homeBannerImgId = homeBannerImgId;
    }

    public String getHomeBannerImgUrl() {
        return homeBannerImgUrl;
    }

    public void setHomeBannerImgUrl(String homeBannerImgUrl) {
        this.homeBannerImgUrl = homeBannerImgUrl;
    }
}