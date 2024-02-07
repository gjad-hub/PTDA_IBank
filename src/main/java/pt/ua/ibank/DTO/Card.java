package pt.ua.ibank.DTO;

import java.sql.Timestamp;

/**
 * Object representing a client's card in the database.
 *
 * Author: PTDA_Staff.
 * Last Modification Date: January 11, 2024
 */
public class Card {

    /**
     * External card number.
     */
    public String cardNumber;

    /**
     * Expiry date of the external card.
     */
    public Timestamp expireDate;

    /**
     * State (Active or Inactive) of the card.
     */
    public String activatedState;

    /**
     * External client ID.
     */
    public int clientID;

    /**
     * Constructor: an instance with a card number, expiry date, state, and
     * client ID.
     *
     * @param cardNumber     Reference number of the card.
     * @param expireDate     Expiry date of the card.
     * @param activatedState State of the active card.
     * @param clientID       Client number owning the card.
     */
    public Card(String cardNumber, Timestamp expireDate, String activatedState,
                  int clientID) {
        this.cardNumber = cardNumber;
        this.expireDate = expireDate;
        this.activatedState = activatedState;
        this.clientID = clientID;
    }

    /**
     * Constructor: an instance with a card number, expiry date, and client ID.
     *
     * @param cardNumber Reference number of the card.
     * @param expireDate Expiry date of the card.
     * @param clientID   Client number owning the card.
     */
    public Card(String cardNumber, Timestamp expireDate, int clientID) {
        this.cardNumber = cardNumber;
        this.expireDate = expireDate;
        this.clientID = clientID;
    }

    /**
     * Constructor: an instance with a card number, expiry date, and state.
     *
     * @param cardNumber     Reference number of the card.
     * @param expireDate     Expiry date of the card.
     * @param activatedState State of the active card.
     */
    public Card(String cardNumber, Timestamp expireDate, String activatedState) {
        this.cardNumber = cardNumber;
        this.expireDate = expireDate;
        this.activatedState = activatedState;
    }

    /**
     * Checks if the card's expiry date is valid.
     *
     * @return True if the card's expiry date is valid; false otherwise.
     */
    public boolean isValid() {
        return expireDate.after(new Timestamp(System.currentTimeMillis()));
    }
}
