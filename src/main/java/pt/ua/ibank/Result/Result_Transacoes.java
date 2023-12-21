package pt.ua.ibank.Result;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import pt.ua.ibank.DTO.transacoes;
import pt.ua.ibank.services.connection;
import static pt.ua.ibank.services.connection.conn;

public class Result_Transacoes {
     public static ArrayList<transacoes> getTransacoes() {
        PreparedStatement stmt = null;
        ResultSet rs = null;

        ArrayList<transacoes> ltransacoes = new ArrayList<>();

        try {
            stmt = conn.prepareStatement("SELECT * FROM  transacoes;");
            rs = stmt.executeQuery();

            while (rs.next()) {
                transacoes tr = new transacoes();
                tr.setId(rs.getInt("id"));
                tr.setNum_cli(rs.getInt("num_cli"));
                tr.setDescricao(rs.getString("descricao"));
                tr.setValor(rs.getInt("valor"));
                ltransacoes.add(tr);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connection.closeConnection(stmt, rs);
        }

        return ltransacoes;
    }

}
