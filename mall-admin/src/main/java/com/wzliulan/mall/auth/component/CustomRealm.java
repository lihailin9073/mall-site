package com.wzliulan.mall.auth.component;

import com.wzliulan.mall.admin.dao.model.Admin;
import com.wzliulan.mall.admin.service.IAdminService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 自定义Realm数据源
 * @author li
 */
@Slf4j
public class CustomRealm extends AuthorizingRealm {
    @Autowired
    private IAdminService adminService;

    /**
     * 认证方法
     * 这里可以注入userService读取库中用户，为了方便演示可以写死帐号、密码
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        log.info("-------认证方法--------");
        String username = (String) authenticationToken.getPrincipal();

        // 根据用户名username，从数据库获取用户信息
        Admin admin = adminService.findByName(username);
        if (null == admin) {
            throw new AccountException("用户不存在");
        }

        // 创建认证对象，注意：参数-3的盐值，加盐方式必须与你创建密码时保持一样，这里即与 AuthUtils.encryptPassword()方法一样
        //SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(username, dbPassword, ByteSource.Md5Util.bytes("salt:" + username), getName());
        SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(admin, admin.getPassword(), ByteSource.Util.bytes("salt:" + admin.getUsername()), getName());
        return simpleAuthenticationInfo;
    }

    /**
     * 鉴权方法
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        log.info("-------鉴权方法--------");
        Admin admin = (Admin) SecurityUtils.getSubject().getPrincipal();

        // 根据用户名username，获取用户保存在数据库中的权限码集合
        List<String> permissions = adminService.getPermissionsById(admin.getId());
        Set<String> perms = new HashSet<>();
        perms.addAll(permissions); // 把权限集合List转换为Set

        //perms.add("user:add");
        //perms.add("user:delete");
        //perms.add("user:update");
        //perms.add("user:find");
        //perms.add("user:search");

        // 设置用户权限
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        authorizationInfo.setStringPermissions(perms);

        // 设置用户角色：根据需要，可以获取、并设置用户的角色集合 authorizationInfo.setRoles(roles);
        // TODO

        return authorizationInfo;
    }
}
