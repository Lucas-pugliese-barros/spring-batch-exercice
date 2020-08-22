package com.barros.batch.model;

public enum Divisor {

    DEFAULT("ç"),
    COMMA(","),
    MINUS("-");

    private String value;

    Divisor(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
