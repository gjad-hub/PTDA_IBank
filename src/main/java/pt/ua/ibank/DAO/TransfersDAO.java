package pt.ua.ibank.DAO;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import pt.ua.ibank.utilities.DBConnection;
import static pt.ua.ibank.utilities.DBConnection.conn;

public class TransfersDAO {

    private final static int codigoSucesso = 1;
    private final static int codigoErro = 2;
    private final static int codigoErroEmail = 3;

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
            return codigoSucesso;

        } catch (SQLException e) {
            return codigoErro;
        } finally {
            DBConnection.closeConnection(stmt);
        }
    }
}
