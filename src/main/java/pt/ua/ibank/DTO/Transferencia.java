package pt.ua.ibank.DTO;

public class Transferencia {
    private int idTransferencia;
    private double valor;
    private int clienteRealiza;
    private int clienteRecebe;

    public int getIdTransferencia() {
        return idTransferencia;
    }

    public void setIdTransferencia(int idTransferencia) {
        this.idTransferencia = idTransferencia;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public int getClienteRealiza() {
        return clienteRealiza;
    }

    public void setClienteRealiza(int clienteRealiza) {
        this.clienteRealiza = clienteRealiza;
    }

    public int getClienteRecebe() {
        return clienteRecebe;
    }

    public void setClienteRecebe(int clienteRecebe) {
        this.clienteRecebe = clienteRecebe;
    }
}
