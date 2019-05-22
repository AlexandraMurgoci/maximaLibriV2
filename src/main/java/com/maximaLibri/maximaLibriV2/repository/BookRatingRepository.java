package com.maximaLibri.maximaLibriV2.repository;

import com.maximaLibri.maximaLibriV2.dto.IBookAndRating;
import com.maximaLibri.maximaLibriV2.dto.IUserHistoryItem;
import com.maximaLibri.maximaLibriV2.model.BookRating;
import com.maximaLibri.maximaLibriV2.model.BookRatingId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRatingRepository extends JpaRepository<BookRating, BookRatingId> {
    //List<BookRating> findTop10GroupByIdOrderByAvgBookRating();
    /** intoarce istoricul voturilor unui user identificat prin id */
    @Query(value = "SELECT * FROM bx_book_ratings WHERE user_id=?1",
            nativeQuery = true)
    List<BookRating> findRatingsByUserId(Long userId);

    @Query(value = "SELECT AVG(book_rating) FROM bx_book_ratings WHERE isbn=?1",
            nativeQuery=true)
    Float getBookRating(String isbn);

    /*@Query(value = "SELECT bx_books.isbn, book_title, book_author, year_of_publication, publisher, image_url_s, image_url_m, image_url_l,\n" +
            "average\n" +
            "FROM bx_books,\n" +
            "(\n" +
            "\tSELECT isbn, average\n" +
            "FROM (\n" +
            "\tSELECT isbn, AVG(book_rating) as average\n" +
            "FROM public.bx_book_ratings as selection1\n" +
            "GROUP BY isbn\n" +
            "ORDER BY COUNT(book_rating) DESC\n" +
            "LIMIT 20\n" +
            "\t) as selection2\n" +
            "\tORDER BY average DESC\n" +
            "\tLIMIT 11\n" +
            "\t) as selection3\n" +
            "WHERE bx_books.isbn = selection3.isbn;",
          nativeQuery=true)
        List<IBookAndRating> getTop10();*/

    @Query(value = "SELECT AVG(book_rating) FROM public.bx_book_ratings;",
    nativeQuery = true)
    Float getAvg();

    /** get the 10 best books relative to popularity and average rating */
    @Query(value = "SELECT bx_books.isbn, book_title, book_author, year_of_publication, publisher, image_url_s, image_url_m, image_url_l, average\n" +
            "FROM bx_books, \n" +
            "(SELECT isbn, ROUND((SUM(book_rating)+1000*((SELECT AVG(book_rating) as avgall from public.bx_book_ratings)))/(1000+COUNT(book_rating)),2) as average\n"+
            "FROM bx_book_ratings\n" +
            "GROUP BY isbn) as selection\n" +
            "WHERE bx_books.isbn=selection.isbn\n" +
            "ORDER BY average DESC\n" +
            "LIMIT 10;",
    nativeQuery = true)
    List<IBookAndRating> getTop10();

    /** intoarce istoricul voturilor unui user identificat prin id */
    @Query(value = "SELECT bx_books.isbn,book_title,book_author,book_rating\n" +
            "FROM public.bx_book_ratings, public.bx_books\n" +
            "WHERE bx_book_ratings.isbn = bx_books.isbn AND bx_book_ratings.user_id = ?1",
            nativeQuery = true)
    List<IUserHistoryItem> getUserHistory(Long id);

    @Query(value = "SELECT bx_books.isbn, book_title, book_author, year_of_publication, publisher, image_url_s, image_url_m, image_url_l, average\n" +
            "FROM public.bx_books, \n" +
            "\t(SELECT isbn,ROUND(AVG(book_rating),2) as average\n" +
            "\tFROM public.bx_book_ratings\n" +
            "\tGROUP BY isbn) as book_rating_avg\n" +
            "WHERE bx_books.isbn = book_rating_avg.isbn AND bx_books.isbn = ?1 ",
    nativeQuery = true)
    IBookAndRating findBookAndRatingById(String isbn);
}
