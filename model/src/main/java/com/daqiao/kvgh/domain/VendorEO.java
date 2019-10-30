package com.daqiao.kvgh.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import com.daqiao.kvgh.entity.Vendor;

/**
 * @Author : saizhang
 * @Date : 2019/10/02
 * @Time : 10:59
 * @Description : TODO
 */
@Data
public class VendorEO {
    @JsonProperty(value = "廠商代碼")
    private String code;

    @JsonProperty(value = "廠商")
    private String name;

    @JsonProperty(value = "廠商電話")
    private String phone;

    @JsonProperty(value = "email地址")
    private String email;

    @JsonProperty(value = "傳真號碼")
    private String fax;

    @JsonProperty(value = "新舊廠商")
    private Boolean isNew;

    public Vendor getVendor() {
        Vendor vendor = new Vendor();
        vendor.setCode(code);
        vendor.setEmail(email);
        vendor.setFax(fax);
        vendor.setIsNew(isNew);
        vendor.setName(name);
        vendor.setPhone(phone);
        return vendor;
    }
}
