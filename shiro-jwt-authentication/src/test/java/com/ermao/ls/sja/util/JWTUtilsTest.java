package com.ermao.ls.sja.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Ermao
 * Date: 2023/2/11 20:17
 */
class JWTUtilsTest {
    @Test
    void testJwt() {
        HashMap<String, Object> map = new HashMap<>();
        map.put("name", "jack");
        String jwt = JWTUtils.encode("me", -1, map);
        Jws<Claims> decode = JWTUtils.decode(jwt);
        assertEquals("jack", decode.getBody().get("name"));
    }
}