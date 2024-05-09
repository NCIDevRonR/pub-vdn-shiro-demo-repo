/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.vaadin.example.shiro;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import javax.annotation.security.DenyAll;
import javax.annotation.security.RolesAllowed;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author NCI Admin
 */
@Route("acctInfo")
//@RolesAllowed("admin")
@DenyAll
public class AccountInfoView extends SecureView {
    H1 h1PageTitle = new H1();
    String pageTitle = "Account Info Page for: ";
    String username;
    Button btnHome = new Button("Home");
    Button btnLogout = new Button("Logout");
    
//    @Autowired
//    ShiroSvc shiroSvc;

    public AccountInfoView() {
//        this.username = username;

        btnHome.addClickListener(e -> {
            btnHome.getUI().ifPresent(ui -> {
                ui.navigate("");
            });
        });
        
        btnLogout.addClickListener(e -> {
            //Log the user out.
            Subject currentUser = shiroSvc.getSubject();
            currentUser.logout();
            btnLogout.getUI().ifPresent(ui -> {
                ui.navigate("");
            });
        });
        
        add(h1PageTitle, btnHome, btnLogout);
    }
    
    @Override
    public void otherBeforeEnter(BeforeEnterEvent event) {
        updateUserName();
        
    }

    private void updateUserName() {
        username = shiroSvc.getUserName();

        h1PageTitle.setText(pageTitle + this.username);

    }

}
