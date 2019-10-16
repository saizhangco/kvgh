package saizhang.erp.kvgh.entity;

import lombok.Data;
import javax.persistence.*;
import java.util.Date;

/**
 * @Author : saizhang
 * @Date : 2019/09/08
 * @Time : 15:11
 * @Description : TODO
 */
@Entity
@Table(name = "t_acceptance")
@Data
public class Acceptance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String date;
    private String accepter;
    private Integer times;
    private String hospitalCode;
    private String name;
    private String unit;
    private String unitPrice;
    private String number;
    private String periodOfValidity;
    private String batchNumber;
    private String vendorCode;
    private String invoiceNo;
    private Float amount;
    private Float materialDiscount;
    private Float medicinalDiscount;
    private Integer giftQuantity;
    private String source;
    private String itemNumber;
    private String description;
    private Long orderId;
}
