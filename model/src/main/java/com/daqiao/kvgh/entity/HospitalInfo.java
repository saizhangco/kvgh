package com.daqiao.kvgh.entity;

import lombok.Data;
import javax.persistence.*;

/**
 * @Author : saizhang
 * @Date : 2019/09/08
 * @Time : 15:20
 * @Description : TODO
 */
@Entity
@Table(name = "t_hospital_info")
@Data
public class HospitalInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String no;
    private String name;
}
