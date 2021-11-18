package utils.database;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import individuals.Varun;
import utils.helpers.Transaction;

/**
 * NOTE : If you make changes to this DatabaseCreator class you might have to reinitialise the program
 */

public class DatabaseCreator {
    static boolean initialising = true;
    public static boolean isInitialising() {
        return initialising;
    }

    public static void setInitialising(boolean initialising) {
        DatabaseCreator.initialising = initialising;
    }

    public static void programInit(String args[]) {
        final String databaseName = "oops_mini_project_group_19_2021";
        final String createDatabaseQuery = "CREATE DATABASE " + databaseName;

        Connection con = ConnectionFactory.getConnection(args[1], args[2]);
        dropDataBaseIfExists(databaseName, args);
        try(PreparedStatement statement = con.prepareStatement(createDatabaseQuery)) {
            statement.execute(createDatabaseQuery);
            System.out.println("Database created....");
            createTables();
            addDummyDataToDataBase();
        } catch (Exception e) {
            if(Varun.inProduction) {
                e.printStackTrace();
            } else {
                System.out.println("Unexpected error occur in creating database....");
            }
        }
        try {
            con.close();
        } catch (SQLException e) {
            // e.printStackTrace();
        }
        setInitialising(false);
    }

    private static void addDummyDataToDataBase() {
        Path path = Paths.get("data/initialUserData.csv");
        if(Files.exists(path)) {
            List<String> lines;
            try {
                lines = Files.readAllLines(path);
                for (String line : lines) {
                    String usersData[] = line.split(",");
                    DataBaseModifier.addDataToAccountTable(usersData);
                }
            } catch (IOException e) {
                if(Varun.inProduction) {
                    e.printStackTrace();
                } else {
                    System.out.println("Unable to read the dummy data file");
                }
            }
        }
        path = Paths.get("data/initialTransactionData.csv");
        if(Files.exists(path)) {
            List<String> lines;
            try {
                lines = Files.readAllLines(path);
                for (String line : lines) {
                    String usersData[] = line.split(",");
                    if(usersData[1].equals("w")) {
                        DataBaseModifier.withdraw(usersData);
                        Transaction t = new Transaction(Integer.valueOf(usersData[4]), usersData[2], "SELF", "W");
                        DataBaseModifier.addTransaction(t);
                    } else if(usersData[1].equals("d")) {
                        DataBaseModifier.deposit(usersData);
                        Transaction t = new Transaction(Integer.valueOf(usersData[3]), usersData[2], "SELF", "D");
                        DataBaseModifier.addTransaction(t);
                    } else if(usersData[1].equals("t")) {
                        DataBaseModifier.transfer(usersData);
                        Transaction t = new Transaction(Integer.valueOf(usersData[4]), usersData[2], usersData[5], "T");
                        DataBaseModifier.addTransaction(t);
                    }
                }
            } catch (IOException e) {
                if(Varun.inProduction) {
                    e.printStackTrace();
                } else {
                    System.out.println("Unable to read the dummy data file");
                }
                return;
            }
        }
        System.out.println("Dummy data added....");
    }

    private static void dropDataBaseIfExists(String databaseName, String args[]) {
        Connection con = ConnectionFactory.getConnection(args[1], args[2]);
        final String dropDataBaseQuery = "DROP DATABASE IF EXISTS " + databaseName;
        try(PreparedStatement stmt = con.prepareStatement(dropDataBaseQuery)) {
            stmt.executeUpdate();
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

    private static void createTables() {
        final Connection con = ConnectionFactory.getConnection();
        final String accountTableQuery = "CREATE TABLE account(username VARCHAR(25) NOT NULL PRIMARY KEY,"
                                        + "password VARCHAR(10) NOT NULL,"
                                        + "account_number VARCHAR(10) NOT NULL,"
                                        + "account_type VARCHAR(5) NOT NULL,"
                                        + "account_holder_name VARCHAR(30) NOT NULL,"
                                        + "account_balance DOUBLE(20,3) NOT NULL,"
                                        + "gender VARCHAR(2) NOT NULL)";

        try(PreparedStatement stmt = con.prepareStatement(accountTableQuery)) {
            stmt.executeUpdate();
        } catch (Exception e) {
            if(Varun.inProduction) {
                e.printStackTrace();
            } else {
                System.out.println("Some error occurred while creating the tables");
            }
        }

        final String transactionTableQuery = "CREATE TABLE transaction(sender VARCHAR(10) NOT NULL,"
                                            + "receiver VARCHAR(10) NOT NULL,"
                                            + "transaction_id INT NOT NULL AUTO_INCREMENT,"
                                            + "transaction_date DATETIME NOT NULL,"
                                            + "amount INT NOT NULL,"
                                            + "type VARCHAR(2) NOT NULL,"
                                            + "PRIMARY KEY(transaction_id),"
                                            + "FOREIGN KEY(sender) REFERENCES account(username))";

        try(PreparedStatement stmt = con.prepareStatement(transactionTableQuery)) {
            stmt.executeUpdate();
            System.out.println("Tables Created....");
        } catch (Exception e) {
            if(Varun.inProduction) {
                e.printStackTrace();
            } else {
                System.out.println("Some error occurred while creating the tables");
            }
        }
        try {
            con.close();
        } catch (SQLException e) {
            // e.printStackTrace();
        }
    }
}
