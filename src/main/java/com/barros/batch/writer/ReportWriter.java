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
    private String outputFolder;
    private String invalidFolder;

    public ReportWriter(String inputFolder, String outputFolder, String invalidFolder) {
        this.inputFolder = inputFolder;
        this.outputFolder = outputFolder;
        this.invalidFolder = invalidFolder;
    }

    @Override
    public void write(List<? extends Relatorio> relatorios) {
        for (Relatorio relatorio : relatorios) {

            if (relatorio.isLoteValid()) {
                logger.info("Writing the report");
                writeReportForValidLote(relatorio);
            } else {
                writeInvalidLote(relatorio.getLote());
                logger.info("Writing invalid lote");
            }
        }
    }

    private void writeReportForValidLote(Relatorio relatorio) {
        try (FileWriter myWriter = new FileWriter(outputFolder + "/" + relatorio.getNomeDoLote() + ".done.dat")) {
            myWriter.write("Quantidade de clientes no arquivo de entrada: " + relatorio.getQuantidadeDeClientes() + "\n");
            myWriter.write("Quantidade de vendedores no arquivo de entrada: " + relatorio.getQuantidadeDeVendedores() + "\n");
            myWriter.write("ID da venda mais cara: " + relatorio.getIdDaMaiorVenda() + "\n");
            myWriter.write("O pior vendedor: " + relatorio.getPiorVendedor() + "\n");
        } catch (IOException exception) {
            logger.error("Error while reading file ", exception);
        }
    }

    private void writeInvalidLote(Lote lote) {
        String loteInputFolder = inputFolder + "/" + lote.getNomeDoArquivo()  + ".dat";
        String loteInvalidFolder = invalidFolder + "/" + lote.getNomeDoArquivo()  + ".dat";

        try {
            Files.move(Paths.get(loteInputFolder), Paths.get(loteInvalidFolder), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException exception) {
            logger.error("Error while moving invalid file ", exception);
        }
    }
}
