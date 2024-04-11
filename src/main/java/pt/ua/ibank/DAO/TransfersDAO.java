package pt.ua.ibank.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import pt.ua.ibank.DTO.Transfers;
import static pt.ua.ibank.utilities.Configs.ERROR_CODE;
import static pt.ua.ibank.utilities.Configs.SUCCESS_CODE;
import pt.ua.ibank.utilities.DBConnection;
import static pt.ua.ibank.utilities.DBConnection.conn;

/**
 * Class with static methods associated with operations performed with external
 * Transfers
 * stored in a MySQL database.
 * Author: PTDA_Staff.
 * Last Modification Date: January 15, 2024
 */
public class TransfersDAO {

    /**
     * Function for
     *
     * @param amount            transfer amount
     * @param performingClient ID of the Client performing the Transfer
     * @param receivingClient  ID of the Client receiving the Transfer
     * @param reason           content / description of the transfer made
     * @return returns success code, 1 if successful, 2 otherwise
     */
    public static int doTransfer(double amount, int performingClient,
                                 int receivingClient, String reason) {
        PreparedStatement stmt = null;

        try {

            stmt = conn.prepareCall("{call make_transfer(?,?,?,?)}");
            stmt.setInt(1, performingClient);
            stmt.setInt(2, receivingClient);
            stmt.setDouble(3, amount);
            stmt.setString(4, reason);
            stmt.execute();

            return SUCCESS_CODE;
        } catch (SQLException e) {
            e.printStackTrace(System.out);
            return ERROR_CODE;
        } finally {
            DBConnection.closeConnection(stmt);
        }
    }

    /**
     * Function used to get a list of external transfers
     *
     * @return List of transfers
     */
    public static ArrayList<Transfers> getTransfersList() {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ArrayList<Transfers> list = new ArrayList<>();

        try {
            stmt = conn.prepareStatement(
            "select "
            + "transfer_id as id,amount,performing_customer,receiving_customer,reason"
            + " from transfers;");
            rs = stmt.executeQuery();

            while (rs.next()) {
                list.add(new Transfers(
                        rs.getInt("id"),
                        rs.getDouble("amount"),
                        rs.getInt("performing_customer"),
                        rs.getInt("receiving_customer"),
                        rs.getString("reason")
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
     * Function that returns a list of transfers by ID
     *
     * @param id Client ID to be used as reference
     * @return
     */
    public static Transfers getTransferByID(int id) {
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            stmt = conn.prepareStatement(
            "select "
            + "transfer_id as id,amount,performing_customer,receiving_customer,reason"
            + " from transfers where transfer_id like ?;");
            stmt.setInt(1, id);
            rs = stmt.executeQuery();

            while (rs.next()) {
                return new Transfers(
                        rs.getInt("id"),
                        rs.getDouble("amount"),
                        rs.getInt("performing_customer"),
                        rs.getInt("receiving_customer"),
                        rs.getString("reason")
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
     * Function that returns a list of Transfers by an ID
     *
     * @param id Client ID to be used as reference
     * @return returns a list of transactions made by a manager
     */
    public static ArrayList<Transfers> getClientTransfersList(
            int id) {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ArrayList<Transfers> list = new ArrayList<>();

        try {
            stmt = conn.prepareStatement(
            "select "
            + "transfer_id as id,amount,performing_customer,receiving_customer,reason"
            + " from transfers where performing_customer like ? or receiving_customer like ?;");
            stmt.setInt(1, id);
            stmt.setInt(2, id);
            rs = stmt.executeQuery();

            while (rs.next()) {
                list.add(new Transfers(
                        rs.getInt("id"),
                        rs.getDouble("amount"),
                        rs.getInt("performing_customer"),
                        rs.getInt("receiving_customer"),
                        rs.getString("reason")
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
     * Function that returns the list of transfers made by a Client through
     * ID
     *
     * @param id Client ID to be used as reference
     * @return returns a list of transfers made by a Client
     */
    public static ArrayList<Transfers> getTransfersListByAuthor(
            int id) {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ArrayList<Transfers> list = new ArrayList<>();

        try {
            stmt = conn.prepareStatement(
            "select "
            + "transfer_id as id,amount,performing_customer,receiving_customer,reason"
            + " from transfers where performing_customer like ?;");
            stmt.setInt(1, id);
            rs = stmt.executeQuery();

            while (rs.next()) {
                list.add(new Transfers(
                        rs.getInt("id"),
                        rs.getDouble("amount"),
                        rs.getInt("performing_customer"),
                        rs.getInt("receiving_customer"),
                        rs.getString("reason")
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
     * Function that returns the list of transfers received by a Client
     * through ID
     *
     * @param id Client ID to be used as reference
     * @return returns a list of transfers that a Client received
     */
    public static ArrayList<Transfers> getTransfersListByRecipient(
            int id) {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ArrayList<Transfers> list = new ArrayList<>();

        try {
            stmt = conn.prepareStatement(
            "select "
            + "transfer_id as id,amount,performing_customer,receiving_customer,reason"
            + " from transfers where receiving_customer like ?;");
            stmt.setInt(1, id);
            rs = stmt.executeQuery();

            while (rs.next()) {
                list.add(new Transfers(
                        rs.getInt("id"),
                        rs.getDouble("amount"),
                        rs.getInt("performing_customer"),
                        rs.getInt("receiving_customer"),
                        rs.getString("reason")
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
