package pt.ua.ibank.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import pt.ua.ibank.DTO.PagamentoServicosCompras;
import pt.ua.ibank.utilities.DBConnection;
import static pt.ua.ibank.utilities.DBConnection.conn;

public class PaymentsDAO {

    public final static int codigoSucesso = 1;
    public final static int codigoErro = 2;

    public static int payService(double valor, int clienteRealiza,
                                 int cliente_criador, int entidade,
                                 int referencia) {
        PreparedStatement stmt = null;
        try {

            stmt = conn.prepareStatement(
            "UPDATE pagamento_servicos_compras SET pago = ?, cliente = ?, cliente_cria = ? WHERE entidade = ? and referencia = ?");
            stmt.setBoolean(1, true);
            stmt.setInt(2, clienteRealiza);
            stmt.setInt(3, cliente_criador);
            stmt.setInt(4, entidade);
            stmt.setInt(5, referencia);
            stmt.execute();

            return codigoSucesso;
        } catch (SQLException e) {
            e.printStackTrace(System.out);
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
                service = new PagamentoServicosCompras(rs.getInt("referencia"),
                                                       rs.getInt("entidade"),
                                                       rs.getDouble("valor"),
                                                       rs.getBoolean("pago"),
                                                       rs.getInt("cliente"),
                                                       rs.getInt("cliente_cria"),
                                                       rs.getBoolean("cancelada"));
            }
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        } finally {
            DBConnection.closeConnection(stmt, rs);
        }

        return service;
    }

    public static int createPayment(int cliente, int ent, int ref, double valor) {
        PreparedStatement stmt = null;
        try {

            stmt = conn.prepareStatement(
            "INSERT INTO pagamento_servicos_compras (entidade, referencia, valor, pago, cliente_cria, cancelada)"
            + "VALUES (?, ?, ?, ?, ?, ?)");
            stmt.setInt(1, ent);
            stmt.setInt(2, ref);
            stmt.setDouble(3, valor);
            stmt.setBoolean(4, false);
            stmt.setInt(5, cliente);
            stmt.setBoolean(6, false);
            stmt.execute();

            return codigoSucesso;
        } catch (SQLException e) {
            e.printStackTrace(System.out);
            return codigoErro;
        } finally {
            DBConnection.closeConnection(stmt);
        }
    }

    public static ArrayList<PagamentoServicosCompras> getAllServicos(
            int num_cliente) {
        PreparedStatement stmt = null;
        ResultSet rs = null;

        ArrayList<PagamentoServicosCompras> lservices = new ArrayList<>();

        try {
            stmt = conn.prepareStatement(
            "SELECT * FROM pagamento_servicos_compras WHERE cliente_cria = ? ORDER BY data_cri DESC;");
            stmt.setInt(1, num_cliente);
            rs = stmt.executeQuery();

            while (rs.next()) {
                PagamentoServicosCompras tr = new PagamentoServicosCompras(
                                         rs.getInt("referencia"),
                                         rs.getInt("entidade"),
                                         rs.getDouble("valor"),
                                         rs.getBoolean("pago"),
                                         rs.getInt("cliente"),
                                         rs.getInt("cliente_cria"),
                                         rs.getBoolean("cancelada")
                                 );
                lservices.add(tr);
            }
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        } finally {
            DBConnection.closeConnection(stmt, rs);
        }

        return lservices;
    }

    public static int cancelPayment(int ent, int ref) {
        PreparedStatement stmt = null;

        try {
            stmt = conn.prepareStatement(
            "UPDATE pagamento_servicos_compras SET cancelada = ? WHERE entidade = ? AND referencia = ?");
            stmt.setBoolean(1, true);
            stmt.setInt(2, ent);
            stmt.setInt(3, ref);

            stmt.execute();

            return codigoSucesso;
        } catch (SQLException e) {
            e.printStackTrace(System.out);
            return codigoErro;
        } finally {
            DBConnection.closeConnection(stmt);
        }
    }
}
