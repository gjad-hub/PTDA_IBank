package pt.ua.ibank.DTO;

import java.sql.Timestamp;

/**
 * Object of a class representing a transaction made.
 * Author: PTDA_Staff.
 * Last Modification Date: December 21, 2023
 */
public class Transactions {

    public final int id;
    public final int clientNumber;
    public String description;
    public final Double value;
    public final Timestamp date;

    /**
     * Constructor: an instance with ID, value, and actors involved in the
     * transaction
     * along with a message.
     *
     * @param clientNumber Receiver of the transaction.
     * @param id           Unique ID of the transaction.
     * @param value        Value in euros of the transaction.
     * @param description  Description to be shown to the user.
     * @param date         Date when the transaction was made.
     */
    public Transactions(int id, int clientNumber, String description,
                        Double value,
                        Timestamp date) {
        this.id = id;
        this.clientNumber = clientNumber;
        this.description = description;
        this.value = value;
        this.date = date;
    }
}
