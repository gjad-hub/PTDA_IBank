package pt.ua.ibank.DTO;

public class deposito {
    private int idDeposito;
    private double valor;
    private String estado;
    private int numFun;
    private int numCli;

    public int getIdDeposito() {
        return idDeposito;
    }

    public void setIdDeposito(int idDeposito) {
        this.idDeposito = idDeposito;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public int getNumFun() {
        return numFun;
    }

    public void setNumFun(int numFun) {
        this.numFun = numFun;
    }

    public int getNumCli() {
        return numCli;
    }

    public void setNumCli(int numCli) {
        this.numCli = numCli;
    }
}
