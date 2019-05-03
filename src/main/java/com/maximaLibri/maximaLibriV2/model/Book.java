package com.maximaLibri.maximaLibriV2.model;

import org.springframework.stereotype.Component;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "bx_books")
public class Book {

    @Id
    @Column
    private String isbn;

    @Column(name = "book_title")
    private String title;

    @Column(name = "book_author")
    private String author;

    @Column(name = "year_of_publication")
    private Integer yearOfPublication;

    @Column
    private String publisher;

    @Column(name = "image_url_s")
    private String imageUrlS;

    @Column(name = "image_url_m")
    private String imageUrlM;

    @Column(name = "image_url_l")
    private String imageUrlL;

    /*@Column
    private String description;

    @Column
    private String keywords;*/

    public Book() {
    }

    public Book(String isbn, String title, String author, Integer yearOfPublication, String publisher, String imageUrlS, String imageUrlM, String imageUrlL) {
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.yearOfPublication = yearOfPublication;
        this.publisher = publisher;
        this.imageUrlS = imageUrlS;
        this.imageUrlM = imageUrlM;
        this.imageUrlL = imageUrlL;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Integer getYearOfPublication() {
        return yearOfPublication;
    }

    public void setYearOfPublication(Integer yearOfPublication) {
        this.yearOfPublication = yearOfPublication;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getImageUrlS() {
        return imageUrlS;
    }

    public void setImageUrlS(String imageUrlS) {
        this.imageUrlS = imageUrlS;
    }

    public String getImageUrlM() {
        return imageUrlM;
    }

    public void setImageUrlM(String imageUrlM) {
        this.imageUrlM = imageUrlM;
    }

    public String getImageUrlL() {
        return imageUrlL;
    }

    public void setImageUrlL(String imageUrlL) {
        this.imageUrlL = imageUrlL;
    }

    /*public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }*/

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return isbn.equals(book.isbn);
    }

    @Override
    public int hashCode() {
        return Objects.hash(isbn);
    }

    @Override
    public String toString() {
        return "Book{" +
                "isbn='" + isbn + '\'' +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", yearOfPublication=" + yearOfPublication +
                ", publisher='" + publisher + '\'' +
                ", imageUrlS='" + imageUrlS + '\'' +
                ", imageUrlM='" + imageUrlM + '\'' +
                ", imageUrlL='" + imageUrlL + '\'' +
                /*", description='" + description + '\'' +
                ", keywords='" + keywords + '\'' +*/
                '}';
    }
}
