package pt.ua.ibank.DTO;

import java.sql.Timestamp;

public class Cartao {

    public String numCartao;
    public Timestamp dataValidade;
    public String estado;
    public int cliente;

    public Cartao(String numCartao, Timestamp dataValidade, String estado,
            int cliente) {
        this.numCartao = numCartao;
        this.dataValidade = dataValidade;
        this.estado = estado;
        this.cliente = cliente;
    }

    public Cartao(String numCartao, Timestamp dataValidade,
            int cliente) {
        this.numCartao = numCartao;
        this.dataValidade = dataValidade;
        this.cliente = cliente;
    }

    public Cartao(String numCartao, Timestamp dataValidade, String estado) {
        this.numCartao = numCartao;
        this.dataValidade = dataValidade;
        this.estado = estado;
    }
}
