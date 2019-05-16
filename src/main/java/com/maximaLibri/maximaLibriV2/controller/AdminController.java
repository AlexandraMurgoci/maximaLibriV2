package com.maximaLibri.maximaLibriV2.controller;

import com.maximaLibri.maximaLibriV2.dto.StringTO;
import com.maximaLibri.maximaLibriV2.model.User;
import com.maximaLibri.maximaLibriV2.service.BookService;
import com.maximaLibri.maximaLibriV2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import static com.maximaLibri.maximaLibriV2.controller.AddRoleToModel.addRoleToModel;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    UserService userService;
    @Autowired
    BookService bookService;

    @GetMapping("/user/{id}")
    public String showUser(Model model, @PathVariable Long id) {
        addRoleToModel(model);
        User user = userService.findById(id);
        model.addAttribute("user",user);
        StringTO userId = new StringTO();
        userId.setStringParameter(user.getId().toString());
        model.addAttribute("userId", userId);
        model.addAttribute("iBookReviewList",userService.getReviewsByUser(id));
        return "userAdminView";
    }

    @PostMapping("/disable-user")
    public String disableUser(@ModelAttribute StringTO userId) {
        userService.disableUserById(Long.valueOf(userId.getStringParameter()));
        return "redirect:/user";
    }

    @PostMapping("/enable-user")
    public String enableUser(@ModelAttribute StringTO userId) {
        userService.enableUserById(Long.valueOf(userId.getStringParameter()));
        return "redirect:/user";
    }

    @GetMapping("/review/delete/{id}")
    public String deleteReview(@PathVariable Long id) {
        bookService.deleteReview(id);
        return "redirect:/user";
    }
}
