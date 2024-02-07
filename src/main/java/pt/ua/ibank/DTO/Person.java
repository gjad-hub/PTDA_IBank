package pt.ua.ibank.DTO;

import java.sql.Date;
import java.text.SimpleDateFormat;

/**
 * Object of an Abstract Class representing a Person Entity.
 *
 * Author: PTDA_Staff.
 * Last Modification Date: January 16, 2024
 */
public abstract class Person {

    /**
     * Full name of the person.
     */
    public String name;

    /**
     * Address of the person.
     */
    public String address;

    /**
     * Email address of the person.
     */
    public String email;

    /**
     * Phone number of the person.
     */
    public String phoneNumber;

    /**
     * NIF (Tax Identification Number) of the person.
     */
    public String nif;

    /**
     * Password stored in an encrypted form.
     */
    public String password;

    /**
     * Date of creation for the person.
     */
    public Date creationDate;

    /**
     * Constructor with a password. Please encrypt the password first.
     *
     * @param name        Full name of the person.
     * @param address     Address of the person.
     * @param email       Email address of the person.
     * @param phoneNumber Phone number of the person.
     * @param nif         NIF (Tax Identification Number) of the person.
     * @param password    Password stored in an encrypted form.
     */
    public Person(String name, String address, String email, String phoneNumber,
                  String nif, String password) {
        this.name = name;
        this.address = address;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.nif = nif;
        this.password = password;
    }

    /**
     * Constructor without a password.
     *
     * @param name         Full name of the person.
     * @param address      Address of the person.
     * @param email        Email address of the person.
     * @param phoneNumber  Phone number of the person.
     * @param nif          NIF (Tax Identification Number) of the person.
     * @param creationDate Date of creation for the person.
     */
    public Person(String name, String address, String email, String phoneNumber,
                  String nif, Date creationDate) {
        this.name = name;
        this.address = address;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.nif = nif;
        this.creationDate = creationDate;
    }

    /**
     * Constructor for email and password to be used in authentication.
     *
     * @param email    Email address of the person.
     * @param password Password stored in an encrypted form.
     */
    public Person(String email, String password) {
        this.email = email;
        this.password = password;
    }

    /**
     * Empty constructor for possible test cases.
     */
    public Person() {
    }

    /**
     * Abstract method for authentication.
     *
     * @return True if authentication is successful; false otherwise.
     */
    public abstract boolean authenticate();

    /**
     * Function used to alter information of a Client/External Person.
     *
     * @param old_email Old email if the local user intends to change it.
     * @return 1 for success, 2 for error, 3 for email error.
     */
    public abstract int alterInformation(String old_email);

    /**
     * Gets the formatted date of creation.
     *
     * @return Formatted date (MM/yy) of creation for the person.
     */
    public String getDateFormatted() {
        SimpleDateFormat dataFormat = new SimpleDateFormat("MM/yy");
        return dataFormat.format(creationDate);
    }
}
