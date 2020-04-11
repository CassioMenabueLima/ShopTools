package br.com.shoptools.ui.produtos;

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
public class ProdutoAdapter extends BaseAdapter {
    Context ctx;
    List<Produto> produtos;

    public ProdutoAdapter(Context ctx, List<Produto> produtos){
        this.ctx = ctx;
        this.produtos = produtos;
    }

    @Override
    public int getCount(){
        return produtos.size();
    }

    @Override
    public Object getItem(int position){
        return produtos.get(position);
    }

    @Override
    public long getItemId(int position){
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent){
        // 1 passo
        Produto produto = produtos.get(position);

        // 2 passo
        View linha = LayoutInflater.from(ctx).inflate(R.layout.item_produto,parent ,false);

        // 3 passo
        TextView txtCodigo = (TextView) linha.findViewById(R.id.it_prod_cod);
        TextView txtDescri = (TextView) linha.findViewById(R.id.it_prod_desc);
        TextView txtPreco = (TextView) linha.findViewById(R.id.it_prod_preco);

        txtCodigo.setText(produto.codigo);
        txtDescri.setText(produto.descricao);
        txtPreco.setText(produto.preco);

        return linha;
    }
}
