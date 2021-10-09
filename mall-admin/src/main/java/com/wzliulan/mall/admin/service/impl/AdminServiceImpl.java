package com.wzliulan.mall.admin.service.impl;

import com.wzliulan.mall.admin.dao.mapper.AdminMapper;
import com.wzliulan.mall.admin.dao.model.Admin;
import com.wzliulan.mall.admin.service.IAdminService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 用户信息表 服务实现类
 * </p>
 *
 * @author li.
 * @since 2021-07-23
 */
@Service
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements IAdminService {
    @Autowired
    private AdminMapper adminMapper;

    @Override
    public Admin findByName(String username) {
        QueryWrapper<Admin> wrapper = new QueryWrapper<>();
        wrapper.eq("username", username);
        Admin admin = baseMapper.selectOne(wrapper);
        return admin;
    }

    @Override
    public List<String> getPermissionsById(String userId) {
        return adminMapper.getPermissionCodeList(userId);
    }
}
