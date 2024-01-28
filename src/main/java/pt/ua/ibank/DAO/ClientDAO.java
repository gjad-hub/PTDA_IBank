package pt.ua.ibank.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import pt.ua.ibank.DTO.Cliente;
import static pt.ua.ibank.utilities.CardGenerator.generateCardNumber;
import static pt.ua.ibank.utilities.Configs.CODIGO_ERRO;
import static pt.ua.ibank.utilities.Configs.CODIGO_ERRO_EMAIL;
import static pt.ua.ibank.utilities.Configs.CODIGO_SUCESSO;
import pt.ua.ibank.utilities.DBConnection;
import static pt.ua.ibank.utilities.DBConnection.conn;
import static pt.ua.ibank.utilities.EntityRefGenerator.generateEnt;
import static pt.ua.ibank.utilities.IbanGenerator.generateRandomIban;

/**
 * Classe com metodos estáticos associados a operações feitas com Clientes
 * guardados em uma base de dados MySQL
 * Author: PTDA_Staff.
 * Ultima Data de Modificação: 29 de Dezembro, 2023
 */
public class ClientDAO {

    /**
     * Função usada para Criar um cliente
     *
     * @param nome     Nome do Cliente
     * @param morada   Morada de Cliente
     * @param email    Email de Cliente
     * @param telefone Telefone de Cliente
     * @param nif      NIF ( Numero de Identificação fiscal ) do cliente
     * @param password Password encriptada
     * @return codigo de Sucesso -> 1 Sucesso, 2 Erro Email, 3 Erro SQL
     */
    public static int CreateClient(String nome, String morada, String email,
                                   String telefone, String nif, String password) {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String num_conta;
        String num_cartao;
        int ent;

        try {

            // Verifica se já existe alguma conta com aquele e-mail
            stmt = conn.prepareStatement(
            "SELECT count(num_cliente) AS valor FROM cliente where email like ?;");
            stmt.setString(1, email);
            rs = stmt.executeQuery();
            rs.next();

            if (rs.getInt("valor") > 0) {
                return CODIGO_ERRO_EMAIL;
            }

            // Gera um novo iban até não existir um cliente com o iban
            do {
                num_conta = generateRandomIban();
                stmt = conn.prepareStatement(
                "SELECT count(num_cliente) AS valor FROM cliente where num_conta like ?;");
                stmt.setString(1, num_conta);
                rs = stmt.executeQuery();
                rs.next();
            } while (rs.getInt("valor") != 0);

            // Gera um novo cartao até não existir nenhum cartao com este numero
            do {
                num_cartao = generateCardNumber();
                stmt = conn.prepareStatement(
                "SELECT count(num_cartao) AS valor FROM cartao where num_cartao like ?;");
                stmt.setString(1, num_cartao);
                rs = stmt.executeQuery();
                rs.next();
            } while (rs.getInt("valor") != 0);

            // Gera uma nova referencia até não existir nenhuma com este numero
            do {
                ent = generateEnt();
                stmt = conn.prepareStatement(
                "SELECT count(num_cliente) AS valor FROM cliente where entidade = ?;");
                stmt.setInt(1, ent);
                rs = stmt.executeQuery();
                rs.next();
            } while (rs.getInt("valor") != 0);
            stmt.close();

            stmt = conn.prepareStatement(
            "INSERT INTO cartao (num_cartao, data_validade, estado) "
            + "VALUES (?, (SELECT DATE_ADD(CURDATE(), INTERVAL +5 YEAR )),"
            + " \"Ativo\");");
            stmt.setString(1, num_cartao);
            stmt.execute();

            stmt = conn.prepareStatement(
            "INSERT INTO cliente (nome, morada, email, telemovel, nif,"
            + " num_conta, password, cartao_default, entidade) "
            + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)");
            stmt.setString(1, nome);
            stmt.setString(2, morada);
            stmt.setString(3, email);
            stmt.setString(4, telefone);
            stmt.setString(5, nif);
            stmt.setString(6, num_conta);
            stmt.setString(7, password);
            stmt.setString(8, num_cartao);
            stmt.setInt(9, ent);
            stmt.execute();

            int num_cli = getClientIdByEmail(email);

            stmt = conn.prepareStatement(
            "UPDATE cartao SET cliente = ? WHERE num_cartao like ?;");
            stmt.setInt(1, num_cli);
            stmt.setString(2, num_cartao);
            stmt.execute();

            return CODIGO_SUCESSO;

        } catch (SQLException e) {
            e.printStackTrace(System.out);
            return CODIGO_ERRO;
        } finally {
            DBConnection.closeConnection(stmt, rs);
        }
    }

