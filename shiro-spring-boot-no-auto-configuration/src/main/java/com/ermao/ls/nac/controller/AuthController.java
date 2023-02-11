package com.ermao.ls.nac.controller;

import com.ermao.ls.nac.util.JwtUtils;
import io.jsonwebtoken.impl.DefaultClaims;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author Ermao
 * Date: 2023/2/11 14:20
 */
@Controller
public class AuthController {
    @GetMapping("login")
    public String loginTemplate() {
        return "login";
    }

    @PostMapping("login")
    public void login(@RequestParam("name") String name,
                      @RequestParam("password") String password) {


    }
}
