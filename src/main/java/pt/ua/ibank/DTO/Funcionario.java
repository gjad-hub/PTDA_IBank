package pt.ua.ibank.DTO;

public class Funcionario extends Pessoa{

    private int numFun;
    private int gerente;

    public int getNumFun() {
        return numFun;
    }

    public void setNumFun(int numFun) {
        this.numFun = numFun;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getMorada() {
        return morada;
    }

    public void setMorada(String morada) {
        this.morada = morada;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelemovel() {
        return telemovel;
    }

    public void setTelemovel(String telemovel) {
        this.telemovel = telemovel;
    }

    public String getNif() {
        return nif;
    }

    public void setNif(String nif) {
        this.nif = nif;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getGerente() {
        return gerente;
    }

    public void setGerente(int gerente) {
        this.gerente = gerente;
    }
}
