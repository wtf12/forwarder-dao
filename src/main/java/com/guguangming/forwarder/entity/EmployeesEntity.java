package com.guguangming.forwarder.entity;


public class EmployeesEntity {

    //员工照片编号
    private Integer employeesId;

    //员工照片名字
    private String employeesImgName;

    //员工照片路径
    private String employeesImgUrl;

    //员工介绍
    private String employeesSynopsis;


    public Integer getEmployeesId() {
        return employeesId;
    }

    public void setEmployeesId(Integer employeesId) {
        this.employeesId = employeesId;
    }

    public String getEmployeesImgName() {
        return employeesImgName;
    }

    public void setEmployeesImgName(String employeesImgName) {
        this.employeesImgName = employeesImgName;
    }

    public String getEmployeesImgUrl() {
        return employeesImgUrl;
    }

    public void setEmployeesImgUrl(String employeesImgUrl) {
        this.employeesImgUrl = employeesImgUrl;
    }

    public String getEmployeesSynopsis() {
        return employeesSynopsis;
    }

    public void setEmployeesSynopsis(String employeesSynopsis) {
        this.employeesSynopsis = employeesSynopsis;
    }
}