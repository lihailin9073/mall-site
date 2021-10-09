package com.wzliulan.mall.admin.service;

import com.wzliulan.mall.admin.dao.model.Admin;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 用户信息表 服务类
 * </p>
 *
 * @author li.
 * @since 2021-07-23
 */
public interface IAdminService extends IService<Admin> {
    /**
     * 系统用户查询方法
     * @param username 系统用户账号
     * @return 返回系统用户对象Admin
     */
    Admin findByName(String username);

    /**
     * 系统用户权限清单获取方法
     * @param userId 用户ID
     * @return 返回用户所拥有的全部权限码列表
     */
    List<String> getPermissionsById(String userId);

}
