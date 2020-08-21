package com.barros.batch.reader;

import lombok.NonNull;
import org.apache.logging.log4j.util.Strings;

import java.util.Arrays;
import java.util.List;

public class Line {

    private static final String SPLIT_BY = "ç";
    private static final int SECOND_ITEM_INDEX = 1;

    private String line;

    public Line(@NonNull String line) {
        this.line = line;
    }

    public String getLineId() {
        return Arrays.asList(line.split(SPLIT_BY))
                .stream()
                .findFirst()
                .orElse(Strings.EMPTY);
    }

    public List<String> getData() {
        return Arrays.asList(line.split(SPLIT_BY))
                .subList(SECOND_ITEM_INDEX,line.length());
    }
}
