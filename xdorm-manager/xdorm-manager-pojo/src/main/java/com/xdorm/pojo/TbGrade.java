package com.xdorm.pojo;

import java.io.Serializable;

public class TbGrade implements Serializable{
    private Long gradeId;

    private String gradeName;

    public Long getGradeId() {
        return gradeId;
    }

    public void setGradeId(Long gradeId) {
        this.gradeId = gradeId;
    }

    public String getGradeName() {
        return gradeName;
    }

    public void setGradeName(String gradeName) {
        this.gradeName = gradeName == null ? null : gradeName.trim();
    }
}