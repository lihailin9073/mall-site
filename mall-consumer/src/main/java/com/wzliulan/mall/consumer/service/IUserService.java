package com.wzliulan.mall.consumer.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wzliulan.mall.consumer.dao.model.User;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 系统管理员表 服务类
 * </p>
 *
 * @author li.
 * @since 2021-10-02
 */
public interface IUserService extends IService<User> {
    /**
     * 会员权限获取方法
     * @param userId 会员ID
     * @return 返回会员的全部权限
     */
    List<Map> findPermissionList(Integer userId);
}
