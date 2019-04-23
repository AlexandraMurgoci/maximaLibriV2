package com.maximaLibri.maximaLibriV2.controller;

import com.maximaLibri.maximaLibriV2.model.Book;
import com.maximaLibri.maximaLibriV2.model.BookRating;
import com.maximaLibri.maximaLibriV2.model.RoleName;
import com.maximaLibri.maximaLibriV2.model.User;
import com.maximaLibri.maximaLibriV2.service.BookService;
import com.maximaLibri.maximaLibriV2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import static com.maximaLibri.maximaLibriV2.controller.AddRoleToModel.addRoleToModel;

@Controller
@RequestMapping("/book")
public class BookController {

    @Autowired
    private BookService bookService;

    @Autowired
    private UserService userService;

    @GetMapping(value={"/",""})
    public String bookList( Model model) {
        addRoleToModel(model);
        model.addAttribute("bookList", bookService.getTop10());
        return "bookList";
    }

    @RequestMapping(value={"/admin/bookEdit","/admin/bookEdit/{isbn}"}, method = RequestMethod.GET)
    public String bookEditForm(Model model, @PathVariable(required = false, name = "isbn") String isbn) {
        if (null != isbn) {
            model.addAttribute("book", bookService.getBookById(isbn));
        } else {
            model.addAttribute("book", new Book());
        }
        addRoleToModel(model);
        return "editBook";
    }

    @RequestMapping(value="/admin/bookEdit", method = RequestMethod.POST)
    public String bookEdit(Model model, @ModelAttribute Book book) {
        bookService.saveBook(book);
        return "index";
    }

    @RequestMapping(value="/show/{isbn}", method = RequestMethod.GET)
    public String bookShow(Model model, @PathVariable(required = true, name = "isbn") String isbn) {
        model.addAttribute("book",bookService.getBookAndRatingById(isbn));
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication.getAuthorities().contains(new SimpleGrantedAuthority(RoleName.ROLE_USER.toString()))) {
            model.addAttribute("role","ROLE_USER");
            User user = userService.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
            BookRating bookRating = bookService.getBookRatingByUserAndIsbn(isbn,user.getId());
            if(bookRating==null) model.addAttribute("userRating",0);
            else model.addAttribute("userRating",bookRating.getBookRating());
        }
        else if(authentication.getAuthorities().contains(new SimpleGrantedAuthority(RoleName.ROLE_ADMIN.toString()))) {
            model.addAttribute("role","ROLE_ADMIN");
            User user = userService.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
            BookRating bookRating = bookService.getBookRatingByUserAndIsbn(isbn,user.getId());
            if(bookRating==null) model.addAttribute("userRating",0);
            else model.addAttribute("userRating",bookRating.getBookRating());
        }
        else {
            model.addAttribute("role","ROLE_ANONYMOUS");
        }

        return "bookShow";
    }

    @RequestMapping(value="/show/{isbn}", method = RequestMethod.POST)
    public String bookShowRateBook(Model model, @PathVariable(required = true, name = "isbn") String isbn,
                                   @ModelAttribute(name = "userRating") Integer userRating) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
        bookService.saveBookRating(user.getId(),isbn,userRating);
        return "bookShow";
    }
}
