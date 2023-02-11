package com.ermao.ls.sja.filter;


import com.ermao.ls.sja.POJO.VO.RespVO;
import com.ermao.ls.sja.util.JWTUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * @author Ermao
 * Date: 2023/2/11 23:29
 */
@Component
@Slf4j
public class AuthorizingFilter extends HttpFilter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest)request;
        HttpServletResponse httpResponse = (HttpServletResponse)response;
        String uri = httpRequest.getRequestURI();
        String method = httpRequest.getMethod();

        if (uri.equals("/users") && method.equals("POST")) {
            // 用户注册
            super.doFilter(request, response, chain);
        } else if (uri.equals("/tokens") && method.equals("POST")) {
            // 登录
            super.doFilter(request, response, chain);
        } else {
            // 其它需要认证的资源
            String token = httpRequest.getHeader("Token");
            try {
                log.info("接收到 jwt：{}", token);
                Jws<Claims> jws = JWTUtils.decode(token);
            } catch (Exception e) {
                httpResponse.setContentType("application/json");
                httpResponse.setCharacterEncoding(StandardCharsets.UTF_8.displayName());
                ServletOutputStream out = httpResponse.getOutputStream();
                RespVO res = RespVO.fail(e.getMessage());
                new ObjectMapper().writeValue(out, res);;
                out.flush();
                return;
            }
            super.doFilter(request, response, chain);
        }
    }
}
