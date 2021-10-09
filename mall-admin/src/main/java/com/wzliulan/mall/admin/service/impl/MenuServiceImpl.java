package com.wzliulan.mall.admin.service.impl;

import com.wzliulan.mall.admin.dao.mapper.MenuMapper;
import com.wzliulan.mall.admin.dao.model.Menu;
import com.wzliulan.mall.domain.ApiResponse;
import com.wzliulan.mall.domain.enums.ApiResponseEnum;
import com.wzliulan.mall.admin.service.IMenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 菜单信息表 服务实现类
 * </p>
 *
 * @author li.
 * @since 2021-07-24
 */
@Slf4j
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements IMenuService {
    @Override
    public ApiResponse findUserMenuTree(String userId) {
        // 1、根据用户ID查询所有的权限菜单（目录、菜单、按钮）
        List<Menu> menuList = baseMapper.findByUserId(userId);
        /**
         * 查询结果集有两种情况：
         * （1）userId对应的用户不存在，menuList为空；
         * （2）userId对应的用户存在但是没有分配权限，则只有一条空记录；因为是多表查询，只要用户存在那么b_user表就会有记录，哪怕查找结果集中只取了b_menu的字段
         */
        if (CollectionUtils.isEmpty(menuList) || menuList.get(0)==null) {
            // 用户不存在，或者用户未被分配权限
            return ApiResponse.build(ApiResponseEnum.MENU_NO);
        }

        // 2、整理结果集：目录和菜单存储在一个List中，按钮存储在另一个List中
        List<Menu> dirMenuList = new ArrayList<>(); // 存放目录和菜单
        List<String> buttonList = new ArrayList<>(); // 存放按钮
        for (Menu menu: menuList) { // 归档目录、菜单
            log.info("菜单类型={}", menu.getType());
            if (menu.getType().equals(1) || menu.getType().equals(2)) {
                log.info("---菜单类型={}", menu.getType());
                dirMenuList.add(menu);
            } else { // 归档按钮
                buttonList.add(menu.getCode());
            }
        }

        // 3、封装成树状结构
        List<Menu> menuTreeList = this.buildTree(dirMenuList);

        // 返回数据
        Map<String, Object> data = new HashMap<>();
        data.put("menuTreeList", menuTreeList);
        data.put("buttonList", buttonList);

        return ApiResponse.ok(data);
    }

    /**
     * 树形菜单构建方法
     * @param menuList 所有菜单列表
     * @return 返回构建成树形结构的菜单列表
     */
    private List<Menu> buildTree(List<Menu> menuList) {
        // 1、获取所有的根菜单
        List<Menu> rootMenuList = new ArrayList<>();
        for (Menu menu : menuList) {
            if (menu.getParentId().equals("0")) { // 寻找根菜单
                rootMenuList.add(menu);
            }
        }

        // 2、获取每个根菜单所包含的子菜单
        for (Menu rootMenu : rootMenuList) {
            // 遍历每一个根菜单，并找到它包含的所有（多级）子菜单
            this.findChildrenMenu(rootMenu, menuList);
        }
        return rootMenuList;
    }

    /**
     * 多级子菜单递归构建方法
     * @param parentMenu 父菜单对象
     * @param menuList 所有菜单列表
     * @return 返回
     */
    private Menu findChildrenMenu(Menu parentMenu, List<Menu> menuList) {
        // 查找子菜单
        List<Menu> childrenMenus = new ArrayList<>();
        for (Menu menu : menuList) {
            if (menu.getParentId().equals(parentMenu.getId())) {
                Menu subMenu = this.findChildrenMenu(menu, menuList);// 递归寻找下一级的子菜单
                childrenMenus.add(subMenu); // 将递归找到的子菜单封装到集合
            }
        }

        // 将找到的子菜单设置到根菜单中
        parentMenu.setChildren(childrenMenus);

        // 返回
        return parentMenu;
    }
}
