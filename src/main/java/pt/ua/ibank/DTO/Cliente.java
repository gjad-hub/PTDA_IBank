package pt.ua.ibank.DTO;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.Date;
import static pt.ua.ibank.DAO.CardsDAO.getCardByNumber;
import pt.ua.ibank.DAO.ClientDAO;
import static pt.ua.ibank.utilities.Configs.LocalClient;
import static pt.ua.ibank.utilities.Configs.LocalClientCard;
import pt.ua.ibank.utilities.Hash;

/**
 *
 * @author ricar
 */
public class Cliente extends Pessoa {

    /**
     * Numero de Cliente externo
     */
    public Integer numCliente;

    /**
     * Numero de Conta externa
     */
    public String numConta;

    /**
     * Saldo de Conta Externa
     */
    public Double saldo;

    /**
     * Saldo cativo/pendente por ser aprovado por um funcionario
     */
    public Double saldo_cativo;

    /**
     * Numero de cartão predefinido escolhido pelo usuario
     */
    public String cartaoDefault;

    /**
     * Entidade de cliente que vai ser usado como referencia para pagamentos
     */
    public Integer entidade;

    /**
     * Construtor completo de cliente
     *
     * @param numCliente    Numero de cliente externo
     * @param nome          Nome de cliente Externo
     * @param morada        Morada de Cliente Externo
     * @param email         Email de Cliente externo
     * @param telemovel     Telemovel de Cliente Externo
     * @param nif           NIF ( Numero de Identificação fiscal ) Externo
     * @param password      Password, guardada de forma encriptada
     * @param numConta      Numero de Conta de Cliente Externo
     * @param saldo         Saldo Aprovado de Cliente
     * @param saldo_cativo  Saldo Pendente de Cliente
     * @param cartaoDefault Digitos de Cartão predefenido
     * @param entidade      Entidade Para ser usada com pagamentos
     */
    public Cliente(Integer numCliente, String nome, String morada, String email,
                   String telemovel, String nif, String password,
                   String numConta,
                   Double saldo, Double saldo_cativo, String cartaoDefault,
                   Integer entidade) {
        super(nome, morada, email, telemovel, nif, password);
        this.numCliente = numCliente;
        this.numConta = numConta;
        this.saldo = saldo;
        this.saldo_cativo = saldo_cativo;
        this.cartaoDefault = cartaoDefault;
        this.entidade = entidade;
    }

    /**
     * Construtor para saldos, "atualizar" saldos
     *
     * @param saldo        Salto Aprovado
     * @param saldo_cativo Saldo Pendente
     */
    public Cliente(Double saldo, Double saldo_cativo) {
        this.saldo = saldo;
        this.saldo_cativo = saldo_cativo;
    }

    /**
     * Construtor de cliente com password e sem cartão
     *
     * @param numCliente Numero de cliente externo
     * @param nome       Nome de cliente Externo
     * @param morada     Morada de Cliente Externo
     * @param email      Email de Cliente externo
     * @param telemovel  Telemovel de Cliente Externo
     * @param nif        NIF ( Numero de Identificação fiscal ) Externo
     * @param password   Password, guardada de forma encriptada
     * @param numConta   Numero de Conta de Cliente Externo
     * @param saldo      Saldo Aprovado de Cliente
     */
    public Cliente(Integer numCliente, String nome, String morada, String email,
                   String telemovel, String nif, String password,
                   String numConta,
                   Double saldo) {
        super(nome, morada, email, telemovel, nif, password);
        this.numCliente = numCliente;
        this.numConta = numConta;
        this.saldo = saldo;
    }

    /*
     * Construtor de cliente sem password
     */
    /**
     *
     * @param numCliente     Numero de cliente externo
     * @param nome           Nome de cliente Externo
     * @param morada         Morada de Cliente Externo
     * @param email          Email de Cliente externo
     * @param telemovel      Telemovel de Cliente Externo
     * @param nif            NIF ( Numero de Identificação fiscal ) Externo
     * @param numConta       Numero de Conta de Cliente Externo
     * @param saldo          Saldo Aprovado de Cliente
     * @param saldo_cativo   Saldo Pendente
     * @param dataCriada     Data em que foi criada a Pessoa Externa
     * @param cartao_default Cartão Predefenido
     * @param entidade       Entidade de cliente como referencia para pagamentos
     */
    public Cliente(Integer numCliente, String nome, String morada, String email,
                   String telemovel, String nif, String numConta, Double saldo,
                   Double saldo_cativo, Date dataCriada, String cartao_default,
                   Integer entidade) {
        super(nome, morada, email, telemovel, nif, dataCriada);
        this.numCliente = numCliente;
        this.numConta = numConta;
        this.saldo = saldo;
        this.saldo_cativo = saldo_cativo;
        this.entidade = entidade;
        this.cartaoDefault = cartao_default;
    }

    /**
     * Construtor para fazer login
     *
     * @param email    email para ser usado na autenticação
     * @param password password relacionada ao Email
     */
    public Cliente(String email, String password) {
        super(email, password);
        this.email = email;
        this.password = password;
    }

    /**
     *
     * @param old_email Email antigo para o caso de trocar o email currente
     * @return Codigo de Sucesso 1 Sucesso, 2 Erro Email, 3 Erro
     */
    @Override
    public int alterarInformacoes(String old_email) {
        int status = ClientDAO.UpdateClient(nome, morada, email, telemovel, nif,
                                            password, old_email);
        return status;
    }

    @Override
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
                ex.printStackTrace(System.out);
            }
        }
        return false;
    }
}
