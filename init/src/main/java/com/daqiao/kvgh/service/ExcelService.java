package com.daqiao.kvgh.service;

import org.springframework.stereotype.Service;

/**
 * @Author : saizhang
 * @Date : 2019/10/06
 * @Time : 9:30
 * @Description : TODO
 */
public interface ExcelService {
    void loadVendorFromExcel();

    void loadMedicineFromExcel();
}
