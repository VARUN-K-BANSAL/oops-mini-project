import java.io.IOException;
import individuals.S20200010223;

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
                    S20200010223.checkAdmin(args);
                    break;

            case "-u":
                    S20200010223.updatePassword(args);
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
        System.out.println("-------------------------------------------------------------------");
    }
}