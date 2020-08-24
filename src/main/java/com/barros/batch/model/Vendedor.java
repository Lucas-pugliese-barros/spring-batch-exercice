package com.barros.batch.model;

import com.barros.batch.exception.InvalidFieldsException;
import com.barros.batch.reader.Line;
import lombok.*;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
@EqualsAndHashCode
public class Vendedor {
    private static final String INVALID_SALESMAN = "Not possible to convert line to salesman";
    public static final String FORMATO_ID = "001";

    private static final int CPF_INDEX = 1;
    private static final int NOME_INDEX = 2;
    private static final int SALARIO_INDEX = 3;

    private static final Integer QUANTITY_OF_FIELDS = 4;

    private String cpf;
    private String nome;
    private BigDecimal salario;

    public static Vendedor ofLine(@NonNull Line line) {
        if(!QUANTITY_OF_FIELDS.equals(line.getDatas().size()))
            throw new InvalidFieldsException(INVALID_SALESMAN);

        String cpf = line.getData(CPF_INDEX);
        String nome = line.getData(NOME_INDEX);
        BigDecimal salario = new BigDecimal(line.getData(SALARIO_INDEX));

        return new Vendedor(cpf, nome, salario);
    }
}
