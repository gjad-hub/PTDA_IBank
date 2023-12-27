package pt.ua.ibank.DTO;

import java.sql.Timestamp;

public class Transacoes {
   
    private int id;
    private int num_cli;
    private String descricao;
    private int valor;
    public Timestamp data;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNum_cli() {
        return num_cli;
    }

    public void setNum_cli(int num_cli) {
        this.num_cli = num_cli;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }   

    @Override
    public String toString() {
        return "transacoes{" + "id=" + id + ", num_cli=" + num_cli + ", descricao=" + descricao + ", valor=" + valor + '}';
    }
}
