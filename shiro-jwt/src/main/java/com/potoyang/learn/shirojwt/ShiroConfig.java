package com.potoyang.learn.shirojwt;

import com.potoyang.learn.shirojwt.filter.JwtFilter;
import org.apache.shiro.mgt.DefaultSessionStorageEvaluator;
import org.apache.shiro.mgt.DefaultSubjectDAO;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import javax.servlet.Filter;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created with Intellij IDEA.
 *
 * @author potoyang
 * Create: 2018/8/15 13:47
 * Modified By:
 * Description:
 */
@Configuration
public class ShiroConfig {
    @Bean("shiroFilterFactoryBean")
    public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        // 拦截链
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
        // 配置不会拦截的链接 顺序判断
        filterChainDefinitionMap.put("/login/**", "anon");
        filterChainDefinitionMap.put("/js/**.js", "anon");
        filterChainDefinitionMap.put("/**.html", "anon");
        filterChainDefinitionMap.put("/swagger**/**", "anon");
        filterChainDefinitionMap.put("/webjars/**", "anon");
        filterChainDefinitionMap.put("/v2/**", "anon");
        filterChainDefinitionMap.put("/401", "anon");
        filterChainDefinitionMap.put("/require_auth", "authc");
        filterChainDefinitionMap.put("/require_permission", "authc,perms[view]");
        filterChainDefinitionMap.put("/require_role", "authc,roles[admin]");
//        filterChainDefinitionMap.put("/user/**", "authc,roles[ROLE_USER]");//用户为ROLE_USER 角色可以访问。由用户角色控制用户行为。
//        filterChainDefinitionMap.put("/events/**", "authc,roles[ROLE_ADMIN]");
        // 添加自己的过滤器
        Map<String, Filter> filterMap = new LinkedHashMap<>(1);
        filterMap.put("shiroLoginFilter", new JwtFilter());
        shiroFilterFactoryBean.setFilters(filterMap);

        filterChainDefinitionMap.put("/**", "shiroLoginFilter");

        shiroFilterFactoryBean.setUnauthorizedUrl("/401");

        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);

        return shiroFilterFactoryBean;
    }

    @Bean("securityManager")
    public SecurityManager securityManager(MyRealm myRealm) {
        DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();
        defaultWebSecurityManager.setRealm(myRealm);

        DefaultSubjectDAO defaultSubjectDAO = new DefaultSubjectDAO();
        DefaultSessionStorageEvaluator defaultSessionStorageEvaluator = new DefaultSessionStorageEvaluator();
        defaultSessionStorageEvaluator.setSessionStorageEnabled(false);
        defaultSubjectDAO.setSessionStorageEvaluator(defaultSessionStorageEvaluator);
        defaultWebSecurityManager.setSubjectDAO(defaultSubjectDAO);

        return defaultWebSecurityManager;
    }

    /**
     * 下面的代码是添加注解支持
     */
    @Bean
    @DependsOn("lifecycleBeanPostProcessor")
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        // 强制使用cglib，防止重复代理和可能引起代理出错的问题
        // https://zhuanlan.zhihu.com/p/29161098
        defaultAdvisorAutoProxyCreator.setProxyTargetClass(true);
        return defaultAdvisorAutoProxyCreator;
    }

    @Bean
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
        advisor.setSecurityManager(securityManager);
        return advisor;
    }

}
