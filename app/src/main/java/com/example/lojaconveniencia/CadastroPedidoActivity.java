package com.example.lojaconveniencia;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lojaconveniencia.modelo.Cliente;
import com.example.lojaconveniencia.modelo.Pedido;
import com.example.lojaconveniencia.modelo.Produto;

import java.util.ArrayList;

public class CadastroPedidoActivity extends AppCompatActivity {

    private Spinner spSelecionaCliente;
    private TextView tvErroCliente;
    private Spinner spSelecionaProduto;
    private TextView tvErroProduto;

    private EditText edQuantidade;
    private TextView tvValorProdutoPedido;
    private EditText edValorProdutoPedido;
    private EditText edCodigoPedido;
    private Button btVoltarPedido;
    private Button btAdicionarProduto;
    private Button btSalvarPedido;
    private TextView tvPedido;
    private TextView tvParcelasValores;

    private ArrayList<Cliente> listaClientes;

    private ArrayList<Produto> listaProdutos;
//    private ArrayList<Pedido> pedido;
    private int posicaoCliente = 0;

    private int posicaoProduto = 0;

    private double valorTotal;

    private double valorFinal;

    private TextView tvTotalItens;
    private TextView tvTotalItensPedido;
    private TextView tvTotalPedido;
    private TextView tvTotalPedidoItem;

    private Spinner spCondicaoPagamento;
    private Spinner spQuantidadeParcelas;
    private TextView tvParcelas;

    private TextView tvTotalCondicao;

    private TextView tvTotalFinal;

