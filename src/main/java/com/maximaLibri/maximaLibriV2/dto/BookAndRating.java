package com.maximaLibri.maximaLibriV2.dto;

public class BookAndRating {

    private String isbn;

    private String title;

    private String author;

    private Integer yearOfPublication;

    private String publisher;

    private String imageUrlS;

    private String imageUrlM;

    private String imageUrlL;

    private Float rating;

    public BookAndRating() {
    }

    public BookAndRating(IBookAndRating iBookAndRating) {
        this.isbn = iBookAndRating.getIsbn();
        this.title = iBookAndRating.getBook_Title();
        this.author = iBookAndRating.getBook_Author();
        this.yearOfPublication = iBookAndRating.getYear_Of_Publication();
        this.publisher = iBookAndRating.getPublisher();
        this.imageUrlS = iBookAndRating.getImage_Url_S();
        this.imageUrlM = iBookAndRating.getImage_Url_M();
        this.imageUrlL = iBookAndRating.getImage_Url_L();
        this.rating = iBookAndRating.getAverage();
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

    public Float getRating() {
        return rating;
    }

    public void setRating(Float rating) {
        this.rating = rating;
    }
}
