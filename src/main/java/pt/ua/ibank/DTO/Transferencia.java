package pt.ua.ibank.DTO;

/**
 * Objeto de uma class que representa uma transação feita Author: PTDA_Staff.
 * Ultima data de modificação: 4 de Janeiro, 2024
 */
public class Transferencia {

    private final int transferenciaID;
    private final double valorEmEuros;
    private final int clienteRealizaID;
    private final int clienteRecebeID;
    private final String descricao;

    /**
     * Constructor: uma instancia com id,valor e atores presentes na transação
     * juntamente com uma mensagem
     *
     * @param transferenciaID Id Unico da transferencia
     * @param valorEmEuros valor flutuante em euros
     * @param clienteRealizaID Receptor da transferencia
     * @param clienteRecebeID Autor da transferencia
     * @param motivo Pequena descrição
     */
    public Transferencia(int transferenciaID, double valorEmEuros,
            int clienteRealizaID,
            int clienteRecebeID, String motivo) {
        this.transferenciaID = transferenciaID;
        this.valorEmEuros = valorEmEuros;
        this.clienteRealizaID = clienteRealizaID;
        this.clienteRecebeID = clienteRecebeID;
        this.descricao = motivo;
    }

    public int getTransferenciaID() {
        return transferenciaID;
    }

    public double getvalorEmEuros() {
        return valorEmEuros;
    }

    public int getclienteRealizaID() {
        return clienteRealizaID;
    }

    public int getclienteRecebeID() {
        return clienteRecebeID;
    }

    public String getDescricao() {
        return descricao;
    }

}
