package com.xdorm.pojo;

import java.io.Serializable;

public class TbCollege  implements Serializable{
    private Long collegeId;

    private String collegeName;

    public Long getCollegeId() {
        return collegeId;
    }

    public void setCollegeId(Long collegeId) {
        this.collegeId = collegeId;
    }

    public String getCollegeName() {
        return collegeName;
    }

    public void setCollegeName(String collegeName) {
        this.collegeName = collegeName == null ? null : collegeName.trim();
    }
}