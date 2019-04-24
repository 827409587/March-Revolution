package com.jk.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.jk.utils.CommonCanstant;
import com.jk.utils.HttpClientUtil;
import com.jk.utils.Md5Util;
import com.jk.utils.TimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

;

@Controller
@RequestMapping("verification")
public class VerificationController {

    static  public Integer userId=1;

    @Resource
    private RedisTemplate<String, Object> redisTemplate;
    @RequestMapping("sendSmsContent")
    @ResponseBody
    public String sendSmsContent(String phone, HttpSession session){

        //发送验证码
        String url = CommonCanstant.MSG_URL;
        HashMap<String, Object> params = new HashMap<>();
        //开发者主ID
        params.put("accountSid", CommonCanstant.MSG_ACCOUNT_SID);
        //短信接收端手机号码集合
        params.put("to", phone);
        String time = TimeUtil.format(new Date());
        //时间戳
        params.put("timestamp", time);
        String sigStr = CommonCanstant.MSG_ACCOUNT_SID + CommonCanstant.MSG_TOKEN + time;
        //签名。MD5(ACCOUNT SID + AUTH TOKEN + timestamp)。共32位（小写）
        params.put("sig", Md5Util.getMd532(sigStr));
        //短信模板ID
        params.put("templateid", "1458320866");
        int str = (int) Math.round(Math.random()*899999+100000);
        //短信内容参数
        params.put("param", str);
        String respData = HttpClientUtil.post(url,params);

        JSONObject parseObject = JSON.parseObject(respData);
        String respCode = parseObject.getString("respCode");
        if (!"00000".equals(respCode)) {
            return "0";
        }
        redisTemplate.opsForValue().set(CommonCanstant.MSG_VALCODE+userId, str, 5, TimeUnit.MINUTES);
        return "1";
    }
}
