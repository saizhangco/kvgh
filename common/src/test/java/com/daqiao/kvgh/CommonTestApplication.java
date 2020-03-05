package com.daqiao.kvgh;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;

@SpringBootApplication
@ComponentScan(value = {
        "com.daqiao.kvgh.constant",
        "com.daqiao.kvgh.utils"
})
public class CommonTestApplication {
    public static void main(String[] args) {
        SpringApplication.run(CommonTestApplication.class, args);
    }
}
