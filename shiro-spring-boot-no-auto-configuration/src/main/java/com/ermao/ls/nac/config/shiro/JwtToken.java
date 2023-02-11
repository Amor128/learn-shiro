package com.ermao.ls.nac.config.shiro;

import org.apache.shiro.authc.AuthenticationToken;

/**
 * @author Ermao
 * Date: 2023/2/11 13:33
 */
public class JwtToken implements AuthenticationToken {

    private String jwt;

    public JwtToken(String jwt) {
        this.jwt = jwt;
    }

    @Override
    public Object getPrincipal() {
        return jwt;
    }

    @Override
    public Object getCredentials() {
        return jwt;
    }
}
