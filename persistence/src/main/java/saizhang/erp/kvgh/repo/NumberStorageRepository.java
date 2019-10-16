package saizhang.erp.kvgh.repo;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import saizhang.erp.kvgh.entity.NumberStorage;

/**
 * @Author : saizhang
 * @Date : 2019/10/14
 * @Time : 22:07
 * @Description : TODO
 */
@Repository
public interface NumberStorageRepository extends PagingAndSortingRepository<NumberStorage, Long> {
    NumberStorage findByDate(String data);
}
