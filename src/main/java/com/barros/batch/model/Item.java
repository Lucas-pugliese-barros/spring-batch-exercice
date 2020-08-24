package com.barros.batch.model;

import com.barros.batch.exception.InvalidFieldsException;
import com.barros.batch.reader.Line;
import lombok.*;
import org.apache.logging.log4j.util.Strings;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
@EqualsAndHashCode
public class Item {
    private static final String INVALID_ITEMS = "Not possible to convert line to sale itens";
    private static final String BRACKETS_REGEX = "[\\[\\]]";

    private static final int ID_INDEX = 0;
    private static final int QUANTIDADE_INDEX = 1;
    private static final int PRECO_INDEX = 2;

    private static final Integer QUANTITY_OF_FIELDS = 3;

    private String id;
    private int quantidade;
    private BigDecimal preco;

    private static Item ofItemString(@NonNull String itemString) {
        Line line = new Line(itemString, Divisor.MINUS);

        if (!QUANTITY_OF_FIELDS.equals(line.getDatas().size()))
            throw new InvalidFieldsException(INVALID_ITEMS);

        String id = line.getData(ID_INDEX);
        int quantidade = Integer.parseInt(line.getData(QUANTIDADE_INDEX));
        BigDecimal nomeDoVendedor = new BigDecimal(line.getData(PRECO_INDEX));

        return new Item(id, quantidade, nomeDoVendedor);
    }

    public static List<Item> ofItensArray(@NonNull String itensArray) {
        if (!(itensArray.contains("[") && itensArray.contains("]")))
            throw new InvalidFieldsException(INVALID_ITEMS);

        String arrayWithoutBrackets = itensArray.replaceAll(BRACKETS_REGEX, Strings.EMPTY);
        Line line = new Line(arrayWithoutBrackets, Divisor.COMMA);

        return line.getDatas().stream()
                .map(Item::ofItemString)
                .collect(Collectors.toList());
    }

    public BigDecimal getValorDeTodosOsItens() {
        return preco.multiply(new BigDecimal(quantidade));
    }
}
