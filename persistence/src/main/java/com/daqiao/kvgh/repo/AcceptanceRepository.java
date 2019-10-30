package com.daqiao.kvgh.repo;

import com.daqiao.kvgh.entity.Acceptance;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 * @Author : saizhang
 * @Date : 2019/09/08
 * @Time : 17:09
 * @Description : TODO
 */
@Repository
public interface AcceptanceRepository extends PagingAndSortingRepository<Acceptance, Long> {
    Acceptance findByOrderId(Long id);
}
