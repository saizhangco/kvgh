package com.daqiao.kvgh;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.daqiao.kvgh.service.ExcelService;

/**
 * @Author : saizhang
 * @Date : 2019/10/26
 * @Time : 19:59
 * @Description : TODO
 */
@SpringBootApplication
public class InitApplication implements ApplicationRunner {

    @Autowired
    private ExcelService excelService;

    /**
     * 初始化数据库，包括表结构，数据
     * @param args
     */
    public static void main(String[] args) {
        SpringApplication.run(InitApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        excelService.loadVendorFromExcel();
        excelService.loadMedicineFromExcel();
    }
}
