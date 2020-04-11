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
public class ItemProdutoAdapter extends BaseAdapter {
    Context ctx;
    List<Produto> produtos;

    public ItemProdutoAdapter(Context ctx, List<Produto> produtos){
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
        View linha = LayoutInflater.from(ctx).inflate(R.layout.activity_item_pedido,parent ,false);

        // 3 passo
        TextView txtDescri = (TextView) linha.findViewById(R.id.it_produto);
        TextView txtQuantidade = (TextView) linha.findViewById(R.id.it_qtd);
        TextView txtPreco = (TextView) linha.findViewById(R.id.it_vunit);
        TextView txtPrecoTotal = (TextView) linha.findViewById(R.id.it_v_tot);

        txtDescri.setText(produto.descricao);
        txtQuantidade.setText(produto.quantidade);
        txtPreco.setText(produto.preco);
        txtPrecoTotal.setText(produto.precoTotal);

        return linha;
    }
}