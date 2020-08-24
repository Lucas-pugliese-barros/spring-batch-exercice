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
