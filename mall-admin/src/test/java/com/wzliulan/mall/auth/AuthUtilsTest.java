package com.wzliulan.mall.auth;

import com.wzliulan.mall.auth.utils.AuthUtils;
//import org.junit.Test;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class AuthUtilsTest {

    @Test
    public void createPasswordTest() {
        String password = AuthUtils.encryptPassword("kefu01", "123456");
        System.out.println("---创建的密码（密文）：" + password);
    }
}
