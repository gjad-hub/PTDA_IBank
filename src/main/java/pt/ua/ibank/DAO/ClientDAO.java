package pt.ua.ibank.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import pt.ua.ibank.DTO.Cliente;
import pt.ua.ibank.services.connection;
import static pt.ua.ibank.services.connection.conn;
import static pt.ua.ibank.utilities.IbanGenerator.generateRandomIban;

public class ClientDAO {

    public static void CreateClient(String nome, String morada, String email, String telefone, String nif, String password) {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String num_conta;

        try {

            do {
                num_conta = generateRandomIban();
                stmt = conn.prepareStatement("SELECT count(num_cliente) AS valor FROM cliente where num_conta like ?;");
                stmt.setString(1, num_conta);
                rs = stmt.executeQuery();
                rs.next();
            } while (rs.getInt("valor") != 0);

            stmt = conn.prepareStatement("INSERT INTO cliente (nome, morada, email, telemovel, nif, num_conta, password) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?)");
            stmt.setString(1, nome);
            stmt.setString(2, morada);
            stmt.setString(3, email);
            stmt.setString(4, telefone);
            stmt.setString(5, nif);
            stmt.setString(6, num_conta);
            stmt.setString(7, password);
            stmt.execute();
            JOptionPane.showMessageDialog(null, "Registado com sucesso !");

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao criar cliente: " + e);
        } finally {
            connection.closeConnection(stmt);
        }
    }

    public static Cliente getClientByEmail(String email) {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Cliente cl = null;

        try {

            stmt = conn.prepareStatement("SELECT * FROM cliente where email like ?;");
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
                        rs.getDouble("saldo"));
            }

            return cl;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connection.closeConnection(stmt);
        }
        return null;
    }
}
