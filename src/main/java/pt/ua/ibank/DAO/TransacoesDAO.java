package pt.ua.ibank.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import pt.ua.ibank.DTO.Transacoes;
import pt.ua.ibank.utilities.DBConnection;
import static pt.ua.ibank.utilities.DBConnection.conn;

public class TransacoesDAO {

    public static ArrayList<Transacoes> getTransacoes(int num_cliente) {
        PreparedStatement stmt = null;
        ResultSet rs = null;

        ArrayList<Transacoes> ltransacoes = new ArrayList<>();

        try {
            stmt = conn.prepareStatement(
                    "SELECT * FROM  transacoes WHERE num_cli = ?;");
            stmt.setInt(1, num_cliente);
            rs = stmt.executeQuery();

            while (rs.next()) {
                Transacoes tr = new Transacoes(
                        rs.getInt("id"),
                        rs.getInt("num_cli"), 
                        rs.getString("descricao"), 
                        rs.getDouble("valor"), 
                        rs.getTimestamp("data"));
                ltransacoes.add(tr);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnection.closeConnection(stmt, rs);
        }

        return ltransacoes;
    }
}
