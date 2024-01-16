package pt.ua.ibank.DTO;

/**
 *
 * @author ricar
 */
public class Deposito {

    /**
     * ID unico de deposito
     */
    public int idDeposito;

    /**
     * Valor Em euros do deposito
     */
    public double valor;

    /**
     * Estado de Aprovação do pedido
     */
    public boolean aprovado;

    /**
     * Numero do Funcionario Encarregue de Aprovar o pedido
     */
    public int numFun;

    /**
     * Numero de Cliente Associado ao Deposito
     */
    public int numCli;

    /**
     * Valor que verifica se o pedido está pendente ou não
     */
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
