package com.jk.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.jk.pojo.Course;
import com.jk.pojo.User;
import com.jk.service.EducationServiceFeign;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.common.text.Text;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.*;

@Controller
public class EducationController {


    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

    /**
     * es 展示
     * @param course
     * @return
     */
    @RequestMapping("/queryCurriculum")
    @ResponseBody
    public JSONObject queryProduct(Course course){
        JSONObject result = new JSONObject();
        //获取到es的客户端
        Client client = elasticsearchTemplate.getClient();
        /*Integer startIndex = rows*(page-1);*/
        SearchRequestBuilder searchRequestBuilder = client.prepareSearch("courses").setTypes("wy");
        //条件
        if(course.getCourseName() !=null && course.getCourseName() != "" ){
            searchRequestBuilder.setQuery(QueryBuilders.boolQuery().must(QueryBuilders.matchQuery("coursename", course.getCourseName())));
        }
        /*if(course.get !=null){
            searchRequestBuilder.setQuery(QueryBuilders.boolQuery().must(QueryBuilders.rangeQuery("price").gte(shop.getStartPrice())));
        }
        if(shop.getEndPrice() !=null){
            searchRequestBuilder.setQuery(QueryBuilders.boolQuery().must(QueryBuilders.rangeQuery("price").lte(shop.getEndPrice())));
        }*/
        //价格排序
        /*if(shop.getSortPrice() == ConstantUtil.ORDER_SORT_ASC){
            searchRequestBuilder.addSort("price", SortOrder.ASC);
        }else if(shop.getSortPrice() == ConstantUtil.ORDER_SORT_DESC){
            searchRequestBuilder.addSort("price", SortOrder.DESC);
        }
        searchRequestBuilder.setFrom(startIndex).setSize(rows);
        // 设置是否按查询匹配度排序
        searchRequestBuilder.setExplain(true);*/

        HighlightBuilder highlightBuilder = new HighlightBuilder();
        highlightBuilder.field("coursename");
        highlightBuilder.preTags("<font color='red'>");
        highlightBuilder.postTags("</font>");
        searchRequestBuilder.highlighter(highlightBuilder);

        SearchResponse searchResponse = searchRequestBuilder.get();

        SearchHits hits = searchResponse.getHits();
        //long total = hits.getTotalHits();
        //System.out.println("total = [" + total + "]");

        Iterator<SearchHit> iterator = hits.iterator();

        List<Course> list = new ArrayList<Course>();

        while (iterator.hasNext()){
            SearchHit next = iterator.next();
            Map<String, HighlightField> highlightFields = next.getHighlightFields();

            String sourceAsString = next.getSourceAsString();
            HighlightField info = highlightFields.get("coursename");
            Course shopBean = JSON.parseObject(sourceAsString, Course.class);
            //取得定义的高亮标签
            if(info !=null) {
                Text[] fragments = info.fragments();
                //为thinkName（相应字段）增加自定义的高亮标签
                String title = "";
                for (Text text1 : fragments) {
                    title += text1;
                }
                shopBean.setCourseName(title);
            }
            list.add(shopBean);
        }
        //result.put("total",total);
        result.put("returnData",list);
        return result;

    }

    @Autowired
    private EducationServiceFeign educationServiceFeign;

    @Autowired
    private AmqpTemplate amqpTemplate;
    /**
     * 注册
     */
    @RequestMapping("/addRegister")
    public void addRegister(User user){
        String s = JSON.toJSONString(user);
        amqpTemplate.convertAndSend("user-register",s);
    }

    /**
     * 登录
     */
    @RequestMapping("/loginUser")
    @ResponseBody
    public String loginUser(@RequestParam("username") String username,
                            @RequestParam("password") String password,
                            HttpSession session) {
        System.out.println("username:" + username + ",password:" + password);
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        //主体
        Subject subject = SecurityUtils.getSubject();
        //认证逻辑可能出现异常
        //主体进行登陆
        subject.login(token);
        //获取登陆用户
        User user = (User) subject.getPrincipal();
        //写入Session
        session.setAttribute("user", user);
        return "1";
    }

/**
 * 加入购课车
 */

    @RequestMapping("/addVideo")
    @ResponseBody
    public void addShoppingCart(Integer id,Integer count){
        Map<String,Integer> map = new HashMap<>();
        map.put("id",id);
        map.put("count",count);
        amqpTemplate.convertAndSend("add-Cart",map);
    }
    /**
     * 查询购物车商品
     */

    @RequestMapping("/queryCartProduct")
    @ResponseBody
    public List<Course> queryCartProduct(){
        List<Course> list = educationServiceFeign.queryCartProduct();
        return list;
    }
}
