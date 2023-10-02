package com.example.lojaconveniencia;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button btCadastrarCliente;

    private Button btCadastrarProduto;

    private Button btCadastrarPedido;

    private Button btListaPedidos;
    private Button btSairApp;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btCadastrarCliente = findViewById(R.id.btCadastrarCliente);
        btCadastrarProduto = findViewById(R.id.btCadastrarProduto);
        btCadastrarPedido = findViewById(R.id.btCadastrarPedido);
        btListaPedidos = findViewById(R.id.btListaPedidos);
        btSairApp = findViewById(R.id.btSairApp);

        btCadastrarCliente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { abrirCadastroCliente(); }
        });

        btCadastrarProduto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { abrirCadastroProduto(); }
        });

        btCadastrarPedido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { abrirCadastroPedido();  }
        });

        btListaPedidos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { abrirListaPedidos();}
        });
        btSairApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { sairAplicativo();  }
        });


    }

    private void abrirListaPedidos() {
        Intent intent = new Intent(MainActivity.this,ListaPedidos.class);
        startActivity(intent);
    }

    private void abrirCadastroCliente(){
        Intent intent = new Intent(MainActivity.this,CadastroClienteActivity.class);
        startActivity(intent);
    }
    private void abrirCadastroProduto(){
        Intent intent = new Intent(MainActivity.this,CadastroProdutoActivity.class);
        startActivity(intent);
    }
    private void abrirCadastroPedido(){
        Intent intent = new Intent(MainActivity.this,CadastroPedidoActivity.class);
        startActivity(intent);
    }
    private void sairAplicativo(){
        finish();
    }
}