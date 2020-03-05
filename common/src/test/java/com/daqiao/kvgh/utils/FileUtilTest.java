package com.daqiao.kvgh.utils;

import com.daqiao.kvgh.CommonTestApplication;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;

@SpringBootTest(classes = CommonTestApplication.class)
@RunWith(SpringRunner.class)
public class FileUtilTest {

    @Test
    public void isFileExist() {
        String projectPath = System.getProperty("user.dir");
        Assert.assertTrue(FileUtil.isFileExist(projectPath + "\\src\\test\\java\\com\\daqiao\\kvgh\\utils\\FileUtilTest.java"));
        Assert.assertFalse(FileUtil.isFileExist(projectPath + "\\src\\test\\java\\com\\daqiao\\kvgh\\utils\\" + UUID.randomUUID() +".java"));
    }
}
