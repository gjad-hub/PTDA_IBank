package pt.ua.ibank.DTO;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.Date;
import pt.ua.ibank.DAO.EmployeeDAO;
import static pt.ua.ibank.utilities.Configs.LocalEmployee;
import pt.ua.ibank.utilities.Hash;

/**
 * Object of a class representing an Employee in the database
 * Author: PTDA_Staff.
 * Last Modification Date: January 16, 2024
 */
public class Employee extends Person {

    /**
     * External Employee Number
     */
    public int empNum;

    /**
     * Manager Number Responsible for the External Employee
     */
    public int managerNum;

    /**
     * Checks if the employee has a valid account
     */
    public Boolean dismissed;

    /**
     * Constructor: an instance with id, value, and actors present in the
     * transaction
     * along with a message
     *
     * @param empNum      External Employee Number
     * @param managerNum  Responsible for External Employee
     * @param name        Employee Name
     * @param address     Employee Address
     * @param phoneNumber Employee Phone Number
     * @param email       Employee Email
     * @param nif         Employee NIF
     * @param dismissed   Boolean variable indicating if still present
     * @param password    Encoded password used by the employee
     */
    public Employee(Integer empNum, String name, String address, String email,
                    String phoneNumber, String nif, String password,
                    Integer managerNum, boolean dismissed) {
        super(name, address, email, phoneNumber, nif, password);
        this.empNum = empNum;
        this.managerNum = managerNum;
        this.dismissed = dismissed;
    }

    /**
     * Constructor: An instance of the constructor class to be used to change
     * information related to an account in the database
     *
     * @param empNum      External Employee Number
     * @param managerNum  Responsible for External Employee
     * @param name        Employee Name
     * @param address     Employee Address
     * @param phoneNumber Employee Phone Number
     * @param email       Employee Email
     * @param nif         Employee NIF
     * @param dismissed   Boolean variable indicating if still present
     * @param createdDate Date when the employee account was created
     */
    public Employee(Integer empNum, Integer managerNum, String name,
                    String address, String email,
                    String phoneNumber, String nif,
                    boolean dismissed, Date createdDate) {
        super(name, address, email, phoneNumber, nif, createdDate);
        this.empNum = empNum;
        this.managerNum = managerNum;
        this.dismissed = dismissed;
    }

    /**
     * Constructor: Constructor to be used in the Login panel, reduced to
     * save memory
     *
     * @param email    User email
     * @param password User password
     */
    public Employee(String email, String password) {
        super(email, password);
    }

    /**
     * @return whether the local user is a manager
     */
    public boolean isManager() {
        return managerNum == 0;
    }

    @Override
    public int alterInformation(String old_email) {
        int status = EmployeeDAO.updateEmployee(name, address, email,
                                                phoneNumber, nif, password,
                                                old_email);
        return status;
    }

    @Override
    public boolean authenticate() {
        Employee tmp = EmployeeDAO.getEmployeeByEmail(email);

        if (tmp != null) {
            try {
                if (Hash.validatePassword(password, tmp.password)) {
                    LocalEmployee = tmp;
                    return true;
                }
            } catch (NoSuchAlgorithmException | InvalidKeySpecException ex) {
                ex.printStackTrace(System.out);
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return "Employee{" + "empNum=" + empNum + ", managerNum=" + managerNum + '}';
    }
}
