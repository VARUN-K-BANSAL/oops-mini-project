package individuals;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import utils.CurrentAccountUser;
import utils.SavingAccountUser;
import utils.database.ConnectionFactory;
import utils.database.DataBaseModifier;
import utils.database.SearchDataBase;
import utils.helpers.Helps;
import utils.helpers.Transaction;

public class Varun {
    /**
     * This is a basically a development purpose variable which will restrict the
     * error
     * While submitting make sure to make this inProduction = false
     * By making this false it will not show detailed error, it will show some
     * beautiful error message
     */
    final public static boolean inDevelopment = false;

    /**
     * Key and String for encrypting passwords
     */
    final private static int KEY = 5;
    final private static String ALPHA = "qwertyuiopasdfghjklzxcvbnm";

    /**
     * method for encrypting strings for storing username and passwords in encrypted
     * form
     */
    public static String encryptString(String inputString) {
        String encryptedString = "";
        for (int i = 0; i < inputString.length(); i++) {
            if (inputString.charAt(i) != ' ') {
                int temp = (KEY + ALPHA.indexOf(inputString.charAt(i))) % 26;
                encryptedString = encryptedString + ALPHA.charAt(temp);
            } else
                encryptedString = encryptedString + ' ';
        }
        return encryptedString;
    }

    /**
     * Method for creating new account
     * It will check the availability of the username as well
     * It will create apprpriate object and add data to the database by using
     * dataBaseModifier.java file
     */
    public static void createNewAccount(String[] args) throws IOException {
        String accType = args[1].toUpperCase();
        String name = args[2];
        String gender = args[3];
        String username = args[4];
        String password = encryptString(args[5]);
        String openingBalance = args[6];

        if (authenticateUsername(username)) {
            System.out.println("Hi " + name + ", your account will be created in a moment");
            if (accType.equals("CA")) {
                CurrentAccountUser user = new CurrentAccountUser(name, gender, username, password,
                        Integer.valueOf(openingBalance));
                DataBaseModifier.addDataToAccountTable(user);
                System.out.println("Account created successfully");
                System.out.println(user);
            } else if (accType.equals("SA")) {
                SavingAccountUser user = new SavingAccountUser(name, gender, username, password,
                        Integer.valueOf(openingBalance));
                DataBaseModifier.addDataToAccountTable(user);
                System.out.println("Account created successfully");
                System.out.println(user);
            } else {
                System.out.println("Invalid type of account entered");
            }
        } else {
            System.out.println("This username already exists please enter another username");
        }
    }

    /**
     * Method for authenticating username
     * This will return false if the username is already present in the database
     * means that the user have to give any other username
     * and returns true if that username is available
     */
    public static boolean authenticateUsername(String username) {
        Connection con = ConnectionFactory.getConnection();
        String query = "SELECT username FROM account";
        try (PreparedStatement stmt = con.prepareStatement(query)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                if (rs.getString("username").equals(username)) {
                    return false;
                }
            }
        } catch (Exception e) {
            if (inDevelopment) {
                e.printStackTrace();
            } else {
                System.out.println("Some internal error occurred");
            }
            return false;
        }
        return true;
    }

    /**
     * Method for updating password
     * After successful login using oldPassword this will update the password in
     * database using DataBaseModifier.java file
     */
    public static void updatePassword(String[] args) throws IOException {
        String username = args[1];
        String oldPassword = encryptString(args[2]);
        String newPassword = encryptString(args[3]);

        Connection con = ConnectionFactory.getConnection();
        String query = "SELECT * FROM account";
        try (PreparedStatement stmt = con.prepareStatement(query)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                if ((rs.getString("username").equals(username)) && (rs.getString("password").equals(oldPassword))) {
                    DataBaseModifier.updatePassword(username, newPassword);
                    break;
                }
            }
            System.out.println("Invalid username or password");
        } catch (Exception e) {
            if (inDevelopment) {
                e.printStackTrace();
            } else {
                System.out.println("Some error occurred while accessing the database");
            }
        }
        try {
            con.close();
        } catch (SQLException e) {
            // e.printStackTrace();
        }
    }

    /**
     * This method is used to execute transactions when a user wants to do
     * transaction
     */
    public static void executeTransaction(String[] args) {
        if (args[1].equals("-w")) {
            if (DataBaseModifier.withdraw(args)) {
                Transaction transaction = new Transaction(Integer.valueOf(args[4]), args[2], "SELF", "W");
                DataBaseModifier.addTransaction(transaction);
            }
        } else if (args[1].equals("-d")) {
            if (DataBaseModifier.deposit(args)) {
                Transaction transaction = new Transaction(Integer.valueOf(args[3]), args[2], "SELF", "D");
                DataBaseModifier.addTransaction(transaction);
            }
        } else if (args[1].equals("-t")) {
            if (DataBaseModifier.transfer(args)) {
                Transaction transaction = new Transaction(Integer.valueOf(args[4]), args[2], args[5], "T");
                DataBaseModifier.addTransaction(transaction);
            }
        } else if (args[1].equals("-lr")) {
            DataBaseModifier.repayLoan(args);
        } else {
            System.out.println("Invalid Input");
            Helps.transactionHelp();
        }
    }

    /**
     * This method is used when the user wants to search for a user or transaction
     * (-s case)
     * -u for username using SearchDataBase.java file and if someone will add -d
     * then it will also print the transaction
     * -a for searching using account number
     * -n for searching using customer name
     * -t for searching of a transaction using its transaction id
     * -ta for searching of a transaction using its transaction amount
     */
    public static void search(String[] args) {
        if (args[1].equals("-u")) {
            Object obj = SearchDataBase.searchUser(args[2]);
            if (obj != null) {
                System.out.println(obj);
                if (args.length >= 4) {
                    if (args[3].equals("-d")) {
                        SearchDataBase.printTransactions(args[2]);
                    }
                }
            }
        } else if (args[1].equals("-a")) {
            SearchDataBase.searchUserByAccountNumber(args[2]);
        } else if (args[1].equals("-n")) {
            SearchDataBase.searchUserByName(args[2]);
        } else if (args[1].equals("-t")) {
            SearchDataBase.searchTransaction(args[2]);
        } else if (args[1].equals("-ta")) {
            if (args[2].equals("-gt")) {
                SearchDataBase.searchTransaction(Integer.valueOf(args[3]), true);
            } else if (args[2].equals("-lt")) {
                SearchDataBase.searchTransaction(Integer.valueOf(args[3]), false);
            }
        } else {
            System.out.println("Invalid Input");
        }
    }

    public static void addTransactionForLoan(Object obj, Double amount) {
        Transaction transaction = new Transaction(amount.intValue(), "BANK", "", "D");
        if (obj.getClass().equals(CurrentAccountUser.class)) {
            transaction.setReceiver(((CurrentAccountUser) obj).getUsername());
        } else {
            transaction.setReceiver(((SavingAccountUser) obj).getUsername());
        }
        DataBaseModifier.addTransaction(transaction);
    }
}
