package pt.ua.ibank.DTO;

/**
 * Object of a class representing an external deposit.
 * Author: PTDA_Staff.
 * Last Modification Date: January 5, 2024
 */
public class Deposit {

    /**
     * Unique ID of the deposit.
     */
    public int depositID;

    /**
     * Value in euros of the deposit.
     */
    public double value;

    /**
     * Approval status of the request.
     */
    public boolean approved;

    /**
     * Number of the employee in charge of approving the request.
     */
    public int employeeNumber;

    /**
     * Number of the client associated with the deposit.
     */
    public int clientNumber;

    /**
     * Value that indicates whether the request is pending or not.
     */
    public boolean pendingApproval;

    /**
     * Constructor: an instance with depositID, value, approved status, employee
     * number, and
     * client number. Represents a deposit in its final state.
     *
     * @param depositID      ID of the deposit.
     * @param value          Deposited value.
     * @param approved       Boolean value indicating the approval status.
     * @param employeeNumber Employee number responsible for the deposit.
     * @param clientNumber   Number of the authorized client.
     */
    public Deposit(int depositID, double value, boolean approved,
                   int employeeNumber,
                   int clientNumber) {
        this.pendingApproval = false;
        this.depositID = depositID;
        this.value = value;
        this.approved = approved;
        this.employeeNumber = employeeNumber;
        this.clientNumber = clientNumber;
    }

    /**
     * Constructor: an instance with depositID and value. Represents a deposit
     * pending approval.
     *
     * @param depositID ID of the deposit.
     * @param value     Deposited value.
     */
    public Deposit(int depositID, double value) {
        this.pendingApproval = true;
        this.depositID = depositID;
        this.value = value;
    }
}
