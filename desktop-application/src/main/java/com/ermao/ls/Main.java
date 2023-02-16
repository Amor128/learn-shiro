package com.ermao.ls;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.text.IniRealm;
import org.apache.shiro.subject.Subject;

/**
 * @author Ermao
 * Date: ${DATE} ${TIME}
 */
@Slf4j
public class Main {

    public static void main(String[] args) {
        IniRealm iniRealm = new IniRealm("classpath:shiro.ini");
        SecurityManager securityManager = new DefaultSecurityManager(iniRealm);

        SecurityUtils.setSecurityManager(securityManager);
        Subject currentUser = SecurityUtils.getSubject();

        if (!currentUser.isAuthenticated()) {
            UsernamePasswordToken token
                    = new UsernamePasswordToken("user", "password");
            token.setRememberMe(true);
            try {
                currentUser.login(token);
            } catch (UnknownAccountException uae) {
                log.error("Username Not Found!", uae);
            } catch (IncorrectCredentialsException ice) {
                log.error("Invalid Credentials!", ice);
            } catch (LockedAccountException lae) {
                log.error("Your Account is Locked!", lae);
            } catch (AuthenticationException ae) {
                log.error("Unexpected Error!", ae);
            }
        }

        if (currentUser.hasRole("admin")) {
            log.info("Welcome Admin");
        } else if(currentUser.hasRole("editor")) {
            log.info("Welcome, Editor!");
        } else if(currentUser.hasRole("author")) {
            log.info("Welcome, Author");
        } else {
            log.info("Welcome, Guest");
        }

        if(currentUser.isPermitted("articles:compose")) {
            log.info("You can compose an article");
        } else {
            log.info("You are not permitted to compose an article!");
        }

        if(currentUser.isPermitted("articles:save")) {
            log.info("You can save articles");
        } else {
            log.info("You can not save articles");
        }

        if(currentUser.isPermitted("articles:publish")) {
            log.info("You can publish articles");
        } else {
            log.info("You can not publish articles");
        }
    }
}