package com.daqiao.kvgh.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * @Author : saizhang
 * @Date : 2019/09/08
 * @Time : 10:27
 * @Description : TODO
 */
@Data
public class Stock {
    @JsonProperty(index = 0, value = "醫院統編")
    private String hospitalId;

    @JsonProperty(index = 1, value = "訂單編號")
    private String orderNo;

    @JsonProperty(index = 2, value = "訂單日期")
    private String orderDate;

    @JsonProperty(index = 3, value = "訂單類別")
    private Integer orderType;

    @JsonProperty(index = 4, value = "原始訂單編號")
    private String originOrderNo;

    @JsonProperty(index = 5, value = "採購案號")
    private String purchaseNo;

    @JsonProperty(index = 6, value = "請購單位")
    private String purchaseDepartment;

    @JsonProperty(index = 7, value = "請購人")
    private String purchaser;

    @JsonProperty(index = 8, value = "請購人電話")
    private String purchaserPhone;

    @JsonProperty(index = 9, value = "請購人傳真")
    private String purchaserFax;

    @JsonProperty(index = 10, value = "請購人電子郵件")
    private String purchaserEmail;

    @JsonProperty(index = 11, value = "下訂人")
    private String orderer;

    @JsonProperty(index = 12, value = "下訂人電話")
    private String ordererPhone;

    @JsonProperty(index = 13, value = "下訂人傳真")
    private String ordererFax;

    @JsonProperty(index = 14, value = "下訂人電子郵件")
    private String ordererEmail;

    @JsonProperty(index = 15, value = "送貨地點")
    private String deliveryAddress;

    @JsonProperty(index = 16, value = "注意事項")
    private String hint;

    @JsonProperty(index = 17, value = "訂單金額")
    private String orderAmount;

    @JsonProperty(index = 18, value = "國際條碼")
    private String internationalBarCode;

    @JsonProperty(index = 19, value = "單位")
    private String unit;

    @JsonProperty(index = 20, value = "單價")
    private String unitPrice;

    @JsonProperty(index = 21, value = "訂單數量")
    private String orderNumber;

    @JsonProperty(index = 22, value = "驗收日期")
    private String acceptanceDate;

    @JsonProperty(index = 23, value = "驗收人")
    private String accepter;

    @JsonProperty(index = 24, value = "驗收人電話")
    private String accepterPhone;

    @JsonProperty(index = 25, value = "驗收人傳真")
    private String accepterFax;

    @JsonProperty(index = 26, value = "驗收人電子郵件")
    private String accepterEmail;

    @JsonProperty(index = 27, value = "驗收次數")
    private String acceptanceTimes;

    @JsonProperty(index = 28, value = "驗收數量")
    private String acceptanceNumber;

    @JsonProperty(index = 29, value = "效期")
    private String periodOfValidity;

    @JsonProperty(index = 30, value = "批號")
    private String batchNumber;

    @JsonProperty(index = 31, value = "廠商代碼")
    private String vendorCode;

    @JsonProperty(index = 32, value = "發票號碼")
    private String invoiceNo;

    @JsonProperty(index = 33, value = "申請金額")
    private String applicationAmount;

    @JsonProperty(index = 34, value = "衛材折讓")
    private String materialDiscount;

    @JsonProperty(index = 35, value = "藥品折讓")
    private String medicinalDiscount;

    @JsonProperty(index = 36, value = "備註")
    private String description;
}
