package ru.max_developer;

import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.security.MessageDigest;

public class User {
    /**
     * the first name of the user
     */
    private String firstName;
    /**
     * the last name of the user
     */
    private String lastName;
    /**
     * the ID number of the user
     */
    private String uuid;
    /**
     * the MD5 hash of the user's pin number.
     */
    private byte pinHash[];
    /**
     * the list of accounts for this user.
     */
    private ArrayList<Account> accounts;

    /**
     * Create a new user
     * @param firstName the user's first name
     * @param lastName  the user's last name
     * @param pin       the user's account pin number
     * @param theBank   the Bank object that the user is a customer of
     */
    public User(String firstName, String lastName, String pin, Bank theBank){

        //set user's name
        this.firstName = firstName;
        this.lastName = lastName;

        //store the pin's MD5 hash, rather than the original value, for the security reasons
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            this.pinHash = md.digest(pin.getBytes());
        } catch (NoSuchAlgorithmException e) {
            System.err.println("error, caught NoSuchAlgorithmException");
            e.printStackTrace();
            System.exit(1);
        }

        // get a new, unique universal ID for user
        this.uuid = theBank.getNewUserUUID();

        //create empty list of accounts
        this.accounts = new ArrayList<Account>();

        //print log message
        System.out.printf("New user %s, %s with ID %s created.\n", lastName, firstName, this.uuid);
    }

    /**
     * Add an account for the user
     * @param anAcct  the account to add
     */
    public void addAccount(Account anAcct) {
        this.accounts.add(anAcct);
    }

    /**
     * Return the user's UUID
     * @return  the uuid
     */
    public String getUUID() {
        return this.uuid;
    }

}
