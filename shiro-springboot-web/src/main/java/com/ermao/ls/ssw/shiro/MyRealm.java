package com.ermao.ls.ssw.shiro;

import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.apache.shiro.util.CollectionUtils;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author Ermao
 * Date: 2023/2/8 23:38
 */
public class MyRealm extends AuthorizingRealm {
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        Object username = principalCollection.getPrimaryPrincipal();
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        simpleAuthorizationInfo.setRoles(getRoles(username.toString()));
        return simpleAuthorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken usernamePasswordToken = ((UsernamePasswordToken)authenticationToken);

        String username = usernamePasswordToken.getUsername();
        Map<String, Object> userInfo = getUserInfo(username);
        if (CollectionUtils.isEmpty(userInfo)) {
            throw new UnknownAccountException();
        }

        ByteSource salt = ByteSource.Util.bytes(username);

        return new SimpleAuthenticationInfo(username, userInfo.get("password"), salt, getName());
    }

    private Map<String, Object> getUserInfo(String username) {
        HashMap<String, Object> map = new HashMap<>();
        if ("ermao".equals(username)) {
            map.put("username", "ermao");
            map.put("password", new SimpleHash("MD5", "123", username, 3));
        } else if ("sanmao".equals(username)) {
            map.put("username", "ermao");
            map.put("password", new SimpleHash("MD5", "321", username, 3));
        }
        return map;
    }

    private Set<String> getRoles(String username) {
        Set<String> roles = new HashSet<>();

        if ("ermao".equals(username)) {
            roles.add("admin");
        }
        roles.add("user");
        return roles;
    }
}
