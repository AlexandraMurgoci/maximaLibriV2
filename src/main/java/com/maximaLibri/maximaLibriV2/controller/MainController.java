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
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        System.out.println(authentication.getPrincipal());
//        System.out.println(authentication.getDetails());
//        System.out.println(authentication.getCredentials());
//        System.out.println(authentication.getAuthorities());
//        System.out.println(authentication.getName());
        if(authentication.getAuthorities().contains(new SimpleGrantedAuthority(RoleName.ROLE_USER.toString()))) {
            model.addAttribute("role","ROLE_USER");
            //System.out.println("USER LOGGED");
        }
        else if(authentication.getAuthorities().contains(new SimpleGrantedAuthority(RoleName.ROLE_ADMIN.toString()))) {
            model.addAttribute("role","ROLE_ADMIN");
            //System.out.println("ADMIN LOGGED");
        }
        else {
            model.addAttribute("role","ROLE_ANONYMOUS");
        }
        return "index";
    }
}
