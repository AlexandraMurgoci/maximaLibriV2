package com.maximaLibri.maximaLibriV2.controller;

import com.maximaLibri.maximaLibriV2.dto.StringTO;
import com.maximaLibri.maximaLibriV2.model.RoleName;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

@Component
public class AddRoleToModel {
    /** adauga rolul utilizitatorului ca atribut modelului trimis catre front-end */
    public static void addRoleToModel(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication.getAuthorities().contains(new SimpleGrantedAuthority(RoleName.ROLE_USER.toString()))) {
            model.addAttribute("role","ROLE_USER");
            //System.out.println("USER LOGGED");
        }
        else if(authentication.getAuthorities().contains(new SimpleGrantedAuthority(RoleName.ROLE_ADMIN.toString()))) {
            model.addAttribute("role","ROLE_ADMIN");
        }
        else {
            model.addAttribute("role","ROLE_ANONYMOUS");
        }
        model.addAttribute("searchForm",new StringTO());
    }
}
