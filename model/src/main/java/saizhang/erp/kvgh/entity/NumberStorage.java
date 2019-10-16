package saizhang.erp.kvgh.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * @Author : saizhang
 * @Date : 2019/10/14
 * @Time : 22:03
 * @Description : TODO
 */
@Entity
@Table(name = "t_number_storage")
@Data
public class NumberStorage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true)
    private String date;
    private int count;
}
