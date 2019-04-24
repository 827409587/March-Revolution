package com.jk.service;

import com.jk.pojo.Course;
import com.jk.pojo.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@RequestMapping("edu")
public interface EducationServiceApi {

    @RequestMapping("/loginUser")
    User findByUsername(@RequestParam("username") String username);
    @RequestMapping("/queryCartProduct")
    List<Course> queryCartProduct();
}
