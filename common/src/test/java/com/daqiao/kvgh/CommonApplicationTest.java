package com.daqiao.kvgh;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest(classes = CommonTestApplication.class)
@RunWith(SpringRunner.class)
public class CommonApplicationTest {

    private static final Logger logger = LoggerFactory.getLogger(CommonApplicationTest.class);

    @Value("${common}")
    private String common;

    @Test
    public void test1() {
        logger.info(common);
    }
}
