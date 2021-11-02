import java.io.IOException;
import individuals.Varun;
import utils.Helps;

/**
 * Main
 */
public class Main {

    public static void main(String[] args) throws IOException {
        if(args.length == 0) {
            Helps.printHelp();
            return;
        }

        switch(args[0]) {
            case "-h":
                    Helps.printHelp();
                    break;
            
            case "-c":
                    if(args.length < 6) {
                        System.out.println("Invalid number of arguments entered, use help for more details");
                        Helps.createNewAccountHelp();
                        return;
                    }

                    Varun.createNewAccount(args);
                    break;

            case "-u":
                    if(args.length < 4) {
                        System.out.println("Invalid number of arguments for updating password");
                        Helps.updatePasswordHelp();
                        return;
                    }
                    Varun.updatePassword(args);
                    break;

            case "--init":
                    Varun.programInit();
                    break;

            default:
                    Helps.printHelp();
                    break;
        }
    }
}