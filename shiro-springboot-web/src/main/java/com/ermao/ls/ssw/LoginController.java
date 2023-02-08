package com.ermao.ls.ssw;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Ermao
 * Date: 2023/2/8 22:41
 */
@Controller
public class LoginController {
    @RequestMapping("/login.html")
    public String loginTemplate() {

        return "login";
    }
}
