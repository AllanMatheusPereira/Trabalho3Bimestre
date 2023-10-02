package com.example.lojaconveniencia;

import com.example.lojaconveniencia.modelo.Cliente;
import com.example.lojaconveniencia.modelo.Pedido;
import com.example.lojaconveniencia.modelo.Produto;

import java.util.ArrayList;

public class Controller {

    private static Controller instancia;

    private ArrayList<Cliente> listaClientes;
    private ArrayList<Produto> listaProdutos;
    private ArrayList<Pedido> listaPedidos;

    public static Controller getInstance(){
        if (instancia == null){
            return instancia = new Controller();
        }else {
            return instancia;
        }
    }

    private Controller(){
        listaClientes = new ArrayList<>();
        listaProdutos = new ArrayList<>();
        listaPedidos = new ArrayList<>();


        Cliente cliente1 = new Cliente();
        cliente1.setNome("Allan");
        cliente1.setCpf("999.999.999-99");
        listaClientes.add(cliente1);

        Produto produto1 = new Produto();
        produto1.setDescricao("Produto 1");
        produto1.setValorProduto(100.00);
        listaProdutos.add(produto1);
    }

    public void salvarCliente(Cliente cliente) { listaClientes.add(cliente);}

    public ArrayList<Cliente> retornaClientes() {return listaClientes;}

    public void salvarProduto(Produto produto) { listaProdutos.add(produto);}

    public ArrayList<Produto> retornaProdutos() {return listaProdutos;}
    public void salvarPedido(Pedido pedido) { listaPedidos.add(pedido);}

    public ArrayList<Pedido> retornaPedidos() {return listaPedidos;}
}
