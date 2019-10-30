package com.daqiao.kvgh.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * @Author : saizhang
 * @Date : 2019/09/08
 * @Time : 13:22
 * @Description : TODO
 */
@Entity
@Table(name = "t_medicine")
@Data
public class Medicine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    //健保码
    private String healthInsuranceCode;
    //院内代码
    private String hospitalNo;
    //新项次
    private String itemNumber;
    //品名&规格
    private String name;
    //规格
    private String specifications;
    //包装数量
    private Integer number;
    //单位
    private String unit;
    //新单价
    private Double unitPrice;
    //旧厂商
    private String oldVendor;
    //旧厂商电话
    private String oldVendorPhone;
    //新厂商
    private String newVendor;
    //新厂商电话
    private String newVendorPhone;
    //Email地址
    private String newVendorEmail;
    //传真号码
    private String newVendorFax;
    //厂商条码
    private String newVendorBarCode;
    //院方条码
    private String hospitalBarCode;
    //备注一
    private String comment1;
    //备注二
    private String comment2;
}
