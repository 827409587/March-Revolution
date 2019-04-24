package com.jk.shiro;

import org.apache.shiro.cache.MemoryConstrainedCacheManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.LinkedHashMap;

@Configuration
public class ShiroConfiguration {

    /**
     * 项目启动shiroFilter首先会被初始化,并且逐层传入SecurityManager，Realm，matcher
     * @param manager
     * @return
     */
    @Bean("shiroFilter")
    public ShiroFilterFactoryBean shiroFilter(@Qualifier("securityManager") SecurityManager manager){
        //切入口，拦截所有请求
        ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
        //设置安全管理器
        bean.setSecurityManager(manager);
        //当认证失败会调用此路径登陆界面
        bean.setLoginUrl("/errorlogin");
        //成功后页面
        bean.setSuccessUrl("/index");

        //键值对:请求-拦截器(权限配置)
        LinkedHashMap<String,String> filterChainDefinitonMap = new LinkedHashMap<String, String>();
        //用来退出登录的   logout自动退出
        filterChainDefinitonMap.put("/logout","logout");
        //登陆不需要任何过滤 anon不做身份验证
        filterChainDefinitonMap.put("/login","anon");
        //注册不需要任何过滤 anon不做身份验证
        filterChainDefinitonMap.put("/register","anon");
        filterChainDefinitonMap.put("/addRegister","anon");
        filterChainDefinitonMap.put("/verification/sendSmsContent","anon");
        filterChainDefinitonMap.put("/queryCurriculum","anon");
        filterChainDefinitonMap.put("/queryDrug","anon");
        filterChainDefinitonMap.put("/addVideo","anon");
        filterChainDefinitonMap.put("/queryCartProduct","anon");
        filterChainDefinitonMap.put("/goAlipay","anon");
        // anon不做身份验证
        filterChainDefinitonMap.put("/loginUser","anon");
        //注意URLPattern写的是两颗星，这样才能实现任意层次的全匹配
        filterChainDefinitonMap.put("/css/**","anon");
        filterChainDefinitonMap.put("/js/**","anon");
        //首页地址index，表示需要认证才能访问
        filterChainDefinitonMap.put("/**","authc");
        //角色过滤器  roles  表示用户必需已通过认证，并拥有 admin 角色才可以正常发起 /admin 请求
        //roles[admin,可以写多个] 允许多个参数（逗号分隔）此时要全部通过才算通过
        filterChainDefinitonMap.put("/admin","roles[admin]");
        // perms 权限过滤器  拥有edit权限
        filterChainDefinitonMap.put("/edit","perms[edit]");
        //port  端口过滤器，[8080]代表URL只能8080
        filterChainDefinitonMap.put("/port","port[8081]");
        //其他请求只验证是否登陆过
        filterChainDefinitonMap.put("/**","user");
        //放入Shiro过滤器
        bean.setFilterChainDefinitionMap(filterChainDefinitonMap);
        return bean;
    }

    /**
     * 将定义好的Realm放入安全会话中心
     * @param authRealm
     * @return
     */
    @Bean("securityManager")
    public SecurityManager securityManager(@Qualifier("authRealm") AuthRealm authRealm){

        DefaultWebSecurityManager manager = new DefaultWebSecurityManager();
        manager.setRealm(authRealm);
        return manager;
    }

    /**
     * 将自定义的校验规格放入Realm
     * @param matcher
     * @return
     */
    @Bean("authRealm")
    public AuthRealm authRealm(@Qualifier("credentialmatcher") Credentialmatcher matcher){

        AuthRealm authRealm = new AuthRealm();
        //信息放入缓存
        authRealm.setCacheManager(new MemoryConstrainedCacheManager());
        authRealm.setCredentialsMatcher(matcher);
        return  authRealm;
    }

    /**
     * 校验规则
     * @return 校验实例
     */
    @Bean("credentialmatcher")
    public Credentialmatcher credentialmatcher(){
        return  new Credentialmatcher();
    }

    /**
     * Spring与Shiro关联
     * @param manager
     * @return
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(@Qualifier("securityManager") SecurityManager manager){

        AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
        advisor.setSecurityManager(manager);
        return advisor;
    }

    /**
     * 开启代理
     * @return
     */
    @Bean
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator(){
        DefaultAdvisorAutoProxyCreator creator = new DefaultAdvisorAutoProxyCreator();
        creator.setProxyTargetClass(true);
        return creator;
    }
}
