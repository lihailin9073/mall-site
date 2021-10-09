package com.wzliulan.mall.auth.component;

import org.apache.shiro.web.servlet.ShiroHttpServletRequest;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.apache.shiro.web.util.WebUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.Serializable;

/**
 * 自定义Session管理器，自己管理sessionId（从请求头中读取 token 字段），实现前后端分离
 * @author li.
 */
public class CustomSessionManager extends DefaultWebSessionManager {
    private static final String AUTHORIZATION = "token";

    public CustomSessionManager() {
        super();
    }

    @Override
    protected Serializable getSessionId(ServletRequest request, ServletResponse response) {
        // 重写获取客户端sessionId的方式：从请求头中读取token字段的值，该字段的值即为sessionId
        String sessionId = WebUtils.toHttp(request).getHeader(AUTHORIZATION);
        
        if (sessionId != null) {
            request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID_SOURCE, ShiroHttpServletRequest.COOKIE_SESSION_ID_SOURCE);
            // 托管sessionId到Shiro，由Shiro进行管理（比如判断是否存在、是否有效等）
            request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID, sessionId);
            // 标记sessionId为有效
            request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID_IS_VALID, Boolean.TRUE);

            return sessionId;
        } else { // 没有提供 token 字段，则调用父类的方式来读取sessionId
            return super.getSessionId(request, response);
        }
    }
}
