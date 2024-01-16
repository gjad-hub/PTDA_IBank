/**
 * Objeto de uma class que representa um Deposito na Base de dados
 * Author: PTDA_Staff.
 * Ultima Data de Modificação: 21 de Dezembro, 2023
 */
package pt.ua.ibank.DTO;

public class Deposito {

    public int idDeposito;
    public double valor;
    public boolean aprovado;
    public int numFun;
    public int numCli;

    //verifica se um funcionário interagiu com ele
    public boolean pendenteAprovacao;

    /**
     * Constructor: uma instancia com id,valor,aprovado, numero de funcionario e
     * numero de cliente.
     * Representa um deposito no seu estado final
     *
     * @param idDeposito ID do deposito
     * @param valor      Valor depositado
     * @param aprovado   Valor booleano que significa o estado de aprovação
     * @param numFun     Numero de funcionario responsavel
     * @param numCli     Numero de cliente autor
     */
    public Deposito(int idDeposito, double valor, boolean aprovado, int numFun,
                    int numCli) {
        this.pendenteAprovacao = false;
        this.idDeposito = idDeposito;
        this.valor = valor;
        this.aprovado = aprovado;
        this.numFun = numFun;
        this.numCli = numCli;
    }

    /**
     * Constructor: uma instancia com id,valor
     * Representa um deposito pendente aprovação
     *
     * @param idDeposito
     * @param valor
     */
    public Deposito(int idDeposito, double valor) {
        this.pendenteAprovacao = true;
        this.idDeposito = idDeposito;
        this.valor = valor;
    }
}
