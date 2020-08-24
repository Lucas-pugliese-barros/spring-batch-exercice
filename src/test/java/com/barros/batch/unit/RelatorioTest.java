package com.barros.batch.unit;

import com.barros.batch.model.*;
import com.barros.batch.reader.LoteReader;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class RelatorioTest {

    private LoteReader loteReader;

    @Before
    public void beforeTheTest() {
        String inputFolder = "/home/lucas.pugliese.barros/NewJob/DBC/agibank-batch/batch/src/test/resources/input";
        loteReader = new LoteReader(inputFolder);
    }

    @Test
    public void givenValidLoteItShouldReturnValidReport() {
        int quantityOfClientsExpected = 2;
        int quantityOfSalesmanExpected = 2;
        String idOfBiggestSale = "10";
        String worstSalesman = "Paulo";

        Lote lote = loteReader.read();
        Relatorio relatorio = new Relatorio(lote);

        assertThat(relatorio.getQuantidadeDeClientes()).isEqualTo(quantityOfClientsExpected);
        assertThat(relatorio.getQuantidadeDeVendedores()).isEqualTo(quantityOfSalesmanExpected);
        assertThat(relatorio.getIdDaMaiorVenda()).isEqualTo(idOfBiggestSale);
        assertThat(relatorio.getPiorVendedor()).isEqualTo(worstSalesman);
    }
}
