/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.vaadin.example.shiro;

import com.vaadin.flow.component.Unit;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.security.DenyAll;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

/**
 *
 * @author NCI Admin
 */
@Route("login")
//@AnonymousAllowed
@DenyAll
public class LoginView extends SecureView {

    String[][] aryInfo = {{"USERNAME", "PASSWORD", "ROLES"}, {"joe.coder", "frankzappa", "user"}, {"jill.coder", "moonunit", "admin"}};
    TextField txtUserName = new TextField("Username");
    TextArea txaInfo = new TextArea();
    PasswordField pwdPassword = new PasswordField("Password");
    Button btnLogin = new Button("Login");

//    @Autowired
//    @Qualifier("securityManager")
//    private SecurityManager securityManager;
    public LoginView(@Autowired ShiroSvc shiroSvc) {
        StringBuilder sbInfo = new StringBuilder();
        for (String[] eachRow : aryInfo) {
            for (String eachColumn : eachRow) {
                sbInfo.append(eachColumn);
                sbInfo.append('\t');
            }
            sbInfo.append('\n');
        }
        txaInfo.setWidth(400, Unit.PIXELS);
        txaInfo.setValue(sbInfo.toString());
        btnLogin.addClickListener(e -> {
            //Attempt to login the user.
            Subject currentUser = shiroSvc.getSubject();
            if (!currentUser.isAuthenticated()) {
                UsernamePasswordToken token = new UsernamePasswordToken(txtUserName.getValue(), pwdPassword.getValue());
                token.setRememberMe(true);
                try {
                    currentUser.login(token);
                    // Handle success                    
//                    // Redirect to home page for another secured page
//                    btnLogin.getUI().ifPresent(ui -> {
//                        ui.navigate("");
//                    });
                } catch (AuthenticationException a) {
                    // Handle authentication failure
                    Notification.show("Login failed!");
                }
            }

            btnLogin.getUI().ifPresent(ui -> {
                ui.navigate("");
            });
        });

        add(txaInfo, txtUserName, pwdPassword, btnLogin);

    }

}
