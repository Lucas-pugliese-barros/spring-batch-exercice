package com.barros.batch.unit;

import com.barros.batch.model.*;
import com.barros.batch.reader.LoteReader;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class LoteReaderTest {

    private LoteReader loteReader;

    @Before
    public void beforeTheTest() {
        String inputFolder = "/home/lucas.pugliese.barros/NewJob/DBC/agibank-batch/batch/src/test/resources/input";
        loteReader = new LoteReader(inputFolder);
    }

    @Test
    public void givenValidInputFolderTheReaderShouldReturnValidVendedores() {
        String fileName = "lote_um";

        List<Vendedor> vendedoresEsperados = new ArrayList<>() {{
            add(new Vendedor("1234567891234", "Pedro", new BigDecimal("50000")));
            add(new Vendedor("3245678865434", "Paulo", new BigDecimal("40000.99")));
        }};

        Lote lote = loteReader.read();

        assertThat(lote.getNomeDoArquivo()).isEqualTo(fileName);
        assertThat(lote.getVendedores()).containsAll(vendedoresEsperados);
    }

    @Test
    public void givenValidInputFolderTheReaderShouldReturnValidClientes() {
        String fileName = "lote_um";

        List<Cliente> clientesEsperados = new ArrayList<>() {{
            add(new Cliente("2345675434544345", "Jose da Silva", "Rural"));
            add(new Cliente("2345675433444345", "Eduardo Pereira", "Rural"));
        }};

        Lote lote = loteReader.read();

        assertThat(lote.getNomeDoArquivo()).isEqualTo(fileName);
        assertThat(lote.getClientes()).containsAll(clientesEsperados);
    }

    @Test
    public void givenValidInputFolderTheReaderShouldReturnValidVenda() {
        String fileName = "lote_um";

        List<Item> itensOfVendaPedro = new ArrayList<>() {{
            add(new Item("1", 10, new BigDecimal("100")));
            add(new Item("2", 30, new BigDecimal("2.50")));
            add(new Item("3", 40, new BigDecimal("3.10")));
        }};

        List<Item> itensOfVendaPaulo = new ArrayList<>() {{
            add(new Item("1", 34, new BigDecimal("10")));
            add(new Item("2", 33, new BigDecimal("1.50")));
            add(new Item("3", 40, new BigDecimal("0.10")));
        }};

        List<Venda> vendasEsperadas = new ArrayList<>() {{
            add(new Venda("10", itensOfVendaPedro, "Pedro"));
            add(new Venda("08", itensOfVendaPaulo, "Paulo"));
        }};

        Lote lote = loteReader.read();

        assertThat(lote.getNomeDoArquivo()).isEqualTo(fileName);
        assertThat(lote.getVendas()).containsAll(vendasEsperadas);
    }
}
