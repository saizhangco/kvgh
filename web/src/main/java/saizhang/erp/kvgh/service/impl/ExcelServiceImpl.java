package saizhang.erp.kvgh.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import saizhang.erp.kvgh.domain.MedicineEO;
import saizhang.erp.kvgh.domain.VendorEO;
import saizhang.erp.kvgh.repo.MedicineRepository;
import saizhang.erp.kvgh.repo.VendorRepository;
import saizhang.erp.kvgh.service.ExcelService;
import saizhang.erp.kvgh.utils.ExcelUtil;

import java.util.List;

/**
 * @Author : saizhang
 * @Date : 2019/10/06
 * @Time : 9:31
 * @Description : TODO
 */
@Service
public class ExcelServiceImpl implements ExcelService {

    @Autowired
    private VendorRepository vendorRepository;

    @Autowired
    private MedicineRepository medicineRepository;

    private static final String EXCEL_PATH_VENDOR = "C:/vhlc/init/Vendors 1081014.xlsx";
    private static final String EXCEL_PATH_MEDICINE1 = "C:/vhlc/init/Mediicines 1081014.xlsx";
    private static final String EXCEL_PATH_MEDICINE2 = "C:/vhlc/init/ShortListMedicine1081006.xls";

    @Override
    public void loadVendorFromExcel() {
        try {
            List<VendorEO> vendorEOList = ExcelUtil.readExcel(EXCEL_PATH_VENDOR, VendorEO.class);
            for (VendorEO vendorEO : vendorEOList) {
                vendorEO.setCode("" + new Double(Double.parseDouble(vendorEO.getCode())).intValue());
                if (vendorEO.getIsNew() == null) {
                    vendorEO.setIsNew(false);
                }
                vendorRepository.save(vendorEO.getVendor());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void loadMedicineFromExcel() {
        try {
            List<MedicineEO> medicineEOList = ExcelUtil.readExcel(EXCEL_PATH_MEDICINE1, MedicineEO.class);
            for (MedicineEO medicineEO : medicineEOList) {
                if (medicineRepository.findByName(medicineEO.getName()) == null
                        && medicineEO.getName() != null) {
                    medicineRepository.save(medicineEO.getMedicine());
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
}
