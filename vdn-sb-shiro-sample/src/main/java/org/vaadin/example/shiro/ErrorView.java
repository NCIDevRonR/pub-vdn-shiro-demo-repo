/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.vaadin.example.shiro;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.Route;
import java.util.Map;
import javax.annotation.security.DenyAll;
import javax.annotation.security.PermitAll;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author NCI Admin
 */
@Route("error")
//@PermitAll
@DenyAll
public class ErrorView extends SecureView {

    H1 h1Errors = new H1();
    TextField txtErrors = new TextField();

    Button btnGoHome = new Button("Go Home");
    Map<String, Object> errors;
    
    
//    @Autowired
//    ShiroSvc shiroSvc;

    public ErrorView() {
        btnGoHome.addClickListener(e -> {
            btnGoHome.getUI().ifPresent(ui -> {
                ui.navigate("");
            });
        });
        
        add(h1Errors, txtErrors, btnGoHome);
    }
    
    @Override
    public void otherBeforeEnter(BeforeEnterEvent event) {
        //Update any error messages from Shiro.
        errors = shiroSvc.error();

        //Display any errors generated from Shiro.
        h1Errors.setText((String)errors.get("status"));
        txtErrors.setValue((String)errors.get("message"));
    }

    
}
