package pt.ua.ibank.DTO;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import static pt.ua.ibank.DAO.CardsDAO.getCardByNumber;
import pt.ua.ibank.DAO.ClientDAO;
import static pt.ua.ibank.utilities.Configs.LocalClientCard;
import static pt.ua.ibank.utilities.Configs.LocalClient;
import pt.ua.ibank.utilities.Hash;

public class Cliente extends Pessoa {

    public Integer numCliente;
    public String numConta;
    public Double saldo;
    public Double saldo_cativo;
    public String cartaoDefault;
    public Integer entidade;

    /*
     * Construtor completo
     */
    public Cliente(Integer numCliente, String nome, String morada, String email,
            String telemovel, String nif, String password, String numConta,
            Double saldo, Double saldo_cativo, String cardaoDefault,
            Integer entidade) {
        this.numCliente = numCliente;
        this.nome = nome;
        this.morada = morada;
        this.email = email;
        this.telemovel = telemovel;
        this.nif = nif;
        this.password = password;
        this.numConta = numConta;
        this.saldo = saldo;
        this.saldo_cativo = saldo_cativo;
        this.cartaoDefault = cardaoDefault;
        this.entidade = entidade;
    }

    /*
     * Construtor para saldos, "atualizar" saldos
     */
    public Cliente(Double saldo, Double saldo_cativo) {
        this.saldo = saldo;
        this.saldo_cativo = saldo_cativo;
    }

    /*
     * Construtor de cliente com password e sem cartão
     */
    public Cliente(Integer numCliente, String nome, String morada, String email,
            String telemovel, String nif, String password, String numConta,
            Double saldo) {
        this.numCliente = numCliente;
        this.nome = nome;
        this.morada = morada;
        this.email = email;
        this.telemovel = telemovel;
        this.nif = nif;
        this.password = password;
        this.numConta = numConta;
        this.saldo = saldo;
    }

    /*
     * Construtor de cliente sem password e sem cartão
     */
    public Cliente(Integer numCliente, String nome, String morada, String email,
            String telemovel, String nif, String numConta, Double saldo) {
        this.numCliente = numCliente;
        this.nome = nome;
        this.morada = morada;
        this.email = email;
        this.telemovel = telemovel;
        this.nif = nif;
        this.numConta = numConta;
        this.saldo = saldo;
    }

    /*
     * Construtor para fazer login
     */
    public Cliente(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public int alterarInformacoes(String old_email) {
        int status = ClientDAO.UpdateClient(nome, morada, email, telemovel, nif,
                password, old_email);
        return status;
    }

    public boolean autenticar() {
        Cliente tmp = ClientDAO.getClientByEmail(email);

        if (tmp != null) {
            try {
                if (Hash.validatePassword(password, tmp.password)) {
                    LocalClient = tmp;
                    LocalClientCard = getCardByNumber(LocalClient.cartaoDefault);
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
        return "Cliente{" + "numCliente=" + numCliente + ", numConta=" + numConta + ", saldo=" + saldo + ", saldo_cativo=" + saldo_cativo + ", cartaoDefault=" + cartaoDefault + '}';
    }
}
