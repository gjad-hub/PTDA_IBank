package pt.ua.ibank.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import pt.ua.ibank.DTO.PaymentServices;
import static pt.ua.ibank.utilities.Configs.ERROR_CODE;
import static pt.ua.ibank.utilities.Configs.SUCCESS_CODE;
import pt.ua.ibank.utilities.DBConnection;
import static pt.ua.ibank.utilities.DBConnection.conn;

/**
 * Class with static methods associated with operations performed with external
 * Payments
 * stored in a MySQL database.
 * Author: PTDA_Staff.
 * Last Modification Date: January 12, 2024
 */
public class PaymentsDAO {

    /**
     * Function used to pay for a service or purchase.
     *
     * @param performingClient External Client ID that made the transfer.
     * @param entity           Transfer entity with External ID.
     * @param reference        External Reference ID.
     * @return Error code, 1 = Success, 2 = Error.
     */
    public static int payService(int performingClient, int entity, int reference) {
        PreparedStatement stmt = null;
        try {

            stmt = conn.prepareStatement(
            "UPDATE payment_services_purchases SET paid = ?, customer = ? WHERE entity = ? and reference = ?");
            stmt.setBoolean(1, true);
            stmt.setInt(2, performingClient);
            stmt.setInt(3, entity);
            stmt.setInt(4, reference);
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
     * Function that returns the list of Services and purchases through entity
     * and
     * reference.
     *
     * @param ref Reference of service and purchase used as reference.
     * @param ent Entity of service and purchase used as reference.
     * @return Returns ServicesCompras object related to reference and entity.
     */
    public static PaymentServices getServicesPurchases(int ref, int ent) {
        PreparedStatement stmt = null;
        ResultSet rs = null;

        PaymentServices service = null;

        try {
            stmt = conn.prepareStatement(
            "SELECT * FROM payment_services_purchases WHERE reference = ? AND entity = ?;");
            stmt.setInt(1, ref);
            stmt.setInt(2, ent);
            rs = stmt.executeQuery();

            while (rs.next()) {
                service = new PaymentServices(rs.getInt("reference"),
                                              rs.getInt("entity"),
                                              rs.getDouble("amount"),
                                              rs.getBoolean("paid"),
                                              rs.getInt("customer"),
                                              rs.getInt("created_by"),
                                              rs.getBoolean("canceled"));
            }
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        } finally {
            DBConnection.closeConnection(stmt, rs);
        }

        return service;
    }

    /**
     * Function used to create a payment linked to a client, entity, and
     * reference with a amount to be paid.
     *
     * @param client Client who made the payment reference.
     * @param ent    Entity generated for payment.
     * @param ref    Reference used for payment.
     * @param amount  Value to be charged to the client.
     * @return Returns the success state, 1 if positive, 2 if error.
     */
    public static int createPayment(int client, int ent, int ref, double amount) {
        PreparedStatement stmt = null;
        try {

            stmt = conn.prepareStatement(
            "INSERT INTO payment_services_purchases (entity, reference, amount, paid, created_by, canceled)"
            + "VALUES (?, ?, ?, ?, ?, ?)");
            stmt.setInt(1, ent);
            stmt.setInt(2, ref);
            stmt.setDouble(3, amount);
            stmt.setBoolean(4, false);
            stmt.setInt(5, client);
            stmt.setBoolean(6, false);
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
     * Function used to obtain all external services related to an ID.
     *
     * @param clientID ID associated with an external service list.
     * @return Returns list associated with a Client ID.
     */
    public static ArrayList<PaymentServices> getAllServices(int clientID) {
        PreparedStatement stmt = null;
        ResultSet rs = null;

        ArrayList<PaymentServices> servicesList = new ArrayList<>();

        try {
            stmt = conn.prepareStatement(
            "SELECT * FROM payment_services_purchases WHERE created_by = ? ORDER BY creation_date DESC;");
            stmt.setInt(1, clientID);
            rs = stmt.executeQuery();

            while (rs.next()) {
                PaymentServices service = new PaymentServices(
                                rs.getInt("reference"),
                                rs.getInt("entity"),
                                rs.getDouble("amount"),
                                rs.getBoolean("paid"),
                                rs.getInt("customer"),
                                rs.getInt("created_by"),
                                rs.getBoolean("canceled")
                        );
                servicesList.add(service);
            }
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        } finally {
            DBConnection.closeConnection(stmt, rs);
        }

        return servicesList.isEmpty() ? null : servicesList;
    }

    /**
     * Function used to cancel an external payment.
     *
     * @param ent Entity generated for payment.
     * @param ref Reference used for payment.
     * @return Returns the success state, 1 if positive, 2 if error.
     */
    public static int cancelPayment(int ent, int ref) {
        PreparedStatement stmt = null;

        try {
            stmt = conn.prepareStatement(
            "UPDATE payment_services_purchases SET canceled = ? WHERE entity = ? AND reference = ?");
            stmt.setBoolean(1, true);
            stmt.setInt(2, ent);
            stmt.setInt(3, ref);

            stmt.execute();

            return SUCCESS_CODE;
        } catch (SQLException e) {
            e.printStackTrace(System.out);
            return ERROR_CODE;
        } finally {
            DBConnection.closeConnection(stmt);
        }
    }
}
