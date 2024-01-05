package pt.ua.ibank.DTO;

import java.sql.Timestamp;

public class Transacoes {
   
    public int id;
    public int num_cli;
    public String descricao;
    public Double valor;
    public Timestamp data;

    public Transacoes(int id, int num_cli, String descricao, Double valor, Timestamp data) {
        this.id = id;
        this.num_cli = num_cli;
        this.descricao = descricao;
        this.valor = valor;
        this.data = data;
    }
}
