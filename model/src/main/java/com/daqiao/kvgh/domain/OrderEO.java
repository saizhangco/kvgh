package com.daqiao.kvgh.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import com.daqiao.kvgh.entity.Order;

/**
 * @Author : saizhang
 * @Date : 2019/10/07
 * @Time : 18:13
 * @Description : TODO
 */
@Data
public class OrderEO {
    @JsonProperty(index = 0, value = "訂單編號")
    private String no;

    @JsonProperty(index = 1, value = "院內代碼")
    private String hospitalCode;

    @JsonProperty(index = 2, value = "品名")
    private String name;

    @JsonProperty(index = 3, value = "單位")
    private String unit;

    @JsonProperty(index = 4, value = "單價")
    private String unitPrice;

    @JsonProperty(index = 5, value = "訂單數量")
    private String number;

    @JsonProperty(index = 6, value = "申請金額")
    private String amount;

    @JsonProperty(index = 7, value = "訂單日期")
    private String date;

    @JsonProperty(index = 8, value = "訂單類別")
    private String type;

    @JsonProperty(index = 9, value = "原始訂單編號")
    private String originNo;

    @JsonProperty(index = 10, value = "採購案號")
    private String purchaseNo;

    @JsonProperty(index = 11, value = "請購單位")
    private String applicantDepartment;

    @JsonProperty(index = 12, value = "請購人")
    private String applicant;

    @JsonProperty(index = 13, value = "下訂人")
    private String orderer;

    @JsonProperty(index = 14, value = "送貨地點")
    private String deliveryAddress;

    @JsonProperty(index = 15, value = "注意事項")
    private String hint;


    public OrderEO(Order order) {
        no = order.getNo();
        hospitalCode = order.getHospitalCode();
        name = order.getName();
        unit = order.getUnit();
        unitPrice = order.getUnitPrice().toString();
        number = order.getNumber().toString();
        amount = order.getAmount().toString();
        date = order.getDate();
        type = order.getType().toString();
        originNo = order.getOriginNo();
        purchaseNo = order.getPurchaseNo();
        applicantDepartment = order.getApplicantDepartment();
        applicant = order.getApplicant();
        orderer = order.getOrderer();
        deliveryAddress = order.getDeliveryAddress();
        hint = order.getHint();
    }
}
