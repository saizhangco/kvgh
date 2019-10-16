package saizhang.erp.kvgh.repo;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import saizhang.erp.kvgh.entity.Vendor;

/**
 * @Author : saizhang
 * @Date : 2019/09/08
 * @Time : 17:07
 * @Description : TODO
 */
@Repository
public interface VendorRepository extends PagingAndSortingRepository<Vendor, Long> {
    Vendor getVendorByName(String name);
}
