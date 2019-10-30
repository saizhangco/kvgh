package com.daqiao.kvgh.entity;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;

/**
 * @Author : saizhang
 * @Date : 2019/09/28
 * @Time : 10:25
 * @Description : TODO
 */
@Entity
@Data
@Table(name = "t_user")
@ToString
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String department;
    private String username;
    private String password;
    private String phone;
    private String fax;
    private String email;
}
