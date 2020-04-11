package br.com.shoptools.ui.cliente;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import br.com.shoptools.R;

/*
 * Alexandre Florentino kanemaki@kanemaki.eti.br
 * 30/05/2019
 *
 * */
public class ClienteAdapter extends BaseAdapter {
    Context ctx;
    List<Cliente> clientes;

    public ClienteAdapter(Context ctx, List<Cliente> clientes){
        this.ctx = ctx;
        this.clientes = clientes;
    }

    @Override
    public int getCount(){
        return clientes.size();
    }

    @Override
    public Object getItem(int position){
        return clientes.get(position);
    }

    @Override
    public long getItemId(int position){
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent){
        // 1 passo
        Cliente cliente = clientes.get(position);

        // 2 passo
        View linha = LayoutInflater.from(ctx).inflate(R.layout.item_cliente,parent ,false);

        // 3 passo
        TextView txtCodigo = (TextView) linha.findViewById(R.id.it_cli_cod);
        TextView txtLoja = (TextView) linha.findViewById(R.id.it_cli_loja);
        TextView txtNome = (TextView) linha.findViewById(R.id.it_cli_nome);
        TextView txtCnpj = (TextView) linha.findViewById(R.id.it_cli_cnpj);

        txtCodigo.setText(cliente.codigo);
        txtLoja.setText(cliente.loja);
        txtNome.setText(cliente.nome);
        txtCnpj.setText(cliente.cnpj);

        return linha;
    }

}
