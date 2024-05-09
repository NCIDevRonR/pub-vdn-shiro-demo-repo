/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.vaadin.example.shiro;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import javax.annotation.security.DenyAll;
import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author NCI Admin
 */
public abstract class SecureView extends VerticalLayout implements BeforeEnterObserver {
    @Autowired
    public ShiroSvc shiroSvc;
    
    @Override
    public void beforeEnter(BeforeEnterEvent event) {
        // Check access to the view
        if (!isAccessAllowed(event.getNavigationTarget())) {
            if (event.getUI().getSession().getAttribute("user") == null) {
                event.rerouteTo(HelloView.class);
            } else {
                event.rerouteTo(ErrorView.class);
            }
        }
        
        otherBeforeEnter(event);
        
    }


    protected boolean isAccessAllowed(Class<?> viewClass) {
        // Implement your Shiro access checks here
        // Example: check if the subject has roles/permissions
        Subject subject = shiroSvc.getSubject();
        if (viewClass.isAnnotationPresent(RolesAllowed.class)) {
            RolesAllowed rolesAnnotation = viewClass.getAnnotation(RolesAllowed.class);
            for (String role : rolesAnnotation.value()) {
                if (subject.hasRole(role)) {
                    return true;
                }
            }
            return false;
        }
        // Handle other annotations similarly
        else if (viewClass.isAnnotationPresent(PermitAll.class)) {
            return subject.isAuthenticated();
        }
        else if (viewClass.isAnnotationPresent(DenyAll.class)) {
            return false;
        }
        else if (viewClass.isAnnotationPresent(AnonymousAllowed.class)) {
            return true;
        }
        return false;
    }

    protected void otherBeforeEnter(BeforeEnterEvent event) {
        //Main BeforeEnter calls this method to enable subclasses to run their own BeforeEnter processes.
        //By default this method does nothing.
    }
    
}
