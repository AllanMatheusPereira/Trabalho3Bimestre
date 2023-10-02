package com.example.lojaconveniencia.modelo;

import com.example.lojaconveniencia.Controller;

import java.util.ArrayList;

public class Pedido {

    private Integer codigo;
    private Cliente cliente;
    //private Produto produto;
    private ArrayList<Produto> listaProdutos;
    private Double valorTotal;
    private int condicaoPagamento;
    private int quantidadeParcelas;


    private Integer quantidade;

    public Pedido() {
        this.listaProdutos = new ArrayList<Produto>();
        this.condicaoPagamento = 0;
        this.valorTotal = 0.0;
        this.quantidadeParcelas = 0;
        this.codigo = 0;

    }

    public int getNextCodigo() {
        ArrayList<Pedido> listaPedidos = Controller.getInstance().retornaPedidos();
        return listaPedidos.size() + 1;
    }



    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public ArrayList<Produto> getListaProdutos() {
        return listaProdutos;
    }




    public Double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(Double valorTotal) {
        this.valorTotal = valorTotal;
    }


    public void setListaProdutos(Produto produto) {
        this.listaProdutos.add(produto);
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public int getCondicaoPagamento() {
        return condicaoPagamento;
    }

    public void setCondicaoPagamento(int condicaoPagamento) {
        this.condicaoPagamento = condicaoPagamento;
    }

    public int getQuantidadeParcelas() {
        return quantidadeParcelas;
    }

    public void setQuantidadeParcelas(int quantidadeParcelas) {
        this.quantidadeParcelas = quantidadeParcelas;
    }

    public double getValorTotalComAjuste(Double percentualAjuste) {
        if (this.condicaoPagamento == 1) {
            return this.valorTotal - (this.valorTotal * (percentualAjuste / 100));
        }

        if (this.condicaoPagamento == 2) {
            return this.valorTotal + (this.valorTotal * (percentualAjuste / 100));
        }

        return this.valorTotal;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }
}
