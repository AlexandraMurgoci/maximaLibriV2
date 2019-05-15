package com.maximaLibri.maximaLibriV2.dto;

public class StringTO {
    private String stringParameter;

    public StringTO() {
        stringParameter = "";
    }

    public StringTO(String stringParameter) {
        this.stringParameter = stringParameter;
    }

    public String getStringParameter() {
        return stringParameter;
    }

    public void setStringParameter(String stringParameter) {
        this.stringParameter = stringParameter;
    }
}
