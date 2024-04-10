package pt.ua.ibank.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import pt.ua.ibank.DTO.Card;
import static pt.ua.ibank.utilities.CardGenerator.generateCardNumber;
import static pt.ua.ibank.utilities.Configs.ERROR_CODE;
import static pt.ua.ibank.utilities.Configs.SUCCESS_CODE;
import pt.ua.ibank.utilities.DBConnection;
import static pt.ua.ibank.utilities.DBConnection.conn;

/**
 * A class containing static methods associated with operations on cards stored
 * in a MySQL database.
 *
 * Author: PTDA_Staff.
 * Last Modification Date: December 25, 2023
 */
public class CardsDAO {

    /**
     * Retrieves a card based on its card number.
     *
     * @param number The card number to be searched.
     * @return The associated Card object, or null if not found.
     */
    public static Card getCardByNumber(String number) {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Card card = null;

        try {
            stmt = conn.prepareStatement(
            "SELECT * FROM card WHERE card_number LIKE ?;");
            stmt.setString(1, number);
            rs = stmt.executeQuery();

            while (rs.next()) {
                card = new Card(
                rs.getString("card_number"),
                rs.getTimestamp("expiration_date"),
                rs.getInt("customer"));
            }

            return card;
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        } finally {
            DBConnection.closeConnection(stmt, rs);
        }
        return null;
    }

    /**
     * Retrieves a list of cards based on a user ID.
     *
     * @param customer_id The client ID used as a reference to obtain cards.
     * @return A list of cards associated with the client.
     */
    public static ArrayList<Card> getCardListFromUserID(int customer_id) {
        PreparedStatement stmt = null;
        ResultSet rs = null;

        ArrayList<Card> cardList = new ArrayList<>();

        try {
            stmt = conn.prepareStatement(
            "SELECT card_number, expiration_date, status FROM cards WHERE customer = ?;");
            stmt.setInt(1, customer_id);
            rs = stmt.executeQuery();

            while (rs.next()) {
                Card card = new Card(
                     rs.getString("card_number"),
                     rs.getTimestamp("expiration_date"),
                     rs.getString("status"));
                cardList.add(card);
            }
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        } finally {
            DBConnection.closeConnection(stmt, rs);
        }

        return cardList.isEmpty() ? null : cardList;
    }

    /**
     * Retrieves the number of cards associated with a client.
     *
     * @param customer_id The client ID used as a reference to obtain the list
     *                    of cards.
     * @return The quantity of cards associated with the client.
     */
    public static int getCardAmountByID(int customer_id) {
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            stmt = conn.prepareStatement(
            "SELECT COUNT(*) AS quantity FROM cards WHERE customer = ?;");
            stmt.setInt(1, customer_id);
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

    /**
     * Cancels a card based on its card number.
     *
     * @param card_number The card number to be canceled.
     * @return Success code: 1 for success, 2 for error.
     */
    public static int cancelCard(String card_number) {
        PreparedStatement stmt = null;

        try {
            stmt = conn.prepareStatement(
            "UPDATE card SET status = 'Canceled' WHERE card_number LIKE ?");
            stmt.setString(1, card_number);
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
     * Sets a card as the default card for a client.
     *
     * @param card_number The card number to be set as default.
     * @param customer_id The client ID used as a reference to obtain the list.
     * @return Success code.
     */
    public static int makeDefault(String card_number, int customer_id) {
        PreparedStatement stmt = null;

        try {
            stmt = conn.prepareStatement(
            "UPDATE customer SET default_card = ? WHERE customer_id = ?");
            stmt.setString(1, card_number);
            stmt.setInt(2, customer_id);
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
     * Creates a new card for a client.
     *
     * @param customer_id The client ID used as a reference.
     * @return Success code.
     */
    public static int createCard(int customer_id) {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String card_number;

        try {
            // Generates a new card until there is no card with this number
            do {
                card_number = generateCardNumber();
                stmt = conn.prepareStatement(
                "SELECT COUNT(card_number) AS value FROM card WHERE card_number LIKE ?;");
                stmt.setString(1, card_number);
                rs = stmt.executeQuery();
                rs.next();
            } while (rs.getInt("value") != 0);
            stmt.close();

            stmt = conn.prepareStatement(
            "INSERT INTO card (card_number, expiration_date, status, customer_id) "
            + "VALUES (?, (SELECT DATE_ADD(CURDATE(), INTERVAL +5 YEAR)), 'Active', ?);");
            stmt.setString(1, card_number);
            stmt.setInt(2, customer_id);
            stmt.execute();

            return SUCCESS_CODE;
        } catch (SQLException e) {
            e.printStackTrace(System.out);
            return ERROR_CODE;
        } finally {
            DBConnection.closeConnection(stmt, rs);
        }
    }
}
