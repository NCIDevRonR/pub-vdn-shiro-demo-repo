/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.vaadin.example.shiro;

import com.vaadin.flow.server.VaadinRequest;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.CollectionUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.ServletWebRequest;

@Service
public class ShiroSvc {
//    Model model;
    private static final String ERROR_PATH = "/error";

    @SuppressWarnings("Duplicates")
    public String getUserName() {

        String name = "World";

        Subject subject = SecurityUtils.getSubject();

        PrincipalCollection principalCollection = subject.getPrincipals();

        if (principalCollection != null && !principalCollection.isEmpty()) {
            Collection<Map> principalMaps = subject.getPrincipals().byType(Map.class);
            if (CollectionUtils.isEmpty(principalMaps)) {
                name = subject.getPrincipal().toString();
            } else {
                name = (String) principalMaps.iterator().next().get("username");
            }
        }

//        model.addAttribute("name", name);

        return name;
    }

    public Subject getSubject() {

        Subject subject = SecurityUtils.getSubject();

        return subject;
    }
    
    public String getErrorPath() {
        return ERROR_PATH;
    }

    @Autowired
    private ErrorAttributes errorAttributes;

//    @RequestMapping(ERROR_PATH)
    Map<String, Object> error() {
        VaadinRequest request = VaadinRequest.getCurrent();
        Map<String, Object> errorMap = errorAttributes.getErrorAttributes(
                (ServletWebRequest) request,
                ErrorAttributeOptions.of(ErrorAttributeOptions.Include.MESSAGE));
//        model.addAttribute("errors", errorMap);
        return errorMap;
    }
    
    
    
    
    
}
