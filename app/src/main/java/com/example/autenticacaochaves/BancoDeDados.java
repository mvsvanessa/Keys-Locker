package com.example.autenticacaochaves;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class BancoDeDados extends SQLiteOpenHelper {

    public String mostrarAutenticadas;

    private final String inserirDados = "INSERT INTO chaves VALUES" +
            "(1, 'ABA9173', 'aus1729a8dans79', 0), " +
            "(2,'HGNAO91', '98dad78ha0ja09a', 0), " +
            "(3, 'IGKM9102', '7dh198d10jd1-ok1', 1), " +
            "(4, 'AUJGM093', '719idh9h1n127i2', 0), " +
            "(5, 'UGIEM43P', '623n23871213h1', 0), " +
            "(6, 'NGUTO94', 'nj7d8198d1j0910', 0)";


    public BancoDeDados(@Nullable Context context, int version) {
        super(context, "ativ1", null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String criarTabela = "CREATE TABLE chaves ( "
                + "_id INT PRIMARY KEY, "
                + "chave VARCHAR(8), "
                + "autenticacao VARCHAR(20), "
                + "status INT)";
        sqLiteDatabase.execSQL(criarTabela);
        sqLiteDatabase.execSQL(inserirDados);
    }

    public List<String> listarChaves(){
        //Lista que ira salvar as informaçoes e apresentalas no fim
        List<String> listaCompleta = new ArrayList<String>();

        SQLiteDatabase banco = getWritableDatabase();

        Cursor c = banco.rawQuery("SELECT * FROM chaves;", new String[]{ });

        if(c.moveToFirst()){

            do{
                //Criação de um String com todos os valores de cada linha (ID, usuário, senha)
                String valores = "ID: " + c.getString(0) + " Chave: " + c.getString(1) + " Autenticação: " + c.getString(2) + " Status: " + c.getString(3);

                //Adicionar cada linha dentro da lista
                listaCompleta.add(valores);

            }while(c.moveToNext());
        }
        banco.close();

        return listaCompleta;
    }

    public List<String> listarNV(){
        List<String> listaNV = new ArrayList<String>();

        SQLiteDatabase banco = getWritableDatabase();

        Cursor c = banco.rawQuery("SELECT * FROM chaves WHERE status = 0;", new String[]{ });

        if(c.moveToFirst()){

            do{
                //Criação de uma String com as informaçoes da tabela com a seguinte sequencia (ID, usuário, senha)
                String valores = "ID: " + c.getString(0) + " Chave: " + c.getString(1) + " Autenticação: " + c.getString(2) + " Status: " + c.getString(3);

                //Adicionando dentro da lista
                listaNV.add(valores);

            }while(c.moveToNext()); //Repetir os comandos acima enquanto houverem linhas no resultado do SELECT
        }
        banco.close();

        return listaNV;

    }

    public boolean validarChave(String chave) {
        SQLiteDatabase banco = getWritableDatabase();

        Cursor c = banco.rawQuery("SELECT chave, autenticacao FROM chaves WHERE chave = ?", new String[]{chave});

        if (c.moveToFirst()) {
            //Se conseguiu encontrar salve os dados das colunas
            String chaveBanco = c.getString(0);
            String autenticacaoChave = c.getString(1);


            //Se a chave for a mesma que a digitada, salve a autenticação para exibir no Toast
            if (chaveBanco.equals(chave)) {
                //Alterando o valorda chave de 'desligada'(0) para 'ligada'(1)
                banco.execSQL("UPDATE chaves SET status = 1 WHERE chave = ?", new String[]{chave});
                //Guarda a autenticação da chave para depois exibila
                mostrarAutenticadas = autenticacaoChave;
                banco.close();
                return true;


            }else{
                //Chave que ja esta autenticada
                banco.close();
                return false;
            }
        }else{
            //Chave não encontrada
            banco.close();
            return false;
        }

    }










    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void listarTodas() {
    }
}
