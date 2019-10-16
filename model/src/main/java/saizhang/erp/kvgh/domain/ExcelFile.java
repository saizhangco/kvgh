package saizhang.erp.kvgh.domain;

import lombok.Data;

/**
 * @Author : saizhang
 * @Date : 2019/10/07
 * @Time : 18:09
 * @Description : TODO
 */
@Data
public class ExcelFile {
    private boolean generate;
    private String name;
    private String path;
}
