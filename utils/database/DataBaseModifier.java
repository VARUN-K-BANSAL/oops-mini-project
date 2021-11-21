package utils.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import individuals.Varun;
import utils.CurrentAccountUser;
import utils.SavingAccountUser;
import utils.helpers.Transaction;

public class DataBaseModifier {
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

    public static void transfer(String[] args) {
        withdraw(args);
        deposit(new String[]{null, null, args[5], args[4]});
    }

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
        } catch (SQLException e) {
            // e.printStackTrace();
        }
    }
}
