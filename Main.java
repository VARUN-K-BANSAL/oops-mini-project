import java.io.IOException;
import individuals.Varun;

/**
 * Main
 */
public class Main {

    public static void main(String[] args) throws IOException {
        if(args.length == 0) {
            printHelp();
            return;
        }

        switch(args[0]) {
            case "-h":
                    printHelp();
                    break;
            
            case "-a":
                    if(args.length < 3) {
                        System.out.println("Invalid number of arguments entered, use help for more details");
                        return;
                    }

                    Varun.checkAdmin(args);
                    break;

            case "-u":
                    Varun.updatePassword(args);
                    break;

            default:
                    printHelp();
                    break;
        }
    }

    public static void printHelp() {
        System.out.println("-------------------------------------------------------------------");
        System.out.println("Record Manager, version 1.0.0");
        System.out.println("  -h\tfor the commands help");
        System.out.println("  -a\tfor admin access followed by username and password");
        System.out.println("  -u\tfor updating password followed by username, old password then new password");
        System.out.println("-------------------------------------------------------------------");
    }
}