package utils.database;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;
import java.util.Properties;

import individuals.Varun;

public class ConnectionFactory {
    /**
     * There are two Strings - URL and URLWithoutDatabase
     * URL is used when database is created and we are trying to get the connection
     * while the other is used when are creating database
     */
    private static final String URLWithoutDatabase = "jdbc:mysql://localhost:3306/";
    private static final String URL = "jdbc:mysql://localhost:3306/" + DatabaseCreator.databaseName;

    public static String getUrl() {
        return URL;
    }

    /**
     * This method is used when we are initializing the program and we don't have
     * the creds yet
     * So we will create the connection and save the creds for future works
     */
    public static Connection getConnection(String username, String password) {
        Connection con = null;
        try {
            Properties connectionProperties = new Properties();
            connectionProperties.put("user", username);
            connectionProperties.put("password", password);
            con = DriverManager.getConnection(URLWithoutDatabase, connectionProperties);
        } catch (Exception e) {
            if (Varun.inDevelopment) {
                e.printStackTrace();
            } else {
                System.out.println("Some internal error occurred");
            }
        }
        saveCreds(username, password);
        return con;
    }

    /**
     * Saving the credentials to SQLCreds.csv file
     * They are stored so that we need not to put the username and password in each
     * and every instruction
     * This method will automatically create a file name SQLCreds.csv if that does
     * not exist
     */
    private static void saveCreds(String username2, String password2) {
        Path path = Paths.get("data/SQLCreds.csv");
        if (Files.exists(path)) {
            try {
                Files.writeString(path, username2 + "," + password2, StandardCharsets.US_ASCII);
            } catch (IOException e) {
                if (Varun.inDevelopment) {
                    e.printStackTrace();
                } else {
                    System.out.println("Some internal error occurred");
                }
            }
        } else {
            try {
                Files.createFile(path);
            } catch (IOException e) {
                if (Varun.inDevelopment) {
                    e.printStackTrace();
                } else {
                    System.out.println("Some internal error occurred");
                }
            }
        }
    }

    /**
     * This getConnection method is used after the initialisation of the program
     * This contains a call to another method which is readCreds which will give the
     * credentials to make the connection
     * the credentials we get here are the one which we stored earlier through the
     * saveCreds method
     * This is the Overloaded method
     */
    public static Connection getConnection() {
        try {
            String[] creds = readCreds();
            Connection con = null;
            try {
                Properties connectionProperties = new Properties();
                connectionProperties.put("user", creds[0]);
                connectionProperties.put("password", creds[1]);
                con = DriverManager.getConnection(URL, connectionProperties);
                return con;
            } catch (Exception e) {
                if (Varun.inDevelopment) {
                    e.printStackTrace();
                } else {
                    System.out.println("Some internal error occurred");
                }
            }
        } catch (IOException e1) {
            if (Varun.inDevelopment) {
                e1.printStackTrace();
            } else {
                System.out.println("Some internal error occurred");
            }
        }
        return null;
    }

    /**
     * Reading and returning the credentials from SQLCreds.csv file
     */
    private static String[] readCreds() throws IOException {
        Path path = Paths.get("data/SQLCreds.csv");
        if (Files.exists(path)) {
            List<String> lines = Files.readAllLines(path);
            for (String line : lines) {
                String[] data = line.split(",");
                return data;
            }
        }
        return null;
    }

}
