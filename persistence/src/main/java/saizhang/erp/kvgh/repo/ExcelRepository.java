package saizhang.erp.kvgh.repo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import saizhang.erp.kvgh.entity.Excel;

import java.util.List;

/**
 * @Author : saizhang
 * @Date : 2019/10/06
 * @Time : 18:25
 * @Description : TODO
 */
@Repository
public interface ExcelRepository extends PagingAndSortingRepository<Excel, Long> {
    Long countByDate(String data);
    // Page<Excel> findByOrderIdLike(String orderId);
    Page<Excel> findByProcessId(String processId, Pageable pageable);
    Page<Excel> findByStatus(Integer status, Pageable pageable);
    Page<Excel> findByStatusAndVendorLike(Integer status, String vendorName, Pageable pageable);
    Excel findByOrderId(String orderId);
}
