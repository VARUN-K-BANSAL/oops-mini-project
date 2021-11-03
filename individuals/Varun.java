package individuals;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import utils.CurrentAccountUser;
import utils.SavingAccountUser;
import utils.database.ConnectionFactory;
import utils.database.DataBaseModifier;

public class Varun {

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
        String query = "SELECT * FROM account";
        try(PreparedStatement stmt = con.prepareStatement(query)) {
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
                if(rs.getString("username").equals(username)) {
                    return false;
                }
            }
        } catch(Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public static void updatePassword(String[] args) throws IOException {
        String username = args[1];
        String oldPassword = encryptString(args[2]);
        String newPassword = encryptString(args[3]);

        Path savingPath = Paths.get("data/savingAccountUser.csv");
        if (Files.exists(savingPath)) {
            List<String> credentials = Files.readAllLines(savingPath);
            List<String> newCredentials = new ArrayList<String>();

            boolean isValidUser = false;
            for (String line : credentials) {
                String creds[] = line.split(",");
                if ((username.equals(creds[0])) && (oldPassword.equals(creds[1]))) {
                    isValidUser = true;
                    String entry = creds[0] + "," + newPassword + "," + creds[2] + "," + creds[3] + "," + creds[4];
                    newCredentials.add(entry);
                } else {
                    newCredentials.add(line);
                }
            }

            if (isValidUser) {
                String file = "data/savingAccountUser.csv";
                FileWriter temp = new FileWriter(file, false);
                temp.write("");
                temp.close();
                FileWriter writer = new FileWriter(file, true);
                for (String line : newCredentials) {
                    line = line + "\n";
                    writer.write(line);
                }
                writer.close();
            } else {
                System.out.println("Invalid Username or password");
            }

        } else {
            System.out.println("File does not exist error");
        }
    }
}
