package individuals;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;


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

    /**
     * checking whether user have correct credentials or not
     */
    public static void checkAdmin(String[] args) throws IOException {
        Path path = Paths.get("data/admin.csv");
        if(Files.exists(path)) {
            List<String> credentials;
            credentials = Files.readAllLines(path)
                            .stream()
                            .toList();
            
            for (String line : credentials) {
                String creds[] = line.split(",");
                String userName = encryptString(args[1]);
                String userPass = encryptString(args[2]);
                
                if((userName.equals(creds[0])) && (userPass.equals(creds[1]))) {
                    System.out.println("Admin auth success");
                    break;
                }
                else {
                    System.out.println("Admin access failed");
                    break;
                }
            }
        } else {
            System.out.println("file does not exists");
        }
    }

    /**
     * updating password after confirming admin access to the app
     */
    public static void updatePassword(String[] args) throws IOException {
        String userName = encryptString(args[1]);
        String oldPassword = encryptString(args[2]);

        Path path = Paths.get("data/admin.csv");
        if(Files.exists(path)) {
            List<String> credentials;
            credentials = Files.readAllLines(path)
                            .stream()
                            .toList();
            
            for (String line : credentials) {
                String creds[] = line.split(",");
                
                if((userName.equals(creds[0])) && (oldPassword.equals(creds[1]))) {
                    String newPassword = encryptString(args[3]);
                    Files.writeString(path, creds[0]+","+newPassword, StandardCharsets.US_ASCII);
                    System.out.println("password changed successfully");
                }
                else {
                    System.out.println("Password given is wrong");
                    break;
                }
            }
        } else {
            System.out.println("file does not exists");
        }
    }
    
}
