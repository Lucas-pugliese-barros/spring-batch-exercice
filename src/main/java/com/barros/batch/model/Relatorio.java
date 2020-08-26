package com.barros.batch.model;

import lombok.*;

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

    public String getNomeDoLote() {
        return lote.getNomeDoArquivo();
    }

    public boolean isLoteValid() {
        return lote.isValid();
    }

    public int getQuantidadeDeClientes() {
        return lote.getQuantidadeDeClientes();
    }

    public int getQuantidadeDeVendedores() {
        return lote.getQuantidadeDeVendedores();
    }

    public String getIdDaMaiorVenda() {
        return lote.getIdDaMaiorVenda();
    }

    public String getPiorVendedor() {
        return lote.getPiorVendedor();
    }

    public String print() {
        return "Quantidade de clientes no arquivo de entrada: " + this.getQuantidadeDeClientes() + "\n" +
        "Quantidade de vendedores no arquivo de entrada: " + this.getQuantidadeDeVendedores() + "\n" +
        "ID da venda mais cara: " + this.getIdDaMaiorVenda() + "\n" +
        "O pior vendedor: " + this.getPiorVendedor() + "\n";
    }

}
