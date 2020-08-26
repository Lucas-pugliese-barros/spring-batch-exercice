package com.barros.batch.writer;

import com.barros.batch.model.Lote;
import com.barros.batch.model.Relatorio;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemWriter;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

public class ReportWriter implements ItemWriter<Relatorio> {

    private final Logger logger = LoggerFactory.getLogger(ReportWriter.class);

    private String inputFolder;
    private String doneFolder;
    private String outputFolder;
    private String invalidFolder;

    public ReportWriter(String inputFolder, String doneFolder, String outputFolder, String invalidFolder) {
        this.inputFolder = inputFolder;
        this.doneFolder = doneFolder;
        this.outputFolder = outputFolder;
        this.invalidFolder = invalidFolder;
    }

    @Override
    public void write(List<? extends Relatorio> relatorios) {
        for (Relatorio relatorio : relatorios) {
            if (relatorio.isLoteValid()) {
                logger.info("Writing the report and moving lote");
                writeReportForValidLote(relatorio);
                moveLoteToDoneFolder(relatorio.getNomeDoLote());
            } else {
                moveLoteToInvalidFolder(relatorio.getNomeDoLote());
                logger.info("Moving invalid lote");
            }
        }
    }

    private void writeReportForValidLote(Relatorio relatorio) {
        try (FileWriter myWriter = new FileWriter(outputFolder + "/" + relatorio.getNomeDoLote() + ".done.dat")) {
            myWriter.write(relatorio.print());
        } catch (IOException exception) {
            logger.error("Error while reading file ", exception);
        }
    }

    private void moveLoteToDoneFolder(String nomeDoLote) {
        try {
            String loteInputFolder = inputFolder + "/" + nomeDoLote  + ".dat";
            String loteDoneFolder = doneFolder + "/" + nomeDoLote  + ".dat";

            Files.move(Paths.get(loteInputFolder), Paths.get(loteDoneFolder), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException exception) {
            logger.error("Error while moving valid file ", exception);
        }
    }

    private void moveLoteToInvalidFolder(String nomeDoLote) {
        try {
            String loteInputFolder = inputFolder + "/" + nomeDoLote  + ".dat";
            String loteInvalidFolder = invalidFolder + "/" + nomeDoLote  + ".dat";

            Files.move(Paths.get(loteInputFolder), Paths.get(loteInvalidFolder), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException exception) {
            logger.error("Error while moving invalid file ", exception);
        }
    }
}
