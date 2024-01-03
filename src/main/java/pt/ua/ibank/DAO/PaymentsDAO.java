package pt.ua.ibank.DAO;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import pt.ua.ibank.services.DBConnection;
import static pt.ua.ibank.services.DBConnection.conn;

public class PaymentsDAO {
    
    public static int payService(double valor, int clienteRealiza, String entidade, String referencia) {
        PreparedStatement stmt = null;
        try {
            
            stmt = conn.prepareStatement(
                    "INSERT INTO pagamento_servicos_compras (referencia, entidade, valor, estado, cliente) "
                    + "VALUES (?,?,?,?,?)"
            );

            stmt.setInt(1, Integer.parseInt(referencia));
            stmt.setInt(2, Integer.parseInt(entidade));
            stmt.setDouble(3, valor);
            stmt.setString(4, "pago");
            stmt.setInt(5, clienteRealiza);
            stmt.execute();
            return 1;

        } catch (SQLException e) {
            return 2;
        } finally {
            DBConnection.closeConnection(stmt);
        }
    }
}
