package br.com.shoptools.ui.pedido;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import br.com.shoptools.R;
import br.com.shoptools.ui.produtos.ItemProdutoAdapter;
import br.com.shoptools.ui.produtos.Produto;

/*
 * Alexandre Florentino kanemaki@kanemaki.eti.br
 * 30/05/2019
 *
 * */
public class AddPedActivity extends AppCompatActivity {

    private static final String ARQ_PREF = "Arquivo";

    private Spinner spCliente;
    private Spinner spProduro;
    private Button addProdPed;
    private Button addFimPed;
    private ListView listaItemProd;
    private ItemProdutoAdapter adapter;
    private ArrayList<String> listCli = new ArrayList();
    private ArrayList<String> listProd = new ArrayList();
    private ArrayList itemProd = new ArrayList<Produto>();
    private EditText mQtd;
    private TextView PedidoTotal;
    private String precoUnit = "";
    private String precoTotal = "";
    private double valTot = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle(this.getResources().getString(R.string.app_CadPed));
        setContentView(R.layout.activity_add_ped);

        spCliente = (Spinner) findViewById(R.id.spinner_cli);
        spProduro = (Spinner) findViewById(R.id.spinner_prod);
        addProdPed = (Button) findViewById(R.id.btnAddProdPed);
        addFimPed = (Button) findViewById(R.id.btnFimPed);
        listaItemProd = (ListView) findViewById(R.id.lv_Item_Prod);
        PedidoTotal = (TextView) findViewById(R.id.txtIdTotal);

        // Carrega spinner cliente
        try{
            SQLiteDatabase bancoDados = openOrCreateDatabase("pedcom", MODE_PRIVATE, null);
            Cursor cursorcli = bancoDados.rawQuery("SELECT codigo, nome FROM clientes", null);

            int indiceColcodigo = cursorcli.getColumnIndex("codigo");
            int indiceColNome = cursorcli.getColumnIndex("nome");

            cursorcli.moveToFirst();

            while(cursorcli != null){

                listCli.add( cursorcli.getString(indiceColcodigo) + "  " + cursorcli.getString(indiceColNome));
                cursorcli.moveToNext();

            }
        }catch(Exception e){
            e.printStackTrace();
        }

        // carrega spinner produto
        try{
            SQLiteDatabase bancoDados = openOrCreateDatabase("pedcom", MODE_PRIVATE, null);
            Cursor cursorProd = bancoDados.rawQuery("SELECT codigo, descricao, preco  FROM produtos", null);

            int indiceColcodigo = cursorProd.getColumnIndex("codigo");
            int indiceColDesc = cursorProd.getColumnIndex("descricao");

            cursorProd.moveToFirst();

            while(cursorProd != null){

                listProd.add( cursorProd.getString(indiceColcodigo) + "  " + cursorProd.getString(indiceColDesc));
                cursorProd.moveToNext();

            }
        }catch(Exception e){
            e.printStackTrace();
        }

