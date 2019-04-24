package com.jk.pojo;

import java.io.Serializable;

public class Course implements Serializable {

    private static final long serialVersionUID = 767549632111351514L;
    private Integer id;
    private String courseName;
    private String editor;
    private Double coursePrice;
    private String courseImg;
    private Integer courseCount;

    public Integer getCourseCount() {
        return courseCount;
    }

    public void setCourseCount(Integer courseCount) {
        this.courseCount = courseCount;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getEditor() {
        return editor;
    }

    public void setEditor(String editor) {
        this.editor = editor;
    }

    public Double getCoursePrice() {
        return coursePrice;
    }

    public void setCoursePrice(Double coursePrice) {
        this.coursePrice = coursePrice;
    }

    public String getCourseImg() {
        return courseImg;
    }

    public void setCourseImg(String courseImg) {
        this.courseImg = courseImg;
    }
}
