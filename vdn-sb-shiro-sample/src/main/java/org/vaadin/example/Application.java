package org.vaadin.example;

import com.vaadin.flow.component.page.AppShellConfigurator;
import com.vaadin.flow.server.PWA;
import com.vaadin.flow.theme.Theme;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.realm.text.TextConfigurationRealm;
import org.apache.shiro.spring.web.config.DefaultShiroFilterChainDefinition;
import org.apache.shiro.spring.web.config.ShiroFilterChainDefinition;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.Map;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;

/**
 * The entry point of the Spring Boot application.
 *
 * Use the @PWA annotation make the application installable on phones, tablets
 * and some desktop browsers.
 *
 */
@SpringBootApplication
@PWA(name = "Project Base for Vaadin with Spring", shortName = "Project Base")
@Theme("my-theme")
public class Application implements AppShellConfigurator {

//    private static Logger log = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

//    @ExceptionHandler(AuthorizationException.class)
//    @ResponseStatus(HttpStatus.FORBIDDEN)
//    public String handleException(AuthorizationException e, Model model) {
//
//        // you could return a 404 here instead (this is how github handles 403, so the user does NOT know there is a
//        // resource at that location)
//        log.debug("AuthorizationException was thrown", e);
//
//        Map<String, Object> map = new HashMap<String, Object>();
//        map.put("status", HttpStatus.FORBIDDEN.value());
//        map.put("message", "No message available");
//        model.addAttribute("errors", map);
//
//        return "error";
//    }
//
//    @Bean
//    public Realm realm() {
//        TextConfigurationRealm realm = new TextConfigurationRealm();
//        realm.setUserDefinitions("joe.coder=password,user\n" + "jill.coder=password,admin");
//
//        realm.setRoleDefinitions("admin=read,write\n" + "user=read");
//        realm.setCachingEnabled(true);
//        return realm;
//    }
//
//    @Bean
//    public ShiroFilterChainDefinition shiroFilterChainDefinition() {
//        DefaultShiroFilterChainDefinition chainDefinition = new DefaultShiroFilterChainDefinition();
//        // need to accept POSTs from the login form
//        chainDefinition.addPathDefinition("/login", "authc");
//        chainDefinition.addPathDefinition("/logout", "logout");
//        return chainDefinition;
//    }
//
//    @Bean
//    public DefaultWebSecurityManager securityManager() {
//        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
//        securityManager.setRealm(realm());
//        return securityManager;
//    }
//
//    @ModelAttribute(name = "subject")
//    public Subject subject() {
//        return SecurityUtils.getSubject();
//    }
//    
}
