package br.com.shoptools.ui.produtos;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

import br.com.shoptools.R;
import br.com.shoptools.ui.cliente.Cliente;

/*
 * Alexandre Florentino kanemaki@kanemaki.eti.br
 * 11/05/2019
 *
 * */
public class ProdutoActivity extends AppCompatActivity {

    private Button btnAddProd;
    private ListView listaProd;
    private ProdutoAdapter adapter;
    private ArrayList prodlist = new ArrayList<Cliente>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_produto);

        btnAddProd = (Button) findViewById(R.id.addProd);
        listaProd = (ListView) findViewById(R.id.listProd);

        try{
            SQLiteDatabase bancoDados = openOrCreateDatabase("pedcom", MODE_PRIVATE, null);
            Cursor cursor = bancoDados.rawQuery("SELECT codigo, descricao, preco  FROM produtos", null);

            int indiceColcodigo = cursor.getColumnIndex("codigo");
            int indiceColDesc = cursor.getColumnIndex("descricao");
            int indiceColpreco = cursor.getColumnIndex("preco");

            cursor.moveToFirst();

            while(cursor != null){

                prodlist.add( new Produto(cursor.getString(indiceColcodigo),cursor.getString(indiceColDesc),cursor.getString(indiceColpreco)));
                cursor.moveToNext();

            }
        }catch(Exception e){
            e.printStackTrace();
        }

        adapter = new ProdutoAdapter(this, prodlist);

        listaProd.setAdapter(adapter);

        btnAddProd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ProdutoActivity.this, AddProdActivity.class));
                finish();
            }
        });
    }
}
