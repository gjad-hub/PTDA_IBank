package pt.ua.ibank.DAO;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import pt.ua.ibank.DTO.Employee;
import static pt.ua.ibank.utilities.Configs.EMAIL_ERROR_CODE;
import static pt.ua.ibank.utilities.Configs.ERROR_CODE;
import static pt.ua.ibank.utilities.Configs.SUCCESS_CODE;
import pt.ua.ibank.utilities.DBConnection;
import static pt.ua.ibank.utilities.DBConnection.conn;

/**
 * Class with static methods associated with operations performed on external
 * employes stored in a MySQL database.
 * Author: PTDA_Staff.
 * Last Modification Date: December 15, 2023.
 */
public class EmployeeDAO {

    /**
     * Function to register an employees
     *
     * @param name       Name of the external client
     * @param address    Address of the external client
     * @param email      Email of the external client
     * @param phone      Phone number of the external client
     * @param nif        NIF (Tax Identification Number) of the external client
     * @param password   Password, stored in an encrypted form
     * @param employeeId External employees number
     * @return Success code, 1 for Success, 2 for Email Error, 3 for SQL Error
     */
    public static int createEmployee(String name, String address, String email,
                                     String phone, String nif,
                                     String password, Integer employeeId) {
        PreparedStatement stmt = null;

        try {
            ResultSet rs = null;
            // Check if there is already an account with that email
            stmt = conn.prepareStatement(
            "SELECT count(*) AS value FROM employees where email like ?;");
            stmt.setString(1, email);
            rs = stmt.executeQuery();
            rs.next();

            if (rs.getInt("value") > 0) {
                return EMAIL_ERROR_CODE;
            }

            stmt = conn.prepareStatement(
            "INSERT INTO employees (name, address, email, phone, nif, password, manager) "
            + "VALUES (?, ?, ?, ?, ?, ?, ?)");
            stmt.setString(1, name);
            stmt.setString(2, address);
            stmt.setString(3, email);
            stmt.setString(4, phone);
            stmt.setString(5, nif);
            stmt.setString(6, password);
            stmt.setInt(7, employeeId);
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
     * Function to return an Employee object by Email
     *
     * @param email String Email used as a reference
     * @return Returns the Employee related to that email
     */
    public static Employee getEmployeeByEmail(String email) {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Employee employees = null;
        try {

            stmt = conn.prepareStatement(
            "SELECT * FROM employees where email like ?;");
            stmt.setString(1, email);
            rs = stmt.executeQuery();

            while (rs.next()) {
                employees = new Employee(rs.getInt("employee_id"),
                                        rs.getString("name"),
                                        rs.getString("address"),
                                        rs.getString("email"),
                                        rs.getString("phone"),
                                        rs.getString("nif"),
                                        rs.getString("password"),
                                        rs.getInt("manager"),
                                        rs.getBoolean("dismissed")
        );
            }

            return employees;
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        } finally {
            DBConnection.closeConnection(stmt, rs);
        }
        return null;
    }

    /**
     * Function used to promote an employees
     *
     * @param id Employee ID used as a reference
     * @return returns a boolean success code
     */
    public static boolean promoteEmployee(int id) {
        PreparedStatement stmt = null;

        try {
            stmt = conn.prepareStatement(
            "UPDATE employees SET manager = NULL WHERE employee LIKE ?");
            stmt.setInt(1, id);
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
     * Function to return an Employee by ID
     *
     * @param id ID used as a reference
     * @return Employee related to that ID
     */
    public static Employee getEmployeeByID(int id) {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Employee employees = null;
        try {
            stmt = conn.prepareStatement(
            "SELECT employee_id, name, address, email, phone, nif, manager, dismissed, creation_date "
            + "FROM employees where employee_id like ?;");
            stmt.setInt(1, id);
            rs = stmt.executeQuery();

            while (rs.next()) {
                employees = new Employee(rs.getInt("employee_id"),
                                        rs.getInt("manager"),
                                        rs.getString("name"),
                                        rs.getString("address"),
                                        rs.getString("email"),
                                        rs.getString("phone"),
                                        rs.getString("nif"),
                                        rs.getBoolean("dismissed"),
                                        rs.getDate("creation_date"));
            }

            return employees;
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        } finally {
            DBConnection.closeConnection(stmt, rs);
        }
        return null;
    }

    /**
     * Function to return the name by ID
     *
     * @param id ID of the Employee used as a reference
     * @return Name of the Employee associated with that ID
     */
    public static String getEmployeeNameByID(int id) {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {

            stmt = conn.prepareStatement(
            "SELECT name FROM employees where employee_id like ?;");
            stmt.setInt(1, id);
            rs = stmt.executeQuery();

            if (rs.next()) {
                return rs.getString("name");
            }
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        } finally {
            DBConnection.closeConnection(stmt, rs);
        }

        return null;
    }

    /**
     * Function to return a list of all Employees
     *
     * @return List of all Employees
     */
    public static ArrayList<Employee> getEmployeeList() {
        PreparedStatement stmt = null;
        ResultSet rs = null;

        ArrayList<Employee> list = new ArrayList<>();
        try {
            stmt = conn.prepareStatement(
            "SELECT employee_id, name, address, email, phone, nif, manager, dismissed, creation_date FROM employees");
            rs = stmt.executeQuery();

            while (rs.next()) {
                Integer number = rs.getInt("employee_id");
                String name = rs.getString("name");
                String address = rs.getString("address");
                String email = rs.getString("email");
                String phone = rs.getString("phone");
                String nif = rs.getString("nif");
                Integer managerId = rs.getInt("manager");
                Boolean dismissed = rs.getBoolean("dismissed");
                Date creationDate = rs.getDate("creation_date");

                list.add(new Employee(number, managerId, name, address, email,
                                      phone,
                                      nif, dismissed,
                                      creationDate
                ));

            }
            return list;
        } catch (SQLException e) {
            e.printStackTrace(System.out);
            return null;
        } finally {
            DBConnection.closeConnection(stmt, rs);
        }
    }

    /**
     * Function to return Employees by address
     *
     * @param address Address used as a reference
     * @return Employees related to that address
     */
    public static ArrayList<Employee> getEmployeeListByAddress(
            String address) {
        PreparedStatement stmt = null;
        ResultSet rs = null;

        ArrayList<Employee> list = new ArrayList<>();
        try {
            stmt = conn.prepareStatement(
            "SELECT employee_id, name, address, email, phone, nif, manager, dismissed, creation_date "
            + "FROM employees where address like ?;");
            stmt.setString(1, address);
            rs = stmt.executeQuery();

            while (rs.next()) {
                Integer number = rs.getInt("employee_id");
                String name = rs.getString("name");
                String employeesAddress = rs.getString("address");
                String email = rs.getString("email");
                String phone = rs.getString("phone");
                String nif = rs.getString("nif");
                Integer managerId = rs.getInt("manager");
                Boolean dismissed = rs.getBoolean("dismissed");
                Date creationDate = rs.getDate("creation_date");

                list.add(new Employee(number, managerId, name, employeesAddress,
                                      email, phone,
                                      nif, dismissed,
                                      creationDate
                ));
            }
            return list;
        } catch (SQLException e) {
            e.printStackTrace(System.out);
            return null;
        } finally {
            DBConnection.closeConnection(stmt, rs);
        }
    }

    /**
     * Function to return Employees by phone number
     *
     * @param phone Phone number used as a reference
     * @return Employees related to that phone number
     */
    public static ArrayList<Employee> getEmployeeListByPhone(String phone) {
        PreparedStatement stmt = null;
        ResultSet rs = null;

        ArrayList<Employee> list = new ArrayList<>();
        try {
            stmt = conn.prepareStatement(
            "SELECT employee_id, name, address, email, phone, nif, manager, dismissed, creation_date "
            + "FROM employees where phone like ?;");
            stmt.setString(1, phone);
            rs = stmt.executeQuery();

            while (rs.next()) {
                Integer number = rs.getInt("employee_id");
                String name = rs.getString("name");
                String address = rs.getString("address");
                String email = rs.getString("email");
                String employeesPhone = rs.getString("phone");
                String nif = rs.getString("nif");
                Integer managerId = rs.getInt("manager");
                Boolean dismissed = rs.getBoolean("dismissed");
                Date creationDate = rs.getDate("creation_date");

                list.add(new Employee(number, managerId, name, address, email,
                                      employeesPhone,
                                      nif, dismissed,
                                      creationDate
                ));
            }
            return list;
        } catch (SQLException e) {
            e.printStackTrace(System.out);
            return null;
        } finally {
            DBConnection.closeConnection(stmt, rs);
        }
    }

    public static String getEmployeeApprovedDepositsNumber(int id) {
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            stmt = conn.prepareStatement(
            "SELECT count(*) as number from deposit where employee_id = ?;");
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getString("number");
            }
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        } finally {
            DBConnection.closeConnection(stmt, rs);
        }
        return null;
    }

    /**
     * Function to return Employees by NIF
     *
     * @param nif NIF used as a reference
     * @return Employees related to that NIF
     */
    public static Employee getEmployeeByNIF(String nif) {
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            stmt = conn.prepareStatement(
            "SELECT employee_id, name, address, email, phone, nif, manager, dismissed, creation_date "
            + "FROM employees where nif like ?;");
            stmt.setString(1, nif);
            rs = stmt.executeQuery();

            while (rs.next()) {
                Integer number = rs.getInt("employee_id");
                String name = rs.getString("name");
                String address = rs.getString("address");
                String email = rs.getString("email");
                String phone = rs.getString("phone");
                String employeesNIF = rs.getString("nif");
                Integer managerId = rs.getInt("manager");
                Boolean dismissed = rs.getBoolean("dismissed");
                Date creationDate = rs.getDate("creation_date");

                return new Employee(number, managerId, name, address, email,
                                    phone,
                                    employeesNIF, dismissed,
                                    creationDate
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
     * Function to return Employees by Manager
     *
     * @param managerId Manager ID used as a reference
     * @return Employees related to that Manager
     */
    public static ArrayList<Employee> getEmployeeListByManager(
            Integer managerId) {
        PreparedStatement stmt = null;
        ResultSet rs = null;

        ArrayList<Employee> list = new ArrayList<>();
        try {
            stmt = conn.prepareStatement(
            "SELECT employee_id, name, address, email, phone, nif, manager, dismissed, creation_date "
            + "FROM employees where manager like ?;");
            stmt.setInt(1, managerId);
            rs = stmt.executeQuery();

            while (rs.next()) {
                Integer number = rs.getInt("employee_id");
                String name = rs.getString("name");
                String address = rs.getString("address");
                String email = rs.getString("email");
                String phone = rs.getString("phone");
                String nif = rs.getString("nif");
                Boolean dismissed = rs.getBoolean("dismissed");
                Date creationDate = rs.getDate("creation_date");

                list.add(new Employee(number, managerId, name, address, email,
                                      phone,
                                      nif, dismissed,
                                      creationDate
                ));
            }
            return list;
        } catch (SQLException e) {
            e.printStackTrace(System.out);
            return null;
        } finally {
            DBConnection.closeConnection(stmt, rs);
        }
    }

    /**
     * Function to return Employees by Dismissal Status
     *
     * @param dismissed Dismissal status used as a reference
     * @return Employees with the specified Dismissal status
     */
    public static ArrayList<Employee> getEmployeeListByDismissalStatus(
            Boolean dismissed) {
        PreparedStatement stmt = null;
        ResultSet rs = null;

        ArrayList<Employee> list = new ArrayList<>();
        try {
            stmt = conn.prepareStatement(
            "SELECT employee_id, name, address, email, phone, nif, manager, dismissed, creation_date "
            + "FROM employees where dismissed like ?;");
            stmt.setBoolean(1, dismissed);
            rs = stmt.executeQuery();

            while (rs.next()) {
                Integer number = rs.getInt("employee_id");
                Integer managerId = rs.getInt("manager");
                String name = rs.getString("name");
                String address = rs.getString("address");
                String email = rs.getString("email");
                String phone = rs.getString("phone");
                String nif = rs.getString("nif");
                Date creationDate = rs.getDate("creation_date");

                list.add(new Employee(number, managerId, name, address, email,
                                      phone,
                                      nif, dismissed,
                                      creationDate
                ));
            }
            return list;
        } catch (SQLException e) {
            e.printStackTrace(System.out);
            return null;
        } finally {
            DBConnection.closeConnection(stmt, rs);
        }
    }

    /**
     * Returns the external employees with the best performance in deposit
     * approvals
     *
     * @return returns the name of the employees with the best performance
     */
    public static String getEmployeeWithMostApprovedDeposits() {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String employeesWithMostDeposits = null;

        try {
            stmt = conn.prepareStatement(
            "SELECT employee, COUNT(*) AS total_approved FROM deposits WHERE approved = 1 GROUP BY employee ORDER BY total_approved DESC LIMIT 1;");
            rs = stmt.executeQuery();

            if (rs.next()) {
                int employeesNumber = rs.getInt("employee");
                employeesWithMostDeposits = getEmployeeNameByID(
                employeesNumber);
            }

            return employeesWithMostDeposits;
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        } finally {
            DBConnection.closeConnection(stmt, rs);
        }

        return null;
    }

    /**
     * Function that returns the number of pending external deposits for
     * approval
     *
     * @return number of deposits pending approval
     */
    public static Integer getPendingDepositsCount() {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Integer pendingDepositsCount = null;

        try {
            stmt = conn.prepareStatement(
            "SELECT COUNT(*) AS pending_deposits FROM deposits WHERE pending_approval = 1;");
            rs = stmt.executeQuery();

            if (rs.next()) {
                pendingDepositsCount = rs.getInt("pending_deposits");
            }

            return pendingDepositsCount;
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        } finally {
            DBConnection.closeConnection(stmt, rs);
        }

        return null;
    }

    /**
     * Function that returns the date of the last deposit request
     *
     * @return returns the date of the last deposit request formatted as String
     */
    public static String getLastDepositRequestDate() {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Timestamp lastDepositRequestDate = null;

        try {
            stmt = conn.prepareStatement(
            "SELECT creation_date FROM deposits ORDER BY creation_date DESC LIMIT 1;");
            rs = stmt.executeQuery();

            if (rs.next()) {
                lastDepositRequestDate = rs.getTimestamp("creation_date");
            }

            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yy HH:mm");

            return dateFormat.format(lastDepositRequestDate);
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        } finally {
            DBConnection.closeConnection(stmt, rs);
        }

        return null;
    }

    /**
     * Function that returns the total number of created accounts
     *
     * @return total number of created accounts
     */
    public static Integer getTotalNumberOfCreatedAccounts() {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Integer totalAccountsCreated = null;

        try {
            stmt = conn.prepareStatement(
            "SELECT COUNT(*) AS total_accounts FROM costumers;");
            rs = stmt.executeQuery();
            if (rs.next()) {
                totalAccountsCreated = rs.getInt("total_accounts");
            }
            return totalAccountsCreated;
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        } finally {
            DBConnection.closeConnection(stmt, rs);
        }
        return null;
    }

    /**
     * Function that returns the name of the last registered customer
     *
     * @return returns the name of the last registered customer
     */
    public static String getLastNameOfLastCreatedAccount() {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String lastNameOfLastAccount = null;

        try {
            stmt = conn.prepareStatement(
            "SELECT name FROM customers ORDER BY account_number DESC LIMIT 1;");
            rs = stmt.executeQuery();

            if (rs.next()) {
                lastNameOfLastAccount = rs.getString("name");
            }

            return lastNameOfLastAccount;
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        } finally {
            DBConnection.closeConnection(stmt, rs);
        }

        return null;
    }

    /**
     * Function to update Employee information
     *
     * @param employeeId Employee ID used as a reference
     * @param name       New name of the Employee
     * @param address    New address of the Employee
     * @param email      New email of the Employee
     * @param phone      New phone number of the Employee
     * @param nif        New NIF of the Employee
     * @param old_email
     * @return Success code, 1 for Success, 2 for Email Error, 3 for SQL Error
     */
    public static int updateEmployee(String name,
                                     String address, String email,
                                     String phone, String nif
    ) {
        PreparedStatement stmt = null;

        try {
            // Check if there is already an account with that email
            stmt = conn.prepareStatement(
            "SELECT count('employee_id') AS value FROM employees where email like ?;");
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();
            rs.next();

            if (rs.getInt("value") > 0) {
                return EMAIL_ERROR_CODE;
            }

            stmt = conn.prepareStatement(
            "UPDATE employees "
            + "SET name = ?, address = ?, email = ?, phone = ?, nif = ? "
            + " WHERE email = ?");
            stmt.setString(1, name);
            stmt.setString(2, address);
            stmt.setString(3, email);
            stmt.setString(4, phone);
            stmt.setString(5, nif);
            stmt.executeUpdate();
            return SUCCESS_CODE;
        } catch (SQLException e) {
            e.printStackTrace(System.out);
            return ERROR_CODE;
        } finally {
            DBConnection.closeConnection(stmt);
        }
    }

    /**
     * Function to update Employee information
     *
     * @param name       New name of the Employee
     * @param address    New address of the Employee
     * @param email      New email of the Employee
     * @param phone      New phone number of the Employee
     * @param nif        New NIF of the Employee
     * @param password   New encrypted password of the Employee
     * @param old_email
     * @return Success code, 1 for Success, 2 for Email Error, 3 for SQL Error
     */
    public static int updateEmployee(String name,
                                     String address, String email,
                                     String phone, String nif,
                                     String password, String old_email) {
        PreparedStatement stmt = null;

        try {
            // Check if there is already an account with that email
            stmt = conn.prepareStatement(
            "SELECT count('employee_id') AS value FROM employees where email like ?;");
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();
            rs.next();

            if (rs.getInt("value") > 0) {
                return EMAIL_ERROR_CODE;
            }

            stmt = conn.prepareStatement(
            "UPDATE employees "
            + "SET name = ?, address = ?, email = ?, phone = ?, nif = ?, "
            + "password = ? WHERE email = ?");
            stmt.setString(1, name);
            stmt.setString(2, address);
            stmt.setString(3, email);
            stmt.setString(4, phone);
            stmt.setString(5, nif);
            stmt.setString(6, password);
            stmt.setString(7, old_email);
            stmt.executeUpdate();
            return SUCCESS_CODE;
        } catch (SQLException e) {
            e.printStackTrace(System.out);
            return ERROR_CODE;
        } finally {
            DBConnection.closeConnection(stmt);
        }
    }

    /**
     * Function to dismiss an Employee
     *
     * @param employeeId Employee ID used as a reference
     * @return Success code, 1 for Success, 3 for SQL Error
     */
    public static boolean dismissEmployee(Integer employeeId) {
        PreparedStatement stmt = null;

        try {
            stmt = conn.prepareStatement(
            "UPDATE employees "
            + "SET dismissed = true "
            + "WHERE employee_id = ?");
            stmt.setInt(1, employeeId);
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace(System.out);
            return false;
        } finally {
            DBConnection.closeConnection(stmt);
        }
    }

    /**
     * Function to validate Employee credentials
     *
     * @param email    Email of the Employee
     * @param password Password of the Employee
     * @return Employee ID if credentials are valid, null otherwise
     */
    public static Integer validateLogin(String email, String password) {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {

            stmt = conn.prepareStatement(
            "SELECT employee_id FROM employees where email like ? and password like ? and dismissed like false;");
            stmt.setString(1, email);
            stmt.setString(2, password);
            rs = stmt.executeQuery();

            if (rs.next()) {
                return rs.getInt("employee_id");
            }
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        } finally {
            DBConnection.closeConnection(stmt, rs);
        }

        return null;
    }

    /**
     * Function to return the creation date of an Employee
     *
     * @param employeeId Employee ID used as a reference
     * @return Creation date of the Employee
     */
    public static Timestamp getEmployeeCreationDate(Integer employeeId) {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = conn.prepareStatement(
            "SELECT creation_date FROM employees where employee_id like ?;");
            stmt.setInt(1, employeeId);
            rs = stmt.executeQuery();

            if (rs.next()) {
                return rs.getTimestamp("creation_date");
            }
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        } finally {
            DBConnection.closeConnection(stmt, rs);
        }

        return null;
    }

    /**
     * Function that checks if an external Employee with ID has been dismissed
     *
     * @param id Employee ID used as a reference
     * @return returns a boolean success state
     */
    public static boolean isEmployeeDismissedByID(int id) {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = conn.prepareStatement(
            "SELECT dismissed FROM employees WHERE employee LIKE ?");
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            while (rs.next()) {
                return rs.getBoolean("dismissed");
            }
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        } finally {
            DBConnection.closeConnection(stmt, rs);
        }
        return false;
    }

    /**
     * Function to return the number of dismissed Employees
     *
     * @return Number of dismissed Employees
     */
    public static int getDismissedEmployeeCount() {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = conn.prepareStatement(
            "SELECT count('employee_id') as count FROM employees where dismissed like true;");
            rs = stmt.executeQuery();

            if (rs.next()) {
                return rs.getInt("count");
            }
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        } finally {
            DBConnection.closeConnection(stmt, rs);
        }

        return 0;
    }
}
