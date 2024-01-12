package pt.ua.ibank.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import static pt.ua.ibank.DAO.PaymentsDAO.codigoErro;
import static pt.ua.ibank.DAO.PaymentsDAO.codigoSucesso;
import pt.ua.ibank.DTO.Funcionario;
import pt.ua.ibank.DTO.ModeratorComment;
import pt.ua.ibank.DTO.Transferencias;
import pt.ua.ibank.utilities.DBConnection;
import static pt.ua.ibank.utilities.DBConnection.conn;

public class TransfersDAO {

    public final static int codigoSucesso = 1;
    public final static int codigoErro = 2;

    public static int doTransfer(double valor, int clienteRealiza,
            int clienteRecebe, String motivo) {
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

            return list;
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        } finally {
            DBConnection.closeConnection(stmt, rs);
        }
        return null;
    }

    public static boolean createTransfer(double valor, int clienteRealiza,
            int clienteRecebe, String motivo) {
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement(
                    "INSERT INTO funcionario_cliente(num_fun,num_cli)"
                    + "VALUES (?, ?, ?, ?");
            stmt.setInt(1, Funcionario.LocalFuncionario.getNumFun());
            stmt.setInt(2, clienteRealiza);
            stmt.execute();

            stmt = conn.prepareStatement(
                    "INSERT INTO funcionario_cliente(num_fun,num_cli)"
                    + "VALUES (?, ?, ?, ?");
            stmt.setInt(1, Funcionario.LocalFuncionario.getNumFun());
            stmt.setInt(2, clienteRecebe);
            stmt.execute();

            stmt = conn.prepareStatement(
                    "INSERT INTO transferencia (valor, cliente_realiza, cliente_recebe, motivo)"
                    + "VALUES (?, ?, ?, ?");
            stmt.setDouble(1, valor);
            stmt.setInt(2, clienteRealiza);
            stmt.setInt(3, clienteRecebe);
            stmt.setString(4, motivo);
            stmt.execute();

            return true;
        } catch (SQLException e) {
            e.printStackTrace(System.out);
            return false;
        } finally {
            DBConnection.closeConnection(stmt);
        }
    }

}
