package br.com.shoptools.ui.pedido;

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
public class PedidoAdapter extends BaseAdapter {
    Context ctx;
    List<Pedido> pedidos;

    public PedidoAdapter(Context ctx, List<Pedido> pedidos){
        this.ctx = ctx;
        this.pedidos = pedidos;
    }

    @Override
    public int getCount(){
        return pedidos.size();
    }

    @Override
    public Object getItem(int position){
        return pedidos.get(position);
    }

    @Override
    public long getItemId(int position){
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent){
        // 1 passo
        Pedido pedido = pedidos.get(position);

        // 2 passo
        View linha = LayoutInflater.from(ctx).inflate(R.layout.pedido,parent ,false);

        // 3 passo
        TextView txtId = (TextView) linha.findViewById(R.id.it_ped_id);
        TextView txtCodCli = (TextView) linha.findViewById(R.id.it_ped_codcli);
        TextView txtLojaCli = (TextView) linha.findViewById(R.id.it_ped_lojacli);
        TextView txtPedText = (TextView) linha.findViewById(R.id.it_ped_pedtex);
        TextView txtData = (TextView) linha.findViewById(R.id.it_ped_data);

        txtId.setText(pedido.id);
        txtCodCli.setText(pedido.codcli);
        txtLojaCli.setText(pedido.lojacli);
        txtPedText.setText(pedido.pedext);
        txtData.setText(pedido.data);

        return linha;
    }
}
