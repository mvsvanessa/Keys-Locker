package com.example.autenticacaochaves;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
private EditText ListaChaves;
private Button btValidação, btListar, btListarNaoVerificados;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListaChaves = findViewById(R.id.txtChave);
        btValidação = findViewById(R.id.buttonValidar);
        btListar = findViewById(R.id.buttonListar);
        btListarNaoVerificados = findViewById(R.id.buttonListarNV);

        btValidação.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BancoDeDados bd = new BancoDeDados(MainActivity.this, 1);
                if(bd.validarChave(ListaChaves.getText().toString().toUpperCase())){
                    Toast.makeText(MainActivity.this, "Autenticação: " + bd.mostrarAutenticadas, Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(MainActivity.this, "Dados inválidos", Toast.LENGTH_SHORT).show();
                }


            }
        });

        btListar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(MainActivity.this, TelaMostrar.class);
                startActivity(it);
            }
        });

        btListarNaoVerificados.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(MainActivity.this, TelaNaoListados.class);
                startActivity(it);
            }
        });
    }
}