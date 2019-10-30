package com.daqiao.kvgh.entity;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;

/**
 * @Author : saizhang
 * @Date : 2019/09/08
 * @Time : 15:09
 * @Description : TODO
 */
@Entity
@Table(name = "t_vendor")
@Data
@ToString
public class Vendor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String code;
    private String name;
    private String phone;
    private String email;
    private String fax;
    private Boolean isNew;
}
