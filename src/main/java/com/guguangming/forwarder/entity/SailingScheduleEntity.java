package com.guguangming.forwarder.entity;


import java.util.Date;

public class SailingScheduleEntity {

    //时间表编号
    private Integer scheduleId;

    //时间表名
    private String scheduleName;

    //时间表大小
    private String scheduleSize;

    //时间表类型
    private String scheduleType;

    //时间表路径
    private String scheduleUrl;

    //时间表时间
    private Date scheduleTime;

    public Integer getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(Integer scheduleId) {
        this.scheduleId = scheduleId;
    }

    public String getScheduleName() {
        return scheduleName;
    }

    public void setScheduleName(String scheduleName) {
        this.scheduleName = scheduleName;
    }

    public String getScheduleSize() {
        return scheduleSize;
    }

    public void setScheduleSize(String scheduleSize) {
        this.scheduleSize = scheduleSize;
    }

    public String getScheduleType() {
        return scheduleType;
    }

    public void setScheduleType(String scheduleType) {
        this.scheduleType = scheduleType;
    }

    public String getScheduleUrl() {
        return scheduleUrl;
    }

    public void setScheduleUrl(String scheduleUrl) {
        this.scheduleUrl = scheduleUrl;
    }

    public Date getScheduleTime() {
        return scheduleTime;
    }

    public void setScheduleTime(Date scheduleTime) {
        this.scheduleTime = scheduleTime;
    }
}