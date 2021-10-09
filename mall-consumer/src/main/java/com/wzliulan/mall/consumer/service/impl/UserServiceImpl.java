package com.wzliulan.mall.consumer.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wzliulan.mall.consumer.dao.mapper.UserMapper;
import com.wzliulan.mall.consumer.dao.model.User;
import com.wzliulan.mall.consumer.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 系统管理员表 服务实现类
 * </p>
 *
 * @author li.
 * @since 2021-10-02
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {
    @Resource
    private UserMapper userMapper;

    @Override
    public List<Map> findPermissionList(Integer userId) {
        List<Map> permissionActionList = userMapper.findPermissionList(userId);
        return permissionActionList;
    }
}
