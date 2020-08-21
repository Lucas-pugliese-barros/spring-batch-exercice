package com.barros.batch.model;

import java.util.List;

public class Lote {

    private String nomeDoArquivo;

    private List<Vendedor> vendedores;
    private List<Cliente> clientes;
    private List<Venda> vendas;

    public Lote() {
    }

    public Lote(String nomeDoArquivo) {

        this.nomeDoArquivo = nomeDoArquivo;
    }

    public List<Vendedor> getVendedores() {
        return vendedores;
    }

    public void setVendedores(List<Vendedor> vendedores) {
        this.vendedores = vendedores;
    }

    public List<Cliente> getClientes() {
        return clientes;
    }

    public void setClientes(List<Cliente> clientes) {
        this.clientes = clientes;
    }

    public List<Venda> getVendas() {
        return vendas;
    }

    public void setVendas(List<Venda> vendas) {
        this.vendas = vendas;
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
