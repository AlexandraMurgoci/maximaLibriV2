package com.maximaLibri.maximaLibriV2.controller;

import com.maximaLibri.maximaLibriV2.dto.IUserHistoryItem;
import com.maximaLibri.maximaLibriV2.model.User;
import com.maximaLibri.maximaLibriV2.repository.BookRatingRepository;
import com.maximaLibri.maximaLibriV2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private BookRatingRepository bookRatingRepository;

    @GetMapping(value = {"","/"})
    public String showUser(Model model) {
        User user = userService.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
        List<IUserHistoryItem> historyItemList = bookRatingRepository.getUserHistory(user.getId());
        model.addAttribute("user",user);
        model.addAttribute("userHistory",historyItemList);
        return "userMe";
    }

}
