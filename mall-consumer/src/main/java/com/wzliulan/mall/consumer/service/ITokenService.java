package com.wzliulan.mall.consumer.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wzliulan.mall.consumer.dao.model.Token;

/**
 * <p>
 * 访问令牌表 服务类
 * </p>
 *
 * @author li.
 * @since 2021-10-02
 */
public interface ITokenService extends IService<Token> {
    /**
     * 令牌查询方法
     * @param token 令牌值
     * @return 返回令牌对象Token
     */
    Token find(String token);

    /**
     * 令牌生成方法
     * @param token 令牌对象
     * @return 返回影响的记录数
     */
    Integer create(Token token);

    /**
     * 令牌删除方法
     * @param token 登录令牌
     * @return 返回影响的记录数
     */
    Integer remove(String token);

    /**
     * 刷新令牌
     * @param token 令牌
     * @return 刷新成功返回true，刷新失败返回false
     */
    boolean refresh(String token);

    /**
     * 是否有效
     * @param token 令牌
     * @return 有效返回true，无效返回false
     */
    boolean isValid(String token);
}
