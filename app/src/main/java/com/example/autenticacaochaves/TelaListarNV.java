package com.example.autenticacaochaves;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class TelaListarNV extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_listar_n_v);

        ListView listNV = findViewById(R.id.listNV);

        //objeto da classe BancoDeDados
        BancoDeDados bd = new BancoDeDados(this, 1);

        //Antes de colocar a lista dentro do ListView, precisamos converter para um adapter
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, bd.listarNV());

        //Depois de converter, colocamos o adapter dentro do ListView
        listNV.setAdapter(adapter);
    }
}