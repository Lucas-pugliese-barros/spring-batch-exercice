package com.barros.batch.writer;

import com.barros.batch.reader.FileReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemWriter;

import java.util.List;

public class ConsoleItemWriter<T> implements ItemWriter<T> {

    private final Logger logger = LoggerFactory.getLogger(ConsoleItemWriter.class);

    @Override
    public void write(List<? extends T> items) {
        for (T item : items) {
            logger.debug(item.toString());
        }
    }
}
