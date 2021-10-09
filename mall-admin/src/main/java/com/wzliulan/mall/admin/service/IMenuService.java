package com.wzliulan.mall.admin.service;

import com.wzliulan.mall.admin.dao.model.Menu;
import com.wzliulan.mall.domain.ApiResponse;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 菜单信息表 服务类
 * </p>
 *
 * @author li.
 * @since 2021-07-24
 */
public interface IMenuService extends IService<Menu> {

    /**
     * 系统用户菜单查询方法
     * @param userId 用户ID
     * @return 返回系统用户的菜单集合
     */
    ApiResponse findUserMenuTree(String userId);
}
