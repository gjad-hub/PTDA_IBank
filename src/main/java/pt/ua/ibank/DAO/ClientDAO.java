package pt.ua.ibank.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import pt.ua.ibank.DTO.Cliente;
import pt.ua.ibank.utilities.DBConnection;
import static pt.ua.ibank.utilities.DBConnection.conn;
import static pt.ua.ibank.utilities.CardGenerator.generateCardNumber;
import static pt.ua.ibank.utilities.IbanGenerator.generateRandomIban;

public class ClientDAO {

    protected final static int codigoSucesso = 1;
    protected final static int codigoErro = 2;
    protected final static int codigoErroEmail = 3;

    public static int CreateClient(String nome, String morada, String email, String telefone, String nif,
            String password) {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String num_conta;
        String num_cartao;

        try {

            // Verifica se já existe alguma conta com aquele e-mail
            stmt = conn.prepareStatement("SELECT count(num_cliente) AS valor FROM cliente where email like ?;");
            stmt.setString(1, email);
            rs = stmt.executeQuery();
            rs.next();

            if (rs.getInt("valor") > 0) {
                return codigoErroEmail;
            }

            // Gera um novo iban até não existir um cliente com o iban
            do {
                num_conta = generateRandomIban();
                stmt = conn.prepareStatement("SELECT count(num_cliente) AS valor FROM cliente where num_conta like ?;");
                stmt.setString(1, num_conta);
                rs = stmt.executeQuery();
                rs.next();
            } while (rs.getInt("valor") != 0);

            // Gera um novo cartao até não existir nenhum cartao com este numero
            do {
                num_cartao = generateCardNumber();
                stmt = conn.prepareStatement("SELECT count(num_cartao) AS valor FROM cartao where num_cartao like ?;");
                stmt.setString(1, num_cartao);
                rs = stmt.executeQuery();
                rs.next();
            } while (rs.getInt("valor") != 0);
            stmt.close();

            stmt = conn.prepareStatement("INSERT INTO cartao (num_cartao, data_validade, estado, credito) "
                    + "VALUES (?, (SELECT DATE_ADD(CURDATE(), INTERVAL +5 YEAR )), \"activo\", ?);");
            stmt.setString(1, num_cartao);
            stmt.setBoolean(2, false);
            stmt.execute();

            stmt = conn.prepareStatement(
                    "INSERT INTO cliente (nome, morada, email, telemovel, nif, num_conta, password, cartao_default) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
            stmt.setString(1, nome);
            stmt.setString(2, morada);
            stmt.setString(3, email);
            stmt.setString(4, telefone);
            stmt.setString(5, nif);
            stmt.setString(6, num_conta);
            stmt.setString(7, password);
            stmt.setString(8, num_cartao);
            stmt.execute();

            int num_cli = getClientIdByEmail(email);

            stmt = conn.prepareStatement(
                    "UPDATE cartao SET cliente = ? WHERE num_cartao like ?;");
            stmt.setInt(1, num_cli);
            stmt.setString(2, num_cartao);
            stmt.execute();

            return codigoSucesso;

        } catch (SQLException e) {
            return codigoErro;
        } finally {
            DBConnection.closeConnection(stmt);
        }
    }

    public static Cliente getClientByEmail(String email) {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Cliente cl = null;

        try {

            stmt = conn.prepareStatement(
                    "SELECT * FROM cliente where email like ?;");
            stmt.setString(1, email);
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
                        rs.getString("cartao_default"));
            }

            return cl;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnection.closeConnection(stmt, rs);
        }
        return null;
    }

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
            e.printStackTrace();
        } finally {
            DBConnection.closeConnection(stmt, rs);
        }
        return null;
    }

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
            e.printStackTrace();
        } finally {
            DBConnection.closeConnection(stmt, rs);
        }
        return null;
    }

    public static int UpdateClient(String nome, String morada, String email, String telefone, String nif,
            String old_email) {
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
                return codigoErroEmail;
            }

            stmt = conn.prepareStatement(
                    "UPDATE cliente SET nome = ? , morada = ?, email = ?, telemovel = ? WHERE email = ?");
            stmt.setString(1, nome);
            stmt.setString(2, morada);
            stmt.setString(3, email);
            stmt.setString(4, telefone);
            stmt.setString(5, old_email);
            stmt.execute();
            return codigoSucesso;

        } catch (SQLException e) {
            return codigoErro;
        } finally {
            DBConnection.closeConnection(stmt, rs);
        }
    }

    public static int UpdateClient(String nome, String morada, String email, String telefone, String nif,
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
                return codigoErroEmail;
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
            return codigoSucesso;

        } catch (SQLException e) {
            return codigoErro;
        } finally {
            DBConnection.closeConnection(stmt, rs);
        }
    }

    public static Cliente getClientBalance(int num_cliente) {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Cliente cl = null;

        try {

            stmt = conn.prepareStatement("SELECT saldo, saldo_cativo FROM cliente where num_cliente = ?;");
            stmt.setInt(1, num_cliente);
            rs = stmt.executeQuery();

            while (rs.next()) {
                cl = new Cliente(rs.getDouble("saldo"), rs.getDouble("saldo_cativo"));
            }

            return cl;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnection.closeConnection(stmt, rs);
        }
        return null;
    }

    public static String getClientDefaultCard(int num_cliente) {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String card = null;

        try {

            stmt = conn.prepareStatement("SELECT cartao_default FROM cliente where num_cliente = ?;");
            stmt.setInt(1, num_cliente);
            rs = stmt.executeQuery();

            while (rs.next()) {
                card = rs.getString("cartao_default");
            }

            return card;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnection.closeConnection(stmt, rs);
        }
        return null;
    }
}
