package com.guguangming.forwarder.entity;

public class CheckNoticeEntity {

    //查单须知编号（有且仅为1）
    private Integer noticeId;

    //查单须知内容
    private String notice;

    public Integer getNoticeId() {
        return noticeId;
    }

    public void setNoticeId(Integer noticeId) {
        this.noticeId = noticeId;
    }

    public String getNotice() {
        return notice;
    }

    public void setNotice(String notice) {
        this.notice = notice;
    }
}