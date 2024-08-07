package pt.ua.ibank.DAO;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import pt.ua.ibank.DTO.Client;
import static pt.ua.ibank.utilities.CardGenerator.generateCardNumber;
import pt.ua.ibank.utilities.DBConnection;
import static pt.ua.ibank.utilities.DBConnection.conn;
import static pt.ua.ibank.utilities.EntityRefGenerator.generateEnt;
import static pt.ua.ibank.utilities.IbanGenerator.generateRandomIban;
import static pt.ua.ibank.utilities.Configs.SUCCESS_CODE;
import static pt.ua.ibank.utilities.Configs.ERROR_CODE;
import static pt.ua.ibank.utilities.Configs.EMAIL_ERROR_CODE;

/**
 * Class with static methods associated with operations performed on Clients
 * stored in a MySQL database
 * Author: PTDA_Staff.
 * Last Modification Date: December 29, 2023
 */
public class ClientDAO {

    /**
     * Function used to create a client
     *
     * @param name        Client name
     * @param address     Client address
     * @param email       Client email
     * @param phone       Client phone number
     * @param nif         Client's tax identification number (nif)
     * @param password    Encrypted password
     * @return success code -> 1 Success, 2 Email Error, 3 SQL Error
     */
    public static int CreateClient(String name, String address, String email,
                                   String phone, String nif, String password) {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String customer_number;
        String card_number;
        int entity;

        try {

            // Verifica se já existe alguma conta com aquele e-mail
            stmt = conn.prepareStatement(
            "SELECT count(account_number) AS value FROM customers where email like ?;");
            stmt.setString(1, email);
            rs = stmt.executeQuery();
            rs.next();

            if (rs.getInt("value") > 0) {
                return EMAIL_ERROR_CODE;
            }

            // generate a new valid IBAN for the account
            do {
                customer_number = generateRandomIban();
                stmt = conn.prepareStatement(
                "SELECT count(account_number) AS value FROM customers where account_number like ?;");
                stmt.setString(1, customer_number);
                rs = stmt.executeQuery();
                rs.next();
            } while (rs.getInt("value") != 0);

            // generate a new valid card number
            do {
                card_number = generateCardNumber();
                stmt = conn.prepareStatement(
                "SELECT count(*) AS value FROM cards where card_number like ?;");
                stmt.setString(1, card_number);
                rs = stmt.executeQuery();
                rs.next();
            } while (rs.getInt("value") != 0);

            // generate a new valid account entity number
            do {
                entity = generateEnt();
                stmt = conn.prepareStatement(
                "SELECT count(customer_number) AS value FROM customers where entity = ?;");
                stmt.setInt(1, entity);
                rs = stmt.executeQuery();
                rs.next();
            } while (rs.getInt("value") != 0);
            stmt.close();

            stmt = conn.prepareStatement(
            "INSERT INTO cards (card_number, expiration_date, status) "
            + "VALUES (?, (SELECT DATE_ADD(CURDATE(), INTERVAL +5 YEAR )),"
            + " \"Active\");");
            stmt.setString(1, card_number);
            stmt.execute();

            stmt = conn.prepareStatement(
            "INSERT INTO customers (name, address, email, phone, nif,"
            + "account_number, password, default_card, entity) "
            + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)");
            stmt.setString(1, name);
            stmt.setString(2, address);
            stmt.setString(3, email);
            stmt.setString(4, phone);
            stmt.setString(5, nif);
            stmt.setString(6, customer_number);
            stmt.setString(7, password);
            stmt.setString(8, card_number);
            stmt.setInt(9, entity);
            stmt.execute();

            int num_cli = getClientIdByEmail(email);

            stmt = conn.prepareStatement(
            "UPDATE cards SET customer_number = ? WHERE card_number like ?;");
            stmt.setInt(1, num_cli);
            stmt.setString(2, card_number);
            stmt.execute();

            return SUCCESS_CODE;

        } catch (SQLException e) {
            e.printStackTrace(System.out);
            return ERROR_CODE;
        } finally {
            DBConnection.closeConnection(stmt, rs);
        }
    }

