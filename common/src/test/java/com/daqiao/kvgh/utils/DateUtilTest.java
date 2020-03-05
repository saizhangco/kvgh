package com.daqiao.kvgh.utils;

import com.daqiao.kvgh.CommonTestApplication;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest(classes = CommonTestApplication.class)
@RunWith(SpringRunner.class)
public class DateUtilTest {

    @Test
    public void getDateString() {
        Assert.assertEquals(DateUtil.getDateString(2020, 1, 4), "1090104");
        Assert.assertEquals(DateUtil.getDateString(2020, 2, 7), "1090207");
        Assert.assertEquals(DateUtil.getDateString(2020, 3, 9), "1090309");
        Assert.assertEquals(DateUtil.getDateString(2020, 4, 11), "1090411");
        Assert.assertEquals(DateUtil.getDateString(2020, 5, 16), "1090516");
        Assert.assertEquals(DateUtil.getDateString(2020, 6, 20), "1090620");
        Assert.assertEquals(DateUtil.getDateString(2020, 7, 26), "1090726");
        Assert.assertEquals(DateUtil.getDateString(2020, 8, 28), "1090828");
        Assert.assertEquals(DateUtil.getDateString(2020, 9, 29), "1090929");
        Assert.assertEquals(DateUtil.getDateString(2020, 10, 30), "1091030");
        Assert.assertEquals(DateUtil.getDateString(2020, 11, 20), "1091120");
        Assert.assertEquals(DateUtil.getDateString(2020, 12, 31), "1091231");
    }
}
