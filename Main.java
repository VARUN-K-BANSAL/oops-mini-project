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
            
            case "-c":
                    if(args.length < 6) {
                        System.out.println("Invalid number of arguments entered, use help for more details");
                        printHelp();
                        return;
                    }

                    Varun.createNewAccount(args);
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
        System.out.println("  -c\tfor creating new account");
        System.out.println("  \t\t\tArguments");
        System.out.println("  \t\t-Type of account SA (savings) or CA (current)");
        System.out.println("  \t\t-Name of account holder");
        System.out.println("  \t\t-Gender M (Male) or F (Female)");
        System.out.println("  \t\t-Username to be used");
        System.out.println("  \t\t-Password to be used");
        System.out.println("  -u\tfor updating password followed by username, old password then new password");
        System.out.println("-------------------------------------------------------------------");
    }
}