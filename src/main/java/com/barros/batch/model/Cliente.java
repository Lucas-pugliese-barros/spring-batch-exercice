package com.barros.batch.model;

import com.barros.batch.reader.Line;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
@EqualsAndHashCode
public class Cliente {
    public static final String FORMATO_ID = "002";

    private static final int CNPJ_INDEX = 1;
    private static final int NOME_INDEX = 2;
    private static final int AREA_DE_NEGOCIO = 3;

    private String cnpj;
    private String nome;
    private String areaDeNegocio;

    public static Cliente ofLine(@NonNull Line line) {
        String cnpj = line.getData(CNPJ_INDEX);
        String nome = line.getData(NOME_INDEX);
        String areaDeNegocio = line.getData(AREA_DE_NEGOCIO);

        return new Cliente(cnpj, nome, areaDeNegocio);
    }
}
