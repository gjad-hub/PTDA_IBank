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
 * Object of a class representing an external client
 * Author: PTDA_Staff.
 * Last Modification Date: December 21, 2023
 */
public class Client extends Person {

    /**
     * External client number
     */
    public Integer clientNumber;

    /**
     * External account number
     */
    public String accountNumber;

    /**
     * External account balance
     */
    public Double balance;

    /**
     * Pending or pending balance to be approved by an employee
     */
    public Double pendingBalance;

    /**
     * Default card number chosen by the user
     */
    public String defaultCard;

    /**
     * Entity of the client used as a reference for payments
     */
    public Integer entity;

    /**
     * Full constructor of the client
     *
     * @param clientNumber   External client number
     * @param name           Name of the external client
     * @param address        Address of the external client
     * @param email          Email of the external client
     * @param phoneNumber    Phone number of the external client
     * @param nif            NIF (Tax Identification Number) of the external
     *                       client
     * @param password       Password, stored encrypted
     * @param accountNumber  External client account number
     * @param balance        Approved balance of the client
     * @param pendingBalance Pending balance of the client
     * @param defaultCard    Default card digits
     * @param entity         Entity to be used for payments
     */
    public Client(Integer clientNumber, String name, String address,
                  String email,
                  String phoneNumber, String nif, String password,
                  String accountNumber, Double balance, Double pendingBalance,
                  String defaultCard, Integer entity) {
        super(name, address, email, phoneNumber, nif, password);
        this.clientNumber = clientNumber;
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.pendingBalance = pendingBalance;
        this.defaultCard = defaultCard;
        this.entity = entity;
    }

    /**
     * Constructor for balances, "update" balances
     *
     * @param balance        Approved balance
     * @param pendingBalance Pending balance
     */
    public Client(Double balance, Double pendingBalance) {
        this.balance = balance;
        this.pendingBalance = pendingBalance;
    }

    /**
     * Constructor for a client with a password and without a card
     *
     * @param clientNumber  External client number
     * @param name          Name of the external client
     * @param address       Address of the external client
     * @param email         Email of the external client
     * @param phoneNumber   Phone number of the external client
     * @param nif           NIF (Tax Identification Number) of the external
     *                      client
     * @param password      Password, stored encrypted
     * @param accountNumber External client account number
     * @param balance       Approved balance of the client
     */
    public Client(Integer clientNumber, String name, String address,
                  String email,
                  String phoneNumber, String nif, String password,
                  String accountNumber, Double balance) {
        super(name, address, email, phoneNumber, nif, password);
        this.clientNumber = clientNumber;
        this.accountNumber = accountNumber;
        this.balance = balance;
    }

    /*
     * Constructor for a client without a password
     */
    /**
     *
     * @param clientNumber   External client number
     * @param name           Name of the external client
     * @param address        Address of the external client
     * @param email          Email of the external client
     * @param phoneNumber    Phone number of the external client
     * @param nif            NIF (Tax Identification Number) of the external
     *                       client
     * @param accountNumber  External client account number
     * @param balance        Approved balance of the client
     * @param pendingBalance Pending balance
     * @param creationDate   Date when the external person was created
     * @param defaultCard    Default card
     * @param entity         Entity of the client as a reference for payments
     */
    public Client(Integer clientNumber, String name, String address,
                  String email,
                  String phoneNumber, String nif, String accountNumber,
                  Double balance,
                  Double pendingBalance, Date creationDate, String defaultCard,
                  Integer entity) {
        super(name, address, email, phoneNumber, nif, creationDate);
        this.clientNumber = clientNumber;
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.pendingBalance = pendingBalance;
        this.entity = entity;
        this.defaultCard = defaultCard;
    }

    /**
     * Constructor for login
     *
     * @param email    Email to be used in authentication
     * @param password Password related to Email
     */
    public Client(String email, String password) {
        super(email, password);
        this.email = email;
        this.password = password;
    }

    /**
     *
     * @param oldEmail Old email in case the local user wants to change the
     *                 current email
     * @return Success Code 1 Success, 2 Error Email, 3 Error
     */
    @Override
    public int alterInformation(String oldEmail) {
        int status = ClientDAO.UpdateClient(name, address, email, phoneNumber,
                                            nif,
                                            password, oldEmail);
        return status;
    }

    @Override
    public boolean authenticate() {
        Client tmp = ClientDAO.getClientByEmail(email);

        if (tmp != null) {
            try {
                if (Hash.validatePassword(password, tmp.password)) {
                    LocalClient = tmp;
                    LocalClientCard = getCardByNumber(LocalClient.defaultCard);
                    return true;
                }
            } catch (NoSuchAlgorithmException | InvalidKeySpecException ex) {
                ex.printStackTrace(System.out);
            }
        }
        return false;
    }
}
