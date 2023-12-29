package pt.ua.ibank.DTO;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import pt.ua.ibank.DAO.ClientDAO;
import static pt.ua.ibank.IBank.client;
import pt.ua.ibank.utilities.Hash;

public class Cliente {

    public Integer numCliente;
    public String nome;
    public String morada;
    public String email;
    public String telemovel;
    public String nif;
    public String password;
    public String numConta;
    public Double saldo;

    public Cliente(Integer numCliente, String nome, String morada, String email, String telemovel, String nif, String password, String numConta, Double saldo) {
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

    public Cliente(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public boolean autenticar() {
        Cliente tmp = ClientDAO.getClientByEmail(email);

        if (tmp != null) {
            try {
                if (Hash.validatePassword(password, tmp.password)) {
                    client = tmp;
                    return true;
                }
            } catch (NoSuchAlgorithmException | InvalidKeySpecException ex) {
                ex.printStackTrace();
            }
        }
        return false;
    }
    
    public int alterarInformacoes(String old_email){
        int status = ClientDAO.UpdateClient(nome, morada, email, telemovel, nif, password, old_email);
        return status;
    }
}
