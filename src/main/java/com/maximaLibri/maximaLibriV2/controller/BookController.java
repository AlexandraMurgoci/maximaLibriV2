package com.maximaLibri.maximaLibriV2.controller;

import com.maximaLibri.maximaLibriV2.model.Book;
import com.maximaLibri.maximaLibriV2.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/book")
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping(value={"/",""})
    public String bookList( Model model) {
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
        return "editBook";
    }

    @RequestMapping(value="/admin/bookEdit", method = RequestMethod.POST)
    public String bookEdit(Model model, Book book) {
        bookService.saveBook(book);
        return "index";
    }
}
