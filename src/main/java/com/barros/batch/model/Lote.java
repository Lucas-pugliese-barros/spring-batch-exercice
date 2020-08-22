package com.barros.batch.model;

import com.barros.batch.reader.Line;
import lombok.Getter;
import lombok.NonNull;

import java.util.ArrayList;
import java.util.List;

@Getter
public class Lote {
    private String nomeDoArquivo;

    private final List<Vendedor> vendedores;
    private final List<Cliente> clientes;
    private final List<Venda> vendas;

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

    private void addVendedor(Vendedor vendedor) {
        this.vendedores.add(vendedor);
    }

    private void addCliente(Cliente cliente) {
        this.clientes.add(cliente);
    }

    private void addVenda(Venda venda) {
        this.vendas.add(venda);
    }

    public void addDadoByFormato(@NonNull Line line) {
        switch (line.getFormatoId()) {
            case Vendedor.FORMATO_ID:
                this.addVendedor(Vendedor.ofLine(line));
                break;
            case Cliente.FORMATO_ID:
                this.addCliente(Cliente.ofLine(line));
                break;
            case Venda.FORMATO_ID:
                this.addVenda(Venda.ofLine(line));
                break;
            default:
                break;
        }
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
