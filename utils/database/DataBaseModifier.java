package utils.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import individuals.Varun;
import utils.CurrentAccountUser;
import utils.SavingAccountUser;
import utils.helpers.Transaction;

public class DataBaseModifier {

    /**
     * This is the method to add data into account table using the array of Strings
     */
    public static void addDataToAccountTable(String data[]) {
        Connection con = ConnectionFactory.getConnection();
        String insertQuery = "INSERT INTO account VALUES(" + "?, " + "?, " + "?, " + "?, " + "?, " + "?, " + "?)";

        try (PreparedStatement stmt = con.prepareStatement(insertQuery)) {
            stmt.setString(1, data[0]);
            stmt.setString(2, data[1]);
            stmt.setString(3, data[2]);
            stmt.setString(4, data[3]);
            stmt.setString(5, data[4].toLowerCase());
            stmt.setString(6, data[5]);
            stmt.setString(7, data[6]);
            stmt.executeUpdate();
        } catch (Exception e) {
            if(Varun.inProduction) {
                e.printStackTrace();
            } else {
                System.out.println("Unable to add data to the database....");
            }
            return;
        }
    }

    /**
     * This method is also used to add data into account table using an object of type CurrentAccountUser
     * This is an overridden method
     */
    public static void addDataToAccountTable(CurrentAccountUser user) {
        String[] data = new String[7];
        data[0] = user.getUsername();
        data[1] = user.getPassword();
        data[2] = user.getAccount().getAccountNumber();
        data[3] = "CA";
        data[4] = user.getAccount().getName();
        data[5] = Double.toString(user.getAccount().getBalance());
        data[6] = user.getAccount().getGender();
        addDataToAccountTable(data);
    }

    /**
     * This method is also used to add data into account table using an object of type SavingAccountUser
     * This is also an overridden method
     */
    public static void addDataToAccountTable(SavingAccountUser user) {
        String[] data = new String[7];
        data[0] = user.getUsername();
        data[1] = user.getPassword();
        data[2] = user.getAccount().getAccountNumber();
        data[3] = "SA";
        data[4] = user.getAccount().getName();
        data[5] = Double.toString(user.getAccount().getBalance());
        data[6] = user.getAccount().getGender();
        addDataToAccountTable(data);
    }

    /**
     * This method will update the password in the database
     * This is called from the Varun.java file
     * Please make sure to authenticate user before calling this method
     */
    public static void updatePassword(String username, String newPassword) {
        Connection con = ConnectionFactory.getConnection();
        String query = "UPDATE account SET password = ? WHERE username = ?";
        try (PreparedStatement statement2 = con.prepareStatement(query)) {
            statement2.setString(1, newPassword);
            statement2.setString(2, username);
            if (statement2.executeUpdate() != 0) {
                System.out.println("Password for " + username +" has been updated Successfully");
            } else {
                System.out.println("Unable to update password");
            }
        } catch (Exception e) {
            if(Varun.inProduction) {
                e.printStackTrace();
            } else {
                System.out.println("Some internal error occured");
            }
        }
        try {
            con.close();
        } catch (SQLException e) {
            // e.printStackTrace();
        }
    }

    /**
     * This method is used to delete the records of a particular user
     */
    public static void deleteAccount(String[] args) {
        Connection con = ConnectionFactory.getConnection();
        String query = "DELETE FROM account WHERE username = ? AND password = ?";
        try(PreparedStatement stmt = con.prepareStatement(query)) {
            stmt.setString(1, args[1]);
            stmt.setString(2, Varun.encryptString(args[2]));
            int rowsUpdated = stmt.executeUpdate();
            if(rowsUpdated != 0) {
                System.out.println("Account deleted successfully");
            } else {
                System.out.println("Cannot delete the account");
            }
        } catch (Exception e) {
            if(Varun.inProduction) {
                e.printStackTrace();
            } else {
                System.out.println("Some internal error occurred");
            }
        }
        try {
            con.close();
        } catch (SQLException e) {
            // e.printStackTrace();
        }
    }

    /**
     * This method is used to update current account of the customer after the transaction
     * It will update the User of type CurrentAccountUser
     */
    public static void updateAccount(CurrentAccountUser obj) {
        Connection con = ConnectionFactory.getConnection();
        try {
            con.setAutoCommit(false);
        } catch (SQLException e1) {
            // e1.printStackTrace();
        }
        String query = "UPDATE account SET account_balance = ? WHERE username = ?";
        try (PreparedStatement statement2 = con.prepareStatement(query)) {
            statement2.setDouble(1, obj.getAccount().getBalance());
            statement2.setString(2, obj.getUsername());
            statement2.executeUpdate();
            if(DatabaseCreator.isInitialising() == false){
                System.out.println("Transaction successful...");
                System.out.println("Current balance : " + obj.getAccount().getBalance());
            }
        } catch (Exception e) {
            try {
                con.rollback();
            } catch (SQLException e1) {
                // e1.printStackTrace();
            }
            if(Varun.inProduction) {
                e.printStackTrace();
            } else {
                System.out.println("Some internal error occured");
            }
        }
        try {
            con.commit();
            con.close();
        } catch (SQLException e) {
            // e.printStackTrace();
        }
    }

