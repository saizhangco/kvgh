package saizhang.erp.kvgh.repo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import saizhang.erp.kvgh.entity.Medicine;

import java.util.List;

/**
 * @Author : saizhang
 * @Date : 2019/09/08
 * @Time : 17:13
 * @Description : TODO
 */
@Repository
public interface MedicineRepository extends PagingAndSortingRepository<Medicine, Long> {
    Medicine findByName(String name);
    Page<Medicine> findByNameLike(String name, Pageable pageable);
}
