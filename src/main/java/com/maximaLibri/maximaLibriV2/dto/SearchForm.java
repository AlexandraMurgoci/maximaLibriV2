package com.maximaLibri.maximaLibriV2.dto;

public class SearchForm {
    private String searchParameter;

    public SearchForm() {
        searchParameter = "";
    }

    public SearchForm(String searchParameter) {
        this.searchParameter = searchParameter;
    }

    public String getSearchParameter() {
        return searchParameter;
    }

    public void setSearchParameter(String searchParameter) {
        this.searchParameter = searchParameter;
    }
}
