package com.barros.batch.unit;

import com.barros.batch.exception.InvalidFieldsException;
import com.barros.batch.reader.Divisor;
import com.barros.batch.model.Item;
import com.barros.batch.model.Venda;
import com.barros.batch.reader.Line;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class VendaTest {

    @Test
    public void givenValidLineOfSaleItShouldReturnValidSaleWithTotalCost() {
        String idDaVenda = "10";
        String nomeDoVendedor = "Pedro";
        BigDecimal valorTotal = new BigDecimal("1199.00");

        List<Item> itens = new ArrayList<>() {{
           add(new Item("1", 10, new BigDecimal("100")));
           add(new Item("2", 30, new BigDecimal("2.50")));
           add(new Item("3", 40, new BigDecimal("3.10")));
        }};

        Line firstLine = new Line("003ç10ç[1-10-100,2-30-2.50,3-40-3.10]çPedro", Divisor.DEFAULT);

        Venda venda = Venda.ofLine(firstLine);

        assertThat(venda.getId()).isEqualTo(idDaVenda);
        assertThat(venda.getNomeDoVendedor()).isEqualTo(nomeDoVendedor);
        assertThat(venda.getItens()).containsAll(itens);
        assertThat(venda.getValorTotal()).isEqualTo(valorTotal);
    }

    @Test(expected = InvalidFieldsException.class)
    public void givenInvalidSaleItShouldReturnException() {
        Line firstLine = new Line("003ç10ç[1-10-100,2-30-2.50,3-40-3.10]Pedro", Divisor.DEFAULT);
        Venda.ofLine(firstLine);
    }
}
