package pt.ua.ibank.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import pt.ua.ibank.DTO.Deposit;
import static pt.ua.ibank.utilities.Configs.ERROR_CODE;
import static pt.ua.ibank.utilities.Configs.SUCCESS_CODE;
import pt.ua.ibank.utilities.DBConnection;
import static pt.ua.ibank.utilities.DBConnection.conn;

/**
 * Class with static methods associated with operations on external deposits
 * stored
 * in a MySQL database.
 * Author: PTDA_Staff.
 * Last Modification Date: December 27, 2023
 */
public class DepositsDAO {

    /**
     * Function used to request a deposit.
     *
     * @param amount           requested deposit amount
     * @param customerPerforms customerID used as reference
     * @return returns success code, 1 Success, 2 Error email, 3 SQL Error
     */
    public static int requestDeposit(double amount, int customerPerforms) {
        PreparedStatement stmt = null;
        try {

            stmt = conn.prepareStatement(
            "INSERT INTO deposit (amount, customer_id) "
            + "VALUES (?,?)"
    );

            stmt.setDouble(1, amount);
            stmt.setInt(2, customerPerforms);
            stmt.execute();
            return SUCCESS_CODE;
        } catch (SQLException e) {
            return ERROR_CODE;
        } finally {
            DBConnection.closeConnection(stmt);
        }
    }

    /**
     * Function used to get a list of deposits through a customer number.
     *
     * @param customerID customerID used as reference
     * @return returns list of deposits associated with this customer
     */
    public static ArrayList<Deposit> getDeposits(int customerID) {
        PreparedStatement stmt = null;
        ResultSet rs = null;

        ArrayList<Deposit> depositList = new ArrayList<>();

        try {
            stmt = conn.prepareStatement(
            "SELECT id_deposit, amount, approved, pending_approval, num_fun, "
            + "num_cli FROM deposit WHERE num_cli = ? ORDER BY date desc;");
            stmt.setInt(1, customerID);
            rs = stmt.executeQuery();

            while (rs.next()) {
                Deposit deposit;

                boolean isPending = rs.getBoolean("pending_approval");
                if (isPending) {
                    deposit = new Deposit(rs.getInt("id_deposit"),
                                          rs.getDouble("amount"));
                } else {
                    deposit = new Deposit(rs.getInt("id_deposit"),
                                          rs.getDouble("amount"),
                                          rs.getBoolean("approved"),
                                          rs.getInt("num_fun"),
                                          rs.getInt("num_cli")
            );
                }

                depositList.add(deposit);
            }
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        } finally {
            DBConnection.closeConnection(stmt, rs);
        }

        return depositList.isEmpty() ? null : depositList;
    }

    /**
     * Function used to approve an external deposit.
     *
     * @param depositID  deposit ID used as reference
     * @param employeeID employee ID used as reference
     * @return
     */
    public static boolean approveDeposit(int depositID, int employeeID) {
        PreparedStatement stmt = null;

        try {
            stmt = conn.prepareCall(
            "{call approve_deposit(?,?)}");
            stmt.setInt(1, depositID);
            stmt.setInt(2, employeeID);
            stmt.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace(System.out);
            return false;
        } finally {
            DBConnection.closeConnection(stmt);
        }
    }

    /**
     * Function used to deny a request for an external deposit.
     *
     * @param depositID  deposit ID used as reference
     * @param employeeID employee ID used as reference
     * @return
     */
    public static boolean denyDeposit(int depositID, int employeeID) {
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            stmt = conn.prepareCall(
            "{call deny_deposit(?,?)}");
            stmt.setInt(1, depositID);
            stmt.setInt(2, employeeID);
            stmt.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace(System.out);
            return false;
        } finally {
            DBConnection.closeConnection(stmt, rs);
        }
    }

    /**
     * Function to get the number of deposits made for an employee.
     *
     * @param customerID customerID used as reference
     * @return returns the number of deposits associated with this customer
     */
    public static int getDepositCount(int customerID) {
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            stmt = conn.prepareStatement(
            "SELECT count(*) AS quantity FROM deposit WHERE num_cli = ? "
            + "and pending_approval = 1");
            stmt.setInt(1, customerID);
            rs = stmt.executeQuery();

            while (rs.next()) {
                return rs.getInt("quantity");
            }
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        } finally {
            DBConnection.closeConnection(stmt, rs);
        }
        return -1;
    }
}
