package com.ermao.ls.nac.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Ermao
 * Date: 2023/2/11 9:53
 */
@RestController
@RequestMapping("user")
public class HelloController {

    @GetMapping("hello")
    public String hello() {
        return "Hello, World";
    }
}
