package pt.ua.ibank.DTO;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.Date;
import pt.ua.ibank.DAO.FuncionarioDAO;
import pt.ua.ibank.utilities.Hash;
import static pt.ua.ibank.utilities.Configs.LocalFuncionario;

public class Funcionario extends Pessoa {

    public int numFun;
    public int gerente;
    public Boolean foiDespedido;
    public Date dataCriada;
    
    /*
     * Construtor se for funcionário comum
     */
    public Funcionario(Integer numFun, String nome, String morada, String email,
                       String telemovel, String nif, String password,
                       boolean foiDespedido) {
        this.numFun = numFun;
        this.nome = nome;
        this.morada = morada;
        this.email = email;
        this.telemovel = telemovel;
        this.nif = nif;
        this.password = password;
        this.foiDespedido = foiDespedido;
    }

    /*
     * Construtor se for gerente
     */
    public Funcionario(Integer numFun, String nome, String morada, String email,
                       String telemovel, String nif, String password,
                       Integer numGerente, boolean foiDespedido) {
        this.numFun = numFun;
        this.nome = nome;
        this.morada = morada;
        this.email = email;
        this.telemovel = telemovel;
        this.nif = nif;
        this.password = password;
        this.gerente = numGerente;
        this.foiDespedido = foiDespedido;
    }

    public Funcionario(Integer numFun, Integer numGerente, String nome,
                       String morada, String email,
                       String telemovel, String nif,
                       boolean foiDespedido, Date dataCriada) {
        this.numFun = numFun;
        this.nome = nome;
        this.morada = morada;
        this.email = email;
        this.telemovel = telemovel;
        this.nif = nif;
        this.gerente = numGerente;
        this.foiDespedido = foiDespedido;
        this.dataCriada = dataCriada;
    }


    /*
     * Construtor para fazer login
     */
    public Funcionario(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public boolean isManager() {
        return gerente == 0;
    }

    public int alterarInformacoes(String old_email) {
        int status = FuncionarioDAO.UpdateFuncionario(nome, morada, email,
                                                      telemovel, nif, password, old_email);
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