    /**
     * This method is used to update current account of the customer after the transaction
     * It will update the User of type SavingAccountUser
     * This is an overridden method
     */
    private static void updateAccount(SavingAccountUser obj) {
        Connection con = ConnectionFactory.getConnection();
        try {
            con.setAutoCommit(false);
        } catch (SQLException e1) {
            // e1.printStackTrace();
        }
        String query = "UPDATE account SET account_balance = ? WHERE username = ?";
        try (PreparedStatement statement2 = con.prepareStatement(query)) {
            statement2.setDouble(1, obj.getAccount().getBalance());
            statement2.setString(2, obj.getUsername());
            statement2.executeUpdate();
            if(DatabaseCreator.isInitialising() == false){
                System.out.println("Transaction successful...");
                System.out.println("Current balance : " + obj.getAccount().getBalance());
            }
        } catch (Exception e) {
            try {
                con.rollback();
            } catch (SQLException e1) {
                // e1.printStackTrace();
            }
            if(Varun.inProduction) {
                e.printStackTrace();
            } else {
                System.out.println("Some internal error occured");
            }
        }
        try {
            con.commit();
            con.close();
        } catch (SQLException e) {
            // e.printStackTrace();
        }
    }

    /**
     * This is the method used to withdraw money from the account
     * Please make sure to authenticate user before using this method
     */
    public static boolean withdraw(String[] args) {
        Object obj = SearchDataBase.searchUser(args[2]);
        if(obj.getClass().equals(CurrentAccountUser.class)) {
            if(((CurrentAccountUser) obj).getPassword().equals(Varun.encryptString(args[3]))) {
                double currBal = ((CurrentAccountUser) obj).getAccount().getBalance();
                if(currBal > Double.valueOf(args[4])) {
                    currBal = currBal - Double.valueOf(args[4]);
                    ((CurrentAccountUser) obj).getAccount().setBalance(currBal);
                } else {
                    System.out.println("Insufficient balance | Current balance : " + currBal);
                    return false;
                }
            } else {
                System.out.println("Invalid Username or password");
                return false;
            }
            updateAccount((CurrentAccountUser) obj);
        } else if(obj.getClass().equals(SavingAccountUser.class)) {
            if(((SavingAccountUser) obj).getPassword().equals(Varun.encryptString(args[3]))) {
                double currBal = ((SavingAccountUser) obj).getAccount().getBalance();
                if(currBal > Double.valueOf(args[4])) {
                    currBal = currBal - Double.valueOf(args[4]);
                    ((SavingAccountUser) obj).getAccount().setBalance(currBal);
                } else {
                    System.out.println("Insufficient balance | Current balance : " + currBal);
                    return false;
                }
            } else {
                System.out.println("Invalid Username or password");
                return false;
            }
            updateAccount((SavingAccountUser) obj);
        } else {
            System.out.println("Some internal error occurred");
        }
        return false;
    }

    /**
     * This is the method used to transfer money from the account
     * No need to authenticate user before using this method
     */
    public static void transfer(String[] args) {
        if(withdraw(args)) {
            deposit(new String[]{null, null, args[5], args[4]});
        }
    }

    /**
     * This is the method used to deposit money from the account
     */
    public static boolean deposit(String[] args) {
        Object obj = SearchDataBase.searchUser(args[2]);
        if(obj.getClass().equals(CurrentAccountUser.class)) {
            double currBal = ((CurrentAccountUser) obj).getAccount().getBalance();
            currBal = currBal + Double.valueOf(args[3]);
            ((CurrentAccountUser) obj).getAccount().setBalance(currBal);
            updateAccount((CurrentAccountUser) obj);
        } else if(obj.getClass().equals(SavingAccountUser.class)) {
            double currBal = ((SavingAccountUser) obj).getAccount().getBalance();
            currBal = currBal + Double.valueOf(args[3]);
            ((SavingAccountUser) obj).getAccount().setBalance(currBal);
            updateAccount((SavingAccountUser) obj);
        } else {
            System.out.println("Some internal error occurred");
        }
        return false;
    }

    /**
     * This method will add a new transaction to the transaction table in the database
     */
    public static void addTransaction(Transaction transaction) {
        Connection con = ConnectionFactory.getConnection();
        String query = "INSERT INTO TRANSACTION (sender, receiver, transaction_date, amount, type)"
                        + "VALUES (?, ?, ?, ?, ?)";

        try {
            con.setAutoCommit(false);
        } catch (SQLException e1) {
            // e1.printStackTrace();
        }
        try(PreparedStatement stmt = con.prepareStatement(query)) {
            stmt.setString(1, transaction.getSender());
            stmt.setString(2, transaction.getReceiver());
            stmt.setString(3, transaction.getDate());
            stmt.setInt(4, transaction.getAmount());
            stmt.setString(5, transaction.getTypeOfTransaction());
            stmt.executeUpdate();
            if(DatabaseCreator.isInitialising() == false) {
                System.out.println("Transaction Successful");
            }
        } catch(Exception e) {
            try {
                con.rollback();
            } catch (SQLException e1) {
                // e1.printStackTrace();
            }
            if(Varun.inProduction) {
                e.printStackTrace();
            } else {
                System.out.println("Some error occurred");
            }
        }
        try {
            con.commit();
            con.close();
        } catch (SQLException e) {
            // e.printStackTrace();
        }
    }
}
