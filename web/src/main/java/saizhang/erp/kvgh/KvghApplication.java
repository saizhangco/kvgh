package saizhang.erp.kvgh;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import saizhang.erp.kvgh.service.ExcelService;

/**
 * @Author : saizhang
 * @Date : 2019/09/08
 * @Time : 13:08
 * @Description : TODO
 */
@SpringBootApplication
public class KvghApplication implements ApplicationRunner {

    @Autowired
    private ExcelService excelService;

    public static void main(String[] args) {
        SpringApplication.run(KvghApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
//        excelService.loadVendorFromExcel();
//        excelService.loadMedicineFromExcel();
    }
}
