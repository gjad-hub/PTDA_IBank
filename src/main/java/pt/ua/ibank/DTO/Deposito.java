package pt.ua.ibank.DTO;

public class Deposito {

    public int idDeposito;
    public double valor;
    public boolean aprovado;
    public int numFun;
    public int numCli;

    //verifica se um funcionário interagiu com ele
    public boolean pendenteAprovacao;

    public Deposito(int idDeposito, double valor, boolean aprovado, int numFun,
            int numCli) {
        this.pendenteAprovacao = false;
        this.idDeposito = idDeposito;
        this.valor = valor;
        this.aprovado = aprovado;
        this.numFun = numFun;
        this.numCli = numCli;
    }

    public Deposito(int idDeposito, double valor, boolean aprovado) {
        this.pendenteAprovacao = false;
        this.idDeposito = idDeposito;
        this.valor = valor;
        this.aprovado = aprovado;
    }

    public Deposito(int idDeposito, double valor) {
        this.pendenteAprovacao = true;
        this.idDeposito = idDeposito;
        this.valor = valor;
    }
}
