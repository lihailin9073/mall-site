package com.wzliulan.mall.consumer.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wzliulan.mall.consumer.dao.mapper.TokenMapper;
import com.wzliulan.mall.consumer.dao.model.Token;
import com.wzliulan.mall.consumer.service.ITokenService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * <p>
 * 系统管理员表 服务实现类
 * </p>
 *
 * @author li.
 * @since 2021-10-02
 */
@Slf4j
@Service
public class TokenServiceImpl extends ServiceImpl<TokenMapper, Token> implements ITokenService {

    @Override
    public Token find(String token) {
        QueryWrapper<Token> wrapper = new QueryWrapper<>();
        wrapper.eq("token", token);
        wrapper.eq("type", 0);
        return this.getOne(wrapper);
    }

    @Override
    public Integer create(Token token) {
        return this.save(token) ? 1 : 0;
    }

    @Override
    public Integer remove(String token) {
        QueryWrapper<Token> wrapper = new QueryWrapper<>();
        wrapper.eq("token", token);
        return this.remove(wrapper) ? 1 : 0;
    }

    @Override
    public boolean refresh(String token) {
        // 查询令牌
        QueryWrapper<Token> wrapper = new QueryWrapper<>();
        wrapper.eq("token", token);
        Token tk = this.getOne(wrapper);
        if (tk == null) {
            return false;
        }

        // 判断有效性
        Long expired = tk.getExpired();
        long createTime = tk.getCreateTime().getTime();
        long systemTime = System.currentTimeMillis();
        log.info("系统时间-创建时间：{}-{}={}，有效期={}", systemTime, createTime, systemTime-createTime, expired);
        if (systemTime-createTime > expired) { // 超出有效期
            return false;
        }

        // 增加有效期
        tk.setCreateTime(new Date());
        return this.updateById(tk);
    }

    @Override
    public boolean isValid(String token) {
        QueryWrapper<Token> wrapper = new QueryWrapper<>();
        wrapper.eq("token", token);
        Token tk = this.getOne(wrapper);
        if (tk == null) {
            return false;
        }

        // 判断有效性
        Long expired = tk.getExpired();
        long createTime = tk.getCreateTime().getTime();
        long systemTime = System.currentTimeMillis();
        log.info("系统时间-创建时间：{}-{}={}，有效期={}", systemTime, createTime, systemTime-createTime, expired);
        if (systemTime-createTime > expired) { // 超出有效期
            return false;
        }

        return true;
    }
}
