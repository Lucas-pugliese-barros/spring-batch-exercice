package com.barros.batch.reader;

import com.barros.batch.model.Divisor;
import lombok.NonNull;
import org.apache.logging.log4j.util.Strings;

import java.util.Arrays;
import java.util.List;

public class Line {

    private String value;
    private Divisor divisor;

    public Line(@NonNull String value, Divisor divisor) {
        this.value = value;
        this.divisor = divisor;
    }

    public String getFormatoId() {
        return Arrays.stream(value.split(divisor.getValue()))
                .findFirst()
                .orElse(Strings.EMPTY);
    }

    public String getData(int index) {
        String[] datas = value.split(divisor.getValue());

        return Arrays.asList(datas).get(index);
    }

    public List<String> getDatas() {
        String[] datas = value.split(divisor.getValue());

        return Arrays.asList(datas);
    }
}
