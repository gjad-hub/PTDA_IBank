package pt.ua.ibank.DAO;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import pt.ua.ibank.utilities.DBConnection;
import static pt.ua.ibank.utilities.DBConnection.conn;

public class PaymentsDAO {

    private final static int codigoSucesso = 1;
    private final static int codigoErro = 2;

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
            stmt.setBoolean(4, true);
            stmt.setInt(5, clienteRealiza);
            stmt.execute();
            return codigoSucesso;

        } catch (SQLException e) {
            return codigoErro;
        } finally {
            DBConnection.closeConnection(stmt);
        }
    }
}
