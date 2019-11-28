package com.daqiao.kvgh.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * @Author : saizhang
 * @Date : 2019/11/28
 * @Time : 21:48
 * @Description : TODO
 */
@Data
public class Stock1 {
    /**
     * 庫別代碼
     */
    @JsonProperty(index = 0, value = "庫別代碼")
    private String code;
    /**
     * 供應商
     */
    @JsonProperty(index = 1, value = "供應商")
    private String vendor;
    /**
     * 發票號碼
     */
    @JsonProperty(index = 2, value = "發票號碼")
    private String invoiceNumber;
    /**
     * 入帳日
     */
    @JsonProperty(index = 3, value = "入帳日")
    private String accountingDay;
    /**
     * 品項代碼
     */
    @JsonProperty(index = 4, value = "品項代碼")
    private String itemCode;
    /**
     * 進貨量
     */
    @JsonProperty(index = 5, value = "進貨量")
    private String purchaseQuantity;
    /**
     * 實收金額
     */
    @JsonProperty(index = 6, value = "實收金額")
    private String receivedAmount;
    /**
     * 折讓金額
     */
    @JsonProperty(index = 7, value = "折讓金額")
    private String allowanceAmount;
    /**
     * 發票金額
     */
    @JsonProperty(index = 8, value = "發票金額")
    private String invoiceAmount;
    /**
     * 贈予
     */
    @JsonProperty(index = 9, value = "贈予")
    private String gift;
    /**
     * 批號
     */
    @JsonProperty(index = 10, value = "批號")
    private String batchNumber;
    /**
     * 有效日期
     */
    @JsonProperty(index = 11, value = "有效日期")
    private String effectiveDate;
    /**
     * 備註
     */
    @JsonProperty(index = 12, value = "備註")
    private String remarks;
    /**
     * 來源
     */
    @JsonProperty(index = 13, value = "來源")
    private String source;
    /**
     * 標號
     */
    @JsonProperty(index = 14, value = "標號")
    private String grade;
    /**
     * 廠商統編
     */
    @JsonProperty(index = 15, value = "廠商統編")
    private String vendorCode;
}
