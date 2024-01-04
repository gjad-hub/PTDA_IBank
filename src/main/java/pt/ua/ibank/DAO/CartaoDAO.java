package pt.ua.ibank.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import pt.ua.ibank.DTO.Cartao;
import pt.ua.ibank.services.DBConnection;
import static pt.ua.ibank.services.DBConnection.conn;

public class CartaoDAO {

    public static Cartao getCardByNumber(String number) {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Cartao cartao = null;

        try {

            stmt = conn.prepareStatement(
                    "SELECT * FROM cartao where num_cartao like ?;");
            stmt.setString(1, number);
            rs = stmt.executeQuery();

            while (rs.next()) {
                cartao = new Cartao(
                        rs.getString("num_cartao"),
                        rs.getDate("data_validade"),
                        rs.getString("cliente"),
                        rs.getBoolean("credito"));
            }

            return cartao;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnection.closeConnection(stmt, rs);
        }
        return null;
    }
}
