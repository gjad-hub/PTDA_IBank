/**
 * Objeto de uma class que representa uma Referencia gerada por um Cliente
 * Author: PTDA_Staff.
 * Ultima data de modificação: 21 de Dezembro, 2024
 */
package pt.ua.ibank.DTO;

public class PagamentoServicosCompras {

    public int referencia;
    public int entidade;
    public double valor;
    public boolean pago;
    public int cliente;
    public int cliente_cria;
    public boolean cancelada;

    /**
     * Constructor: Instancia com referencia,entidade,valor,
     * pago,cliente_referente,cliente_cria,cancelada
     *
     *
     * @param referencia   numero unico de referencia
     * @param entidade     entidade que cria referencia
     * @param valor        valor a ser pago
     * @param pago         valor binario que significa se o pagamento foi feito
     * @param cliente      cliente que vai pagar / pagou a referencia
     * @param cliente_cria cliente que cria referencia
     * @param cancelada    valor binario que significa se foi cancelado
     */
    public PagamentoServicosCompras(int referencia, int entidade, double valor,
                                    boolean pago, int cliente, int cliente_cria,
                                    boolean cancelada) {
        this.referencia = referencia;
        this.entidade = entidade;
        this.valor = valor;
        this.pago = pago;
        this.cliente = cliente;
        this.cliente_cria = cliente_cria;
        this.cancelada = cancelada;
    }
}
