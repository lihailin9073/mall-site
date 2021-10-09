package com.wzliulan.mall.auth.config;

import com.wzliulan.mall.auth.component.CustomRealm;
import com.wzliulan.mall.auth.component.CustomSessionManager;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.crazycake.shiro.RedisCacheManager;
import org.crazycake.shiro.RedisManager;
import org.crazycake.shiro.RedisSessionDAO;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Shiro配置类
 * @author li.
 */
@Slf4j
@Configuration
public class ShiroConfig {
//    @Autowired
//    private RedisProperties redisProperties;

    @Bean(name = "shiroFilter")
    public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactory = new ShiroFilterFactoryBean();
        shiroFilterFactory.setSecurityManager(securityManager);
        shiroFilterFactory.setLoginUrl("/auth/un-login"); // 未认证，跳转到此地址
        shiroFilterFactory.setUnauthorizedUrl("/auth/un-perms"); // 无权限，跳转到此地址
        // 注意：setUnauthorizedUrl()方法需要走AuthorizationFilter类型的过滤器才会跳转地址，对于权限注解 @RequiresRoles、@RequiresPermissions 等需要做异常处理、而不会触发跳转地址
        // 比如配置代码： filterChainDefinitionMap.put("/admin/**","roles[admin]"); filterChainDefinitionMap.put("/video/update","perms[video_update]"); 会跳转，但是控制器方法上的权限注解则只会抛异常

        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
        /**
         * anon：匿名过滤器，对应的地址将可匿名访问；
         * authc：认证过滤器，对应的地址需登录才能访问
         */

        // 放行的地址：认证相关、开放API等
        filterChainDefinitionMap.put("/auth/un-login", "anon");
        filterChainDefinitionMap.put("/auth/un-perms", "anon");
        filterChainDefinitionMap.put("/auth/register", "anon");
        filterChainDefinitionMap.put("/auth/login", "anon");
        filterChainDefinitionMap.put("/open/**", "anon");
        //filterChainDefinitionMap.put("/", "anon"); // 如果是前后端分离架构，不要配置这个过滤器

        // 放行Swagger文档
        filterChainDefinitionMap.put("/swagger-resources/**", "anon");
        filterChainDefinitionMap.put("/swagger-ui.html", "anon");
        filterChainDefinitionMap.put("/webjars/**", "anon");
        filterChainDefinitionMap.put("/v2/**", "anon");

        // 放行监控端点
        filterChainDefinitionMap.put("/actuator/**", "anon");

        // 设置退出处理器：该Filter会拦截配置的退出接口URL、并清除用户会话，然后跳转到系统首页 / 地址
        //filterChainDefinitionMap.put("/auth/logout", "logout");

        // 拦截的URL：特定地址需要特定的角色、权限才能访问
        //filterChainDefinitionMap.put("/vip/**", "roles[vip]"); // 该地址需要具备名称为 vip 的角色才能访问
        //filterChainDefinitionMap.put("/user/create", "perms[user:add]"); // 该地址需要具有名称为 user:add 的权限码才能访问

        // 拦截的地址：拦截除了前面配置的URL之外的全部地址
        filterChainDefinitionMap.put("/**", "authc"); /** 这行代码必须放在所有权限设置的最后，不然会导致所有 url 都被拦截；这行代码的作用：除前面配置的URL路径外，剩余的都需要认证 */
        shiroFilterFactory.setFilterChainDefinitionMap(filterChainDefinitionMap);

        return shiroFilterFactory;
    }

    @Bean // Shiro的安全管理器
    public SecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setSessionManager(sessionManager()); // 设置session管理器
        securityManager.setCacheManager(cacheManager()); // 设置Cache缓存管理器
        securityManager.setRealm(customRealm()); // 设置Realm数据源；这行代码要放在最后，否则在有些情况下（比如有些Shiro的版本）会失效
        return securityManager;
    }

    @Bean // Shiro的数据源Realm
    public CustomRealm customRealm() {
        CustomRealm customRealm = new CustomRealm();
        customRealm.setCredentialsMatcher(hashedCredentialsMatcher()); // 设置密码匹配器
        //customRealm.setCachingEnabled(false); // 禁用缓存
        return customRealm;
    }

    @Bean // Shiro的密码匹配器
    public HashedCredentialsMatcher hashedCredentialsMatcher() {
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        hashedCredentialsMatcher.setHashAlgorithmName("md5"); // 散列算法:这里使用MD5算法
        hashedCredentialsMatcher.setHashIterations(2); // 散列的次数，比如散列两次，相当于 md5(md5("xxx"))
        hashedCredentialsMatcher.setStoredCredentialsHexEncoded(true); // storedCredentialsHexEncoded默认是true，此时密码加密用的是Hex编码；false时用Base64编码
        return hashedCredentialsMatcher;
    }

    @Bean // Shiro的Session管理器
    public SessionManager sessionManager() {
        CustomSessionManager sessionManager = new CustomSessionManager();
        sessionManager.setSessionDAO(sessionDAO()); // 持久化Session，避免应用重启后用户会话消失
        //sessionManager.setSessionIdUrlRewritingEnabled(false); // 去除因JSESSIONID导致第一次访问请求时报400错误
        //sessionManager.setGlobalSessionTimeout(1000*20); // 设置超时时间：默认30分钟超时，这里设置为20s
        return sessionManager;
    }
    protected SessionDAO sessionDAO() {
        RedisSessionDAO sessionDAO = new RedisSessionDAO();
        sessionDAO.setRedisManager(getRedisManager());
        return sessionDAO;
    }

    // Shiro的缓存管理器
    protected CacheManager cacheManager() {
        RedisCacheManager cacheManager = new RedisCacheManager();
        cacheManager.setRedisManager(getRedisManager());
        cacheManager.setExpire(30); // 缓存时间为30s
        return cacheManager;
    }

    // Redis管理器
    protected RedisManager getRedisManager() {
        // 注意：由于在这里读取不到配置文件中的配置，因此这里的Redis配置被写死了
        //log.info("-----Redis的配置：{}", redisProperties);
        RedisManager redisManager = new RedisManager();
        redisManager.setHost("192.168.1.201");
        redisManager.setPort(16379);
        redisManager.setPassword("123456");
        return redisManager;
    }


    // --------------------------------- 启用Shiro权限注解：Begin ---------------------------------
    @Bean // Shiro 的Bean生命周期管理器
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }
    /**
     * 开启Shiro的注解(如@RequiresRoles、@RequiresPermissions)，需借助Spring AOP扫描使用Shiro注解的类,并在必要时进行安全逻辑验证；
     * 配置以下两个bean即可实现此功能：DefaultAdvisorAutoProxyCreator(可选)、AuthorizationAttributeSourceAdvisor
     */
    @Bean // 用来扫描上下文、寻找所有的Advisor通知器，将符合条件的Advisor应用到AOP切点指向的Bean中，需要在 LifecycleBeanPostProcessor 这个Bean创建后才可以创建
    @DependsOn({"lifecycleBeanPostProcessor"})
    public DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        advisorAutoProxyCreator.setProxyTargetClass(true);
        return advisorAutoProxyCreator;
    }
    @Bean // 启用Shiro的注解功能，不加入这个Bean则Shiro的所有注解都无效；原因是这些注解都是需要Shiro借助Spring AOP来扫描并处理的
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor() {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager());
        return authorizationAttributeSourceAdvisor;
    }
    // --------------------------------- 启用Shiro权限注解：End ---------------------------------
}
