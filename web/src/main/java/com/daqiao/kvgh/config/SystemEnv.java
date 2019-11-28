package com.daqiao.kvgh.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @Author : saizhang
 * @Date : 2019/10/07
 * @Time : 18:31
 * @Description : TODO
 */
@Component
@Data
public class SystemEnv {

    @Value("${excel.path}")
    private String excelPath;

    @Value("${spring.jpa.hibernate.ddl-auto}")
    private String ddlAuto;

    @Value("${kvgh.branch.taxid}")
    private String taxId;
}
