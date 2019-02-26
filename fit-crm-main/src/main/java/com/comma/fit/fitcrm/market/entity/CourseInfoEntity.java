package com.comma.fit.fitcrm.market.entity;

/**
 * @author magang
 */
public class CourseInfoEntity {
    /**
     * 课程类型
     */
    private Integer courseType;
    /**
     * 商品名称
     */
    private String productName;
    /**
     * 课程名称
     */
    private String courseName;

    public Integer getCourseType() {
        return courseType;
    }

    public void setCourseType(Integer courseType) {
        this.courseType = courseType;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }
}