    private Pedido pedido;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_pedido);

        spSelecionaCliente = findViewById(R.id.spSelecionaCliente);
        tvErroCliente = findViewById(R.id.tvErroCliente);
        tvErroProduto = findViewById(R.id.tvErroProduto);
        spSelecionaProduto = findViewById(R.id.spSelecionaProduto);
        edQuantidade = findViewById(R.id.edQuantidade);
        tvValorProdutoPedido = findViewById(R.id.tvValorProdutoPedido);
        edValorProdutoPedido = findViewById(R.id.edValorProdutoPedido);
        btVoltarPedido = findViewById(R.id.btVoltarPedido);
        btAdicionarProduto = findViewById(R.id.btAdicionarProduto);
        btSalvarPedido = findViewById(R.id.btSalvarPedido);
        tvPedido = findViewById(R.id.tvPedido);
        tvParcelasValores = findViewById(R.id.tvParcelasValores);
        tvTotalItens = findViewById(R.id.tvTotalItens);
        tvTotalPedido = findViewById(R.id.tvTotalPedido);
        spCondicaoPagamento = findViewById(R.id.spCondicaoPagamento);
        spQuantidadeParcelas = findViewById(R.id.spQuantidadeParcelas);
        tvParcelas = findViewById(R.id.tvParcelas);
        tvTotalItensPedido = findViewById(R.id.tvTotalItensPedido);
        tvTotalPedidoItem = findViewById(R.id.tvTotalPedidoItem);
        tvTotalFinal = findViewById(R.id.tvTotalFinal);
        tvTotalCondicao = findViewById(R.id.tvTotalCondicao);
        edCodigoPedido = findViewById(R.id.edCodigoPedido);


        btVoltarPedido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                voltarMenu();
            }
        });

        btAdicionarProduto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adicionarProduto();
            }
        });

        btSalvarPedido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                salvarPedido();
            }
        });


        alimentarCliente();
        alimentarProduto();
        alimentarCondicaoPagamento();

        spSelecionaCliente.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int posicao, long l) {
                if (posicao > 0){
                    atualizaClientePedido();
                    tvErroCliente.setVisibility(View.GONE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spSelecionaProduto.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int posicao, long l) {
                listaProdutos = Controller.getInstance().retornaProdutos();
                if (posicao > 0){
                    edValorProdutoPedido.setText(Double.toString(listaProdutos.get(posicao-1).getValorProduto()));
                    tvErroProduto.setVisibility(View.GONE);
                 }else {
                    edValorProdutoPedido.setText("");
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spCondicaoPagamento.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int posicao, long l) {
                if (posicao == 2) {
                    spQuantidadeParcelas.setVisibility(View.VISIBLE);
                    pedido.setQuantidadeParcelas(1);
                }else {
                    spQuantidadeParcelas.setVisibility(View.GONE);
                    pedido.setQuantidadeParcelas(0);
                }
                pedido.setCondicaoPagamento(posicao);
                atualizarTotal();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        spQuantidadeParcelas.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int posicao, long l) {
                pedido.setQuantidadeParcelas(posicao+1);
                atualizarTotal();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        pedido = new Pedido();
        carregaCodigoPedido(pedido.getNextCodigo());

    }

    private void alimentarCliente() {
        listaClientes = Controller.getInstance().retornaClientes();
        String[]vetClientes = new String[ listaClientes.size() + 1 ];
        vetClientes[0] = "Selecione o cliente";
        for (int i = 0; i < listaClientes.size(); i++){
            Cliente cliente = this.listaClientes.get(i);
            vetClientes[i+1] =cliente.getNome()+ " - "+cliente.getCpf();
        }
        ArrayAdapter adapter = new ArrayAdapter(
            CadastroPedidoActivity.this, android.R.layout.simple_dropdown_item_1line,vetClientes
                );

        spSelecionaCliente.setAdapter(adapter);

    }

    private void carregaCodigoPedido(int codigoPedido) {
        pedido.setCodigo(codigoPedido);
        edCodigoPedido.setText(Integer.toString(pedido.getCodigo()));
    }

    private void alimentarProduto() {
        edQuantidade.setText("1");

        listaProdutos = Controller.getInstance().retornaProdutos();
        String[]vetProduto = new String[ listaProdutos.size() + 1 ];
        vetProduto[0] = "Selecione o produto";
        for (int i = 0; i < listaProdutos.size(); i++){
            Produto produto = this.listaProdutos.get(i);
            vetProduto[i+1] =produto.getDescricao();
        }
        ArrayAdapter adapter = new ArrayAdapter<>(
                CadastroPedidoActivity.this,android.R.layout.simple_dropdown_item_1line,vetProduto
        );

        spSelecionaProduto.setAdapter(adapter);
    }

    private void alimentarCondicaoPagamento() {
        String[]vetCondicao = new String[ 3 ];
        vetCondicao[0] = "Selecione a condição";
        vetCondicao[1] = "À vista";
        vetCondicao[2] = "À prazo";
        ArrayAdapter adapter = new ArrayAdapter<>(
                CadastroPedidoActivity.this,android.R.layout.simple_dropdown_item_1line,vetCondicao
        );

        spCondicaoPagamento.setAdapter(adapter);

        String[]vetParcelas = new String[ 3 ];
        vetParcelas[0] = "1x";
        vetParcelas[1] = "2x";
        vetParcelas[2] = "3x";
        ArrayAdapter adapter2 = new ArrayAdapter<>(
                CadastroPedidoActivity.this,android.R.layout.simple_dropdown_item_1line,vetParcelas
        );

        spQuantidadeParcelas.setAdapter(adapter2);


    }

    private void adicionarProduto() {
        int posicaoProduto = spSelecionaProduto.getSelectedItemPosition();
        int quantidade = Integer.valueOf(String.valueOf(edQuantidade.getText()));
        double valorProduto = Double.valueOf(String.valueOf(edValorProdutoPedido.getText()));


         if (posicaoProduto <= 0) {
            tvErroProduto.setVisibility(View.VISIBLE);
        }


        if (posicaoProduto> 0 && quantidade > 0 ){

            Produto produtoSelecionado = listaProdutos.get(posicaoProduto -1);
            Produto produtoInserido = new Produto();

            produtoInserido.setQuantidade(quantidade);
            produtoInserido.setDescricao(produtoSelecionado.getDescricao());
            produtoInserido.setValorProduto(valorProduto);

            valorFinal = produtoInserido.getValorProduto() * produtoInserido.getQuantidade();
            valorTotal += valorFinal;

            pedido.setListaProdutos(produtoInserido);
            pedido.setValorTotal(valorTotal);
            atualizarTotal();
            atualizarPedido(pedido);
        }
    }

    private void salvarPedido(){
        if (pedido.getCliente() == null) {
            tvErroCliente.setVisibility(View.VISIBLE);
        }

        Controller.getInstance().salvarPedido(pedido);
        Toast.makeText(CadastroPedidoActivity.this,"Pedido: "+ pedido.getCodigo().toString()+" cadastrado com sucesso!",Toast.LENGTH_LONG).show();

        finish();
    }

    private void atualizaClientePedido() {
        int posicaoCliente = spSelecionaCliente.getSelectedItemPosition();
        if (posicaoCliente > 0) {
            Cliente clienteSelecionado = listaClientes.get(posicaoCliente - 1);
            pedido.setCliente(clienteSelecionado);
        } else {
            pedido.setCliente(null);
        }
    }

    private void atualizarTotal() {
        int quantidadeProdutosTotal;
        quantidadeProdutosTotal = 0;

        for (Produto prod : pedido.getListaProdutos()) {
            quantidadeProdutosTotal += prod.getQuantidade();
        }

        tvTotalItensPedido.setText(Integer.toString(quantidadeProdutosTotal));
        tvTotalPedidoItem.setText("R$ " + Double.toString(valorTotal));
        tvParcelas.setText("Parcelas: " + Integer.toString(pedido.getQuantidadeParcelas()));

        tvTotalCondicao.setText("Valor total: R$ " + Double.toString(pedido.getValorTotal()));
        atualizaParcelas();
        tvTotalFinal.setText("Valor final: R$ " + Double.toString(pedido.getValorTotalComAjuste(5.0)));
    }

    private void atualizaParcelas() {
        double totalParcela = (pedido.getValorTotalComAjuste(5.0) / pedido.getQuantidadeParcelas());
        String parcelasText = "";
        for (int i = 0; i < pedido.getQuantidadeParcelas(); i++) {
            parcelasText += "Parcela " + Integer.toString(i + 1) + ": " + Double.toString(totalParcela) + "\n";
        }
        tvParcelasValores.setText(parcelasText);
    }

    private void atualizarPedido(Pedido pedido) {

        //ArrayList<Pedido> lista = Controller.getInstance().retornaPedidos();
        String texto = "";
        //for (Pedido ped : lista){

            Cliente cliente1 = pedido.getCliente();
            texto += "Cliente: " + cliente1.getNome() + "- C.P.F.: " + cliente1.getCpf() + "\n";

            for (Produto prod : pedido.getListaProdutos()) {
                texto += "Produto: " + prod.getDescricao() +
                        "- Código: " + prod.getCodigo() +
                        " - Valor: " + prod.getValorProduto() +
                        "\n";
            }

       // }
        tvPedido.setText(texto);
    }

    private void voltarMenu() {
        finish();
    }


}