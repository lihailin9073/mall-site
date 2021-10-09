package com.wzliulan.mall.utils;

import org.springframework.util.DigestUtils;

/**
 * 工具类
 */
public class Md5Util {

    /**
     * md5加密方法
     * @param s 明文字符串
     * @return 返回md5加密后的密文
     */
    public static String md5(String s) {
        return DigestUtils.md5DigestAsHex(s.getBytes());
    }

}
