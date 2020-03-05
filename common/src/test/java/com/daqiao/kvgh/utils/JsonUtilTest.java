package com.daqiao.kvgh.utils;

import com.daqiao.kvgh.CommonTestApplication;
import com.daqiao.kvgh.model.JsonTestObject;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@SpringBootTest(classes = CommonTestApplication.class)
@RunWith(SpringRunner.class)
public class JsonUtilTest {

    @Test
    public void getColumnNameList() {
        List<String> columnNameList = JsonUtil.getColumnNameList(JsonTestObject.class);
        Assert.assertEquals(6, columnNameList.size());
        Assert.assertEquals("编号", columnNameList.get(0));
        Assert.assertEquals("用户名", columnNameList.get(1));
        Assert.assertEquals("密码", columnNameList.get(2));
        Assert.assertEquals("年龄", columnNameList.get(3));
        Assert.assertEquals("身高", columnNameList.get(4));
        Assert.assertEquals("工资", columnNameList.get(5));
    }
}
