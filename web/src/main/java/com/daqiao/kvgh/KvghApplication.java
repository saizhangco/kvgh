package com.daqiao.kvgh;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @Author : saizhang
 * @Date : 2019/09/08
 * @Time : 13:08
 * @Description : TODO
 */
@SpringBootApplication
@ComponentScan(basePackages = {"com.daqiao.license", "com.daqiao.kvgh"})
public class KvghApplication {

    public static void main(String[] args) {
        SpringApplication.run(KvghApplication.class, args);
    }
}
