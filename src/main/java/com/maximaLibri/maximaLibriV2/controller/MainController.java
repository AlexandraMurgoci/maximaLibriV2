package com.maximaLibri.maximaLibriV2.controller;


import com.maximaLibri.maximaLibriV2.model.RoleName;
import com.maximaLibri.maximaLibriV2.service.BookService;
import com.maximaLibri.maximaLibriV2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import static com.maximaLibri.maximaLibriV2.controller.AddRoleToModel.addRoleToModel;

@Controller
public class MainController {

    @Autowired
    private BookService bookService;

    @Autowired
    private UserService userService;

    @GetMapping(value = {"/",""})
    public String root() {
        return "redirect:/index";
    }

    @GetMapping("/login")
    public String login(Model model) {
        addRoleToModel(model);
        return "login";
    }



    @GetMapping("/user")
    public String userIndex() {
        return "user/index";
    }

//    @GetMapping("/home")
//    public String home() {return "home";}

    @GetMapping("/index")
    public String showHomePage(Model model) {
        model.addAttribute("bookList", bookService.getTop10());
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        if(authentication.getAuthorities().contains(new SimpleGrantedAuthority(RoleName.ROLE_USER.toString()))) {
//            model.addAttribute("role","ROLE_USER");
//            //System.out.println("USER LOGGED");
//        }
//        else if(authentication.getAuthorities().contains(new SimpleGrantedAuthority(RoleName.ROLE_ADMIN.toString()))) {
//            model.addAttribute("role","ROLE_ADMIN");
//        }
//        else {
//            model.addAttribute("role","ROLE_ANONYMOUS");
//        }
        addRoleToModel(model);
        return "index";
    }
}
