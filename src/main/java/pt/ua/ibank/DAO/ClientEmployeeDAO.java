/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pt.ua.ibank.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import pt.ua.ibank.utilities.DBConnection;
import static pt.ua.ibank.utilities.DBConnection.conn;

/**
 * Class with static methods associated with operations performed with Clients
 * and
 * External Employees stored in a MySQL database
 * Author: PTDA_Staff.
 * Last Modification Date: December 21, 2023
 */
public class ClientEmployeeDAO {

    /**
     * Function used to obtain external information about interactions of an
     * Employee
     *
     * @param employeeID Employee ID used as a reference
     * @return List of clients who interacted with this Employee
     */
    public static ArrayList<Integer> getClientsInteractedListByID(
            int employeeID) {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ArrayList<Integer> list = new ArrayList<>();
        try {
            stmt = conn.prepareStatement(
            "select customer_iban from employee_customer where num_emp = ?;");
            stmt.setInt(1, employeeID);
            rs = stmt.executeQuery();

            while (rs.next()) {
                list.add(rs.getInt("customer_iban"));
            }
            return list;
        } catch (SQLException e) {
            e.printStackTrace(System.out);
            return null;
        } finally {
            DBConnection.closeConnection(stmt, rs);
        }
    }
}
