package com.wzliulan.mall.consumer.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wzliulan.mall.consumer.dao.mapper.SmsMapper;
import com.wzliulan.mall.consumer.dao.model.Sms;
import com.wzliulan.mall.consumer.service.ISmsService;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * <p>
 * 短信表 服务实现类
 * </p>
 *
 * @author li.
 * @since 2021-10-03
 */
@Service
public class SmsServiceImpl extends ServiceImpl<SmsMapper, Sms> implements ISmsService {
    @Override
    public boolean send(String phone, String content, int type) {
        Sms sms = new Sms();
        sms.setPhone(phone);
        sms.setContent(content);
        sms.setType(type);
        sms.setCreateTime(new Date());
        return this.save(sms);
    }

    /**
     * 6位随机验证码生成方法
     * @return 返回6位随机验证码
     */
    public static String generateCode() {
        // 通过
        int code = (int)(Math.random() * 900000 + 100000);
        return String.valueOf(code);
    }

    /**
     * N位随机验证码生成方法
     * @param length 验证码的位数
     * @return 返回N位随机验证码
     */
    public static String generateCode(int length) {
        if (length<3 || length>6) { // 非法长度，设置为默认4位
            length = 4;
        }

        // 取系统的nanoTime()时间后length位，作为随机数
        Long codeL = System.nanoTime();
        String codeStr = Long.toString(codeL);
        String verifyCode = codeStr.substring(codeStr.length() - length);

        return verifyCode;
    }
}
