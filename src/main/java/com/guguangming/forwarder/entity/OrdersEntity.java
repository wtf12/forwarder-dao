package com.guguangming.forwarder.entity;

import java.util.Date;

public class OrdersEntity {

    //编号
    private Integer id;

    //提单号
    private String billOfLadingNumber;

    //委托编号
    private String entrustTheNumber;

    //工作编号
    private String jobNumber;

    //船名
    private String shipName;

    //航次
    private String voyage;

    //预配舱单
    private String dockPlannerManifest;

    //开航日期
    private Date dateOfSailing;

    //截关日期
    private Date cutOfDate;

    //船公司
    private String shipCompanyName;

    //箱型箱量
    private String boxAndBoxQuantity;

    //卸货港
    private String portOfDischarge;

    //交货地点
    private String placeOfDelivery;

    //操作员姓名
    private String operatorName;

    //操作员电话
    private String operatorPhone;

    //操作员email
    private String operatorEmail;

    //单证员姓名
    private String documentaryClerkName;

    //单证员电话
    private String documentaryClerkPhone;

    //单证员email
    private String documentaryClerkEmail;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBillOfLadingNumber() {
        return billOfLadingNumber;
    }

    public void setBillOfLadingNumber(String billOfLadingNumber) {
        this.billOfLadingNumber = billOfLadingNumber;
    }

    public String getEntrustTheNumber() {
        return entrustTheNumber;
    }

    public void setEntrustTheNumber(String entrustTheNumber) {
        this.entrustTheNumber = entrustTheNumber;
    }

    public String getJobNumber() {
        return jobNumber;
    }

    public void setJobNumber(String jobNumber) {
        this.jobNumber = jobNumber;
    }

    public String getShipName() {
        return shipName;
    }

    public void setShipName(String shipName) {
        this.shipName = shipName;
    }

    public String getVoyage() {
        return voyage;
    }

    public void setVoyage(String voyage) {
        this.voyage = voyage;
    }

    public String getDockPlannerManifest() {
        return dockPlannerManifest;
    }

    public void setDockPlannerManifest(String dockPlannerManifest) {
        this.dockPlannerManifest = dockPlannerManifest;
    }

    public Date getDateOfSailing() {
        return dateOfSailing;
    }

    public void setDateOfSailing(Date dateOfSailing) {
        this.dateOfSailing = dateOfSailing;
    }

    public Date getCutOfDate() {
        return cutOfDate;
    }

    public void setCutOfDate(Date cutOfDate) {
        this.cutOfDate = cutOfDate;
    }

    public String getShipCompanyName() {
        return shipCompanyName;
    }

    public void setShipCompanyName(String shipCompanyName) {
        this.shipCompanyName = shipCompanyName;
    }

    public String getBoxAndBoxQuantity() {
        return boxAndBoxQuantity;
    }

    public void setBoxAndBoxQuantity(String boxAndBoxQuantity) {
        this.boxAndBoxQuantity = boxAndBoxQuantity;
    }

    public String getPortOfDischarge() {
        return portOfDischarge;
    }

    public void setPortOfDischarge(String portOfDischarge) {
        this.portOfDischarge = portOfDischarge;
    }

    public String getPlaceOfDelivery() {
        return placeOfDelivery;
    }

    public void setPlaceOfDelivery(String placeOfDelivery) {
        this.placeOfDelivery = placeOfDelivery;
    }

    public String getOperatorName() {
        return operatorName;
    }

    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName;
    }

    public String getOperatorPhone() {
        return operatorPhone;
    }

    public void setOperatorPhone(String operatorPhone) {
        this.operatorPhone = operatorPhone;
    }

    public String getOperatorEmail() {
        return operatorEmail;
    }

    public void setOperatorEmail(String operatorEmail) {
        this.operatorEmail = operatorEmail;
    }

    public String getDocumentaryClerkName() {
        return documentaryClerkName;
    }

    public void setDocumentaryClerkName(String documentaryClerkName) {
        this.documentaryClerkName = documentaryClerkName;
    }

    public String getDocumentaryClerkPhone() {
        return documentaryClerkPhone;
    }

    public void setDocumentaryClerkPhone(String documentaryClerkPhone) {
        this.documentaryClerkPhone = documentaryClerkPhone;
    }

    public String getDocumentaryClerkEmail() {
        return documentaryClerkEmail;
    }

    public void setDocumentaryClerkEmail(String documentaryClerkEmail) {
        this.documentaryClerkEmail = documentaryClerkEmail;
    }
}