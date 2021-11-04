package utils.helpers;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import utils.database.ConnectionFactory;

public abstract class Account {
    private String accountNumber;
    private double balance;
    private String name;
    private String gender;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
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

    public Account(String accountNumber, double balance, String name, String gender) {
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.name = name;
        this.gender = gender;
    }

    public abstract void addInterest();

    public static String generateAccountNumber() throws IOException {
        long accNumber = Math.round(Math.random() * 1000000 + 1);
        String acc = Long.toString(accNumber);
        while(!(isValidAccountNumber(acc))) {
            accNumber = Math.round(Math.random() * 1000000 + 1);
            acc = Long.toString(accNumber);
        }
        return acc;
    }

    private static boolean isValidAccountNumber(String acc) {
        Connection con = ConnectionFactory.getConnection();
        String query = "SELECT * FROM account";
        try(PreparedStatement stmt = con.prepareStatement(query)) {
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
                 if(rs.getString("account_number").equals(acc)) {
                     return false;
                 }
            }
        } catch(Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean withdraw(int amount) {
        if(this.balance > amount) {
            this.setBalance(this.getBalance() - amount);
            return true;
        } else {
            System.out.println("No enough balance | Available balance : " + this.getBalance());
            return false;
        }
    }
}