package com.daqiao.kvgh.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class JsonTestObject {
    @JsonProperty(index = 0, value = "编号")
    private Long id;

    @JsonProperty(index = 1, value = "用户名")
    private String username;

    @JsonProperty(index = 2, value = "密码")
    private String password;

    @JsonProperty(index = 3, value = "年龄")
    private Integer age;

    @JsonProperty(index = 4, value = "身高")
    private Short tall;

    @JsonProperty(index = 5, value = "工资")
    private Double salary;
}
