package com.barros.batch.reader;

import com.barros.batch.exception.InvalidFieldsException;
import com.barros.batch.model.Divisor;
import com.barros.batch.model.Lote;
import org.apache.logging.log4j.util.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemReader;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class LoteReader implements ItemReader<Lote> {

    private final Logger logger = LoggerFactory.getLogger(LoteReader.class);
    private static final String FILE_EXTENSION = ".dat";

    private String inputFolder;
    private List<File> listOfFiles;

    public LoteReader(String inputFolder) {
        this.inputFolder = inputFolder;
    }

    @Override
    public Lote read() {
        final int FIRST_INDEX = 0;

        if (listOfFiles == null) {
            listOfFiles = getFilesFromFolder();
        }

        if (!listOfFiles.isEmpty()) {
            logger.info("Reading file: ", listOfFiles.get(FIRST_INDEX));

            Lote lote = convertFileToLote(listOfFiles.get(FIRST_INDEX));
            listOfFiles.remove(FIRST_INDEX);
            return lote;
        }

        return null;
    }

    private Lote convertFileToLote(File file) {
        Lote lote = new Lote(file.getName().replace(FILE_EXTENSION, Strings.EMPTY));
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                Line line = new Line(scanner.nextLine(), Divisor.DEFAULT);
                lote.addDadoByFormato(line);
            }

            return lote;
        } catch (FileNotFoundException exception) {
            logger.error("Error while reading file ", exception);
            return null;
        } catch (InvalidFieldsException exception) {
            logger.error("Error while reading file ", exception);
            lote.setValid(Boolean.FALSE);
            return lote;
        }
    }

    private List<File> getFilesFromFolder() {
        File file = new File(inputFolder);
        File[] arrayOfFiles = file.listFiles((directory, name) -> name.endsWith(FILE_EXTENSION));

        return arrayOfFiles != null ? new ArrayList<>(Arrays.asList(arrayOfFiles)) : new ArrayList<>();
    }
}
