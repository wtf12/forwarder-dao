package com.guguangming.forwarder.entity;

public class BusinessScopeEntity {

    //业务编号
    private Integer businessId;

    //业务名称
    private String businessName;

    //关键字描述
    private String describeKeyword;

    //业务内容
    private String businessContent;

    public Integer getBusinessId() {
        return businessId;
    }

    public void setBusinessId(Integer businessId) {
        this.businessId = businessId;
    }

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public String getDescribeKeyword() {
        return describeKeyword;
    }

    public void setDescribeKeyword(String describeKeyword) {
        this.describeKeyword = describeKeyword;
    }

    public String getBusinessContent() {
        return businessContent;
    }

    public void setBusinessContent(String businessContent) {
        this.businessContent = businessContent;
    }
}