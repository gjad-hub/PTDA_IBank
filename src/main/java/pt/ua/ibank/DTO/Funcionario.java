package pt.ua.ibank.DTO;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import pt.ua.ibank.DAO.FuncionarioDAO;
import pt.ua.ibank.utilities.Hash;

public class Funcionario extends Pessoa {

    public int numFun;
    public int gerente;

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
                       String telemovel, String nif, String password,
                       Integer numGerente) {
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

    public int alterarInformacoes(String old_email) {
        int status = FuncionarioDAO.UpdateFuncionario(nome, morada, email,
                                                      telemovel, nif, old_email);
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
