package com.example.autenticacaochaves;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class TelaMostrar extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_listar);

        ListView listChaves = findViewById(R.id.listChaves);

        BancoDeDados bd = new BancoDeDados(this, 1);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, bd.listarChaves());

        //Colocar na listview
        listChaves.setAdapter(adapter);
    }
}