package com.maximaLibri.maximaLibriV2.recommender;

import com.maximaLibri.maximaLibriV2.dto.IBookAndRating;
import com.maximaLibri.maximaLibriV2.dto.IUserHistoryItem;
import com.maximaLibri.maximaLibriV2.model.Book;
import com.maximaLibri.maximaLibriV2.model.BookRating;
import com.maximaLibri.maximaLibriV2.model.User;
import com.maximaLibri.maximaLibriV2.repository.BookRatingRepository;
import com.maximaLibri.maximaLibriV2.service.UserService;
import com.maximaLibri.maximaLibriV2.testData.TestData;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.SortedMap;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
@ComponentScan(value = "com.maximaLibri.maximaLibriV2")
@EnableTransactionManagement
public class RecommenderTest {
    @Autowired
    RecommenderService recommenderService;

    @Autowired
    BookRatingRepository bookRatingRepository;

    @Autowired
    UserService userService;

    @Autowired
    EntityManager entityManager;


    @Test
    @Transactional
    public void makeUtilityMatrixTest() {
        SortedMap<Long, SortedMap<String, Integer> > utilityMatrix = recommenderService.makeUtilityMatrix();
        List<IUserHistoryItem> userHistoryItems = bookRatingRepository.getUserHistory(8L);
        System.out.println(utilityMatrix.size());
        assertThat(utilityMatrix).isNotNull();
        assertThat(utilityMatrix.get(8L))
                .isNotNull()
                .isNotEmpty();
        assertThat(utilityMatrix.get(8L).keySet().containsAll(userHistoryItems.stream().map(x -> x.getIsbn()).collect(Collectors.toSet()))).isTrue();
    }


    @Test
    @Transactional
    public void getRecommandationTest() {
        TestData testData = new TestData();
        User jimmyUser = testData.getJimmyUser();
        jimmyUser = userService.save(jimmyUser);
        entityManager.flush();

        userService.rateBook("059035342X",jimmyUser.getId(),9);
        userService.rateBook("0312195516",jimmyUser.getId(),7);
        userService.rateBook("B0001GDNCK",jimmyUser.getId(),5);
        userService.rateBook("0671027360",jimmyUser.getId(),8);
        entityManager.flush();

        List<IBookAndRating> recommendedBooks = recommenderService.recommendForUser(jimmyUser);
        recommendedBooks.stream()
                .map(x -> x.getIsbn()+" "+x.getBook_Title()+" "+x.getBook_Author()+" "+x.getAverage())
                .forEach(System.out::println);
    }
}
