package com.wzliulan.mall.consumer.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wzliulan.mall.consumer.dao.model.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 系统管理员表 Mapper 接口
 * </p>
 *
 * @author li.
 * @since 2021-10-02
 */
public interface UserMapper extends BaseMapper<User> {
    /**
     * 会员权限查询方法
     * @param adminId 会员ID
     * @return 返回会员的权限清单
     */
    List<Map> findPermissionList(@Param("user_id") Integer adminId);
}