    /**
     * Função usada para obter um cliente através de um ID
     *
     * @param id ID do Cliente usado como refencia para obter lista
     * @return Objeto de cliente obtido
     */
    public static Cliente getClientByID(int id) {
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {

            stmt = conn.prepareStatement(
            "SELECT * FROM cliente where num_cliente= ?;");
            stmt.setInt(1, id);
            rs = stmt.executeQuery();

            while (rs.next()) {
                return new Cliente(
                        rs.getInt("num_cliente"),
                        rs.getString("nome"),
                        rs.getString("morada"),
                        rs.getString("email"),
                        rs.getString("telemovel"),
                        rs.getString("nif"),
                        rs.getString("num_conta"),
                        rs.getDouble("saldo"),
                        rs.getDouble("saldo_cativo"),
                        rs.getDate("data_criacao"),
                        rs.getString("cartao_default"),
                        rs.getInt("entidade")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace(System.out);
        } finally {
            DBConnection.closeConnection(stmt, rs);
        }
        return null;
    }

    /**
     * Função usada para obter um objeto cliente através de um email
     *
     * @param id Email de Cliente usado como referência
     * @return Retorna cliente associado, ou null caso haja erro
     */
    public static Cliente getClientByEmail(String id) {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Cliente cl = null;

        try {

            stmt = conn.prepareStatement(
            "SELECT * FROM cliente where email like ?;");
            stmt.setString(1, id);
            rs = stmt.executeQuery();

            while (rs.next()) {
                cl = new Cliente(
                rs.getInt("num_cliente"),
                rs.getString("nome"),
                rs.getString("morada"),
                rs.getString("email"),
                rs.getString("telemovel"),
                rs.getString("nif"),
                rs.getString("password"),
                rs.getString("num_conta"),
                rs.getDouble("saldo"),
                rs.getDouble("saldo_cativo"),
                rs.getString("cartao_default"),
                rs.getInt("entidade"));
            }

            return cl;
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        } finally {
            DBConnection.closeConnection(stmt, rs);
        }
        return null;
    }

    /**
     * Função usada para obter um Cliente externo associado a um ID
     *
     * @param id NIF do Cliente usado como refencia para obter lista
     * @return Retorna cliente associado, ou null caso haja erro
     */
    public static Cliente getClientByNIF(String id) {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Cliente cl = null;

        try {

            stmt = conn.prepareStatement(
            "SELECT * FROM cliente where nif like ?;");
            stmt.setString(1, id);
            rs = stmt.executeQuery();

            while (rs.next()) {
                cl = new Cliente(
                rs.getInt("num_cliente"),
                rs.getString("nome"),
                rs.getString("morada"),
                rs.getString("email"),
                rs.getString("telemovel"),
                rs.getString("nif"),
                rs.getString("password"),
                rs.getString("num_conta"),
                rs.getDouble("saldo"),
                rs.getDouble("saldo_cativo"),
                rs.getString("cartao_default"),
                rs.getInt("entidade"));
            }

            return cl;
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        } finally {
            DBConnection.closeConnection(stmt, rs);
        }
        return null;
    }

    /**
     * Usado para procurar um frupo de Clientes por Morada
     *
     * @param address Morada de Clientes Externos
     * @return Retorna de Clientes associados, ou null caso haja erro
     */
    public static ArrayList<Cliente> getClientListByAddress(String address) {
        PreparedStatement stmt = null;
        ResultSet rs = null;

        ArrayList<Cliente> list = new ArrayList<>();
        try {
            stmt = conn.prepareStatement(
            "SELECT num_cliente,nome,morada,email,telemovel,nif,num_conta,"
            + "data_criacao,cartao_default FROM cliente where morada like ?");
            stmt.setString(1, address);
            rs = stmt.executeQuery();

            while (rs.next()) {
                Integer numero = rs.getInt("num_cliente");
                String nome = rs.getString("nome");
                String morada = rs.getString("morada");
                String email = rs.getString("email");
                String telemovel = rs.getString("telemovel");
                String nif = rs.getString("nif");
                String numConta = rs.getString("num_conta");
                var dataCriacao = rs.getDate("data_criacao");
                String cartaoDefault = rs.getString("cartao_default");

                list.add(
                        new Cliente(numero, nome, morada, email,
                                    telemovel, nif, numConta, 0.0, 0.0,
                                    dataCriacao,
                                    cartaoDefault, 0));
            }

            return list;
        } catch (SQLException e) {
            e.printStackTrace(System.out);
            return null;
        } finally {
            DBConnection.closeConnection(stmt, rs);
        }
    }

    /**
     * Retorna uma lista de todos os clientes
     *
     * @return Retorna de Clientes associados, ou null caso haja erro
     */
    public static ArrayList<Cliente> getClientList() {
        PreparedStatement stmt = null;
        ResultSet rs = null;

        ArrayList<Cliente> list = new ArrayList<>();
        try {
            stmt = conn.prepareStatement(
            "SELECT num_cliente,nome,morada,email,"
            + "telemovel,nif,num_conta,cartao_default,data_criacao "
            + "FROM cliente");
            rs = stmt.executeQuery();

            while (rs.next()) {
                Integer numero = rs.getInt("num_cliente");
                String nome = rs.getString("nome");
                String morada = rs.getString("morada");
                String email = rs.getString("email");
                String telemovel = rs.getString("telemovel");
                String nif = rs.getString("nif");
                String numConta = rs.getString("num_conta");
                var dataCriacao = rs.getDate("data_criacao");
                String cartaoDefault = rs.getString("cartao_default");
                list.add(
                        new Cliente(numero, nome, morada, email,
                                    telemovel, nif, numConta, 0.0, 0.0,
                                    dataCriacao,
                                    cartaoDefault, 0));
            }

            return list.isEmpty() ? null : list;
        } catch (SQLException e) {
            e.printStackTrace(System.out);
            return null;
        } finally {
            DBConnection.closeConnection(stmt, rs);
        }
    }

    /**
     * Função usada para retornar numero de cliente através de um IBAN
     *
     * @param iban IBAN do Cliente usado como referencia
     * @return retorna o ID do cliente associado ao IBAN
     */
    public static Integer getClientIdByIBAN(String iban) {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Integer num_cliente = null;

        try {

            stmt = conn.prepareStatement(
            "SELECT num_cliente FROM cliente where num_conta like ?;");
            stmt.setString(1, iban);
            rs = stmt.executeQuery();

            while (rs.next()) {
                num_cliente = rs.getInt("num_cliente");
            }

            return num_cliente;
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        } finally {
            DBConnection.closeConnection(stmt, rs);
        }
        return null;
    }

    /**
     * Função usada para obter o nome de um Cliente através de um ID
     *
     * @param clientID ID de Cliente a ser usado como referencia
     * @return Retorna o nome do Cliente associado ao ID
     */
    public static String getClienteNomeByID(int clientID) {
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            stmt = conn.prepareStatement(
            "SELECT nome FROM cliente WHERE num_cliente = ?");
            stmt.setInt(1, clientID);
            rs = stmt.executeQuery();

            if (rs.next()) {
                return rs.getString("nome");
            }
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        } finally {
            DBConnection.closeConnection(stmt, rs);
        }

        return null;
    }

    /**
     * Função usada para obter o ID de um cliente através do seu email
     *
     * @param email Email usado como referencia
     * @return Retorna o ID do cliente associado ao Email
     */
    public static Integer getClientIdByEmail(String email) {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Integer num_cliente = null;

        try {

            stmt = conn.prepareStatement(
            "SELECT num_cliente FROM cliente where email like ?;");
            stmt.setString(1, email);
            rs = stmt.executeQuery();

            while (rs.next()) {
                num_cliente = rs.getInt("num_cliente");
            }

            return num_cliente;
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        } finally {
            DBConnection.closeConnection(stmt, rs);
        }
        return null;
    }

    /**
     * Função usada para actualizar as informações externas de um Cliente
     *
     * @param num_cliente
     * @param nome        Nome de cliente Externo
     * @param morada      Morada de Cliente Externo
     * @param email       Email de Cliente externo
     * @param telefone    Telefone de Cliente externo
     * @param nif         NIF ( Numero de Identificação fiscal ) Externo
     * @return
     */
    public static boolean UpdateClient(
            int num_cliente, String nome,
            String morada, String email,
            String telefone, String nif) {
        PreparedStatement stmt = null;

        try {
            stmt = conn.prepareStatement(
            "UPDATE cliente "
            + "SET nome = ? , morada = ?, email = ?, telemovel = ?, nif = ? "
            + "WHERE num_cliente = ?");
            stmt.setString(1, nome);
            stmt.setString(2, morada);
            stmt.setString(3, email);
            stmt.setString(4, telefone);
            stmt.setString(5, nif);
            stmt.setInt(6, num_cliente);
            stmt.executeUpdate();
            return true;

        } catch (SQLException e) {
            e.printStackTrace(System.out);
            return false;
        } finally {
            DBConnection.closeConnection(stmt);
        }
    }

    /**
     * Função usada para actualizar as Informações externas de um Funcionario"
     *
     * @param nome      Nome de cliente Externo
     * @param morada    Morada de Cliente Externo
     * @param email     Email de Cliente externo
     * @param telefone  Telefone de Cliente externo
     * @param nif       NIF ( Numero de Identificação fiscal ) Externo
     * @param password  Password encriptada do cliente
     * @param old_email Email antigo caso pretenda mudar
     * @return Codigo de Estado, 1 se Sucedido, 2 Erro de Emai, 3 Erro SQL
     */
    public static int UpdateClient(String nome, String morada, String email,
                                   String telefone, String nif,
                                   String password, String old_email) {
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {

            // Verifica se já existe alguma conta com aquele e-mail
            stmt = conn.prepareStatement(
            "SELECT count(num_cliente) AS valor FROM cliente where email like ?;");
            stmt.setString(1, email);
            rs = stmt.executeQuery();
            rs.next();

            if (rs.getInt("valor") > 0 && !email.equals(old_email)) {
                return CODIGO_ERRO_EMAIL;
            }

            stmt = conn.prepareStatement(
            "UPDATE cliente SET nome = ? , morada = ?, email = ?, telemovel = ?, password = ? WHERE email = ?");
            stmt.setString(1, nome);
            stmt.setString(2, morada);
            stmt.setString(3, email);
            stmt.setString(4, telefone);
            stmt.setString(5, password);
            stmt.setString(6, old_email);
            stmt.execute();
            return CODIGO_SUCESSO;

        } catch (SQLException e) {
            return CODIGO_ERRO;
        } finally {
            DBConnection.closeConnection(stmt, rs);
        }
    }

    /**
     * Função usada para obter o saldo de um Cliente
     *
     * @param num_cliente Numero de cliente usado cmom Referencia
     * @return Retorna Objeto cliente com um Saldo
     */
    public static Cliente getClientBalance(int num_cliente) {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Cliente cl = null;

        try {

            stmt = conn.prepareStatement(
            "SELECT saldo, saldo_cativo FROM cliente where num_cliente = ?;");
            stmt.setInt(1, num_cliente);
            rs = stmt.executeQuery();

            while (rs.next()) {
                cl = new Cliente(rs.getDouble("saldo"), rs.getDouble(
                                 "saldo_cativo"));
            }

            return cl;
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        } finally {
            DBConnection.closeConnection(stmt, rs);
        }
        return null;
    }

    /**
     * Função usada para obter o cartão predefinido de um Cliente
     *
     * @param num_cliente ID de cliente usado como referencia
     * @return retorna Cartão predefenido do cliente
     */
    public static String getClientDefaultCard(int num_cliente) {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String card = null;

        try {

            stmt = conn.prepareStatement(
            "SELECT cartao_default FROM cliente where num_cliente = ?;");
            stmt.setInt(1, num_cliente);
            rs = stmt.executeQuery();

            while (rs.next()) {
                card = rs.getString("cartao_default");
            }

            return card;
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        } finally {
            DBConnection.closeConnection(stmt, rs);
        }
        return null;
    }
}
