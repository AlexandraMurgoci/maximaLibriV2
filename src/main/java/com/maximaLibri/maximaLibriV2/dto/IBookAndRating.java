package com.maximaLibri.maximaLibriV2.dto;

public interface IBookAndRating {

    String getIsbn();
    String getBook_Title();
    String getBook_Author();
    Integer getYear_Of_Publication();
    String getPublisher();
    String getImage_Url_S();
    String getImage_Url_M();
    String getImage_Url_L();
    Float getAverage();
}
