package com.jk.mapper;

import com.jk.pojo.Course;
import com.jk.pojo.User;

public interface EducationMapper {
    /**
     * 注册
     * @param user
     */
    void addRegister(User user);

    /**
     * 登录
     * @param username
     * @return
     */
    User findByUsername(String username);

    /**
     * 购物车信息
     * @param productId
     * @return
     */
    Course queryCourse(Integer productId);
}
