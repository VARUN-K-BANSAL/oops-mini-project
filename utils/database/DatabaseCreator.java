package utils.database;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class DatabaseCreator {
    public static void programInit(String args[]) {
        final String databaseName = "oops_mini_project_group_19_2021";
        final String createDatabaseQuery = "CREATE DATABASE " + databaseName;

        Connection con = ConnectionFactory.getConnection(args[1], args[2]);
        dropDataBaseIfExists(databaseName);
        try(PreparedStatement statement = con.prepareStatement(createDatabaseQuery)) {
            statement.execute(createDatabaseQuery);
            System.out.println("Database created....");
            // con.close();
            createTables();
            addDummyDataToAccountDataBase();
        } catch (Exception e) {
            System.out.println("Unexpected error occur in creating database....");
            // e.printStackTrace();
        }
    }

    private static void addDummyDataToAccountDataBase() {
        Path path = Paths.get("data/initialUserData.csv");
        if(Files.exists(path)) {
            List<String> lines;
            try {
                lines = Files.readAllLines(path);
                for (String line : lines) {
                    String usersData[] = line.split(",");
                    DataBaseModifier.addDataToAccountTable(usersData);
                }
                System.out.println("Dummy data added....");
            } catch (IOException e) {
                System.out.println("Unable to read the User data file");
            }
        }
    }

    private static void dropDataBaseIfExists(String databaseName) {
        final Connection con = ConnectionFactory.getConnection();
        final String dropDataBaseQuery = "DROP DATABASE " + databaseName;
        try(PreparedStatement stmt = con.prepareStatement(dropDataBaseQuery)) {
            stmt.executeUpdate();
        } catch (Exception e) {
            // e.printStackTrace();
        }
        try {
            con.close();
        } catch (SQLException e) {
            // e.printStackTrace();
        }
    }

    private static void createTables() {
        final Connection con = ConnectionFactory.getConnection();
        final String accountTableQuery = "CREATE TABLE account(username VARCHAR(25) NOT NULL,"
                                        + "password VARCHAR(10) NOT NULL,"
                                        + "account_number VARCHAR(10) NOT NULL,"
                                        + "account_type VARCHAR(5) NOT NULL,"
                                        + "account_holder_name VARCHAR(30) NOT NULL,"
                                        + "account_balance DOUBLE(20,3) NOT NULL,"
                                        + "gender VARCHAR(2) NOT NULL)";

        try(PreparedStatement stmt = con.prepareStatement(accountTableQuery)) {
            // int rowsAffected = 
            stmt.executeUpdate();
            // System.out.println(rowsAffected + " rows affected");
        } catch (Exception e) {
            e.printStackTrace();
        }

        final String transactionTableQuery = "CREATE TABLE transaction(sender VARCHAR(25) NOT NULL,"
                                            + "receiver VARCHAR(25) NOT NULL,"
                                            + "transaction_id VARCHAR(10) PRIMARY KEY AUTO_INCREMENT,"
                                            + "transaction_date DATETIME NOT NULL,"
                                            + "amount INT NOT NULL)";

        try(PreparedStatement stmt = con.prepareStatement(transactionTableQuery)) {
            // int rowsAffected = 
            stmt.executeUpdate();
            System.out.println("Tables Created....");
            // System.out.println(rowsAffected);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
