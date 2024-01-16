package pt.ua.ibank.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import pt.ua.ibank.DTO.Cartao;
import static pt.ua.ibank.utilities.CardGenerator.generateCardNumber;
import pt.ua.ibank.utilities.DBConnection;
import static pt.ua.ibank.utilities.DBConnection.conn;

/**
 *
 * @author ricar
 */
public class CardsDAO {

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
     * @param number numero do cartão a ser procurado
     * @return
     */
    public static Cartao getCardByNumber(String number) {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Cartao cartao = null;

        try {

            stmt = conn.prepareStatement(
            "SELECT * FROM cartao where num_cartao like ?;");
            stmt.setString(1, number);
            rs = stmt.executeQuery();

            while (rs.next()) {
                cartao = new Cartao(
                rs.getString("num_cartao"),
                rs.getTimestamp("data_validade"),
                rs.getInt("cliente"));
            }

            return cartao;
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        } finally {
            DBConnection.closeConnection(stmt, rs);
        }
        return null;
    }

    /**
     *
     * @param num_cliente
     * @return
     */
    public static ArrayList<Cartao> getCardListFromUserID(int num_cliente) {
        PreparedStatement stmt = null;
        ResultSet rs = null;

        ArrayList<Cartao> lcartao = new ArrayList<>();

        try {
            stmt = conn.prepareStatement(
            "SELECT num_cartao, data_validade, estado FROM cartao WHERE cliente = ?;");
            stmt.setInt(1, num_cliente);
            rs = stmt.executeQuery();

            while (rs.next()) {
                Cartao tr = new Cartao(rs.getString("num_cartao"),
                                       rs.getTimestamp("data_validade"),
                                       rs.getString("estado"));
                lcartao.add(tr);
            }
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        } finally {
            DBConnection.closeConnection(stmt, rs);
        }

        return lcartao;
    }

    /**
     *
     * @param num_cliente
     * @return
     */
    public static int getCardAmountByID(int num_cliente) {
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            stmt = conn.prepareStatement(
            "SELECT count(*) as qtd FROM cartao WHERE cliente = ?;");
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

    /**
     *
     * @param card_number
     * @return
     */
    public static int cancelCard(String card_number) {
        PreparedStatement stmt = null;

        try {
            stmt = conn.prepareStatement(
            "UPDATE cartao SET estado = \"cancelado\" WHERE num_cartao LIKE ? ");
            stmt.setString(1, card_number);
            stmt.execute();

            return codigoSucesso;
        } catch (SQLException e) {
            e.printStackTrace(System.out);
            return codigoErro;
        } finally {
            DBConnection.closeConnection(stmt);
        }
    }

    /**
     *
     * @param card_number
     * @param num_cliente
     * @return
     */
    public static int makeDefault(String card_number, int num_cliente) {
        PreparedStatement stmt = null;

        try {
            stmt = conn.prepareStatement(
            "UPDATE cliente SET cartao_default = ? WHERE num_cliente = ?");
            stmt.setString(1, card_number);
            stmt.setInt(2, num_cliente);
            stmt.execute();

            return codigoSucesso;
        } catch (Exception e) {
            e.printStackTrace(System.out);
            return codigoErro;
        } finally {
            DBConnection.closeConnection(stmt);
        }

    }

    /**
     *
     * @param num_cliente
     * @return
     */
    public static int createCard(int num_cliente) {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String num_cartao;

        try {
            // Gera um novo cartao até não existir nenhum cartao com este numero
            do {
                num_cartao = generateCardNumber();
                stmt = conn.prepareStatement(
                "SELECT count(num_cartao) AS valor FROM cartao where num_cartao like ?;");
                stmt.setString(1, num_cartao);
                rs = stmt.executeQuery();
                rs.next();
            } while (rs.getInt("valor") != 0);
            stmt.close();

            stmt = conn.prepareStatement(
            "INSERT INTO cartao (num_cartao, data_validade, estado, cliente) "
            + "VALUES (?, (SELECT DATE_ADD(CURDATE(), INTERVAL +5 YEAR )), \"activo\", ?);");
            stmt.setString(1, num_cartao);
            stmt.setInt(2, num_cliente);
            stmt.execute();

            return codigoSucesso;
        } catch (SQLException e) {
            return codigoErro;
        } finally {
            DBConnection.closeConnection(stmt, rs);
        }
    }
}
