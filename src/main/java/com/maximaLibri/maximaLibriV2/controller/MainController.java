package com.maximaLibri.maximaLibriV2.controller;


import com.maximaLibri.maximaLibriV2.dto.StringTO;
import com.maximaLibri.maximaLibriV2.model.User;
import com.maximaLibri.maximaLibriV2.service.BookService;
import com.maximaLibri.maximaLibriV2.service.EmailSenderService;
import com.maximaLibri.maximaLibriV2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import static com.maximaLibri.maximaLibriV2.controller.AddRoleToModel.addRoleToModel;

@Controller
public class MainController {

    @Autowired
    private BookService bookService;

    @Autowired
    private UserService userService;

    @Autowired
    private EmailSenderService emailSenderService;

    @GetMapping(value = {"/",""})
    public String root() {
        return "redirect:/index";
    }

    @GetMapping("/login")
    public String login(Model model) {
        addRoleToModel(model);
        return "login";
    }

    @GetMapping("/checkEnabled")
    public String checkUserIsEnabled() {
        User user = userService.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
        if(user.getEnabled()) {
            return "redirect:/index";
        }
        else {
            return "redirect:/logout";
        }
    }

    @PostMapping("/search")
    public String search(@ModelAttribute StringTO searchParameter) {
        return "redirect:/book/"+ searchParameter.getStringParameter().replace(' ','+');
    }


//    @GetMapping("/user")
//    public String userIndex() {
//        return "user/index";
//    }

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

    @GetMapping("/contact-admin")
    public String contactAdmin(Model model) {

        addRoleToModel(model);
        model.addAttribute("messageForAdmin", new StringTO());
        return "contactAdmin";
    }

    @PostMapping("/contact-admin")
    public String sendMailToAdmin(@ModelAttribute StringTO messageForAdmin) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo("alexa.murgoci@gmail.com");
        mailMessage.setSubject("Maxima Libri Admin Contacted");
        mailMessage.setFrom("alexa.murgoci@gmail.com");
        mailMessage.setText(messageForAdmin.getStringParameter());

        emailSenderService.sendEmail(mailMessage);
        return "redirect:/index";
    }
}
