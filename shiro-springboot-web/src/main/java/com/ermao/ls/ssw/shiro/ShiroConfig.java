package com.ermao.ls.ssw.shiro;

import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.spring.web.config.DefaultShiroFilterChainDefinition;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Ermao
 * Date: 2023/2/8 23:25
 */
@Configuration
public class ShiroConfig {

    @Bean
    public Realm realm() {
        MyRealm myRealm = new MyRealm();
        HashedCredentialsMatcher matcher = new HashedCredentialsMatcher();
        matcher.setHashAlgorithmName("MD5");
        matcher.setHashIterations(3);
        myRealm.setCredentialsMatcher(matcher);
        myRealm.setCachingEnabled(true);
        return myRealm;
    }
}
