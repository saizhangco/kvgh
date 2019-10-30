package com.daqiao.kvgh.repo;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import com.daqiao.kvgh.entity.User;

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
