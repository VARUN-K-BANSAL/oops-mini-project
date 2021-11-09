import java.io.IOException;
import individuals.Varun;
import utils.database.DatabaseCreator;
import utils.database.SearchDataBase;
import utils.helpers.Helps;

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
                    if(args.length < 7) {
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

            case "-su":
                    if(args.length < 2) {
                        System.out.println("Invalid number of arguments for searching");
                    }
                    SearchDataBase.searchUser(args[1]);
                    return;
            
            case "-sa":
                    if(args.length < 2) {
                        System.out.println("Invalid number of arguments for searching");
                    }
                    SearchDataBase.searchUserByAccountNumber(args[1]);
                    return;

            case "--init":
                    DatabaseCreator.programInit(args);
                    break;

            default:
                    Helps.printHelp();
                    break;
        }
    }
}