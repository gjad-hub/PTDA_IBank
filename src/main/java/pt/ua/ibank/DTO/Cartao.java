package pt.ua.ibank.DTO;

import java.sql.Date;

public class Cartao {

    public String numCartao;
    public Date dataValidade;
    public String estado;
    public int cliente;
    public boolean credito;
    public double saldo_credito;
    public Date data_venciemnto;

    public Cartao(String numCartao, Date dataValidade, String estado,
            int cliente, boolean credito, double saldo_credito,
            Date data_venciemnto) {
        this.numCartao = numCartao;
        this.dataValidade = dataValidade;
        this.estado = estado;
        this.cliente = cliente;
        this.credito = credito;
        this.saldo_credito = saldo_credito;
        this.data_venciemnto = data_venciemnto;
    }

    public Cartao(String numCartao, Date dataValidade,
            int cliente, boolean credito) {
        this.numCartao = numCartao;
        this.dataValidade = dataValidade;
        this.credito = credito;
        this.cliente = cliente;
    }

    public Cartao(String numCartao, Date dataValidade,
            double saldo, String estado) {
        this.numCartao = numCartao;
        this.dataValidade = dataValidade;
        this.estado = estado;
        this.saldo_credito = saldo;
    }
}
