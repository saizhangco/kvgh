package com.daqiao.kvgh.utils;

import com.daqiao.kvgh.domain.MedicineEO;
import com.daqiao.kvgh.domain.Stock;
import com.daqiao.kvgh.domain.VendorEO;
import com.daqiao.kvgh.repo.VendorRepository;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author : saizhang
 * @Date : 2019/09/08
 * @Time : 11:04
 * @Description : TODO
 */
@SpringBootTest
@RunWith(SpringRunner.class)
@Ignore
public class ExcelUtilTest {

    @Test
    public void createExcel() {
        Stock stock = new Stock();
        stock.setHospitalId("92505506");
        stock.setOrderNo("1080806041");
        stock.setOrderDate("1080829");
        stock.setOrderType(1);
        stock.setOriginOrderNo("108080604");
        stock.setPurchaseNo("108080604");
        stock.setPurchaseDepartment("0_藥庫");
        stock.setPurchaser("何夢櫻");
        stock.setPurchaserPhone("08-7700026");
        stock.setPurchaserFax("08-7700689");
        stock.setPurchaserEmail("meddep@mail.vhlc.gov.tw");
        stock.setOrderer("何夢櫻");
        stock.setOrdererPhone("08-7700026");
        stock.setOrdererFax("08-7700689");
        stock.setOrdererEmail("meddep@mail.vhlc.gov.tw");
        stock.setDeliveryAddress("藥庫");
        stock.setHint("");
        stock.setOrderAmount("915");
        stock.setInternationalBarCode("ECRAV");
        stock.setUnit("BOT");
        stock.setUnitPrice("91.5");
        stock.setOrderNumber("10");
        stock.setAcceptanceDate("1080903");
        stock.setAccepter("何夢櫻");
        stock.setAccepterPhone("08-7700026");
        stock.setAccepterFax("08-7700689");
        stock.setAccepterEmail("meddep@mail.vhlc.gov.tw");
        stock.setAcceptanceTimes("1");
        stock.setAcceptanceNumber("10");
        stock.setPeriodOfValidity("1110101");
        stock.setBatchNumber("R12574C");
        stock.setVendorCode("48");
        stock.setInvoiceNo("UY09522240");
        stock.setApplicationAmount("915");
        stock.setMaterialDiscount("");
        stock.setMedicinalDiscount("12");
        stock.setDescription("");
        List<Stock> stockList = new ArrayList<>();
        stockList.add(stock);
        try {
            ExcelUtil.createExcel("D:/writeExcel5.xls", stockList, Stock.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Autowired
    private VendorRepository vendorRepository;

    @Test
    public void loadVendorFromExcel() {
        try {
            List<VendorEO> vendorEOList = ExcelUtil.readExcel("D:/Project/屏东/seedData/vendor.xlsx", VendorEO.class);
            for (VendorEO vendorEO : vendorEOList) {
                vendorRepository.save(vendorEO.getVendor());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void loadMedicineFromExcel() {
        try {
            List<MedicineEO> medicineEOList = ExcelUtil.readExcel("D:/Project/屏东/seedData/藥品基本清冊1081005 test(1).xls", MedicineEO.class);
            for (MedicineEO medicineEO : medicineEOList) {
                System.out.println(medicineEO);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
