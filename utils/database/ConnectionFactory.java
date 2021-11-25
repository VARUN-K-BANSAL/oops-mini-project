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
    private static final String URLWithoutDatabase = "jdbc:mysql://localhost:3306/";
    private static final String URL = "jdbc:mysql://localhost:3306/oops_mini_project_group_19_2021";

    public static String getUrl() {
        return URL;
    }

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
                if(Varun.inProduction) {
                    e.printStackTrace();
                } else {
                    System.out.println("Some internal error occurred");
                }
            }
        } catch (IOException e1) {
            if(Varun.inProduction) {
                e1.printStackTrace();
            } else {
                System.out.println("Some internal error occurred");
            }
        }
        return null;
    }

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

    public static Connection getConnection(String username, String password) {
        saveCreds(username, password);
        Connection con = null;
        try {
            Properties connectionProperties = new Properties();
            connectionProperties.put("user", username);
            connectionProperties.put("password", password);
            con = DriverManager.getConnection(URLWithoutDatabase, connectionProperties);
        } catch (Exception e) {
            if(Varun.inProduction) {
                e.printStackTrace();
            } else {
                System.out.println("Some internal error occurred");
            }
        }

        return con;
    }

    private static void saveCreds(String username2, String password2) {
        Path path = Paths.get("data/SQLCreds.csv");
        if (Files.exists(path)) {
            try {
                Files.writeString(path, username2 + "," + password2, StandardCharsets.US_ASCII);
            } catch (IOException e) {
                if(Varun.inProduction) {
                    e.printStackTrace();
                } else {
                    System.out.println("Some internal error occurred");
                }
            }
        } else {
            try {
                Files.createFile(path);
            } catch (IOException e) {
                if(Varun.inProduction) {
                    e.printStackTrace();
                } else {
                    System.out.println("Some internal error occurred");
                }
            }
        }
    }
}
