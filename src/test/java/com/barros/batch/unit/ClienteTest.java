package com.barros.batch.unit;

import com.barros.batch.exception.InvalidFieldsException;
import com.barros.batch.model.Cliente;
import com.barros.batch.model.Divisor;
import com.barros.batch.reader.Line;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ClienteTest {

    @Test
    public void givenValidLineOfClientItShouldReturnValidClient() {
        String cnpj = "2345675433444345";
        String name = "Eduardo Pereira";
        String businessArea = "Rural";

        Line line = new Line("002ç2345675433444345çEduardo PereiraçRural", Divisor.DEFAULT);

        Cliente client = Cliente.ofLine(line);

        assertThat(client.getCnpj()).isEqualTo(cnpj);
        assertThat(client.getNome()).isEqualTo(name);
        assertThat(client.getAreaDeNegocio()).isEqualTo(businessArea);
    }

    @Test(expected = InvalidFieldsException.class)
    public void givenInvalidLineOfClientItShouldReturnException() {
        Line line = new Line("002ç2345675433444345çEduardo PereiraRural", Divisor.DEFAULT);
        Cliente.ofLine(line);
    }
}
