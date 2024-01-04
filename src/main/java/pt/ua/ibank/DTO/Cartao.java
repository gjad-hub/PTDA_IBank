package pt.ua.ibank.DTO;

import java.sql.Date;
import static pt.ua.ibank.DTO.Cliente.LocalClient;

public class Cartao {
    public int numCartao;
    public Date dataValidade;
    public String estado;
    public String conta;
    public boolean credito;
    public double saldo_credito;
    public Date data_venciemnto;

    public Cartao(int numCartao, Date dataValidade, String estado, String conta, boolean credito, double saldo_credito, Date data_venciemnto) {
        this.numCartao = numCartao;
        this.dataValidade = dataValidade;
        this.estado = estado;
        this.conta = conta;
        this.credito = credito;
        this.saldo_credito = saldo_credito;
        this.data_venciemnto = data_venciemnto;
    }

    @Override
    public String toString() {
        return "Núm. Cartão: " + numCartao + " Saldo: " + (credito == false ? LocalClient.saldo : saldo_credito);
    }
}
