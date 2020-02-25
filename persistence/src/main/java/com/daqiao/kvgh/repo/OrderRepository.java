package com.daqiao.kvgh.repo;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import com.daqiao.kvgh.entity.Order;

import java.util.List;

/**
 * @Author : saizhang
 * @Date : 2019/09/08
 * @Time : 17:14
 * @Description : TODO
 */
@Repository
public interface OrderRepository extends PagingAndSortingRepository<Order, Long> {
    /**
     *
     * @param status
     * @return
     */
    List<Order> findAllByStatus(Integer status);
    List<Order> findAllByNo(String no);
    List<Order> findAllByNoLike(String no);
    Integer countByNoLikeAndStatus(String no, Integer status);
    List<Order> findFirst15ByStatusAndHospitalCode(Integer status, String hospitalCode);
    List<Order> findFirst15ByStatusAndVendorBarCode(Integer status, String vendorBarCode);
}
