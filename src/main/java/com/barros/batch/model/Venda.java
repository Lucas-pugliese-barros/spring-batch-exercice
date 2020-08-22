package com.barros.batch.model;

import com.barros.batch.reader.Line;
import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
@EqualsAndHashCode
public class Venda {
    public static final String FORMATO_ID = "003";

    private static final int ID_INDEX = 1;
    private static final int ITENS_INDEX = 2;
    private static final int NOME_DO_VENDEDOR_INDEX = 3;

    private String id;
    private List<Item> itens;
    private String nomeDoVendedor;

    public static Venda ofLine(@NonNull Line line) {
        String id = line.getData(ID_INDEX);
        List<Item> itens = Item.ofItensArray(line.getData(ITENS_INDEX));
        String nomeDoVendedor = line.getData(NOME_DO_VENDEDOR_INDEX);

        return new Venda(id, itens, nomeDoVendedor);
    }
}
