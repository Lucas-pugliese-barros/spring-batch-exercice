package com.barros.batch.model;

import lombok.*;

import java.math.BigDecimal;
import java.util.*;
import java.util.function.Function;

import static java.util.stream.Collectors.*;

@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class Relatorio {
    private Lote lote;

    public Relatorio(Lote lote) {
        this.lote = lote;
    }

    public Lote getLote() {
        return lote;
    }

    public int getQuantidadeDeClientes() {
        return lote.getClientes().size();
    }

    public int getQuantidadeDeVendedores() {
        return lote.getVendedores().size();
    }

    public String getIdDaMaiorVenda() {
        Map<String, BigDecimal> precoTotalDaVendaById = precoTotalDaVendaBy(Venda::getId);

        Optional<Map.Entry<String, BigDecimal>> melhorVendaDoLote = precoTotalDaVendaById.entrySet()
                .stream().max(Map.Entry.comparingByValue());

        return melhorVendaDoLote.isPresent() ? melhorVendaDoLote.get().getKey() : "";
    }

    public String getPiorVendedor() {
        Map<String, BigDecimal> precoTotalDaVendaByVendedor = precoTotalDaVendaBy(Venda::getNomeDoVendedor);

        Optional<Map.Entry<String, BigDecimal>> piorVendaDoLote = precoTotalDaVendaByVendedor.entrySet()
                .stream().min(Map.Entry.comparingByValue());

        return piorVendaDoLote.isPresent() ? piorVendaDoLote.get().getKey() : "";
    }

    private Map<String, BigDecimal> precoTotalDaVendaBy (Function<Venda, String> groupBy) {
        Map<String, List<Venda>> vendasDoLoteById = groupVendasBy(groupBy);
        Map<String, BigDecimal> precoTotalDaVendaById = new HashMap<>();

        vendasDoLoteById.forEach((idVenda, vendas) -> {
            BigDecimal valorTotalDaVenda = vendas.stream()
                    .map(Venda::getValorTotal)
                    .reduce(BigDecimal.ZERO, BigDecimal::add);

            precoTotalDaVendaById.put(idVenda, valorTotalDaVenda);
        });

        return precoTotalDaVendaById;
    }

    private Map<String, List<Venda>> groupVendasBy(Function<Venda, String> groupBy) {
        return lote.getVendas()
                .stream()
                .collect(groupingBy(groupBy));
    }
}
