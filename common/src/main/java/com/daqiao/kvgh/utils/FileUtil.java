package com.daqiao.kvgh.utils;

import java.io.File;

/**
 * @Author : saizhang
 * @Date : 2019/09/08
 * @Time : 10:16
 * @Description : TODO
 */
public class FileUtil {

    public static Boolean isFileExist(String path) {
        File file = new File(path);
        return file.exists();
    }
}
