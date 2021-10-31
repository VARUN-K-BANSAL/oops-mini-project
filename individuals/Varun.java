package individuals;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import utils.CurrentAccountUser;
import utils.SavingAccountUser;

/**
 * change to sub packages with admin class
 */
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
        Path path1 = Paths.get("data/savingAccountUser.csv");
        Path path2 = Paths.get("data/currentAccountUser.csv");

        if ((authenticateUsername(username, path1)) && (authenticateUsername(username, path2))) {
            System.out.println("Hi " + name + ", your account will be created in a moment");
            if (accType.equals("CA")) {
                CurrentAccountUser user = new CurrentAccountUser(name, gender, username, password, 0);
                System.out.println(user);
            } else if (accType.equals("SA")) {
                SavingAccountUser user = new SavingAccountUser(name, gender, username, password, 0);
                System.out.println(user);
            } else {
                System.out.println("Invalid type of Account");
            }
        } else {
            System.out.println("This username already exists please enter another username");
        }
    }

    private static boolean authenticateUsername(String username, Path path) throws IOException {
        if (Files.exists(path)) {
            List<String> lines = Files.readAllLines(path);
            for (String line : lines) {
                String creds[] = line.split(",");
                if (username.equals(creds[0])) {
                    return false;
                }
            }
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
