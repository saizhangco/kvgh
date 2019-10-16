package saizhang.erp.kvgh.entity;

import lombok.Data;
import javax.persistence.*;
import java.util.Date;

/**
 * @Author : saizhang
 * @Date : 2019/09/08
 * @Time : 15:01
 * @Description : TODO
 */
@Entity
@Table(name = "t_order")
@Data
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String no;
    private String hospitalCode;
    private String name;
    private String unit;
    private Double unitPrice;
    private Integer number;
    private Double amount;
    private String date;
    private Integer type;
    private String originNo;
    private String purchaseNo;
    private String applicantDepartment;
    private String applicant;
    private String orderer;
    private String deliveryAddress;
    private String hint;
    /**
     * 0-未验收，1-已验收
      */
    private Integer status;
    private String vendor;
}
