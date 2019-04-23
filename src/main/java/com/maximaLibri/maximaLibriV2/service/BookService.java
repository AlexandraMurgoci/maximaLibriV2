package com.maximaLibri.maximaLibriV2.service;

import com.maximaLibri.maximaLibriV2.dto.IBookAndRating;
import com.maximaLibri.maximaLibriV2.model.Book;
import com.maximaLibri.maximaLibriV2.model.BookRating;
import com.maximaLibri.maximaLibriV2.model.BookRatingId;
import com.maximaLibri.maximaLibriV2.repository.BookRatingRepository;
import com.maximaLibri.maximaLibriV2.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private BookRatingRepository bookRatingRepository;

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Optional<Book> getBookById(String isbn) {
        return bookRepository.findById(isbn);
    }

    public Book saveBook(Book book) {
        return bookRepository.save(book);
    }

    public List<IBookAndRating> getTop10() {
        return bookRatingRepository.getTop10();
    }

    public IBookAndRating getBookAndRatingById(String isbn) {
        return bookRatingRepository.findBookAndRatingById(isbn);
    }

    public BookRating getBookRatingByUserAndIsbn(String isbn, Long userId) {
        BookRatingId bookRatingId = new BookRatingId();
        bookRatingId.setUserId(userId);
        bookRatingId.setIsbn(isbn);
        Optional<BookRating> optionalBookRating = bookRatingRepository.findById(bookRatingId);
        return optionalBookRating.orElse(null);
    }

    public void saveBookRating(Long userId, String isbn, Integer rating) {
        BookRatingId bookRatingId = new BookRatingId();
        bookRatingId.setUserId(userId);
        bookRatingId.setIsbn(isbn);
        BookRating bookRating = new BookRating();
        bookRating.setBookRatingId(bookRatingId);
        bookRating.setBookRating(rating);
        bookRatingRepository.save(bookRating);
    }

    public void saveBookRating(BookRating bookRating) {
        bookRatingRepository.save(bookRating);
    }
}
