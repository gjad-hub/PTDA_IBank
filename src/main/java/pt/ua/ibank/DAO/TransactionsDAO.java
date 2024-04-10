package pt.ua.ibank.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import pt.ua.ibank.DTO.Transactions;
import pt.ua.ibank.utilities.DBConnection;
import static pt.ua.ibank.utilities.DBConnection.conn;

/**
 * Class with static methods associated with operations on external transactions
 * stored
 * in a MySQL database.
 * Author: PTDA_Staff.
 * Last Modification Date: December 17, 2024
 */
public class TransactionsDAO {

    /**
     * Function used to retrieve transactions of a customer through an ID.
     *
     * @param customerID External customer ID associated with a transaction
     * @return returns a list of transactions associated with this customer
     */
    public static ArrayList<Transactions> getTransactionsByClientID(int customerID) {
        PreparedStatement stmt = null;
        ResultSet rs = null;

        ArrayList<Transactions> transactionList = new ArrayList<>();

        try {
            stmt = conn.prepareStatement(
            "SELECT * FROM transactions WHERE customer_iban = ?;");
            stmt.setInt(1, customerID);
            rs = stmt.executeQuery();

            while (rs.next()) {
                Transactions transaction = new Transactions(
                             rs.getInt("id"),
                             rs.getInt("customer_iban"),
                             rs.getString("description"),
                             rs.getDouble("amount"),
                             rs.getTimestamp("creation_date"));
                transactionList.add(transaction);
            }
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        } finally {
            DBConnection.closeConnection(stmt, rs);
        }

        return transactionList.isEmpty() ? null : transactionList;
    }
}
