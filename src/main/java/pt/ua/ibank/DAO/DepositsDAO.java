package pt.ua.ibank.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import pt.ua.ibank.DTO.Deposito;
import pt.ua.ibank.utilities.DBConnection;
import static pt.ua.ibank.utilities.DBConnection.conn;

public class DepositsDAO {

    public final static int codigoSucesso = 1;
    public final static int codigoErro = 2;

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

    public static ArrayList<Deposito> getDeposits(int num_cliente) {
        PreparedStatement stmt = null;
        ResultSet rs = null;

        ArrayList<Deposito> ldeposito = new ArrayList<>();

        try {
            stmt = conn.prepareStatement(
                    "SELECT valor, aprovado FROM deposito WHERE num_cli = ? ORDER BY data desc;");
            stmt.setInt(1, num_cliente);
            rs = stmt.executeQuery();

            while (rs.next()) {
                Deposito tr = new Deposito(rs.getDouble("valor"), rs.getBoolean(
                        "aprovado"));
                ldeposito.add(tr);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnection.closeConnection(stmt, rs);
        }

        return ldeposito;
    }

    public static int getDepositCount(int num_cliente) {
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            stmt = conn.prepareStatement(
                    "SELECT count(*) AS qtd FROM deposito WHERE num_cli = ?");
            stmt.setInt(1, num_cliente);
            rs = stmt.executeQuery();

            while (rs.next()) {
                return rs.getInt("qtd");
            }
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        } finally {
            DBConnection.closeConnection(stmt, rs);
        }
        return -1;
    }

}
