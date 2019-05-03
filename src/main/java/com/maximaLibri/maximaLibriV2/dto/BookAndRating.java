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

    private String description;

    public BookAndRating() {
    }

    public BookAndRating(IBookAndRating iBookAndRating, String description) {
        this.isbn = iBookAndRating.getIsbn();
        this.title = iBookAndRating.getBook_Title();
        this.author = iBookAndRating.getBook_Author();
        this.yearOfPublication = iBookAndRating.getYear_Of_Publication();
        this.publisher = iBookAndRating.getPublisher();
        this.imageUrlS = iBookAndRating.getImage_Url_S();
        this.imageUrlM = iBookAndRating.getImage_Url_M();
        this.imageUrlL = iBookAndRating.getImage_Url_L();
        this.rating = iBookAndRating.getAverage();
        this.description = description;
    }

    String getIsbn() {return isbn;}
    String getBook_Title() {return title;}
    String getBook_Author() {return author;}
    Integer getYear_Of_Publication() {return yearOfPublication;}
    String getPublisher() {return publisher;}
    String getImage_Url_S() {return imageUrlS;}
    String getImage_Url_M() {return imageUrlM;}
    String getImage_Url_L() {return imageUrlL;}
    Float getAverage() {return rating;}
    String getDescription() {return description;}
}
