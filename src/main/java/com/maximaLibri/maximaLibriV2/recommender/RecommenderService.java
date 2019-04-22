package com.maximaLibri.maximaLibriV2.recommender;

import com.maximaLibri.maximaLibriV2.model.Book;
import com.maximaLibri.maximaLibriV2.model.BookRating;
import com.maximaLibri.maximaLibriV2.model.BxUser;
import com.maximaLibri.maximaLibriV2.model.User;
import com.maximaLibri.maximaLibriV2.repository.BookRatingRepository;
import com.maximaLibri.maximaLibriV2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

@Service
public class RecommenderService {

    @Autowired
    UserService userService;

    @Autowired
    BookRatingRepository bookRatingRepository;

    public List<Book> recommendForUser(User user) {
        return null;
    }

    public List<Book> recommend(List<BookRating> bookRatings) {
        return null;
    }

    public SortedMap<Long, SortedMap<String, Integer> > makeUtilityMatrix() {
        List<BookRating> bookRatingList = bookRatingRepository.findAll();
        SortedMap<Long, SortedMap<String, Integer> > utilityMatrix = new TreeMap<>();
        for(BookRating bookRating : bookRatingList) {
            SortedMap<String, Integer> ratingsByUser = utilityMatrix.get(bookRating.getBookRatingId().getUserId());
            if(ratingsByUser==null) {
                ratingsByUser = new TreeMap<>();
                ratingsByUser.put(bookRating.getBookRatingId().getIsbn(),bookRating.getBookRating());
            }
        }
        return null;
    }
}