        ArrayAdapter<String> adapterCli = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, listCli);
        adapterCli.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spCliente.setAdapter(adapterCli);

        ArrayAdapter<String> adapterProd = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, listProd);
        adapterProd.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spProduro.setAdapter(adapterProd);

        addProdPed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(AddPedActivity.this);
                View mView = getLayoutInflater().inflate(R.layout.item_pedido,null);
                mQtd = (EditText) mView.findViewById(R.id.et_Qtd);

                mBuilder.setCancelable(false);

                mBuilder.setNegativeButton("Cancelar",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });

                mBuilder.setPositiveButton("Gravar",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                String infProduto = (String) spProduro.getSelectedItem();
                                String qtd =  mQtd.getText().toString();
                                calcPreco(infProduto.substring(0,6), qtd);

                                if(!qtd.isEmpty()) {
                                    itemProd.add(new Produto(infProduto, qtd, precoUnit, precoTotal));
                                }else{
                                    Toast.makeText(getBaseContext(), "Impossivel adicionar produto sem quantidade.!"  , Toast.LENGTH_SHORT).show();
                                }
                            }
                        });

                mBuilder.setView(mView);
                AlertDialog dialog = mBuilder.create();
                dialog.show();
            }
        });

            adapter = new ItemProdutoAdapter(this, itemProd);

            listaItemProd.setAdapter(adapter);

        listaItemProd.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                itemProd.remove(position);
                adapter.notifyDataSetChanged();

                Toast.makeText(getBaseContext(), "Item removido!", Toast.LENGTH_SHORT).show();

            }
        });

        addFimPed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Produto pAux;
                SharedPreferences.Editor editor = getSharedPreferences(ARQ_PREF, MODE_PRIVATE).edit();
                SharedPreferences prefs = getSharedPreferences(ARQ_PREF, MODE_PRIVATE);
                String Numaux  =  prefs.getString("cod", "0");
                String auxCOd  = Numaux;

                SimpleDateFormat dateMask =  new SimpleDateFormat("dd/MM/yyyy");
                Date dataAtual = new Date();

                try {
                    SQLiteDatabase bancoDados = openOrCreateDatabase("pedcom", MODE_PRIVATE, null);

                    ContentValues novoPedidoCabec = new ContentValues();
                    ContentValues novoPedidoItem = new ContentValues();

                    String infCliente = (String) spCliente.getSelectedItem();
                    int numAux = Integer.parseInt(Numaux) + 1;

                    // - pecab
                    novoPedidoCabec.put("id"        ,numAux);
                    novoPedidoCabec.put("codcli"    ,infCliente.substring(0,6));
                    novoPedidoCabec.put("lojacli"   ,"");
                    novoPedidoCabec.put("pedext"    ,"");
                    novoPedidoCabec.put("data"      ,dateMask.format(dataAtual));

                    bancoDados.insert("pecab",null,novoPedidoCabec);

                    for(int i = 0; i < itemProd.size(); i++){
                        // - peitem
                        pAux = (Produto) itemProd.get(i);

                        novoPedidoItem.put("id"         ,numAux);
                        novoPedidoItem.put("codpro"     ,pAux.descricao.substring(0,6));
                        novoPedidoItem.put("item"       ,i);
                        novoPedidoItem.put("qtd"        ,pAux.quantidade.toString());
                        novoPedidoItem.put("preco"      ,pAux.preco);

                      //  bancoDados.insert("peitem",null,novoPedidoItem);
                        editor.putString("cod", String.valueOf(numAux));
                        editor.apply();
                        Toast.makeText(getBaseContext(), "Pedido gravado com sucesso", Toast.LENGTH_SHORT).show();
                    }

                    bancoDados.close();
                    //pecab( id varchar , codcli varchar, lojacli varchar, pedext varchar, data varchar, PRIMARY KEY (id, pedext) )
                    //peitem( id varchar, codpro varchar,item int ,qtd int ,preco double , PRIMARY KEY (id, item) )
                    //bancoDados.delete("clientes","codigo = ?",new String[]{"egsgdg"});
                }catch(Exception e){
                    e.printStackTrace();
                    editor.putString("cod", auxCOd);
                    editor.apply();
                }

                finish();
            }
        });
    }

    private void calcPreco(String codigov, String qtdv){
        double preço = 0;
        double precoTot = 0;
        int quanti = 0;

        String aux = codigov.trim();

        try{
            SQLiteDatabase bancoDados = openOrCreateDatabase("pedcom", MODE_PRIVATE, null);
            Cursor cursorProd = bancoDados.rawQuery("SELECT  preco  FROM produtos WHERE codigo = ?", new String[]{ aux });

            int indiceColpreco = cursorProd.getColumnIndex("preco");

            cursorProd.moveToFirst();

            preço = Double.parseDouble(cursorProd.getString(indiceColpreco));
            quanti = Integer.parseInt(qtdv);
            precoTot = preço * quanti;
            valTot += precoTot;

            precoUnit = String.valueOf(preço) ;
            precoTotal = String.valueOf(precoTot);

            PedidoTotal.setText(String.valueOf(valTot));

        }catch(Exception e){
            e.printStackTrace();
        }

    }
}