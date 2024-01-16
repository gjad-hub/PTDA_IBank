/**
 * Objeto de uma class que representa um Cartao de um cliente na base de dados
 * Author: PTDA_Staff.
 * Ultima data de modificação: 11 de Janeiro, 2024
 */
package pt.ua.ibank.DTO;

import java.sql.Timestamp;

public class Cartao {

    public String numCartao;
    public Timestamp dataValidade;
    public String estado;
    public int cliente;

    /**
     * Constructor: uma instancia com numero,data de validade, estado e cliente
     *
     * @param numCartao    Numero de referencia do cartão
     * @param dataValidade Data de validade do cartão
     * @param estado       Estado de cartão ativo
     * @param cliente      Numero de cliente dono do cartão
     */
    public Cartao(String numCartao, Timestamp dataValidade, String estado,
                  int cliente) {
        this.numCartao = numCartao;
        this.dataValidade = dataValidade;
        this.estado = estado;
        this.cliente = cliente;
    }

    /**
     * Constructor: uma instancia com numero,data de validade e cliente
     *
     * @param numCartao    Numero de referencia do cartão
     * @param dataValidade Data de validade do cartão
     * @param cliente      Numero de cliente dono do cartão
     */
    public Cartao(String numCartao, Timestamp dataValidade,
                  int cliente) {
        this.numCartao = numCartao;
        this.dataValidade = dataValidade;
        this.cliente = cliente;
    }

    /**
     * Constructor: uma instancia com numero,data de validade e estado
     *
     * @param numCartao    Numero de referencia do cartão
     * @param dataValidade Data de validade do cartão
     * @param estado       Estado de cartão ativo
     */
    public Cartao(String numCartao, Timestamp dataValidade, String estado) {
        this.numCartao = numCartao;
        this.dataValidade = dataValidade;
        this.estado = estado;
    }

    public boolean isValid() {
        return dataValidade.after(new Timestamp(System.currentTimeMillis()));
    }
}
