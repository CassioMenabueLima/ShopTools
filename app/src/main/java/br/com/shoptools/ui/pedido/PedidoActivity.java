package br.com.shoptools.ui.pedido;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import br.com.shoptools.R;

/*
 * Alexandre Florentino kanemaki@kanemaki.eti.br
 * 11/05/2019
 *
 * */
public class PedidoActivity extends AppCompatActivity {

    private Button btnAddPed;
    private ListView listaPed;
    private PedidoAdapter adapter;
    private List pedidos = new ArrayList<Pedido>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_pedido);

        btnAddPed   = (Button) findViewById(R.id.addPed);
        listaPed    = (ListView) findViewById(R.id.listidped);

        try{
            SQLiteDatabase bancoDados = openOrCreateDatabase("pedcom", MODE_PRIVATE, null);
            Cursor cursor = bancoDados.rawQuery("SELECT id, codcli, lojacli, pedext, data FROM pecab", null);

            int indiceColid = cursor.getColumnIndex("id");
            int indiceColcodcli = cursor.getColumnIndex("codcli");
            int indiceCollojacli = cursor.getColumnIndex("lojacli");
            int indiceColpedext = cursor.getColumnIndex("pedext");
            int indiceColdata = cursor.getColumnIndex("data");

            cursor.moveToFirst();

            while(cursor != null){
                pedidos.add(new Pedido(cursor.getString(indiceColid),cursor.getString(indiceColcodcli),cursor.getString(indiceCollojacli),cursor.getString(indiceColpedext),cursor.getString(indiceColdata)));
                cursor.moveToNext();
            }
        }catch(Exception e){
            e.printStackTrace();
        }

        adapter = new PedidoAdapter(this, pedidos);

        listaPed.setAdapter(adapter);

        /*
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                getApplicationContext(),
                android.R.layout.simple_list_item_1,
                android.R.id.text1,
                itens
        );


        listaPed.setAdapter(adapter);
        listaPed.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int codPos = position;
                String valor = listaPed.getItemAtPosition(codPos).toString();
                Toast.makeText(getBaseContext(), valor, Toast.LENGTH_SHORT).show();
            }
        });
        */

        btnAddPed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PedidoActivity.this, AddPedActivity.class));
                finish();
            }
        });


    }
}
