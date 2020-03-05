package com.daqiao.kvgh.utils;

import com.daqiao.kvgh.CommonTestApplication;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest(classes = CommonTestApplication.class)
@RunWith(SpringRunner.class)
public class CountUtilTest {

    @Test
    public void getCount() {
        Assert.assertEquals(CountUtil.getCount(1L), "001");
        Assert.assertEquals(CountUtil.getCount(9L), "009");
        Assert.assertEquals(CountUtil.getCount(10L), "010");
        Assert.assertEquals(CountUtil.getCount(23L), "023");
        Assert.assertEquals(CountUtil.getCount(100L), "100");
        Assert.assertEquals(CountUtil.getCount(456L), "456");
        Assert.assertEquals(CountUtil.getCount(1100L), "100");
        Assert.assertEquals(CountUtil.getCount(9999L), "999");
        Assert.assertEquals(CountUtil.getCount(99999L), "999");
    }

}
