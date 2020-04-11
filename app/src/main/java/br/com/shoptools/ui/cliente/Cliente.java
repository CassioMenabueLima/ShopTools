package br.com.shoptools.ui.cliente;
/*
 * Alexandre Florentino kanemaki@kanemaki.eti.br
 * 30/05/2019
 *
 * */
public class Cliente {

    public String codigo;
    public String loja;
    public String nome;
    public String cnpj;

    public Cliente(String codigo, String loja, String nome, String cnpj){
        this.codigo = codigo;
        this.loja = loja;
        this.nome = nome;
        this.cnpj = cnpj;
    }

}
