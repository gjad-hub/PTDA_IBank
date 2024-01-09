package pt.ua.ibank.DAO;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import pt.ua.ibank.utilities.DBConnection;
import static pt.ua.ibank.utilities.DBConnection.conn;

public class PaymentsDAO {

    protected final static int codigoSucesso = 1;
    protected final static int codigoErro = 2;

    public static int payService(double valor, int clienteRealiza, int entidade, int referencia) {
        PreparedStatement stmt = null;
        try {

            stmt = conn.prepareStatement("UPDATE pagamento_servicos_compras SET pago = ?, cliente = ? WHERE entidade = ? and referencia = ?");
            stmt.setBoolean(1, true);
            stmt.setInt(2, clienteRealiza);
            stmt.setInt(3, entidade);
            stmt.setInt(4, referencia);
            stmt.execute();
            
            return codigoSucesso;
        } catch (SQLException e) {
            return codigoErro;
        } finally {
            DBConnection.closeConnection(stmt);
        }
    }
}
