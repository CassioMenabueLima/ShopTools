package br.com.shoptools.ui.configuracoes;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONException;
import org.json.JSONObject;

import br.com.shoptools.R;
import br.com.shoptools.util.OkHttpHandler;

/*
 * Alexandre Florentino kanemaki@kanemaki.eti.br
 * 11/05/2019
 *
 * */
public class ConfigActivity extends AppCompatActivity {

    private Button btnSincCli;
    private Button btnSincProd;
    private Button btnSincPed;

    private static final String TAG_WS = "UPDATE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // getSupportActionBar().hide();
        setContentView(R.layout.activity_config);

        btnSincCli = (Button) findViewById(R.id.btn_sinc_cli);
        btnSincProd = (Button) findViewById(R.id.btn_sinc_pro);
        btnSincPed = (Button) findViewById(R.id.btn_sinc_ped);

        btnSincCli.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ok_update = "T";

                OkHttpHandler handler = new OkHttpHandler();

                try{
                    SQLiteDatabase bancoDados = openOrCreateDatabase("pedcom", MODE_PRIVATE, null);
                    Cursor cursor = bancoDados.rawQuery("SELECT codigo, loja, nome, cnpj  FROM clientes", null);

                    int indiceColcodigo = cursor.getColumnIndex("codigo");
                    int indiceColloja = cursor.getColumnIndex("loja");
                    int indiceColnome = cursor.getColumnIndex("nome");
                    int indiceColcnpj = cursor.getColumnIndex("cnpj");
                    cursor.moveToFirst();

                    while(cursor != null){

                        String txtCodigo = cursor.getString(indiceColcodigo);
                        String txtLoja = cursor.getString(indiceColloja);
                        String txtNome = cursor.getString(indiceColnome);
                        String txtCnpj = cursor.getString(indiceColcnpj);

                        handler.setIotParam("cli_andr?cod=" + txtCodigo +   "&loja=" + txtLoja + "&nome=" + txtNome + "&cnpj=04271662000157");

                        String iotStringJson = "";

                        try {
                            iotStringJson = handler.execute().get();
                            if (iotStringJson != null ) {
                                try {

                                    if(iotStringJson.equals("")){
                                        Toast.makeText(getApplicationContext(),"Falha na atualização", Toast.LENGTH_LONG).show();
                                    }else{

                                        JSONObject jsonObj = new JSONObject(iotStringJson);

                                        if(ok_update.equals(jsonObj.getString(TAG_WS).toString()))
                                        {
                                            Toast.makeText(getApplicationContext(),"Estado alterado com sucesso!", Toast.LENGTH_LONG).show();
                                        }else{
                                            Toast.makeText(getApplicationContext(),"Falha ao alterar estado do sensor!", Toast.LENGTH_LONG).show();
                                        }

                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        }catch (Exception e) {

                        }

                        cursor.moveToNext();

                    }
                }catch(Exception e){
                    e.printStackTrace();
                }

            }
        });


        btnSincProd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ok_update = "T";

                OkHttpHandler handler = new OkHttpHandler();

                try{
                    SQLiteDatabase bancoDados = openOrCreateDatabase("pedcom", MODE_PRIVATE, null);
                    Cursor cursor = bancoDados.rawQuery("SELECT codigo, descricao, preco  FROM produtos", null);

                    int indiceColcodigo = cursor.getColumnIndex("codigo");
                    int indiceColDesc = cursor.getColumnIndex("descricao");
                    int indiceColpreco = cursor.getColumnIndex("preco");

                    cursor.moveToFirst();

                    while(cursor != null){

                        String txtCodigo = cursor.getString(indiceColcodigo);
                        String txtdescri = cursor.getString(indiceColDesc);
                        String txtPreco = cursor.getString(indiceColpreco);

                        handler.setIotParam("pro_andr?cod=" + txtCodigo +   "&descri=" + txtdescri + "&preco=" + txtPreco);

                        String iotStringJson = "";

                        try {
                            iotStringJson = handler.execute().get();
                            if (iotStringJson != null ) {
                                try {

                                    if(iotStringJson.equals("")){
                                        Toast.makeText(getApplicationContext(),"Falha na atualização", Toast.LENGTH_LONG).show();
                                    }else{

                                        JSONObject jsonObj = new JSONObject(iotStringJson);

                                        if(ok_update.equals(jsonObj.getString(TAG_WS).toString()))
                                        {
                                            Toast.makeText(getApplicationContext(),"Estado alterado com sucesso!", Toast.LENGTH_LONG).show();
                                        }else{
                                            Toast.makeText(getApplicationContext(),"Falha ao alterar estado do sensor!", Toast.LENGTH_LONG).show();
                                        }

                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        }catch (Exception e) {

                        }

                        cursor.moveToNext();

                    }
                }catch(Exception e){
                    e.printStackTrace();
                }

            }
        });



    }
}
