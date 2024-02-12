package pt.ua.ibank.DAO;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import pt.ua.ibank.DTO.Employee;
import static pt.ua.ibank.utilities.Configs.EMAIL_ERROR_CODE;
import static pt.ua.ibank.utilities.Configs.ERROR_CODE;
import static pt.ua.ibank.utilities.Configs.SUCCESS_CODE;
import pt.ua.ibank.utilities.DBConnection;
import static pt.ua.ibank.utilities.DBConnection.conn;

/**
 * Class with static methods associated with operations performed on external
 * employees stored in a MySQL database.
 * Author: PTDA_Staff.
 * Last Modification Date: December 15, 2023.
 */
public class EmployeeDAO {

    /**
     * Function to register an employee
     *
     * @param name       Name of the external client
     * @param address    Address of the external client
     * @param email      Email of the external client
     * @param phone      Phone number of the external client
     * @param nif        NIF (Tax Identification Number) of the external client
     * @param password   Password, stored in an encrypted form
     * @param employeeId External employee number
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
            "SELECT count('num_fun') AS value FROM employee where email like ?;");
            stmt.setString(1, email);
            rs = stmt.executeQuery();
            rs.next();

            if (rs.getInt("value") > 0) {
                return EMAIL_ERROR_CODE;
            }

            stmt = conn.prepareStatement(
            "INSERT INTO employee (name, address, email, phone, nif, password, manager) "
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
        Employee employee = null;
        try {

            stmt = conn.prepareStatement(
            "SELECT * FROM employee where email like ?;");
            stmt.setString(1, email);
            rs = stmt.executeQuery();

            while (rs.next()) {
                employee = new Employee(rs.getInt("num_fun"),
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

            return employee;
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        } finally {
            DBConnection.closeConnection(stmt, rs);
        }
        return null;
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
        Employee employee = null;
        try {
            stmt = conn.prepareStatement(
            "SELECT num_fun, name, address, email, phone, nif, manager, dismissed, creation_date "
            + "FROM employee where num_fun like ?;");
            stmt.setInt(1, id);
            rs = stmt.executeQuery();

            while (rs.next()) {
                employee = new Employee(rs.getInt("num_fun"),
                                        rs.getInt("manager"),
                                        rs.getString("name"),
                                        rs.getString("address"),
                                        rs.getString("email"),
                                        rs.getString("phone"),
                                        rs.getString("nif"),
                                        rs.getBoolean("dismissed"),
                                        rs.getDate("creation_date"));
            }

            return employee;
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
            "SELECT name FROM employee where num_fun like ?;");
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
            "SELECT num_fun, name, address, email, phone, nif, manager, dismissed, creation_date FROM employee");
            rs = stmt.executeQuery();

            while (rs.next()) {
                Integer number = rs.getInt("num_fun");
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
            "SELECT num_fun, name, address, email, phone, nif, manager, dismissed, creation_date "
            + "FROM employee where address like ?;");
            stmt.setString(1, address);
            rs = stmt.executeQuery();

            while (rs.next()) {
                Integer number = rs.getInt("num_fun");
                String name = rs.getString("name");
                String employeeAddress = rs.getString("address");
                String email = rs.getString("email");
                String phone = rs.getString("phone");
                String nif = rs.getString("nif");
                Integer managerId = rs.getInt("manager");
                Boolean dismissed = rs.getBoolean("dismissed");
                Date creationDate = rs.getDate("creation_date");

                list.add(new Employee(number, managerId, name, employeeAddress,
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
            "SELECT num_fun, name, address, email, phone, nif, manager, dismissed, creation_date "
            + "FROM employee where phone like ?;");
            stmt.setString(1, phone);
            rs = stmt.executeQuery();

            while (rs.next()) {
                Integer number = rs.getInt("num_fun");
                String name = rs.getString("name");
                String address = rs.getString("address");
                String email = rs.getString("email");
                String employeePhone = rs.getString("phone");
                String nif = rs.getString("nif");
                Integer managerId = rs.getInt("manager");
                Boolean dismissed = rs.getBoolean("dismissed");
                Date creationDate = rs.getDate("creation_date");

                list.add(new Employee(number, managerId, name, address, email,
                                      employeePhone,
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
     * Function to return Employees by NIF
     *
     * @param nif NIF used as a reference
     * @return Employees related to that NIF
     */
    public static ArrayList<Employee> getEmployeeListByNIF(String nif) {
        PreparedStatement stmt = null;
        ResultSet rs = null;

        ArrayList<Employee> list = new ArrayList<>();
        try {
            stmt = conn.prepareStatement(
            "SELECT num_fun, name, address, email, phone, nif, manager, dismissed, creation_date "
            + "FROM employee where nif like ?;");
            stmt.setString(1, nif);
            rs = stmt.executeQuery();

            while (rs.next()) {
                Integer number = rs.getInt("num_fun");
                String name = rs.getString("name");
                String address = rs.getString("address");
                String email = rs.getString("email");
                String phone = rs.getString("phone");
                String employeeNIF = rs.getString("nif");
                Integer managerId = rs.getInt("manager");
                Boolean dismissed = rs.getBoolean("dismissed");
                Date creationDate = rs.getDate("creation_date");

                list.add(new Employee(number, managerId, name, address, email,
                                      phone,
                                      employeeNIF, dismissed,
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
            "SELECT num_fun, name, address, email, phone, nif, manager, dismissed, creation_date "
            + "FROM employee where manager like ?;");
            stmt.setInt(1, managerId);
            rs = stmt.executeQuery();

            while (rs.next()) {
                Integer number = rs.getInt("num_fun");
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
            "SELECT num_fun, name, address, email, phone, nif, manager, dismissed, creation_date "
            + "FROM employee where dismissed like ?;");
            stmt.setBoolean(1, dismissed);
            rs = stmt.executeQuery();

            while (rs.next()) {
                Integer number = rs.getInt("num_fun");
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
     * Function to update Employee information
     *
     * @param employeeId Employee ID used as a reference
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
            "SELECT count('num_emp') AS value FROM employee where email like ?;");
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();
            rs.next();

            if (rs.getInt("value") > 0) {
                return EMAIL_ERROR_CODE;
            }

            stmt = conn.prepareStatement(
            "UPDATE employee "
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
            "UPDATE employee "
            + "SET dismissed = true "
            + "WHERE num_fun = ?");
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
            "SELECT num_fun FROM employee where email like ? and password like ? and dismissed like false;");
            stmt.setString(1, email);
            stmt.setString(2, password);
            rs = stmt.executeQuery();

            if (rs.next()) {
                return rs.getInt("num_fun");
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
            "SELECT creation_date FROM employee where num_fun like ?;");
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
     * Function to return the number of dismissed Employees
     *
     * @return Number of dismissed Employees
     */
    public static int getDismissedEmployeeCount() {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = conn.prepareStatement(
            "SELECT count('num_fun') as count FROM employee where dismissed like true;");
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
