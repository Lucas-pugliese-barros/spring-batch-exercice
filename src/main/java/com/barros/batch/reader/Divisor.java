package com.barros.batch.reader;

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
