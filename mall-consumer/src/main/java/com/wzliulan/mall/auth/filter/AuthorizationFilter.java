package com.wzliulan.mall.auth.filter;

import com.alibaba.fastjson.JSON;
import com.wzliulan.mall.consumer.dao.model.Token;
import com.wzliulan.mall.consumer.dao.model.User;
import com.wzliulan.mall.consumer.domain.properties.AuthProperties;
import com.wzliulan.mall.consumer.service.ITokenService;
import com.wzliulan.mall.consumer.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 鉴权过滤器
 */
@Slf4j
@WebFilter(filterName = "Filter002", urlPatterns = {"/*"})
public class AuthorizationFilter implements Filter {
    @Resource
    private ITokenService tokenService;
    @Autowired
    private IUserService userService;
    @Autowired
    private AuthProperties authProperties;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("----消费者鉴权过滤器：启动");
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

        // 获取用户角色包含的权限URI
        String token = request.getHeader("TOKEN") == null ? request.getParameter("token") : request.getHeader("TOKEN");
        Token tk = tokenService.find(token);
        User user = userService.getById(Integer.parseInt(tk.getUserId()));
        if (null == user) {
            Map<String, Object> result = new HashMap<>();
            result.put("code", -1001);
            result.put("message", "用户不存在！");
            result.put("data", "用户不存在！");

            response.setContentType("application/json;charset=utf-8");
            response.getWriter().println(JSON.toJSONString(result));
            return ;
        }
        List<Map> userPermissionList = userService.findPermissionList(user.getId());
        for (Map uri : userPermissionList) {
            log.info("用户权限：{}，当前请求path为：{}", uri.get("action"), path);
            if (path.contains(uri.get("action").toString())) { // 存在与当前请求path匹配的地址，表示有权限访问，放行通过
                // 权限校验通过
                filterChain.doFilter(servletRequest, servletResponse);
                return;
            }
        }

        // 无权限访问
        Map<String, Object> result = new HashMap<>();
        result.put("code", 401);
        result.put("message", "权限不足！");
        result.put("data", "权限不足！");

        response.setContentType("application/json;charset=utf-8");
        response.getWriter().println(JSON.toJSONString(result));
        //return ;
    }

    @Override
    public void destroy() {
        log.info("----消费者鉴权过滤器：销毁");
    }
}
