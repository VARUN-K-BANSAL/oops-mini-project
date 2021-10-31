package individuals;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import utils.CurrentAccountUser;
import utils.SavingsAccountUser;


/**
 * change to sub packages with admin class
 */
public class Varun {

    final private static int KEY = 5;
    final private static String ALPHA = "abcdefghijklmnopqrstuvwxyz";

    /**
     * method for encrypting strings for storing username and passwords
     */
    private static String encryptString(String inputString) {
        String encryptedString = "";
        for(int i=0; i<inputString.length(); i++) {
            if(inputString.charAt(i) != ' ') {
                int temp = (KEY + ALPHA.indexOf(inputString.charAt(i))) % 26;
                encryptedString = encryptedString + ALPHA.charAt(temp);
            }
            else encryptedString = encryptedString + ' ';
        }
        return encryptedString;
    }

    public static void createNewAccount(String[] args) throws IOException {
        String accType = args[1];
        String name = args[2];
        String gender = args[3];
        String username = args[4];
        String password = encryptString(args[5]);

        if(accType.equals("CA")) {
            CurrentAccountUser user = new CurrentAccountUser(name, gender, username, password, 0);
            System.out.println(user);
        } else if(accType.equals("SA")) {
            SavingsAccountUser user = new SavingsAccountUser(name, gender, username, password, 0);
            System.out.println(user);
        } else {
            System.out.println("Invalid type of Account");
        }
    }

    public static void updatePassword(String[] args) throws IOException {
        String username = args[1];
        String oldPassword = encryptString(args[2]);
        String newPassword = encryptString(args[3]);

        Path savingsPath = Paths.get("data/savingsAccountUser.csv");
        if(Files.exists(savingsPath)) {
            List<String> credentials;
            credentials = Files.readAllLines(savingsPath)
                            .stream()
                            .toList();

            for (String line : credentials) {
                String creds[] = line.split(",");

                if((username.equals(creds[0])) && (oldPassword.equals(creds[1]))) {
                    String newEntry = username + "," + newPassword + "," + creds[2] + "," + creds[3] + "\n";
                    FileWriter writer = new FileWriter("data/savingsAccountUser.csv");
                }
            }
        }
    }
    
}
