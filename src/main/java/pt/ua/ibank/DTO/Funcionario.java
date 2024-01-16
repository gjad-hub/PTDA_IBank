package pt.ua.ibank.DTO;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.Date;
import pt.ua.ibank.DAO.FuncionarioDAO;
import static pt.ua.ibank.utilities.Configs.LocalFuncionario;
import pt.ua.ibank.utilities.Hash;

/**
 * Objeto de uma class que representa um Funcionario na base de dados
 * Author: PTDA_Staff.
 * Ultima data de modificação: 16 de Janeiro, 2024
 */
public class Funcionario extends Pessoa {

    /**
     * Numero de Funcionario Externo
     */
    public int numFun;

    /**
     * Numero de Gerente Responsavel pelo Funcionario Externo
     */
    public int gerente;

    /**
     * Verifica se o cliente tem uma conta valida
     */
    public Boolean foiDespedido;

    /**
     * Constructor: uma instancia com id,valor e atores presentes na transação
     * juntamente com uma mensagem
     *
     * @param numFun       Numero de Funcionario Abstrato
     * @param numGerente   Reponsavel por Funcionario Abstrato
     * @param nome         Nome de Funcionario Abstrato
     * @param morada       Morada de Funcionario Abstrato
     * @param telemovel    Telemovel de Funcionario Abstrato
     * @param email        Email de Funcionario Abstrato
     * @param nif          NIF de Funcionario Abstrato
     * @param foiDespedido Variavel Binaria que significa se ainda está presente
     * @param password     Password usada pelo cliente codificada
     */
    public Funcionario(Integer numFun, String nome, String morada, String email,
                       String telemovel, String nif, String password,
                       Integer numGerente, boolean foiDespedido) {
        super(nome, morada, email, telemovel, nif, password);
        this.numFun = numFun;
        this.gerente = numGerente;
        this.foiDespedido = foiDespedido;
    }

    /**
     * Constructor: Uma instancia de Class construtor a ser usada para alterar
     * informações relacionadas com uma conta na base de dados
     *
     * @param numFun       Numero de Funcionario Abstrato
     * @param numGerente   Reponsavel por Funcionario Abstrato
     * @param nome         Nome de Funcionario Abstrato
     * @param morada       Morada de Funcionario Abstrato
     * @param telemovel    Telemovel de Funcionario Abstrato
     * @param email        Email de Funcionario Abstrato
     * @param nif          NIF de Funcionario Abstrato
     * @param foiDespedido Variavel Binaria que significa se ainda está presente
     * @param dataCriada   Data em que foi criada a conta de funcionario
     */
    public Funcionario(Integer numFun, Integer numGerente, String nome,
                       String morada, String email,
                       String telemovel, String nif,
                       boolean foiDespedido, Date dataCriada) {
        super(nome, morada, email, telemovel, nif, dataCriada);
        this.numFun = numFun;
        this.gerente = numGerente;
        this.foiDespedido = foiDespedido;
    }

    /**
     * Constructor: Construtor a ser usado no painel de Login, reduzido a fim de
     * poupar memoria
     *
     * @param email    Email de usuario
     * @param password Passsword de usuario
     */
    public Funcionario(String email, String password) {
        super(email, password);
    }

    /**
     *
     * @return retorna se o usuario local é gerente
     */
    public boolean isManager() {
        return gerente == 0;
    }

    /**
     *
     * @param old_email
     * @return
     */
    @Override
    public int alterarInformacoes(String old_email) {
        int status = FuncionarioDAO.UpdateFuncionario(nome, morada, email,
                                                      telemovel, nif, password,
                                                      old_email);
        return status;
    }

    @Override
    public boolean autenticar() {
        Funcionario tmp = FuncionarioDAO.getFuncionarioByEmail(email);

        if (tmp != null) {
            try {
                if (Hash.validatePassword(password, tmp.password)) {
                    LocalFuncionario = tmp;
                    return true;
                }
            } catch (NoSuchAlgorithmException | InvalidKeySpecException ex) {
                ex.printStackTrace(System.out);
            }
        }
        return false;
    }

    /**
     *
     * @return
     */
    @Override
    public String toString() {
        return "Funcionario{" + "numFun=" + numFun + ", gerente=" + gerente + '}';
    }
}
