package saizhang.erp.kvgh.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * @Author : saizhang
 * @Date : 2019/10/06
 * @Time : 18:19
 * @Description : TODO
 */
@Entity
@Table(name = "t_excel")
@Data
public class Excel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String processId;
    private String vendor;
    private String vendorEmail;
    private String orderId;
    private Integer orderSize;
    private String date;
    private String excelName;
    /**
     * 0 未验收
     * 1 已验收
     * 2 部分验收
     */
    private Integer status;
}
