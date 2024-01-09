package pt.ua.ibank.DAO;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import pt.ua.ibank.utilities.DBConnection;
import static pt.ua.ibank.utilities.DBConnection.conn;

public class DepositsDAO {

    protected final static int codigoSucesso = 1;
    protected final static int codigoErro = 2;

    public static int requestDeposit(double valor, int clienteRealiza) {
        PreparedStatement stmt = null;
        try {
            
            stmt = conn.prepareStatement(
                    "INSERT INTO deposito (valor, num_cli) "
                    + "VALUES (?,?)"
            );

            stmt.setDouble(1, valor);
            stmt.setInt(2, clienteRealiza);
            stmt.execute();
            return codigoSucesso;
        } catch (SQLException e) {
            return codigoErro;
        } finally {
            DBConnection.closeConnection(stmt);
        }
    }
}
