package pt.ua.ibank.DTO;

import java.sql.Timestamp;

/**
 * Objeto de uma class que representa uma transação feita
 * Author: PTDA_Staff.
 * Ultima data de modificação: 21 de Dezembro, 2023
 */
public class Transacoes {

    public int id;
    public int num_cli;
    private final int numCliente;
    public String descricao;
    public Double valor;
    public Timestamp data;

    /**
     * Constructor: uma instancia com id,valor e atores presentes na transação
     * juntamente com uma mensagem
     *
     * @param numCliente Receptor da transação
     * @param id         Id Unico da transação
     * @param valor      Valor em euros da transação
     * @param descricao  Descrição a ser mostrada ao usuario
     * @param data       Data a que foi feita a transação
     */
    public Transacoes(int id, int numCliente, String descricao, Double valor,
                      Timestamp data) {
        this.id = id;
        this.num_cli = numCliente;
        this.numCliente = numCliente;
        this.descricao = descricao;
        this.valor = valor;
        this.data = data;
    }

}
