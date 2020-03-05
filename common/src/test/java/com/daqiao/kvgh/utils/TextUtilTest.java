package com.daqiao.kvgh.utils;

import com.daqiao.kvgh.CommonTestApplication;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest(classes = CommonTestApplication.class)
@RunWith(SpringRunner.class)
public class TextUtilTest {

    @Test
    public void getUUID() {
        Assert.assertEquals("495051525354", TextUtil.getUUID("123456"));
    }
}