    /**
     * Function used to get a client object by id.
     *
     * @param id ID of the client used as a reference
     * @return Client Object
     */
    public static Client getClientByID(int id) {
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {

            stmt = conn.prepareStatement(
            "SELECT * FROM customers where customer_number = ?;");
            stmt.setInt(1, id);
            rs = stmt.executeQuery();

            while (rs.next()) {
                return new Client(
                        rs.getInt("customer_number"),
                        rs.getString("name"),
                        rs.getString("address"),
                        rs.getString("email"),
                        rs.getString("phone"),
                        rs.getString("nif"),
                        rs.getString("account_number"),
                        rs.getDouble("balance"),
                        rs.getDouble("pending_balance"),
                        rs.getDate("creation_date"),
                        rs.getString("default_card"),
                        rs.getInt("entity")
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
     * Function used to get the client object by email
     *
     * @param id Client email used as reference
     * @return Returns client associated with the object, null otherwise
     */
    public static Client getClientByEmail(String id) {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Client cl = null;

        try {

            stmt = conn.prepareStatement(
            "SELECT * FROM customers where email like ?;");
            stmt.setString(1, id);
            rs = stmt.executeQuery();

            while (rs.next()) {

                cl = new Client(
                        rs.getInt("customer_number"),
                        rs.getString("name"),
                        rs.getString("address"),
                        rs.getString("email"),
                        rs.getString("phone"),
                        rs.getString("nif"),
                        rs.getString("password"),
                        rs.getString("account_number"),
                        rs.getDouble("balance"),
                        rs.getDouble("pending_balance"),
                        rs.getString("default_card"),
                        rs.getInt("entity"));
            }
            return cl;
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        } finally {
            DBConnection.closeConnection(stmt, rs);
        }
        return null;
    }

    /**
     * Function used to get an external client based of nif
     *
     * @param id nif of the associated client
     * @return Returns the associated client object, null otherwise
     */
    public static Client getClientByNIF(String id) {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Client cl = null;

        try {

            stmt = conn.prepareStatement(
            "SELECT * FROM customers where nif like ?;");
            stmt.setString(1, id);
            rs = stmt.executeQuery();

            while (rs.next()) {
                cl = new Client(
                rs.getInt("customer_number"),
                rs.getString("name"),
                rs.getString("address"),
                rs.getString("email"),
                rs.getString("phone"),
                rs.getString("nif"),
                rs.getString("password"),
                rs.getString("account_number"),
                rs.getDouble("balance"),
                rs.getDouble("pending_balance"),
                rs.getString("default_card"),
                rs.getInt("entity"));
            }

            return cl;
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        } finally {
            DBConnection.closeConnection(stmt, rs);
        }
        return null;
    }

    /**
     * Used to get a list of customers with the same address
     *
     * @param address address to search.
     * @return Returns a list of customers
     */
    public static ArrayList<Client> getClientListByAddress(String selected_address) {
        PreparedStatement stmt = null;
        ResultSet rs = null;

        ArrayList<Client> list = new ArrayList<>();
        try {
            stmt = conn.prepareStatement(
            "SELECT customer_number,name,address,email,phone,nif,account_number,"
            + "creation_date,default_card FROM customers where address like ?");
            stmt.setString(1, selected_address);
            rs = stmt.executeQuery();

            while (rs.next()) {
                Integer number = rs.getInt("customer_number");
                String name = rs.getString("name");
                String address = rs.getString("address");
                String email = rs.getString("email");
                String phone = rs.getString("phone");
                String nif = rs.getString("nif");
                String account_number = rs.getString("account_number");
                Date creation_date = rs.getDate("creation_date");
                String cartaoDefault = rs.getString("default_card");

                list.add(new Client(number, name, address, email,
                                    phone, nif, account_number, 0.0, 0.0,
                                    creation_date,
                                    cartaoDefault, 0));
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
     * Function used to get the client name by ID
     *
     * @param iban IBAN do Cliente usado como referencia
     * @return retorna o ID do customers associado ao IBAN
     */
    public static String getClientNameByID(Integer ID) {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String customer_number = null;

        try {

            stmt = conn.prepareStatement(
            "SELECT name FROM customers where customer_number like ?;");
            stmt.setInt(1, ID);
            rs = stmt.executeQuery();

            while (rs.next()) {
                customer_number = rs.getString("name");
            }

            return customer_number;
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        } finally {
            DBConnection.closeConnection(stmt, rs);
        }
        return null;
    }

    
    /**
     * Returns a list of all customers
     *
     * @return Returns a list of customers, null if empty
     */
    public static ArrayList<Client> getClientList() {
        PreparedStatement stmt = null;
        ResultSet rs = null;

        ArrayList<Client> list = new ArrayList<>();
        try {
            stmt = conn.prepareStatement(
            "SELECT customer_number,name,address,email,"
            + "phone,nif,account_number,default_card,creation_date "
            + "FROM customers");
            rs = stmt.executeQuery();

            while (rs.next()) {
                Integer numero = rs.getInt("customer_number");
                String name = rs.getString("name");
                String address = rs.getString("address");
                String email = rs.getString("email");
                String phone = rs.getString("phone");
                String nif = rs.getString("nif");
                String account_number = rs.getString("account_number");
                Date creation_date = rs.getDate("creation_date");
                String cartaoDefault = rs.getString("default_card");
                list.add(new Client(numero, name, address, email,
                                    phone, nif, account_number, 0.0, 0.0,
                                    creation_date,
                                    cartaoDefault, 0));
            }

            return list.isEmpty() ? null : list;
        } catch (SQLException e) {
            e.printStackTrace(System.out);
            return null;
        } finally {
            DBConnection.closeConnection(stmt, rs);
        }
    }

    /**
     * Function sued to get the client id from an account_number
     *
     * @param account_number used as reference
     * @return returns the associated client ID
     */
    public static Integer getClientIdByIBAN(String iban) {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Integer customer_number = null;

        try {

            stmt = conn.prepareStatement(
            "SELECT customer_number FROM customers where account_number like ?;");
            stmt.setString(1, iban);
            rs = stmt.executeQuery();

            while (rs.next()) {
                customer_number = rs.getInt("customer_number");
            }

            return customer_number;
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        } finally {
            DBConnection.closeConnection(stmt, rs);
        }
        return null;
    }

    /**
     * Function used to get the client name by its id
     *
     * @param clientID client id used as reference
     * @return returns the name associated to the client
     */
    public static String getClienteNomeByID(int clientID) {
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            stmt = conn.prepareStatement(
            "SELECT name FROM customers WHERE customer_number = ?");
            stmt.setInt(1, clientID);
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
     * function used to get the client id by its email
     *
     * @param email Email used as reference
     * @return returns the id associated with the email
     */
    public static Integer getClientIdByEmail(String email) {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Integer customer_number = null;

        try {

            stmt = conn.prepareStatement(
            "SELECT customer_number FROM customers where email like ?;");
            stmt.setString(1, email);
            rs = stmt.executeQuery();

            while (rs.next()) {
                customer_number = rs.getInt("customer_number");
            }

            return customer_number;
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        } finally {
            DBConnection.closeConnection(stmt, rs);
        }
        return null;
    }

    /**
     * Function used to uplate the external client
     *
     * @return returns if successful
     */
    public static boolean UpdateClient(
            int customer_number, String name,
            String address, String email,
            String phone, String nif) {
        PreparedStatement stmt = null;

        try {
            stmt = conn.prepareStatement(
            "UPDATE customers "
            + "SET name = ? , address = ?, email = ?, phone = ?, nif = ? "
            + "WHERE customer_number = ?");
            stmt.setString(1, name);
            stmt.setString(2, address);
            stmt.setString(3, email);
            stmt.setString(4, phone);
            stmt.setString(5, nif);
            stmt.setInt(6, customer_number);
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
     * Função usada para actualizar as Informações externas de um Funcionario"
     *
     * @param name      Nome de customers Externo
     * @param address    Morada de Cliente Externo
     * @param email     Email de Cliente externo
     * @param phone  Telefone de Cliente externo
     * @param nif       nif ( Numero de Identificação fiscal ) Externo
     * @param password  Password encriptada do customers
     * @param old_email Email antigo caso pretenda mudar
     * @return 1 if success, 2 if email error, 3 if SQL Error
     */
    public static int UpdateClient(String name, String address, String email,
                                   String phone, String nif,
                                   String password, String old_email) {
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {

            // check if theres a costumer with that email
            stmt = conn.prepareStatement(
            "SELECT count(customer_number) AS value FROM customers where email like ?;");
            stmt.setString(1, email);
            rs = stmt.executeQuery();
            rs.next();

            if (rs.getInt("value") > 0 && !email.equals(old_email)) {
                return EMAIL_ERROR_CODE;
            }

            stmt = conn.prepareStatement(
            "UPDATE customers SET name = ? , address = ?, email = ?, phone = ?, password = ? WHERE email = ?");
            stmt.setString(1, name);
            stmt.setString(2, address);
            stmt.setString(3, email);
            stmt.setString(4, phone);
            stmt.setString(5, password);
            stmt.setString(6, old_email);
            stmt.execute();
            return SUCCESS_CODE;

        } catch (SQLException e) {
            return ERROR_CODE;
        } finally {
            DBConnection.closeConnection(stmt, rs);
        }
    }

    /**
     * Function used to obtain the balance of an account by costumer ID
     *
     * @param customer_number Customer number used as ID
     * @return Returns client object
     */
    public static Client getClientBalance(int customer_number) {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Client cl = null;

        try {

            stmt = conn.prepareStatement(
            "SELECT balance, pending_balance FROM customers where customer_number = ?;");
            stmt.setInt(1, customer_number);
            rs = stmt.executeQuery();

            while (rs.next()) {
                cl = new Client(rs.getDouble("balance"), rs.getDouble(
                                 "pending_balance"));
            }

            return cl;
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        } finally {
            DBConnection.closeConnection(stmt, rs);
        }
        return null;
    }

    /**
     * Function used to get the default card of a client
     *
     * @param customer_number costumer id used as Reference
     * @return returns the card number
     */
    public static String getClientDefaultCard(int customer_number) {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String card = null;

        try {

            stmt = conn.prepareStatement(
            "SELECT default_card FROM customers where customer_number = ?;");
            stmt.setInt(1, customer_number);
            rs = stmt.executeQuery();

            while (rs.next()) {
                card = rs.getString("default_card");
            }

            return card;
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        } finally {
            DBConnection.closeConnection(stmt, rs);
        }
        return null;
    }
}
