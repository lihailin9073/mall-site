package com.wzliulan.mall.admin.dao.mapper;

import com.wzliulan.mall.admin.dao.model.Menu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 菜单信息表 Mapper 接口
 * </p>
 *
 * @author li.
 * @since 2021-07-24
 */
public interface MenuMapper extends BaseMapper<Menu> {
    /**
     * 用户菜单权限（目录、菜单、按钮）查询方法
     * @param userId 用户ID
     * @return 返回用户拥有的权限集合
     */
    List<Menu> findByUserId(@Param("userId") String userId);
}
