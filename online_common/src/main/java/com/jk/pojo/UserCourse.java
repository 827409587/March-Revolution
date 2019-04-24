package com.jk.pojo;

import com.alibaba.fastjson.JSONArray;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "UserCourse")

public class UserCourse {

    private String id;

    private Integer userid;

    private JSONArray course;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public JSONArray getCourse() {
        return course;
    }

    public void setCourse(JSONArray course) {
        this.course = course;
    }
}
