package com.barros.batch.unit;

import com.barros.batch.exception.InvalidFieldsException;
import com.barros.batch.model.Item;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class ItemTest {

    @Test
    public void givenValidArraysOfStringItShouldReturnValidListOfItens() {
        String arrayOfStrings = "[1-10-100,2-30-2.50,3-40-3.10]";

        List<Item> itensExpected = new ArrayList<>() {{
            add(new Item("1", 10, new BigDecimal("100")));
            add(new Item("2", 30, new BigDecimal("2.50")));
            add(new Item("3", 40, new BigDecimal("3.10")));
        }};

        List<Item> itens = Item.ofItensArray(arrayOfStrings);

        assertThat(itens).containsAll(itensExpected);
    }

    @Test(expected = InvalidFieldsException.class)
    public void givenInvalidLineWithOutBracketsItShouldReturnException() {
        String arrayOfStrings = "[1-10-100,2-30-2.50,3-40-3.10";
        Item.ofItensArray(arrayOfStrings);
    }

    @Test(expected = InvalidFieldsException.class)
    public void givenInvalidLineOfItemsItShouldReturnException() {
        String arrayOfStrings = "[1-10-100,2-30-2.50,3-40]";
        Item.ofItensArray(arrayOfStrings);
    }
}
