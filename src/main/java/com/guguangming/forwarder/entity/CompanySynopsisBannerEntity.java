package com.guguangming.forwarder.entity;


public class CompanySynopsisBannerEntity {

    //编号
    private Integer id;

    //公司介绍轮播图片编号
    private Integer companySynopsisBannerImgId;

    //公司介绍轮播图片路径
    private String companySynopsisBannerImgUrl;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCompanySynopsisBannerImgId() {
        return companySynopsisBannerImgId;
    }

    public void setCompanySynopsisBannerImgId(Integer companySynopsisBannerImgId) {
        this.companySynopsisBannerImgId = companySynopsisBannerImgId;
    }

    public String getCompanySynopsisBannerImgUrl() {
        return companySynopsisBannerImgUrl;
    }

    public void setCompanySynopsisBannerImgUrl(String companySynopsisBannerImgUrl) {
        this.companySynopsisBannerImgUrl = companySynopsisBannerImgUrl;
    }
}