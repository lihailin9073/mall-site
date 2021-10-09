package com.wzliulan.mall.consumer;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

@Slf4j
@SpringBootTest
class MallConsumerApplicationTests {

    @Test
    void contextLoads() {
        // TODO
        log.info("这是一行单元测试代码：{}", UUID.randomUUID().toString());
    }

}
