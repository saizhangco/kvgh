package com.daqiao.kvgh.service.impl;

import com.daqiao.kvgh.domain.VendorEO;
import com.daqiao.kvgh.entity.Medicine;
import com.daqiao.kvgh.repo.VendorRepository;
import com.daqiao.kvgh.service.ExcelService;
import com.daqiao.kvgh.utils.ExcelUtil;
import com.daqiao.kvgh.utils.TextUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.daqiao.kvgh.domain.MedicineEO;
import com.daqiao.kvgh.repo.MedicineRepository;

import java.util.List;

/**
 * @Author : saizhang
 * @Date : 2019/10/06
 * @Time : 9:31
 * @Description : TODO
 */
@Service
public class ExcelServiceImpl implements ExcelService {

    private static final Logger logger = LoggerFactory.getLogger(ExcelServiceImpl.class);

    @Autowired
    private VendorRepository vendorRepository;

    @Autowired
    private MedicineRepository medicineRepository;

    private static final String EXCEL_PATH_VENDOR = "C:/vhlc/init/Vendors 1081014.xlsx";
    private static final String EXCEL_PATH_MEDICINE1 = "C:/vhlc/init/Mediicines 1081014.xlsx";
    private static final String EXCEL_PATH_MEDICINE2 = "C:/vhlc/init/ShortListMedicine1081006.xls";

    @Override
    public void loadVendorFromExcel() {
        int count = 1;
        try {
            List<VendorEO> vendorEOList = ExcelUtil.readExcel(EXCEL_PATH_VENDOR, VendorEO.class);
            for (VendorEO vendorEO : vendorEOList) {
                vendorEO.setName(vendorEO.getName().trim());
                vendorEO.setCode("" + new Double(Double.parseDouble(vendorEO.getCode())).intValue());
                if (vendorEO.getIsNew() == null) {
                    vendorEO.setIsNew(false);
                }
                vendorRepository.save(vendorEO.getVendor());
                System.out.println("Insert vendor : " + count++ + " [" + vendorEO.getName() + "]");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void loadMedicineFromExcel() {
        int count = 1;
        try {
            List<MedicineEO> medicineEOList = ExcelUtil.readExcel(EXCEL_PATH_MEDICINE1, MedicineEO.class);
            for (MedicineEO medicineEO : medicineEOList) {
                if (medicineRepository.findByName(medicineEO.getName()) == null
                        && medicineEO.getName() != null) {
                    medicineRepository.save(medicineEO.getMedicine());
                    System.out.println("Insert medicine : " + count++ + " [" + medicineEO.getName() + "]");
                }
            }
//            medicineEOList = ExcelUtil.readExcel(EXCEL_PATH_MEDICINE1, MedicineEO.class);
//            for (MedicineEO medicineEO : medicineEOList) {
//                if(medicineRepository.findByName(medicineEO.getName()) == null && medicineEO.getName() != null ) {
//                    medicineRepository.save(medicineEO.getMedicine());
//                }
//            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateVendorBarCode() {
        int page = 0;
        int totalPages = 1;
        do {
            logger.info("page={}, totalPages={}", page, totalPages);
            Page<Medicine> medicinePage = medicineRepository.findAll(PageRequest.of(page, 10));
            for (Medicine medicine : medicinePage.getContent()) {
                String hospitalBarCode = medicine.getHospitalBarCode();
                medicine.setNewVendorBarCode(TextUtil.getUUID(medicine.getHospitalBarCode()));
                medicineRepository.save(medicine);
            }
            totalPages = medicinePage.getTotalPages();
            page++;
        } while (page < totalPages);
    }
}
