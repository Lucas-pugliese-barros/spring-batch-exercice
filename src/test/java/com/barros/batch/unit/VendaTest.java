package com.barros.batch.unit;

import com.barros.batch.model.Divisor;
import com.barros.batch.model.Venda;
import com.barros.batch.reader.Line;
import org.junit.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

public class VendaTest {

    @Test
    public void givenValidLineOfSaleItShouldReturnTheTotalValueOfSale() {
        BigDecimal valorTotal = new BigDecimal("1199.00");

        Line firstLine = new Line("003ç10ç[1-10-100,2-30-2.50,3-40-3.10]çPedro", Divisor.DEFAULT);

        Venda venda = Venda.ofLine(firstLine);

        assertThat(venda.getValorTotal()).isEqualTo(valorTotal);
    }
}
