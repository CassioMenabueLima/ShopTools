package br.com.shoptools.ui.cliente;

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

/*
 * Alexandre Florentino kanemaki@kanemaki.eti.br
 * 11/05/2019
 *
 * */
public class ClienteActivity extends AppCompatActivity {

    private Button btnAddCli;
    private ListView listaCli;
    private ClienteAdapter adapter;
    private ArrayList clilist = new ArrayList<Cliente>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_cliente);

        btnAddCli   = (Button) findViewById(R.id.addCli);
        listaCli = (ListView) findViewById(R.id.listid);

        try{
            SQLiteDatabase bancoDados = openOrCreateDatabase("pedcom", MODE_PRIVATE, null);
            Cursor cursor = bancoDados.rawQuery("SELECT codigo, loja, nome, cnpj  FROM clientes", null);

            int indiceColcodigo = cursor.getColumnIndex("codigo");
            int indiceColloja = cursor.getColumnIndex("loja");
            int indiceColnome = cursor.getColumnIndex("nome");
            int indiceColcnpj = cursor.getColumnIndex("cnpj");
            cursor.moveToFirst();

            while(cursor != null){

                clilist.add( new Cliente(cursor.getString(indiceColcodigo),cursor.getString(indiceColloja),cursor.getString(indiceColnome),cursor.getString(indiceColcnpj)));
                cursor.moveToNext();

            }
        }catch(Exception e){
            e.printStackTrace();
        }

       adapter = new ClienteAdapter(this, clilist);

        listaCli.setAdapter(adapter);
       /*
        listaCli.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int codPos = position;
                String valor = listaCli.getItemAtPosition(codPos).toString();
                Toast.makeText(getBaseContext(), valor, Toast.LENGTH_SHORT).show();
            }
        });
*/
        btnAddCli.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ClienteActivity.this, AddCliActivity.class));
                finish();
            }
        });


    }
}
