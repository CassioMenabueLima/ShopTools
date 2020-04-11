package br.com.shoptools.ui.produtos;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.github.rtoshiro.util.format.SimpleMaskFormatter;
import com.github.rtoshiro.util.format.text.MaskTextWatcher;

import br.com.shoptools.R;

/*
 * Alexandre Florentino kanemaki@kanemaki.eti.br
 * 30/05/2019
 *
 * */
public class AddProdActivity extends AppCompatActivity {

    private Button btnGrvProd;
    private Button btnCncProd;
    private EditText etProdCodigo;
    private EditText etProdDescri;
    private EditText etProdValor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle(this.getResources().getString(R.string.app_CadProd));
        setContentView(R.layout.activity_add_prod);

        btnGrvProd   = (Button) findViewById(R.id.btnGrvProd);
        btnCncProd   = (Button) findViewById(R.id.btnCncProd);
        etProdCodigo = (EditText) findViewById(R.id.etProdCod);
        etProdDescri = (EditText) findViewById(R.id.etProdDesc);
        etProdValor  = (EditText) findViewById(R.id.etProdPrec);

        SimpleMaskFormatter smf = new SimpleMaskFormatter("NNNNNN");
        MaskTextWatcher mtw = new MaskTextWatcher(etProdCodigo, smf);
        etProdCodigo.addTextChangedListener(mtw);

        btnGrvProd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(etProdCodigo.getText().toString().isEmpty() || etProdDescri.getText().toString().isEmpty() || etProdValor.getText().toString().isEmpty()){
                    Toast.makeText(getBaseContext(), "Campos em branco", Toast.LENGTH_SHORT).show();
                }else{

                    try {

                        ContentValues novoProduto = new ContentValues();

                        novoProduto.put("codigo",etProdCodigo.getText().toString());
                        novoProduto.put("descricao",etProdDescri.getText().toString());
                        novoProduto.put("preco", Double.parseDouble(etProdValor.getText().toString()));

                        SQLiteDatabase bancoDados = openOrCreateDatabase("pedcom", MODE_PRIVATE, null);
                        bancoDados.insert("produtos",null,novoProduto);
                        //bancoDados.delete("clientes","codigo = ?",new String[]{"egsgdg"});

                        bancoDados.close();
                    }catch(Exception e){
                        e.printStackTrace();
                    }
                    Toast.makeText(getBaseContext(), "Gravado com sucesso", Toast.LENGTH_SHORT).show();
                    finish();

                }
            }
        });

        btnCncProd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}
