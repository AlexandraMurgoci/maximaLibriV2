package com.maximaLibri.maximaLibriV2.controller;

import com.maximaLibri.maximaLibriV2.model.*;
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

    @GetMapping(value = "/{searchParameter}")
    public String bookListSearchResults(Model model, @PathVariable(name="searchParameter") String searchParameter) {
        addRoleToModel(model);
        model.addAttribute("bookList", bookService.getSearchResults(searchParameter.replace('+',' ')));
        model.addAttribute("goodreadsSearchLink","https://www.goodreads.com/search?q="+searchParameter);
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
        model.addAttribute("description",bookService.getBookDescription(isbn));
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        BookRating bookRating = null;
        Long userId = null;
        if(authentication.getAuthorities().contains(new SimpleGrantedAuthority(RoleName.ROLE_USER.toString()))) {
            model.addAttribute("role","ROLE_USER");
            User user = userService.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
            bookRating = bookService.getBookRatingByUserAndIsbn(isbn,user.getId());
            userId = user.getId();
        }
        else if(authentication.getAuthorities().contains(new SimpleGrantedAuthority(RoleName.ROLE_ADMIN.toString()))) {
            model.addAttribute("role","ROLE_ADMIN");
            User user = userService.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
            bookRating = bookService.getBookRatingByUserAndIsbn(isbn,user.getId());
            userId = user.getId();
        }
        else {
            model.addAttribute("role","ROLE_ANONYMOUS");
            model.addAttribute("userRating","0");
        }
        if(bookRating==null) {
            BookRatingId bookRatingId = new BookRatingId();
            bookRatingId.setIsbn(isbn);
            bookRatingId.setUserId(userId);
            bookRating = new BookRating();
            bookRating.setBookRatingId(bookRatingId);
            bookRating.setBookRating(0);
        }
        model.addAttribute("bookRating",bookRating);
        return "bookShow";
    }

    @RequestMapping(value="/show", method = RequestMethod.POST)
    public String bookShowRateBook(//Model model, //@PathVariable(required = true, name = "isbn") String isbn,
                                   @ModelAttribute("bookRating") BookRating bookRating) {
        bookService.saveBookRating(bookRating);
        return "redirect:/index";
    }
}
