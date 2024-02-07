package pt.ua.ibank.DTO;

/**
 * Object of a class representing a Transfer in the database.
 *
 * Author: PTDA_Staff.
 * Last Modification Date: January 8, 2024
 */
public class Transfers {

    private final int transferID;
    private final double valueInEuros;
    private final int clientAuthorID;
    private final int clientRecipientID;
    private final String description;

    /**
     * Constructor: an instance with ID, value, and actors involved in the
     * transaction
     * along with a message.
     *
     * @param transferID        Unique ID of the transfer.
     * @param valueInEuros      Floating-point value in euros.
     * @param clientAuthorID    Sender of the transfer.
     * @param clientRecipientID Receiver of the transfer.
     * @param description       Brief description.
     */
    public Transfers(int transferID, double valueInEuros,
                     int clientAuthorID,
                     int clientRecipientID, String description) {
        this.transferID = transferID;
        this.valueInEuros = valueInEuros;
        this.clientAuthorID = clientAuthorID;
        this.clientRecipientID = clientRecipientID;
        this.description = description;
    }

    /**
     * Gets the unique ID of the transfer.
     *
     * @return Unique ID of the transfer.
     */
    public int getTransferID() {
        return transferID;
    }

    /**
     * Gets the value in euros for the transfer.
     *
     * @return Floating-point value in euros.
     */
    public double getValueInEuros() {
        return valueInEuros;
    }

    /**
     * Gets the ID of the client who initiated the transfer.
     *
     * @return Sender's ID.
     */
    public int getClientAuthorID() {
        return clientAuthorID;
    }

    /**
     * Gets the ID of the client who received the transfer.
     *
     * @return Receiver's ID.
     */
    public int getClientRecipientID() {
        return clientRecipientID;
    }

    /**
     * Gets a brief description of the transfer.
     *
     * @return Brief description.
     */
    public String getDescription() {
        return description;
    }
}
