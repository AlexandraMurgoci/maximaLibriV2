package com.maximaLibri.maximaLibriV2.repository;

import com.maximaLibri.maximaLibriV2.dto.IBookAndRating;
import com.maximaLibri.maximaLibriV2.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, String> {

    //List<Book> findTopByTitle10OrderByTitle(String title);
    @Query(value = "SELECT bx_books.isbn, book_title, book_author, year_of_publication, publisher, image_url_s, image_url_m, image_url_l, average\n" +
            "FROM public.bx_books,\n" +
            "(SELECT isbn,ROUND(AVG(book_rating),2) as average\n" +
            "FROM public.bx_book_ratings\n" +
            "GROUP BY isbn) as book_rating_avg\n" +
            "WHERE bx_books.isbn = book_rating_avg.isbn AND \n" +
            "(bx_books.isbn LIKE %?1%\n" +
            " OR book_title LIKE %?1%\n" +
            " OR book_author LIKE %?1%);",
    nativeQuery = true)
    List<IBookAndRating> findSearchResults(String searchParameter);

    //List<Book> findByIsbnContaining()
}
