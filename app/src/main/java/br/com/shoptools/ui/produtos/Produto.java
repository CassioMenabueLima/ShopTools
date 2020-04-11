package br.com.shoptools.ui.produtos;
/*
 * Alexandre Florentino kanemaki@kanemaki.eti.br
 * 30/05/2019
 *
 * */
public class Produto {

    public String codigo;
    public String descricao;
    public String preco;
    public String precoTotal;
    public String quantidade;

    public Produto(String codigo, String descricao, String preco){
        this.codigo = codigo;
        this.descricao = descricao;
        this.preco = preco;
    }

    public Produto(String descricao, String quantidade, String preco, String precoTotal){
        this.descricao = descricao;
        this.quantidade = quantidade;
        this.preco = preco;
        this.precoTotal = precoTotal;
    }

}
