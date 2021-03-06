package com.daqiao.kvgh.repo;

import com.daqiao.kvgh.entity.NumberStorage;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

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
