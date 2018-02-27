package com.guguangming.forwarder.entity;

import java.util.Date;

public class ImportantAnnouncementEntity {

    //重要公告编号
    private Integer announcementId;

    //重要公告名字
    private String announcementName;

    //重要公告内容
    private String announcementContent;

    //重要公告时间
    private Date announcementTime;

    public Integer getAnnouncementId() {
        return announcementId;
    }

    public void setAnnouncementId(Integer announcementId) {
        this.announcementId = announcementId;
    }

    public String getAnnouncementName() {
        return announcementName;
    }

    public void setAnnouncementName(String announcementName) {
        this.announcementName = announcementName;
    }

    public String getAnnouncementContent() {
        return announcementContent;
    }

    public void setAnnouncementContent(String announcementContent) {
        this.announcementContent = announcementContent;
    }

    public Date getAnnouncementTime() {
        return announcementTime;
    }

    public void setAnnouncementTime(Date announcementTime) {
        this.announcementTime = announcementTime;
    }
}