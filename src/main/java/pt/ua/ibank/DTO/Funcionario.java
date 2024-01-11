package pt.ua.ibank.DTO;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import pt.ua.ibank.DAO.FuncionarioDAO;
import pt.ua.ibank.utilities.Hash;

public class Funcionario extends Pessoa {

    private int numFun;
    private int gerente;

    public static Funcionario LocalFuncionario;
    
    /*
     * Construtor se for funcionário comum
     */
    public Funcionario(Integer numFun, String nome, String morada, String email,
            String telemovel, String nif, String password) {
        this.numFun = numFun;
        this.nome = nome;
        this.morada = morada;
        this.email = email;
        this.telemovel = telemovel;
        this.nif = nif;
        this.password = password;
    }

    /*
     * Construtor se for gerente
     */
    public Funcionario(Integer numFun, String nome, String morada, String email,
            String telemovel, String nif, String password, Integer numGerente) {
        this.numFun = numFun;
        this.nome = nome;
        this.morada = morada;
        this.email = email;
        this.telemovel = telemovel;
        this.nif = nif;
        this.password = password;
        this.gerente = numGerente;
    }

    /*
     * Construtor para fazer login
     */
    public Funcionario(String email, String password) {
        this.email = email;
        this.password = password;
    }

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

    public int alterarInformacoes(String old_email) {
        int status = FuncionarioDAO.UpdateFuncionario(nome, morada, email, telemovel, nif, old_email);
        return status;
    }

    public boolean autenticar() {
        Funcionario tmp = FuncionarioDAO.getFuncionarioByEmail(email);

        if (tmp != null) {
            try {
                if (Hash.validatePassword(password, tmp.password)) {
                    LocalFuncionario = tmp;
                    return true;
                }
            } catch (NoSuchAlgorithmException | InvalidKeySpecException ex) {
                ex.printStackTrace();
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return "Funcionario{" + "numFun=" + numFun + ", gerente=" + gerente + '}';
    }
}
