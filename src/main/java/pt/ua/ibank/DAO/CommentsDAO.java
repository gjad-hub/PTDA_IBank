package pt.ua.ibank.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import pt.ua.ibank.DTO.ModeratorComment;
import pt.ua.ibank.utilities.DBConnection;
import static pt.ua.ibank.utilities.DBConnection.conn;

/**
 * Class with static methods associated with operations performed with external
 * Comments
 * stored in a Persistent data layer
 * Author: PTDA_Staff.
 * Last Modification Date: January 14, 2024
 */
public class CommentsDAO {

    /**
     * Function used to obtain a list of comments through a client ID.
     *
     * @param clientID Client ID used as a reference
     * @return Returns a list of comments related to this client
     */
    public static ArrayList<ModeratorComment> getCommentListByID(
            int clientID) {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ArrayList<ModeratorComment> list = new ArrayList<>();

        try {

            stmt = conn.prepareStatement(
            "select id,employees.name as name,description,profile_comments.creation_date"
            + " from profile_comments inner join employees on employees.employee_id = profile_comments.employee_id"
            + " where customer_number = ?;");
            stmt.setInt(1, clientID);
            rs = stmt.executeQuery();

            while (rs.next()) {
                list.add(new ModeratorComment(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("description"),
                        rs.getTimestamp("creation_date")));
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
     * Function used to obtain the list of comments from a client through ID.
     *
     * @param clientID Client ID used as a reference
     * @return List of Comments associated with this profile
     */
    public static ArrayList<ModeratorComment> getModeratorCommentListByID(
            int clientID) {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ArrayList<ModeratorComment> list = new ArrayList<>();

        try {
            stmt = conn.prepareStatement(
            "select id,employees.name as name,description,creation_date"
            + " from profile_comments inner join employees on employees.employee_id = profile_comments.employee_id"
            + " where client_id = ?;");
            stmt.setInt(1, clientID);
            rs = stmt.executeQuery();

            while (rs.next()) {
                list.add(new ModeratorComment(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("description"),
                        rs.getTimestamp("creation_date")));
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
     * Function used to add a comment to an external client.
     *
     * @param employeeID Employee ID used as a reference
     * @param clientID   Client ID used as a reference
     * @param comment    Comment content
     * @return Success code boolean
     */
    public static boolean addNewComment(int employeeID, int clientID,
                                        String comment) {
        PreparedStatement stmt = null;
        try {

            stmt = conn.prepareStatement(
            "INSERT INTO profile_comments"
            + "(employee_id,client_id,description) VALUES"
            + "(?,?,?);"
    );

            stmt.setInt(1, employeeID);
            stmt.setInt(2, clientID);
            stmt.setString(3, comment);
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
     * Function used to delete a comment by ID.
     *
     * @param id clientID Client ID used as a reference
     * @return Success code boolean
     */
    public static boolean deleteComment(int id) {
        PreparedStatement stmt = null;

        try {
            stmt = conn.prepareStatement(
            "DELETE FROM profile_comments WHERE id = ?");
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
}
