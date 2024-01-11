package pt.ua.ibank.DTO;

import java.sql.Date;

public class Cartao {

    public String numCartao;
    public Date dataValidade;
    public String estado;
    public int cliente;

    public Cartao(String numCartao, Date dataValidade, String estado,
            int cliente) {
        this.numCartao = numCartao;
        this.dataValidade = dataValidade;
        this.estado = estado;
        this.cliente = cliente;
    }

    public Cartao(String numCartao, Date dataValidade,
            int cliente) {
        this.numCartao = numCartao;
        this.dataValidade = dataValidade;
        this.cliente = cliente;
    }

    public Cartao(String numCartao, Date dataValidade, String estado) {
        this.numCartao = numCartao;
        this.dataValidade = dataValidade;
        this.estado = estado;
    }
}
