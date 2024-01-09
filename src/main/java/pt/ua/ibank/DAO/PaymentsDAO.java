package pt.ua.ibank.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import pt.ua.ibank.DTO.PagamentoServicosCompras;
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
    
        public static PagamentoServicosCompras getServicosCompras(int ref, int ent) {
        PreparedStatement stmt = null;
        ResultSet rs = null;

        PagamentoServicosCompras service = null;

        try {
            stmt = conn.prepareStatement(
                    "SELECT * FROM  pagamento_servicos_compras WHERE referencia = ? AND entidade = ?;");
            stmt.setInt(1, ref);
            stmt.setInt(2, ent);
            rs = stmt.executeQuery();

            while (rs.next()) {
                service = new PagamentoServicosCompras(rs.getInt("referencia"), rs.getInt("entidade"), 
                        rs.getDouble("valor"), rs.getBoolean("pago"), rs.getInt("cliente"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnection.closeConnection(stmt, rs);
        }

        return service;
    }
}
