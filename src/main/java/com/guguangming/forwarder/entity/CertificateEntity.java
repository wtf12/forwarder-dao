package com.guguangming.forwarder.entity;


import java.util.Date;

public class CertificateEntity {

    //证书编号
    private Integer certificateId;

    //证书名字
    private String certificateName;

    //证书照片路径
    private String certificateImgUrl;

    //证书介绍
    private String certificateSynopsis;

    //证书时间
    private Date certificateTime;

    public Integer getCertificateId() {
        return certificateId;
    }

    public void setCertificateId(Integer certificateId) {
        this.certificateId = certificateId;
    }

    public String getCertificateName() {
        return certificateName;
    }

    public void setCertificateName(String certificateName) {
        this.certificateName = certificateName;
    }

    public String getCertificateImgUrl() {
        return certificateImgUrl;
    }

    public void setCertificateImgUrl(String certificateImgUrl) {
        this.certificateImgUrl = certificateImgUrl;
    }

    public String getCertificateSynopsis() {
        return certificateSynopsis;
    }

    public void setCertificateSynopsis(String certificateSynopsis) {
        this.certificateSynopsis = certificateSynopsis;
    }

    public Date getCertificateTime() {
        return certificateTime;
    }

    public void setCertificateTime(Date certificateTime) {
        this.certificateTime = certificateTime;
    }
}