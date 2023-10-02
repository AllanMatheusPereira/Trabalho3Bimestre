package com.example.lojaconveniencia;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lojaconveniencia.modelo.Cliente;

public class CadastroClienteActivity extends AppCompatActivity {

    private EditText edNome;
    private EditText edCPF;
    private Button btSalvarCliente;
    private TextView tvMostrarCliente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_cliente);

        edNome = findViewById(R.id.edNome);
        edCPF = findViewById(R.id.edCPF);
        btSalvarCliente = findViewById(R.id.btSalvarCliente);
        tvMostrarCliente = findViewById(R.id.tvMostrarCliente);

        atualizarListaClientes();

        btSalvarCliente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                salvarCliente();
            }
        });
    }

    private void salvarCliente(){
        if (edNome.getText().toString().isEmpty()){
            edNome.setError("Informe o nome do Cliente!");
            return;
        }
        if (edCPF.getText().toString().isEmpty()) {
            edCPF.setError("Informe o C.P.F. do Cliente!");
            return;
        }

        Cliente cliente = new Cliente();
        cliente.setNome(edNome.getText().toString());
        cliente.setCpf(edCPF.getText().toString());

        Controller.getInstance().salvarCliente(cliente);

        Toast.makeText(CadastroClienteActivity.this,"Cliente cadastrado com sucesso!",Toast.LENGTH_LONG).show();

        finish();
    }
    private void atualizarListaClientes(){
        String texto = "";
        for (Cliente cliente : Controller.getInstance().retornaClientes()){
            texto += "Nome: "+cliente.getNome()+" - "+"\nC.P.F.:"+cliente.getCpf()+"\n--------------------------------\n";
        }
        tvMostrarCliente.setText(texto);
    }
}