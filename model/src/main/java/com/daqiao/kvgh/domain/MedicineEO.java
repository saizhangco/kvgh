package com.daqiao.kvgh.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import com.daqiao.kvgh.entity.Medicine;

/**
 * @Author : saizhang
 * @Date : 2019/10/05
 * @Time : 17:41
 * @Description : TODO
 */
@Data
public class MedicineEO {

    @JsonProperty("健保碼")
    private String healthInsuranceCode;

    @JsonProperty("院內代碼")
    private String hospitalNo;

    @JsonProperty("新項次")
    private String itemNumber;

    @JsonProperty("品名")
    private String name;

    @JsonProperty("規格")
    private String specifications;

    @JsonProperty("包裝數量")
    private Integer number;

    @JsonProperty("單位")
    private String unit;

    @JsonProperty("新單價")
    private Double unitPrice;

    @JsonProperty("舊廠商")
    private String oldVendor;

    @JsonProperty("舊廠商電話")
    private String oldVendorPhone;

    @JsonProperty("新廠商")
    private String newVendor;

    @JsonProperty("新廠商電話")
    private String newVendorPhone;

    @JsonProperty("email地址")
    private String newVendorEmail;

    @JsonProperty("傳真號碼")
    private String newVendorFax;

    @JsonProperty("廠商條碼")
    private String newVendorBarCode;

    @JsonProperty("院方條碼")
    private String hospitalBarCode;

    @JsonProperty("備註一")
    private String comment1;

    @JsonProperty("備註二")
    private String comment2;

    public Medicine getMedicine() {
        Medicine medicine = new Medicine();
        medicine.setHealthInsuranceCode(healthInsuranceCode);
        medicine.setHospitalNo(hospitalNo);
        medicine.setItemNumber(itemNumber);
        medicine.setName(name);
        medicine.setSpecifications(specifications);
        medicine.setNumber(number);
        medicine.setUnit(unit);
        medicine.setUnitPrice(unitPrice);
        medicine.setOldVendor(oldVendor);
        medicine.setOldVendorPhone(oldVendorPhone);
        medicine.setNewVendor(newVendor);
        medicine.setNewVendorPhone(newVendorPhone);
        medicine.setNewVendorEmail(newVendorEmail);
        medicine.setNewVendorFax(newVendorFax);
        medicine.setNewVendorBarCode(newVendorBarCode);
        medicine.setHospitalBarCode(hospitalBarCode);
        medicine.setComment1(comment1);
        medicine.setComment2(comment2);
        return medicine;
    }
}
