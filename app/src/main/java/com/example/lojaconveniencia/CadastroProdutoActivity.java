package com.example.lojaconveniencia;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lojaconveniencia.modelo.Produto;

public class CadastroProdutoActivity extends AppCompatActivity {

    private EditText edCodigoProduto;
    private EditText edDescricaoProduto;
    private EditText edValorProduto;

    private Button btSalvarProduto;
    private TextView tvMostrarProduto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_produto);

        edCodigoProduto = findViewById(R.id.edCodigoProduto);
        edDescricaoProduto = findViewById(R.id.edDescricaoProduto);
        edValorProduto = findViewById(R.id.edValorProduto);
        btSalvarProduto = findViewById(R.id.btSalvarProduto);
        tvMostrarProduto = findViewById(R.id.tvMostrarProduto);

        atualizarListaProdutos();

        btSalvarProduto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                salvarProduto();
            }
        });
    }

    private void salvarProduto(){
        if (edCodigoProduto.getText().toString().isEmpty()){
            edCodigoProduto.setError("Informe o código do produto!");
            return;
        }
        if (edDescricaoProduto.getText().toString().isEmpty()){
            edDescricaoProduto.setError("Informe a descrição do produto!");
            return;
        }
        if (edValorProduto.getText().toString().isEmpty()){
            edValorProduto.setError("Informe o valor do produto!");
            return;
        }

        Produto produto = new Produto();
        produto.setCodigo(Integer.parseInt(edCodigoProduto.getText().toString()));
        produto.setDescricao(edDescricaoProduto.getText().toString());
        produto.setValorProduto(Double.parseDouble(edValorProduto.getText().toString()));

        Controller.getInstance().salvarProduto(produto);

        Toast.makeText(CadastroProdutoActivity.this,"Produto cadastrado com sucesso!",Toast.LENGTH_LONG).show();

        finish();
    }

    private void atualizarListaProdutos(){
        String texto = "";
        for (Produto produto: Controller.getInstance().retornaProdutos()){
            texto += "Descrição: "+produto.getDescricao()+"\nCódigo: "+produto.getCodigo()+"\nValor: "+produto.getValorProduto()+"\n--------------------------------------------------\n";
        }
        tvMostrarProduto.setText(texto);
    }
}