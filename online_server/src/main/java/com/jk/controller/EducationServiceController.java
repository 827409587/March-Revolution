package com.jk.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.jk.mapper.EducationMapper;
import com.jk.pojo.Course;
import com.jk.pojo.User;
import com.jk.pojo.UserCourse;
import com.jk.service.EducationServiceApi;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Controller
public class EducationServiceController implements EducationServiceApi {

    @Autowired
    private EducationMapper educationMapper;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 注册
     * @param s
     */
    @RabbitListener(queues = "user-register")
    public void addRegister(String s){
        User user = JSON.parseObject(s, User.class);
        educationMapper.addRegister(user);
    }

    /**
     * 登录
     * @param username
     * @return
     */
    @Override
    @ResponseBody
    public User findByUsername(@RequestParam("username") String username) {

        return educationMapper.findByUsername(username);
    }

    /**
     * 购物车信息
     * @return
     */
    @Override
    @ResponseBody
    public List<Course> queryCartProduct() {
        List<Course> list = new ArrayList<Course>();
        Query query = new Query();
        query.addCriteria(Criteria.where("userid").is(1));
        UserCourse one = mongoTemplate.findOne(query, UserCourse.class);
        if(one!=null){
            JSONArray course = one.getCourse();

            for (int i = 0; i < course.size(); i++) {
                Course product = new Course();
                //获取商品id
                Integer productId = course.getJSONArray(i).getInteger(0);
                String cacheKey = "wy" + productId;
                if (redisTemplate.hasKey(cacheKey)) {
                    product = (Course) redisTemplate.opsForValue().get(cacheKey);
                } else {
                    product = educationMapper.queryCourse(productId);
                    redisTemplate.opsForValue().set(cacheKey, product);
                    redisTemplate.expire(cacheKey, 60, TimeUnit.DAYS);
                }
                Integer count = course.getJSONArray(i).getInteger(1);
                product.setCourseCount(count);
                list.add(product);
            }
        }
        return list;
    }

    /**
     * 加入购物车
     */


    @RabbitListener(queues = "add-Cart")
    public void receiveMessage(Map<String,Integer> map){
        Query query = new Query();
        query.addCriteria(Criteria.where("userid").is(1));
        UserCourse one = mongoTemplate.findOne(query, UserCourse.class);
        if(one==null){
            UserCourse userCourse = new UserCourse();
            userCourse.setUserid(1);
            JSONArray objects = new JSONArray();
            JSONArray arr = new JSONArray();
            arr.add(0,map.get("id"));
            arr.add(1,map.get("count"));
            objects.add(arr);
            userCourse.setCourse(objects);
            mongoTemplate.save(userCourse);
        }else{
            int num = 0;
            JSONArray course = one.getCourse();
            for(int i=0;i<course.size();i++){
                JSONArray jsonArray = course.getJSONArray(i);
                if(jsonArray.get(0).equals(map.get("id"))){
                    num++;
                    Integer sum = jsonArray.getInteger(1);
                    jsonArray.set(1,sum+map.get("count"));
                    one.getCourse().set(i,jsonArray);
                }
            }
            if(num==0){
                JSONArray arr = new JSONArray();
                arr.add(0,map.get("id"));
                arr.add(1,map.get("count"));
                one.getCourse().add(arr);
            }
            mongoTemplate.save(one);
        }
    }


}
