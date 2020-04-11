package br.com.shoptools.ui.cliente;

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
public class AddCliActivity extends AppCompatActivity {

    private Button btnGrvCli;
    private Button btnCncCli;
    private EditText etCliCodigo;
    private EditText etCliLoja;
    private EditText etCliNome;
    private EditText etCliCNPJ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle(this.getResources().getString(R.string.app_CadCli));
        setContentView(R.layout.activity_add_cli);

        btnGrvCli   = (Button) findViewById(R.id.brnGrvCli);
        btnCncCli   = (Button) findViewById(R.id.btnCncCli);
        etCliCodigo = (EditText) findViewById(R.id.etCliCodigo);
        etCliLoja   = (EditText) findViewById(R.id.etCliLoja);
        etCliNome   = (EditText) findViewById(R.id.etCliNome);
        etCliCNPJ   = (EditText) findViewById(R.id.etCliCNPJ);

        // MASCARA CÓDIGO
        SimpleMaskFormatter smf1 = new SimpleMaskFormatter("NNNNNN");
        MaskTextWatcher mtw1 = new MaskTextWatcher(etCliCodigo, smf1);
        etCliCodigo.addTextChangedListener(mtw1);

        // MASCARA LOJA
        SimpleMaskFormatter smf2 = new SimpleMaskFormatter("NN");
        MaskTextWatcher mtw2 = new MaskTextWatcher(etCliLoja, smf2);
        etCliLoja.addTextChangedListener(mtw2);

        // MASCARA CNPJ
        SimpleMaskFormatter smf3 = new SimpleMaskFormatter("NN.NNN.NNN/NNNN-NN");
        MaskTextWatcher mtw3 = new MaskTextWatcher(etCliCNPJ, smf3);
        etCliCNPJ.addTextChangedListener(mtw3);

        btnGrvCli.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(etCliCodigo.getText().toString().isEmpty() || etCliLoja.getText().toString().isEmpty() || etCliNome.getText().toString().isEmpty() || etCliCNPJ.getText().toString().isEmpty()){
                    Toast.makeText(getBaseContext(), "Campos em branco", Toast.LENGTH_SHORT).show();
                }else{

                    try {

                        ContentValues novoCliente = new ContentValues();

                        novoCliente.put("codigo",etCliCodigo.getText().toString());
                        novoCliente.put("loja",etCliLoja.getText().toString());
                        novoCliente.put("nome",etCliNome.getText().toString());
                        novoCliente.put("cnpj",etCliCNPJ.getText().toString());

                        SQLiteDatabase bancoDados = openOrCreateDatabase("pedcom", MODE_PRIVATE, null);
                        bancoDados.insert("clientes",null,novoCliente);
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

        btnCncCli.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }
}
