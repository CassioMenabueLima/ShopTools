package br.com.shoptools.ui.pedido;
/*
 * Alexandre Florentino kanemaki@kanemaki.eti.br
 * 30/05/2019
 *
 * */
public class Pedido {
    public String id;
    public String codcli;
    public String lojacli;
    public String pedext;
    public String data;

    public Pedido(String id, String codcli, String lojacli, String pedext, String data){
        this.id = id;
        this.codcli = codcli;
        this.lojacli = lojacli;
        this.lojacli = pedext;
        this.lojacli = data;
    }

}
