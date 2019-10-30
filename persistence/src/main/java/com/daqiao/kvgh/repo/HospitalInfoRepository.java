package com.daqiao.kvgh.repo;

import com.daqiao.kvgh.entity.HospitalInfo;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 * @Author : saizhang
 * @Date : 2019/09/08
 * @Time : 17:12
 * @Description : TODO
 */
@Repository
public interface HospitalInfoRepository extends PagingAndSortingRepository<HospitalInfo, Long> {
}
