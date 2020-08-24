package com.barros.batch.writer;

import com.barros.batch.model.Relatorio;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemWriter;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class ReportWriter implements ItemWriter<Relatorio> {

    private final Logger logger = LoggerFactory.getLogger(ReportWriter.class);
    private String outputFolder;

    public ReportWriter(String outputFolder) {
        this.outputFolder = outputFolder;
    }

    @Override
    public void write(List<? extends Relatorio> relatorios) {
        for (Relatorio relatorio : relatorios) {
            logger.info("Writing the report");
            try (FileWriter myWriter = new FileWriter(outputFolder + "/" + relatorio.getLote().getNomeDoArquivo() + ".done.dat")) {
                myWriter.write("Quantidade de clientes no arquivo de entrada: " + relatorio.getQuantidadeDeClientes() + "\n");
                myWriter.write("Quantidade de vendedores no arquivo de entrada: " + relatorio.getQuantidadeDeVendedores() + "\n");
                myWriter.write("ID da venda mais cara: " + relatorio.getIdDaMaiorVenda() + "\n");
                myWriter.write("O pior vendedor: " + relatorio.getPiorVendedor() + "\n");
            } catch (IOException exception) {
                logger.error("Error while reading file ", exception);
            }
        }
    }
}
