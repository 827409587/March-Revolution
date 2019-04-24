package com.jk.config;

import java.io.FileWriter;
import java.io.IOException;

public class AlipayConfig {
    // 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号
    public static String app_id = "2016092600601236";

    // 商户私钥，您的PKCS8格式RSA2私钥
    public static String merchant_private_key = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCO4QnZobaxkmEBY+uQhOX7CHxrubUECcP6NkGNBNEunnGiZwjYbzwv1IieOyx+V/Zq7JUoR5Rm+jTrWz0YR0jK5y9nnfwZY94fIgoNooRXb1yNiYIx0YNh9jWvj3yfMyc8mk94GQ6jmwyDw+exoONAJ+5My2VaJkZiwfgWVrGf9bRs9m9jPoCbRyKJWYgde5y4Vo2yPvGTmCIbokh5fd54i7U4u540hmHhEkg3H2KZQ29ffavvmpPtQEuo6c56NkSXTJ487z//B+IrtHz7b9YB3l+0Zq/BdWxdBYy67KyYixdKUoZntGVV27+j/C6rm3hspN6vQt0ERJJewtuJMmzPAgMBAAECggEAZmPEgE6QMt6YX7uwU0e529Tv/O7/Vp587/EztpAuK79l5aD6yMemFQy0vtffAcedknBqT4xEQCusAsHGnYBwHN/hq9qej79urVbiZ3UAa/wlElTplTP0eB5NyLsF7rxksNxQeNtUD1CMflzEzBlk4VP8RNI5lon5RMqGM9P6xIroYz3e9hOGDG0Tk5wZDbqU1zWLjJ0yHnah84LuGp1I0wIa5WcXOHibRmQuh2ZDqOaVua+fG22oHQT0P4KqsGS17rGZ/z1h1x7ufjx5ivmj0TYYYPTpKyNrWcV93PZ3eVU+102Vfe0mVxOWqTER79N81qtllA7rumxJ4X813HtsyQKBgQDHN4GYGOEalmduG+jtx923ITdJiZGRWXpiY95VdK7jwbkNzNUMIpIFscvahbGYSlut77X9qkATUqTg4/NW9YCbEV3G+wjI6bd5auRiyFQCUSsSLsZEheFXPcgSiUPdy2BUiD+nt33I/1iE6j5/iScORGhJ1+mv+E+xIEstp5eidQKBgQC3mqqtl3WSisB+PJdIuDQCuIzaD/V+CMKMVOuHtyoLa2IcRkBojD2AUCLKiNfEBcDZb3ITdIE7t7bgiGjXYeogAajIVA97heGW9veqe03wGHPjwMiH+CzeHyopU/AoRIKGNPWWxE09wjpD+K7/sCXSCJOkLW5hiWRPbzmaUHfhswKBgQCSMbuawHdBKarRdQ3koU2lvGx0PM5kmgNj/OIGWFCXt2x0IiMeeqgqIaX1wTLpDXhmFom5k5zeEUIJ/35uDE/KfF4TTdnOhyUrpzPfAiAUazhuaYD6fD035pDSeYUiWMNihXIhRFi8jhaJw9UFhsLwh2b4+cf2aQUieQ5yfJCcmQKBgQCCZyzAPGm6GihbwIcmlAYExLTtkBYpXOloqEvcyX/oOm2lbVQDrU9lGgWL2iN9SyTMijX4kW/UTUhrMxdbmmSiNOt+1Q1mCxHNmj0PbaJOW9+7rEoBIIglfvcAAWj6AKa/0U94tJWzvoaJjlAktCQ4h/Tsz4OtbNq8/F9qNarftwKBgDCm15Rv0Rzo8jobPw1vpcAJOWTgolUiW/YShFvbILte0tR4OSUosPzhnSzx0YjGnlPt/kUsqfdIERlAJvPPlEhJtA0mw/EMXcdVvLbo5aA2UevjJ59C3BBaCHs+tHHuTjPhsmQp71EUcq1K9IFY2ONn6bZMTBftXVGK9zJtLMfG";

    // 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
    public static String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAjuEJ2aG2sZJhAWPrkITl+wh8a7m1BAnD+jZBjQTRLp5xomcI2G88L9SInjssflf2auyVKEeUZvo061s9GEdIyucvZ538GWPeHyIKDaKEV29cjYmCMdGDYfY1r498nzMnPJpPeBkOo5sMg8PnsaDjQCfuTMtlWiZGYsH4Flaxn/W0bPZvYz6Am0ciiVmIHXucuFaNsj7xk5giG6JIeX3eeIu1OLueNIZh4RJINx9imUNvX32r75qT7UBLqOnOejZEl0yePO8//wfiK7R8+2/WAd5ftGavwXVsXQWMuuysmIsXSlKGZ7RlVdu/o/wuq5t4bKTer0LdBESSXsLbiTJszwIDAQAB";

    // 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public static String notify_url = "http://kyd.free.idcfengye.com/notify";

    // 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public static String return_url = "http://kyd.free.idcfengye.com/returnUrl";

    // 签名方式
    public static String sign_type = "RSA2";

    // 字符编码格式
    public static String charset = "utf-8";

    // 支付宝网关
    public static String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";

    // 支付宝网关
    public static String log_path = "D:\\";


//↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑

    /**
     * 写日志，方便测试（看网站需求，也可以改成把记录存入数据库）
     * @param sWord 要写入日志里的文本内容
     */
    public static void logResult(String sWord) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(log_path + "alipay_log_" + System.currentTimeMillis()+".txt");
            writer.write(sWord);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
