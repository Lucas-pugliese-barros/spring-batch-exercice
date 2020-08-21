package com.barros.batch.reader;

import com.barros.batch.model.Lote;
import org.springframework.batch.item.ItemReader;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class FileReader implements ItemReader<Lote> {

    private String folder;
    private List<File> listOfFiles;

    public FileReader(String folder) {
        this.folder = folder;
    }

    @Override
    public Lote read() {
        final int FIRST_INDEX = 0;

        if (listOfFiles == null) {
            listOfFiles = getFilesFromFolder();
        }

        if (!listOfFiles.isEmpty()) {
            Lote lote = convertFileToLote(listOfFiles.get(FIRST_INDEX));
            listOfFiles.remove(FIRST_INDEX);
            return lote;
        }

        return null;
    }

    public Lote convertFileToLote(File file) {
        //The try-with-resources statement is a try statement that declares one or more resources.
        // A resource is an object that must be closed after the program is finished with it.
        // The try-with-resources statement ensures that each resource is closed at the end of the statement.
        // Any object that implements java.lang.AutoCloseable, which includes all objects which implement java.io.Closeable, can be used as a resource.
        try (Scanner scanner = new Scanner(file)) {

            while (scanner.hasNextLine()) {
                Line line = new Line(scanner.nextLine());
                line.getData().forEach(System.out::println);
            }
        }catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return new Lote(file.getAbsolutePath());
    }

    public List<File> getFilesFromFolder() {
        File file = new File(folder);
        return Arrays.asList(file.listFiles((directory, name) -> name.endsWith(".dat")));
    }
}
