package com.maximaLibri.maximaLibriV2.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class BookRatingId implements Serializable {

    @Column(name = "user_id")
    private Long userId;

    @Column
    private String isbn;

    public BookRatingId() {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookRatingId that = (BookRatingId) o;
        return userId.equals(that.userId) &&
                isbn.equals(that.isbn);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, isbn);
    }

    @Override
    public String toString() {
        return "BookRatingId{" +
                "userId=" + userId +
                ", isbn='" + isbn + '\'' +
                '}';
    }
}
