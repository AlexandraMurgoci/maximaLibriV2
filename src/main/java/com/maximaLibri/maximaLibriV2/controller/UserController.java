package com.maximaLibri.maximaLibriV2.controller;

import com.maximaLibri.maximaLibriV2.dto.IBookAndRating;
import com.maximaLibri.maximaLibriV2.dto.IUserHistoryItem;
import com.maximaLibri.maximaLibriV2.model.User;
import com.maximaLibri.maximaLibriV2.recommender.RecommenderService;
import com.maximaLibri.maximaLibriV2.repository.BookRatingRepository;
import com.maximaLibri.maximaLibriV2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

import static com.maximaLibri.maximaLibriV2.controller.AddRoleToModel.addRoleToModel;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private BookRatingRepository bookRatingRepository;
    @Autowired
    private RecommenderService recommenderService;

    @GetMapping(value = {"","/"})
    public String showUser(Model model) {
        System.out.println("am intrat aici");
        User user = userService.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
        List<IUserHistoryItem> historyItemList = bookRatingRepository.getUserHistory(user.getId());
        model.addAttribute("user",user);
        model.addAttribute("userHistory",historyItemList);
        if(historyItemList.size()>=3) {
            model.addAttribute("isRecommendation", true);
            model.addAttribute("RecommendationsList", recommenderService.recommendForUser(user));
        }
        else {
            model.addAttribute("isRecommendation", false);
            model.addAttribute("RecommendationsList", new ArrayList<IBookAndRating>());
        }
        addRoleToModel(model);
        return "userMe";
    }



}
