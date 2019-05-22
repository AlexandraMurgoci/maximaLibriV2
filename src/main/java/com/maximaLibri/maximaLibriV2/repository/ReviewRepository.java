package com.maximaLibri.maximaLibriV2.repository;

import com.maximaLibri.maximaLibriV2.dto.IBookReview;
import com.maximaLibri.maximaLibriV2.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review,Long> {

    Review findByUserIdAndIsbn(Long userId, String isbn);

    /** intoarce lista tuturor review-urilor primite de o carte */
    @Query(value = "SELECT review_id, username, text\n" +
            "FROM public.reviews, public.users\n" +
            "WHERE reviews.user_id = users.user_id AND book_id = ?1\n" +
            "ORDER BY review_id DESC;",
    nativeQuery = true)
    List<IBookReview> findReviewsForBook(String isbn);

    /** intoarce lista tuturor review-urilor lasate de un user */
    @Query(value = "SELECT review_id, username, text\n" +
            "FROM public.reviews, public.users\n" +
            "WHERE reviews.user_id = users.user_id AND users.user_id = ?1\n" +
            "ORDER BY review_id DESC;",
    nativeQuery = true)
    List<IBookReview> findReviewsByUser(Long id);
}
