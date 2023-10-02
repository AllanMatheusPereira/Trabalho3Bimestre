package com.example.lojaconveniencia;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.lojaconveniencia.modelo.Cliente;
import com.example.lojaconveniencia.modelo.Pedido;
import com.example.lojaconveniencia.modelo.Produto;

import java.util.ArrayList;

public class ListaPedidos extends AppCompatActivity {

    private TextView tvTodosPedidos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_pedidos);


        tvTodosPedidos = findViewById(R.id.tvTodosPedidos);
        listarPedidos();

    }

    private void listarPedidos() {
        ArrayList<Pedido> lista = Controller.getInstance().retornaPedidos();
        String stringListaPedidos = "";
        for (Pedido ped : lista) {

            String condicao;
            String parcelas;
            if (ped.getCondicaoPagamento() == 2) {
                condicao = "À prazo";
                parcelas = Integer.toString(ped.getQuantidadeParcelas()) + "x";
            } else {
                condicao = "À vista";
                parcelas = "";
            }

            Cliente cliente1 = ped.getCliente();
            stringListaPedidos += "Codigo: " + Integer.toString(ped.getCodigo()) + "\n";
            stringListaPedidos += " - Cliente: " + cliente1.getNome() + "- C.P.F.: " + cliente1.getCpf() + "\n";
            stringListaPedidos += " - Condição de pagamento: " + condicao + " " + parcelas + "\n";
            stringListaPedidos += " - Produtos: \n";

            for (Produto prod : ped.getListaProdutos()) {
                stringListaPedidos += "    - Produto: " + prod.getDescricao() +
                        " - Código: " + prod.getCodigo() +
                        " - Valor: " + prod.getValorProduto() +
                        "\n";
            }

            stringListaPedidos += "\n";

        }
        tvTodosPedidos.setText(stringListaPedidos);

    }



}