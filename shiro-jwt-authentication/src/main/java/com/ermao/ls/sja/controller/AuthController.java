package com.ermao.ls.sja.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ermao.ls.sja.POJO.DO.User;
import com.ermao.ls.sja.POJO.VO.RespVO;
import com.ermao.ls.sja.POJO.query.UserLoginQuery;
import com.ermao.ls.sja.exception.ParameterFormatException;
import com.ermao.ls.sja.mapper.UserMapper;
import com.ermao.ls.sja.util.JWTUtils;
import org.apache.commons.codec.digest.Crypt;
import org.apache.commons.validator.GenericValidator;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.HashMap;
import java.util.Objects;
import java.util.UUID;

/**
 * @author Ermao
 * Date: 2023/2/11 18:42
 */
@Controller
public class AuthController {

    @Resource
    private UserMapper userMapper;

    @PostMapping("tokens")
    @ResponseBody
    public RespVO userLogin(@RequestBody UserLoginQuery userLoginQuery) {
        // validate user
        if (false) {
            throw new ParameterFormatException();
        }

        // 检索数据库获取盐
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("username", userLoginQuery.getUsername());
        User user = userMapper.selectOne(wrapper);

        if (!Objects.nonNull(user)) {
            return RespVO.fail();
        }
        String cryptPassword = Crypt.crypt(userLoginQuery.getPassword(), user.getSalt());
        if (!cryptPassword.equals(user.getPassword())) {
            return RespVO.fail();
        }
        HashMap<String, Object> map = new HashMap<>();
        map.put("username", user.getUsername());
        map.put("role", user.getRole());
        String jwt = JWTUtils.encode("me", 1000L, map);
        return RespVO.success(jwt);

        // 使用 Shiro 进行认证
//        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken();
//        System.out.println(userLoginQuery);
//        return "hello";
    }

    @PostMapping("users")
    @ResponseBody
    public RespVO userRegister(@RequestBody UserLoginQuery userLoginQuery) {
        // validate user
        if (false) {
            throw new ParameterFormatException();
        }

        // 检索数据库获取盐
        String salt = new String(Base64.getEncoder().encode(UUID.randomUUID().toString().getBytes(StandardCharsets.UTF_8)),
                StandardCharsets.UTF_8);
        String password = Crypt.crypt(userLoginQuery.getPassword(), salt);
        User user = new User();
        user.setUsername(userLoginQuery.getUsername());
        user.setPassword(password);
        user.setSalt(salt);
        user.setRole("user");
        user.setPermission("user:view");
        if (userMapper.insert(user) > 0) {
            return RespVO.success();
        }
        return RespVO.fail();
    }

    @GetMapping("login")
    public String userLoginTemplate() {
        // 使用 Shiro 进行认证
        return "login";
    }

    @GetMapping("register")
    public String userRegisterTemplate() {
        // 使用 Shiro 进行认证
        return "register";
    }
}
