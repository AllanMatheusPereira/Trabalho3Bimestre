package com.example.lojaconveniencia.modelo;

public class Produto {

    private int codigo;
    private String descricao;
    private double valorProduto;
    private int quantidade;

   public Produto() {
   }

   public Produto(int codigo, String descricao, double vlrProduto){
        this.codigo= codigo;
        this.descricao= descricao;
        this.valorProduto = vlrProduto;
   }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getValorProduto() {
        return valorProduto;
    }

    public void setValorProduto(double valorProduto) {
        this.valorProduto = valorProduto;
    }

    public int getQuantidade() {
        return quantidade;
    }
    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
}
