package saizhang.erp.kvgh.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import saizhang.erp.kvgh.entity.Acceptance;

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
