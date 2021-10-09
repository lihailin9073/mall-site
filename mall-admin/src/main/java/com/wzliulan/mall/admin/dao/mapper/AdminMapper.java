package com.wzliulan.mall.admin.dao.mapper;

import com.wzliulan.mall.admin.dao.model.Admin;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 用户信息表 Mapper 接口
 * </p>
 *
 * @author li.
 * @since 2021-07-23
 */
public interface AdminMapper extends BaseMapper<Admin> {
    /**
     * 系统用户权限查询方法
     * @param userId 用户ID
     * @return 返回用户所拥有的全部权限码列表
     */
    List<String> getPermissionCodeList(@Param("user_id") String userId);
}
