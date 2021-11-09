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

public class Varun {
    public static boolean inProduction = true;

    final private static int KEY = 5;
    final private static String ALPHA = "qwertyuiopasdfghjklzxcvbnm";

    /**
     * method for encrypting strings for storing username and passwords
     */
    private static String encryptString(String inputString) {
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

    public static void createNewAccount(String[] args) throws IOException {
        String accType = args[1];
        String name = args[2];
        String gender = args[3];
        String username = args[4];
        String password = encryptString(args[5]);
        String openingBalance = args[6];

        if(authenticateUsername(username)) {
            System.out.println("Hi " + name + ", your account will be created in a moment");
            if(accType.equals("CA")) {
                CurrentAccountUser user = new CurrentAccountUser(name, gender, username, password, Integer.valueOf(openingBalance));
                DataBaseModifier.addDataToAccountTable(user);
            } else if(accType.equals("SA")) {
                SavingAccountUser user = new SavingAccountUser(name, gender, username, password, Integer.valueOf(openingBalance));
                DataBaseModifier.addDataToAccountTable(user);
            } else {
                System.out.println("Invalid type of account entered");
            }
        } else {
            System.out.println("This username already exists please enter another username");
        }
    }

    private static boolean authenticateUsername(String username) {
        Connection con = ConnectionFactory.getConnection();
        String query = "SELECT username FROM account";
        try(PreparedStatement stmt = con.prepareStatement(query)) {
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
                if(rs.getString("username").equals(username)) {
                    return false;
                }
            }
        } catch(Exception e) {
            if(inProduction) {
                e.printStackTrace();
            } else {
                System.out.println("Some internal error occurred");
            }
            return false;
        }
        return true;
    }

    public static void updatePassword(String[] args) throws IOException {
        String username = args[1];
        String oldPassword = encryptString(args[2]);
        String newPassword = encryptString(args[3]);

        Connection con = ConnectionFactory.getConnection();
        String query = "SELECT * FROM account";
        try(PreparedStatement stmt = con.prepareStatement(query)) {
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
                if((rs.getString("username").equals(username)) && (rs.getString("password").equals(oldPassword))) {
                    DataBaseModifier.updatePassword(username, newPassword);
                    break;
                }
            }
        } catch(Exception e) {
            if(inProduction) {
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
}
