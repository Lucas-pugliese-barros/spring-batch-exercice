package com.barros.batch.unit;

import com.barros.batch.exception.InvalidFieldsException;
import com.barros.batch.model.Divisor;
import com.barros.batch.model.Vendedor;
import com.barros.batch.reader.Line;
import org.junit.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

public class VendedorTest {

    @Test
    public void givenValidLineOfSalesmanItShouldReturnValidSalesman() {
        String cpfDoVendedor = "1234567891234";
        String nomeDoVendedor = "Pedro";
        BigDecimal salarioDoVendedor = new BigDecimal("50000");

        Line line = new Line("001ç1234567891234çPedroç50000", Divisor.DEFAULT);

        Vendedor vendedor = Vendedor.ofLine(line);

        assertThat(vendedor.getCpf()).isEqualTo(cpfDoVendedor);
        assertThat(vendedor.getNome()).isEqualTo(nomeDoVendedor);
        assertThat(vendedor.getSalario()).isEqualTo(salarioDoVendedor);
    }

    @Test(expected = InvalidFieldsException.class)
    public void givenInvalidSaleItShouldReturnException() {
        Line line = new Line("001ç1234567891234çPedro50000", Divisor.DEFAULT);
        Vendedor.ofLine(line);
    }
}
