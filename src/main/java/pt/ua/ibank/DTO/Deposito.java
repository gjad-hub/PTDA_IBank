package pt.ua.ibank.DTO;

public class Deposito {
    public int idDeposito;
    public double valor;
    public boolean aprovado;
    public int numFun;
    public int numCli;

    public Deposito(int idDeposito, double valor, boolean aprovado, int numFun, int numCli) {
        this.idDeposito = idDeposito;
        this.valor = valor;
        this.aprovado = aprovado;
        this.numFun = numFun;
        this.numCli = numCli;
    }
}
