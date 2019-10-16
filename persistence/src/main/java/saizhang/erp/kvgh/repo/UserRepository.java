package saizhang.erp.kvgh.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import saizhang.erp.kvgh.entity.User;

/**
 * @Author : saizhang
 * @Date : 2019/09/28
 * @Time : 10:29
 * @Description : TODO
 */
@Repository
public interface UserRepository extends PagingAndSortingRepository<User, Long> {

    User findByUsername(String username);
}
