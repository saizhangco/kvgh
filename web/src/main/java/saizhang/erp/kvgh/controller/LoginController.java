package saizhang.erp.kvgh.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import saizhang.erp.kvgh.entity.User;
import saizhang.erp.kvgh.repo.UserRepository;

import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotNull;

/**
 * @Author : saizhang
 * @Date : 2019/09/28
 * @Time : 19:46
 * @Description : TODO
 */
@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/{username}/{password}")
    public Boolean login(@NotNull @PathVariable("username") String username,
                      @NotNull @PathVariable("password") String password) {
        User user = userRepository.findByUsername(username);
        if( user == null || !user.getPassword().equals(password) ) {
            return false;
        }
        return true;
    }
}
