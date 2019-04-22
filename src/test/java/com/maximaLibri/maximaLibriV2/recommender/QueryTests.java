package com.maximaLibri.maximaLibriV2.recommender;

import com.maximaLibri.maximaLibriV2.dto.IBookAndRating;
import com.maximaLibri.maximaLibriV2.model.BookRating;
import com.maximaLibri.maximaLibriV2.repository.BookRatingRepository;
import com.maximaLibri.maximaLibriV2.repository.BookRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
@ComponentScan(value = "com.maximaLibri.maximaLibriV2")
public class QueryTests {

    @Autowired
    private BookRatingRepository bookRatingRepository;

    @Autowired
    private BookRepository bookRepository;

    @Test
    @Transactional
    public void findRatingsByUserIdTest() {
        List<BookRating> bookRatingList = bookRatingRepository.findRatingsByUserId(8L);
        assertThat(bookRatingList)
                .isNotEmpty()
                .hasSize(7);
        assertTrue(bookRatingList.get(0).getBookRatingId().getUserId()==8);
        assertTrue(bookRatingList.get(0).getBookRatingId().getIsbn().equals("0002005018"));
        assertTrue(bookRatingList.get(0).getBookRating()==5);
    }

    @Test
    @Transactional
    public void findTop10Test() {
        List<IBookAndRating> bookList =  bookRatingRepository.getTop10();
        assertThat(bookList)
                .isNotEmpty()
                .hasSize(10);
//        for (IBookAndRating iBookAndRating : bookList) {
//
//        }
        /*IBookAndRating iBookAndRating = bookList.get(0);
        System.out.println(iBookAndRating.getIsbn());
        System.out.println(iBookAndRating.getBook_Title());
        System.out.println(iBookAndRating.getBook_Author());
        System.out.println(iBookAndRating.getYear_Of_Publication());
        System.out.println(iBookAndRating.getPublisher());
        System.out.println(iBookAndRating.getImage_Url_S());
        System.out.println(iBookAndRating.getImage_Url_M());
        System.out.println(iBookAndRating.getImage_Url_L());
        System.out.println(iBookAndRating.getAverage());*/
        //assertThat(bookList.get(0).getImage_Url_S()).isNotNull();

        assertThat(bookList.get(0).getIsbn()).isNotNull();
        assertThat(bookList.get(0).getBook_Title()).isNotNull();
        assertThat(bookList.get(0).getBook_Author()).isNotNull();
        assertThat(bookList.get(0).getYear_Of_Publication()).isNotNull();
        assertThat(bookList.get(0).getPublisher()).isNotNull();
        assertThat(bookList.get(0).getImage_Url_S()).isNotNull();
        assertThat(bookList.get(0).getImage_Url_M()).isNotNull();
        assertThat(bookList.get(0).getImage_Url_L()).isNotNull();
        assertThat(bookList.get(0).getAverage()).isNotNull();
    }
}
