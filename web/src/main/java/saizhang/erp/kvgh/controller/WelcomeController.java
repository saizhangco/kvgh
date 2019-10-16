package saizhang.erp.kvgh.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @Author : saizhang
 * @Date : 2019/09/08
 * @Time : 17:35
 * @Description : TODO
 */
@Controller
public class WelcomeController {

    @GetMapping("/index")
    public String index() {
        return "index";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

}
