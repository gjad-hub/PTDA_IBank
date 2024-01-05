package pt.ua.ibank.DTO;

import java.sql.Date;
import static pt.ua.ibank.DAO.CartaoDAO.getCardByNumber;
import static pt.ua.ibank.DTO.Cliente.LocalClient;

public class Cartao {

    public String numCartao;
    public Date dataValidade;
    public String estado;
    public int cliente;
    public boolean credito;
    public double saldo_credito;
    public Date data_venciemnto;

    public static Cartao LocalClientCard = getCardByNumber(LocalClient.cartaoDefault);

    public Cartao(String numCartao, Date dataValidade, String estado, int cliente, boolean credito, double saldo_credito, Date data_venciemnto) {
        this.numCartao = numCartao;
        this.dataValidade = dataValidade;
        this.estado = estado;
        this.cliente = cliente;
        this.credito = credito;
        this.saldo_credito = saldo_credito;
        this.data_venciemnto = data_venciemnto;
    }


    public Cartao(String numCartao, Date dataValidade, int cliente, boolean credito) {
        this.numCartao = numCartao;
        this.dataValidade = dataValidade;
        this.cliente = cliente;
        this.credito = credito;
    }

//    @Override
//    public String toString() {
//        return "Núm. Cartão: " + numCartao + " Saldo: " + (credito == false ? LocalClient.saldo : saldo_credito);
//    }

    @Override
    public String toString() {
        return "Cartao{" + "numCartao=" + numCartao + ", dataValidade=" + dataValidade + ", estado=" + estado + ", cliente=" + cliente + ", credito=" + credito + ", saldo_credito=" + saldo_credito + ", data_venciemnto=" + data_venciemnto + '}';
    }
}
