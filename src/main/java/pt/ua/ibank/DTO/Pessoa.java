package pt.ua.ibank.DTO;

import java.sql.Date;
import java.text.SimpleDateFormat;

/**
 * Objeto de uma Class Abstrata que representa uma Entidade Pessoa
 * Author: PTDA_Staff.
 * Ultima data de modificação: 16 de Janeiro, 2024
 */
public abstract class Pessoa {

    public String nome;
    public String morada;
    public String email;
    public String telemovel;
    public String nif;
    public String password;
    public Date dataCriada;

    /**
     * Construtor com password, por favor encriptar a password primeiro
     */
    public Pessoa(String pNome, String pMorada, String pEmail, String pTelemovel,
                  String pNif, String pPassword) {
        this.nome = pNome;
        this.morada = pMorada;
        this.email = pEmail;
        this.telemovel = pTelemovel;
        this.nif = pNif;
        this.password = pPassword;
    }

    /**
     * Construtor sem password
     */
    public Pessoa(String pNome, String pMorada, String pEmail, String pTelemovel,
                  String pNif, Date dataCriada) {
        this.nome = pNome;
        this.morada = pMorada;
        this.email = pEmail;
        this.telemovel = pTelemovel;
        this.nif = pNif;
        this.dataCriada = dataCriada;
    }

    /**
     * Construtor Email Password para ser usado na autenticação
     *
     * @param email
     * @param password
     */
    public Pessoa(String email, String password) {
        this.email = email;
        this.password = password;

    }

    /**
     * Construtor vazio para possiveis casos de teste
     */
    public Pessoa() {
    }

    public abstract boolean autenticar();

    /**
     *
     * Função usada para alterar informações de Cliente/Pessoa externa
     *
     * @param old_email Email antigo caso o usuario local pretenda trocar
     * @return 1 Sucesso, 2 Erro, 3 ErroEmail
     */
    public abstract int alterarInformacoes(String old_email);

    public String getDateFormatted() {
        SimpleDateFormat dataFormat = new SimpleDateFormat("MM/yy");
        return dataFormat.format(dataCriada);
    }
}
