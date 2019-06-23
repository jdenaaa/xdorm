package com.xdorm.pojo;

import java.io.Serializable;

public class TbDorm implements Serializable{
    private Long dormId;

    private String dormName;

    private Integer floorTotal;

    public Long getDormId() {
        return dormId;
    }

    public void setDormId(Long dormId) {
        this.dormId = dormId;
    }

    public String getDormName() {
        return dormName;
    }

    public void setDormName(String dormName) {
        this.dormName = dormName == null ? null : dormName.trim();
    }

    public Integer getFloorTotal() {
        return floorTotal;
    }

    public void setFloorTotal(Integer floorTotal) {
        this.floorTotal = floorTotal;
    }
}