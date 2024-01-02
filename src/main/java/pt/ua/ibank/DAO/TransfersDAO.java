package pt.ua.ibank.DAO;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import pt.ua.ibank.services.DBConnection;
import static pt.ua.ibank.services.DBConnection.conn;

public class TransfersDAO {

    public static int doTransfer(double valor, int clienteRealiza, int clienteRecebe, String motivo) {
       // Adiconar as verificacoes
        PreparedStatement stmt = null;

        try {

            stmt = conn.prepareStatement(
                    "INSERT INTO transferencia (valor, cliente_realiza, cliente_recebe, motivo) "
                    + "VALUES (?,?,?,?)");
            stmt.setDouble(1, valor);
            stmt.setInt(2, clienteRealiza);
            stmt.setInt(3, clienteRecebe);
            stmt.setString(4, motivo);
            stmt.execute();
            return 1;

        } catch (SQLException e) {
            return 2;
        } finally {
            DBConnection.closeConnection(stmt);
        }
    }
}
