package com.barros.batch.unit;

import com.barros.batch.model.*;
import com.barros.batch.processor.LoteProcessor;
import com.barros.batch.reader.Line;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class LoteProcessorTest {

    private LoteProcessor loteProcessor;

    @Before
    public void beforeTheTest() {
        loteProcessor = new LoteProcessor();
    }

    @Test
    public void givenValidLoteTheProcessorShouldReturnAhReportWithTheQuantityOfClients() {
        int TWO_CLIENTS = 2;

        Line firstLine = new Line("002ç2345675434544345çJose da SilvaçRural", Divisor.DEFAULT);
        Line secondLine = new Line("002ç2345675433444345çEduardo PereiraçRural", Divisor.DEFAULT);

        Lote lote = new Lote();
        lote.addDadoByFormato(firstLine);
        lote.addDadoByFormato(secondLine);

        Relatorio relatorio = loteProcessor.process(lote);

        assertThat(relatorio.getLote()).isEqualTo(lote);
        assertThat(relatorio.getQuantidadeDeClientes()).isEqualTo(TWO_CLIENTS);
    }

    @Test
    public void givenValidLoteTheProcessorShouldReturnAhReportWithTheQuantityOfSalesman() {
        int TWO_SALESMAN = 2;

        Line firstLine = new Line("001ç1234567891234çPedroç50000", Divisor.DEFAULT);
        Line secondLine = new Line("001ç3245678865434çPauloç40000.99", Divisor.DEFAULT);

        Lote lote = new Lote();
        lote.addDadoByFormato(firstLine);
        lote.addDadoByFormato(secondLine);

        Relatorio relatorio = loteProcessor.process(lote);

        assertThat(relatorio.getLote()).isEqualTo(lote);
        assertThat(relatorio.getQuantidadeDeVendedores()).isEqualTo(TWO_SALESMAN);
    }

    @Test
    public void givenValidLoteWithTwoSalesTheProcessorShouldReturnAhReportWithTheBestSale() {
        String idDaMelhorVenda = "10";

        Line firstLine = new Line("003ç10ç[1-10-100,2-30-2.50,3-40-3.10]çPedro", Divisor.DEFAULT);
        Line secondLine = new Line("003ç08ç[1-34-10,2-33-1.50,3-40-0.10]çPaulo", Divisor.DEFAULT);

        Lote lote = new Lote();
        lote.addDadoByFormato(firstLine);
        lote.addDadoByFormato(secondLine);

        Relatorio relatorio = loteProcessor.process(lote);

        assertThat(relatorio.getLote()).isEqualTo(lote);
        assertThat(relatorio.getIdDaMaiorVenda()).isEqualTo(idDaMelhorVenda);
    }

    @Test
    public void givenValidLoteWithThreeSalesTheProcessorShouldReturnAhReportWithTheBestSale() {
        String idDaMelhorVenda = "08";

        Line firstLine = new Line("003ç10ç[1-10-100,2-30-2.50,3-40-3.10]çPedro", Divisor.DEFAULT);
        Line secondLine = new Line("003ç08ç[1-34-10,2-33-1.50,3-40-0.10]çPaulo", Divisor.DEFAULT);
        Line third = new Line("003ç08ç[1-1-900]çPaulo", Divisor.DEFAULT);

        Lote lote = new Lote();
        lote.addDadoByFormato(firstLine);
        lote.addDadoByFormato(secondLine);
        lote.addDadoByFormato(third);

        Relatorio relatorio = loteProcessor.process(lote);

        assertThat(relatorio.getLote()).isEqualTo(lote);
        assertThat(relatorio.getIdDaMaiorVenda()).isEqualTo(idDaMelhorVenda);
    }

    @Test
    public void givenValidLoteWithTwoSalesTheProcessorShouldReturnAhReportWithTheWorstSalesman() {
        String worstSalesman = "Paulo";

        Line firstLine = new Line("003ç10ç[1-10-100,2-30-2.50,3-40-3.10]çPedro", Divisor.DEFAULT);
        Line secondLine = new Line("003ç08ç[1-34-10,2-33-1.50,3-40-0.10]çPaulo", Divisor.DEFAULT);

        Lote lote = new Lote();
        lote.addDadoByFormato(firstLine);
        lote.addDadoByFormato(secondLine);

        Relatorio relatorio = loteProcessor.process(lote);

        assertThat(relatorio.getLote()).isEqualTo(lote);
        assertThat(relatorio.getPiorVendedor()).isEqualTo(worstSalesman);
    }

    @Test
    public void givenValidLoteWithThreeSalesTheProcessorShouldReturnAhReportWithTheWorstSalesman() {
        String worstSalesman = "Pedro";

        Line firstLine = new Line("003ç10ç[1-10-100,2-30-2.50,3-40-3.10]çPedro", Divisor.DEFAULT);
        Line secondLine = new Line("003ç08ç[1-34-10,2-33-1.50,3-40-0.10]çPaulo", Divisor.DEFAULT);
        Line third = new Line("003ç08ç[1-1-900]çPaulo", Divisor.DEFAULT);

        Lote lote = new Lote();
        lote.addDadoByFormato(firstLine);
        lote.addDadoByFormato(secondLine);
        lote.addDadoByFormato(third);

        Relatorio relatorio = loteProcessor.process(lote);

        assertThat(relatorio.getLote()).isEqualTo(lote);
        assertThat(relatorio.getPiorVendedor()).isEqualTo(worstSalesman);
    }
}
