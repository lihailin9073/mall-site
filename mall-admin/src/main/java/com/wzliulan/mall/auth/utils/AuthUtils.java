package com.wzliulan.mall.auth.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

/**
 * 安全管理工具类
 */
@Slf4j
public class AuthUtils {
    /**
     * 密码加密方法
     * @param username 用户账号
     * @param password 登录密码（明文）
     * @return 返回MD5加密后的用户密码（密文）
     */
    public static String encryptPassword(String username, String password) {
        // 参数-1指定加密算法：MD5，参数-2指定salt盐值：username + salt，参数-3指定加密的迭代次数
        String md5Password = new SimpleHash("MD5", password, ByteSource.Util.bytes("salt:" + username), 2).toHex();
        return md5Password;
    }
}
