package com.maximaLibri.maximaLibriV2.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@SequenceGenerator(name = "sequence_reviews", initialValue=1, allocationSize=10)
@Table(name="reviews")
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence_reviews")
    @Column(name = "review_id")
    private Long id;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "book_id")
    private String isbn;

    @Column
    private String text;

    public Review() {
    }

    public Review(Long userId, String isbn) {
        this.userId = userId;
        this.isbn = isbn;
        this.text = "";
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Review review = (Review) o;
        return id.equals(review.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Review{" +
                "id=" + id +
                ", userId=" + userId +
                ", isbn='" + isbn + '\'' +
                ", text='" + text + '\'' +
                '}';
    }
}
