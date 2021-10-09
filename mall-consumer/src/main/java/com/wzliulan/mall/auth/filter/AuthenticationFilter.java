package com.wzliulan.mall.auth.filter;

import com.alibaba.fastjson.JSON;
import com.wzliulan.mall.consumer.domain.properties.AuthProperties;
import com.wzliulan.mall.consumer.service.ITokenService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * 认证过滤器
 */
@Slf4j
@WebFilter(filterName = "Filter001", urlPatterns = {"/*"})
public class AuthenticationFilter implements Filter {
    @Resource
    private ITokenService tokenService;
    @Autowired
    private AuthProperties authProperties;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("----消费者认证过滤器：启动");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        String path = request.getServletPath();

        // 放行白名单API接口
        ArrayList<String> allowList = authProperties.getAllowList();
        for (String uri : allowList) {
            if (path.contains(uri)) {
                filterChain.doFilter(servletRequest, servletResponse);
                return;
            }
        }

        // 获取token
        String token = request.getHeader("TOKEN") == null ? request.getParameter("token") : request.getHeader("TOKEN");
        if (StringUtils.isEmpty(token)) { // token未提供
            Map<String, Object> result = new HashMap<>();
            result.put("code", -1000);
            result.put("message", "请提供登录令牌！");
            result.put("data", "请提供登录令牌！");

            response.setContentType("application/json;charset=utf-8");
            response.getWriter().println(JSON.toJSONString(result));
            return ;
        }

        // 校验token
        if (tokenService.isValid(token)) {
            // token校验通过
            filterChain.doFilter(servletRequest, servletResponse);
            //return ;
        } else { // 令牌无效
            Map<String, Object> result = new HashMap<>();
            result.put("code", -1000);
            result.put("message", "您的登录令牌无效！");
            result.put("data", "您的登录令牌无效！");

            response.setContentType("application/json;charset=utf-8");
            response.getWriter().println(JSON.toJSONString(result));
            //return ;
        }
    }

    @Override
    public void destroy() {
        log.info("----消费者认证过滤器：销毁");
    }
}
