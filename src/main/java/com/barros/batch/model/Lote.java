package com.barros.batch.model;

import com.barros.batch.reader.Line;
import lombok.Getter;
import lombok.NonNull;

import java.math.BigDecimal;
import java.util.*;
import java.util.function.Function;

import static java.util.stream.Collectors.groupingBy;

@Getter
public class Lote {
    private String nomeDoArquivo;

    private final List<Vendedor> vendedores;
    private final List<Cliente> clientes;
    private final List<Venda> vendas;

    private boolean isValid = true;

    public Lote() {
        vendedores = new ArrayList<>();
        clientes = new ArrayList<>();
        vendas = new ArrayList<>();
    }

    public Lote(String nomeDoArquivo) {
        this.nomeDoArquivo = nomeDoArquivo;

        vendedores = new ArrayList<>();
        clientes = new ArrayList<>();
        vendas = new ArrayList<>();
    }

    public void addDadoByFormato(@NonNull Line line) {
        switch (line.getFormatoId()) {
            case Vendedor.FORMATO_ID:
                this.vendedores.add(Vendedor.ofLine(line));
                break;
            case Cliente.FORMATO_ID:
                this.clientes.add(Cliente.ofLine(line));
                break;
            case Venda.FORMATO_ID:
                this.vendas.add(Venda.ofLine(line));
                break;
            default:
                break;
        }
    }

    public void setValid(boolean valid) {
        isValid = valid;
    }

    public int getQuantidadeDeClientes() {
        return clientes.size();
    }

    public int getQuantidadeDeVendedores() {
        return vendedores.size();
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
        return vendas.stream()
                .collect(groupingBy(groupBy));
    }

    @Override
    public String toString() {
        return "Lote{" +
                "nomeDoArquivo='" + nomeDoArquivo + '\'' +
                ", vendedores=" + vendedores +
                ", clientes=" + clientes +
                ", vendas=" + vendas +
                '}';
    }
}
