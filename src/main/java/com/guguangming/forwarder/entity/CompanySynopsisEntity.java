package com.guguangming.forwarder.entity;

public class CompanySynopsisEntity {

    //编号（有且仅有1）
    private Integer id;

    //简介
    private String synopsis;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }
}