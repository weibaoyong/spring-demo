package com.wby.shiro.config;

import com.wby.shiro.realm.ShiroRealm;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;

@Configuration
public class ShiroConfig {

    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager){
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        //设置securityManager
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        //登陆url
        shiroFilterFactoryBean.setLoginUrl("/login");
        //登陆成功后跳转url
        shiroFilterFactoryBean.setSuccessUrl("/index");
        //未授权的url
        shiroFilterFactoryBean.setUnauthorizedUrl("/403");


        LinkedHashMap<String,String> filterChainDefinitionMap = new LinkedHashMap<>();
        //定义filterChain,静态资源不拦截
        filterChainDefinitionMap.put("/css/**","anon");
        filterChainDefinitionMap.put("/js/**","anon");
        filterChainDefinitionMap.put("/fonts/**","anon");
        filterChainDefinitionMap.put("/img/**","anon");
        //druid数据源监控页面不拦截
        filterChainDefinitionMap.put("/druid/**","anon");
        //配置退出过滤器，其中具体的退出代码shiro已经替换我们实现
        filterChainDefinitionMap.put("/logout/**","logout");
        filterChainDefinitionMap.put("/","logout");
        //除以上外所有url都必须认证通过才可以访问，未通过认证自动访问LoginUrl
        filterChainDefinitionMap.put("/**","authc");

        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return shiroFilterFactoryBean;
    }

    @Bean
    public SecurityManager securityManager(){
        //配置SecurityManager，并注入shiroRealm
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(shiroRealm());
        return securityManager;
    }

    @Bean
    public ShiroRealm shiroRealm() {
        ShiroRealm shiroRealm = new ShiroRealm();
        return shiroRealm;
    }
}
