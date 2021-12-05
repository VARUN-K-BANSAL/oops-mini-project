package utils.helpers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import individuals.Varun;
import utils.database.ConnectionFactory;

/**
 * This is an abstract class which is used by another classes like
 * CurrentAccount, SavingAccount and LoanAccount.
 */
public abstract class Account {
    private String accountNumber;
    private double balance;
    private String name;
    private String gender;

    /**
     * Getters and setters
     */
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public String getGender(boolean isPrinting) {
        if (gender.equals("M")) {
            return "Male";
        } else if (gender.equals("F")) {
            return "Female";
        }
        return null;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    /*
     * Constructor
     */
    public Account(String accountNumber, double balance, String name, String gender) {
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.name = name;
        this.gender = gender;
    }

    /**
     * Abstract method which will we modified according to the need
     */
    public abstract void addInterest();

    /**
     * This method generated unique random account number
     */
    public static String generateAccountNumber() {
        long accNumber = Math.round(Math.random() * 1000000 + 1);
        String acc = Long.toString(accNumber);
        while (!(isValidAccountNumber(acc))) {
            accNumber = Math.round(Math.random() * 1000000 + 1);
            acc = Long.toString(accNumber);
        }
        return acc;
    }

    /**
     * This method verifies the uniqueness of the account number
     */
    private static boolean isValidAccountNumber(String acc) {
        Connection con = ConnectionFactory.getConnection();
        String query = "SELECT * FROM account";
        try (PreparedStatement stmt = con.prepareStatement(query)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                if (rs.getString("account_number").equals(acc)) {
                    return false;
                }
            }
        } catch (Exception e) {
            if (Varun.inDevelopment) {
                e.printStackTrace();
            } else {
                System.out.println("Some internal error occurred");
            }
            return false;
        }
        return true;
    }
}
