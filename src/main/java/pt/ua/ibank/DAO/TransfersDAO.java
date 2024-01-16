package pt.ua.ibank.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import pt.ua.ibank.DTO.Transferencias;
import pt.ua.ibank.utilities.DBConnection;
import static pt.ua.ibank.utilities.DBConnection.conn;

/**
 *
 * @author ricar
 */
public class TransfersDAO {

    /**
     *
     */
    public final static int codigoSucesso = 1;

    /**
     *
     */
    public final static int codigoErro = 2;

    /**
     *
     * @param valor
     * @param clienteRealiza
     * @param clienteRecebe
     * @param motivo
     * @return
     */
    public static int doTransfer(double valor, int clienteRealiza,
                                 int clienteRecebe, String motivo) {
        PreparedStatement stmt = null;

        try {

            stmt = conn.prepareCall("{call fazer_transferencia(?,?,?,?)}");
            stmt.setInt(1, clienteRealiza);
            stmt.setInt(2, clienteRecebe);
            stmt.setDouble(3, valor);
            stmt.setString(4, motivo);
            stmt.execute();

            return codigoSucesso;
        } catch (SQLException e) {
            e.printStackTrace(System.out);
            return codigoErro;
        } finally {
            DBConnection.closeConnection(stmt);
        }
    }

    public static ArrayList<Transferencias> getTransfersList() {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ArrayList<Transferencias> list = new ArrayList<>();

        try {
            stmt = conn.prepareStatement(
            "select "
            + "id_transferencia as id,valor,cliente_realiza,cliente_recebe,motivo"
            + " from transferencia;");
            rs = stmt.executeQuery();

            while (rs.next()) {
                list.add(new Transferencias(
                        rs.getInt("id"),
                        rs.getDouble("valor"),
                        rs.getInt("cliente_realiza"),
                        rs.getInt("cliente_recebe"),
                        rs.getString("motivo")
                ));
            }

            return list.isEmpty() ? null : list;
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        } finally {
            DBConnection.closeConnection(stmt, rs);
        }
        return null;
    }

    /**
     *
     * @param id
     * @return
     */
    public static Transferencias getTransferByID(int id) {
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            stmt = conn.prepareStatement(
            "select "
            + "id_transferencia as id,valor,cliente_realiza,cliente_recebe,motivo"
            + " from transferencia where id_transferencia like ?;");
            stmt.setInt(1, id);
            rs = stmt.executeQuery();

            while (rs.next()) {
                return new Transferencias(
                        rs.getInt("id"),
                        rs.getDouble("valor"),
                        rs.getInt("cliente_realiza"),
                        rs.getInt("cliente_recebe"),
                        rs.getString("motivo")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace(System.out);
        } finally {
            DBConnection.closeConnection(stmt, rs);
        }
        return null;
    }

    /**
     *
     * @param id
     * @return
     */
    public static ArrayList<Transferencias> getClientTransfersList(
            int id) {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ArrayList<Transferencias> list = new ArrayList<>();

        try {
            stmt = conn.prepareStatement(
            "select "
            + "id_transferencia as id,valor,cliente_realiza,cliente_recebe,motivo"
            + " from transferencia where cliente_realiza like ? or cliente_recebe like ?;");
            stmt.setInt(1, id);
            stmt.setInt(2, id);
            rs = stmt.executeQuery();

            while (rs.next()) {
                list.add(new Transferencias(
                        rs.getInt("id"),
                        rs.getDouble("valor"),
                        rs.getInt("cliente_realiza"),
                        rs.getInt("cliente_recebe"),
                        rs.getString("motivo")
                ));
            }

            return list.isEmpty() ? null : list;
        } catch (SQLException e) {
            e.printStackTrace(System.out);
            return null;
        } finally {
            DBConnection.closeConnection(stmt, rs);
        }
    }

    /**
     *
     * @param id
     * @return
     */
    public static ArrayList<Transferencias> getTransfersListByAutor(
            int id) {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ArrayList<Transferencias> list = new ArrayList<>();

        try {
            stmt = conn.prepareStatement(
            "select "
            + "id_transferencia as id,valor,cliente_realiza,cliente_recebe,motivo"
            + " from transferencia where cliente_realiza like ?;");
            stmt.setInt(1, id);
            rs = stmt.executeQuery();

            while (rs.next()) {
                list.add(new Transferencias(
                        rs.getInt("id"),
                        rs.getDouble("valor"),
                        rs.getInt("cliente_realiza"),
                        rs.getInt("cliente_recebe"),
                        rs.getString("motivo")
                ));
            }

            return list.isEmpty() ? null : list;
        } catch (SQLException e) {
            e.printStackTrace(System.out);
            return null;
        } finally {
            DBConnection.closeConnection(stmt, rs);
        }
    }

    /**
     *
     * @param id
     * @return
     */
    public static ArrayList<Transferencias> getTransfersListByReceptor(
            int id) {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ArrayList<Transferencias> list = new ArrayList<>();

        try {
            stmt = conn.prepareStatement(
            "select "
            + "id_transferencia as id,valor,cliente_realiza,cliente_recebe,motivo"
            + " from transferencia where cliente_recebe like ?;");
            stmt.setInt(1, id);
            rs = stmt.executeQuery();

            while (rs.next()) {
                list.add(new Transferencias(
                        rs.getInt("id"),
                        rs.getDouble("valor"),
                        rs.getInt("cliente_realiza"),
                        rs.getInt("cliente_recebe"),
                        rs.getString("motivo")
                ));
            }

            return list.isEmpty() ? null : list;
        } catch (SQLException e) {
            e.printStackTrace(System.out);
            return null;
        } finally {
            DBConnection.closeConnection(stmt, rs);
        }
    }
}
