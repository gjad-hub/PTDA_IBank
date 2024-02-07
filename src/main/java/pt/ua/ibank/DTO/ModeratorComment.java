package pt.ua.ibank.DTO;

import java.sql.Timestamp;

/**
 * Object of a class representing a moderator comment.
 * Author: PTDA_Staff.
 * Last Modification Date: December 9, 2023
 */
public class ModeratorComment {

    private final int id;
    private final String employeeName;
    private final String content;
    private final Timestamp dateMade;

    /**
     * Constructor: an instance with ID, employeeName, content, and dateMade.
     *
     * @param id           Unique ID of the comment.
     * @param employeeName Name of the message author.
     * @param content      Content of the message.
     * @param dateMade     Date when it was posted.
     */
    public ModeratorComment(int id, String employeeName, String content,
                            Timestamp dateMade) {
        this.id = id;
        this.employeeName = employeeName;
        this.content = content;
        this.dateMade = dateMade;
    }

    /**
     * Gets the name of the employee who made the comment.
     *
     * @return Name of the employee.
     */
    public String getEmployeeName() {
        return employeeName;
    }

    /**
     * Gets the content of the comment.
     *
     * @return Content of the comment.
     */
    public String getContent() {
        return content;
    }

    /**
     * Gets the date when the comment was made.
     *
     * @return Date when the comment was made.
     */
    public Timestamp getDateMade() {
        return dateMade;
    }

    /**
     * Gets the unique ID of the comment.
     *
     * @return Unique ID of the comment.
     */
    public int getId() {
        return id;
    }
}
