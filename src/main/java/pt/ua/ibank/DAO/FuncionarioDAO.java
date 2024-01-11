package pt.ua.ibank.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import pt.ua.ibank.DTO.Funcionario;
import pt.ua.ibank.utilities.DBConnection;
import static pt.ua.ibank.utilities.DBConnection.conn;

public class FuncionarioDAO {

    protected final static int codigoSucesso = 1;
    protected final static int codigoErro = 2;
    protected final static int codigoErroEmail = 3;

    public static int CreateFuncionario(String nome, String morada, String email,
            String telefone, String nif,
            String password) {
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {

            // Verifica se já existe alguma conta com aquele e-mail
            stmt = conn.prepareStatement(
                    "SELECT count(num_fun) AS valor FROM cliente where email like ?;");
            stmt.setString(1, email);
            rs = stmt.executeQuery();
            rs.next();

            if (rs.getInt("valor") > 0) {
                return codigoErroEmail;
            }

            stmt = conn.prepareStatement(
                    "INSERT INTO funcionario (nome, morada, email, telemovel, nif, password, gerente) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?)");
            stmt.setString(1, nome);
            stmt.setString(2, morada);
            stmt.setString(3, email);
            stmt.setString(4, telefone);
            stmt.setString(5, nif);
            stmt.setString(6, password);
            stmt.setInt(7, 1); // falta adicionar um número de gerente ao funcionário
            stmt.execute();
            int num_fun = getFuncionarioIdByEmail(email);
            return codigoSucesso;
        } catch (SQLException e) {
            return codigoErro;
        } finally {
            DBConnection.closeConnection(stmt);
        }
    }

    public static Funcionario getFuncionarioByEmail(String email) {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Funcionario fun = null;

        try {

            stmt = conn.prepareStatement(
                    "SELECT * FROM funcionario where email like ?;");
            stmt.setString(1, email);
            rs = stmt.executeQuery();

            while (rs.next()) {
                fun = new Funcionario(rs.getInt("num_cliente"),
                        rs.getString("nome"),
                        rs.getString("morada"),
                        rs.getString("email"),
                        rs.getString("telemovel"),
                        rs.getString("nif"),
                        rs.getString("password"),
                        1); // falta adicionar um número de gerente ao funcionário
            }

            return fun;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnection.closeConnection(stmt, rs);
        }
        return null;
    }

    public static ArrayList<Funcionario> getFuncionarioList() {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Integer num_fun = null; //não percebi para que serve esta linha

        ArrayList list = new ArrayList<>();
        try {
            stmt = conn.prepareStatement(
                    "SELECT num_fun, nome, morada, email, telemovel, nif, gerente FROM funcionario");
            rs = stmt.executeQuery();

            while (rs.next()) {
                Integer numero = rs.getInt("num_fun");
                String nome = rs.getString("nome");
                String morada = rs.getString("morada");
                String email = rs.getString("email");
                String telemovel = rs.getString("telemovel");
                String nif = rs.getString("nif");
                Integer numGerente = rs.getInt("gerente");

                num_fun = rs.getInt("num_cliente"); //não percebi porque precisamos disto
                list.add(new Funcionario(num_fun, nome, morada, email, telemovel, nif, morada, numGerente));
            }
            return list;
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        } finally {
            DBConnection.closeConnection(stmt, rs);
        }
        return null;
    }

    public static Integer getFuncionarioIdByEmail(String email) {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Integer num_fun = null;

        try {

            stmt = conn.prepareStatement(
                    "SELECT num_fun FROM funcionario where email like ?;");
            stmt.setString(1, email);
            rs = stmt.executeQuery();

            while (rs.next()) {
                num_fun = rs.getInt("num_fun");
            }

            return num_fun;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnection.closeConnection(stmt, rs);
        }
        return null;
    }

    public static int UpdateFuncionario(String nome, String morada, String email,
            String telemovel, String nif,
            String old_email) {
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            // Verifica se já existe alguma conta com aquele e-mail
            stmt = conn.prepareStatement(
                    "SELECT count(num_fun) AS valor FROM funcionario where email like ?;");
            stmt.setString(1, email);
            rs = stmt.executeQuery();
            rs.next();

            if (rs.getInt("valor") > 0 && !email.equals(old_email)) {
                return codigoErroEmail;
            }

            stmt = conn.prepareStatement(
                    "UPDATE funcionario SET nome = ? , morada = ?, email = ?, telemovel = ? WHERE email = ?");
            stmt.setString(1, nome);
            stmt.setString(2, morada);
            stmt.setString(3, email);
            stmt.setString(4, telemovel);
            stmt.setString(5, old_email);
            stmt.execute();
            return codigoSucesso;
        } catch (SQLException e) {
            return codigoErro;
        } finally {
            DBConnection.closeConnection(stmt, rs);
        }
    }

    public static int UpdateFuncionario(String nome, String morada, String email,
            String telefone, String nif,
            String password, String old_email) {
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            // Verifica se já existe alguma conta com aquele e-mail
            stmt = conn.prepareStatement(
                    "SELECT count(num_fun) AS valor FROM funcionario where email like ?;");
            stmt.setString(1, email);
            rs = stmt.executeQuery();
            rs.next();

            if (rs.getInt("valor") > 0 && !email.equals(old_email)) {
                return codigoErroEmail;
            }

            stmt = conn.prepareStatement(
                    "UPDATE funcionario SET nome = ? , morada = ?, email = ?, telemovel = ?, password = ? WHERE email = ?");
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
}
