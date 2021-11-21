package utils.helpers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import individuals.Varun;
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

    public static String generateAccountNumber() {
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
            if(Varun.inProduction) {
                e.printStackTrace();
            } else {
                System.out.println("Some internal error occurred");
            }
            return false;
        }
        return true;
    }

    /*
     * Need modification just started now 
     */
    

    private double checkBalance(String[] creds, Connection con) {
        String query = "SELECT * FROM account WHERE username = " + creds[0] + " AND password = " + creds[1];
        try(PreparedStatement statement = con.prepareStatement(query)) {
            ResultSet rs = statement.executeQuery();
            return rs.getDouble("account_balance");
        } catch(Exception e) {
            if(Varun.inProduction) {
                e.printStackTrace();
            } else {
                System.out.println("Some internal error occurred");
            }
        }
        return 0;
    }

    private boolean authUser(String[] creds, Connection con) {
        String query = "SELECT * FROM account";
        try(PreparedStatement statement = con.prepareStatement(query)) {
            ResultSet rs = statement.executeQuery();
            while(rs.next()) {
                if(rs.getString("username").equals(creds[0]) 
                    && rs.getString("password").equals(creds[1])) {
                    return true;
                }
            }
        } catch(Exception e) {
            if(Varun.inProduction) {
                e.printStackTrace();
            } else {
                System.out.println("Some internal error occurred");
            }
        }
        return false;
    }
}
