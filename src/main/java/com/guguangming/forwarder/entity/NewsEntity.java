package com.guguangming.forwarder.entity;


import java.util.Date;

public class NewsEntity {

    //新闻编号
    private Integer id;

    //新闻标题
    private String newsTitle;

    //新闻图片路径
    private String newsImgUrl;

    //新闻内容
    private String newsContent;

    //新闻简介
    private String newsSynopsis;

    //新闻缩略图
    private String newsSynopsisImgUrl;

    //新闻时间
    private Date newsTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNewsTitle() {
        return newsTitle;
    }

    public void setNewsTitle(String newsTitle) {
        this.newsTitle = newsTitle;
    }

    public String getNewsImgUrl() {
        return newsImgUrl;
    }

    public void setNewsImgUrl(String newsImgUrl) {
        this.newsImgUrl = newsImgUrl;
    }

    public String getNewsContent() {
        return newsContent;
    }

    public void setNewsContent(String newsContent) {
        this.newsContent = newsContent;
    }

    public String getNewsSynopsis() {
        return newsSynopsis;
    }

    public void setNewsSynopsis(String newsSynopsis) {
        this.newsSynopsis = newsSynopsis;
    }

    public String getNewsSynopsisImgUrl() {
        return newsSynopsisImgUrl;
    }

    public void setNewsSynopsisImgUrl(String newsSynopsisImgUrl) {
        this.newsSynopsisImgUrl = newsSynopsisImgUrl;
    }

    public Date getNewsTime() {
        return newsTime;
    }

    public void setNewsTime(Date newsTime) {
        this.newsTime = newsTime;
    }
}