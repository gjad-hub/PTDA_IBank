package pt.ua.ibank.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
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

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao criar cliente: " + e);
        } finally {
            connection.closeConnection(stmt);
        }
    }
}
