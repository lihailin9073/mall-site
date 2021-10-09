package com.wzliulan.mall.consumer.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wzliulan.mall.consumer.dao.model.Sms;

/**
 * <p>
 * 短信表 服务类
 * </p>
 *
 * @author li.
 * @since 2021-10-03
 */
public interface ISmsService extends IService<Sms> {
    /**
     * 短信发送方法
     * @param phone 手机号码
     * @param content 短信内容
     * @param type 短信类型：0=登录验证码，1=注册验证码，2=支付验证码
     * @return 发送成功返回true，发送失败返回false
     */
    boolean send(String phone, String content, int type);
}
