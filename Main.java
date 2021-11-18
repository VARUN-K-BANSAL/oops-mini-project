/**
 * Github Repo Link for clarification - https://github.com/VARUN-K-BANSAL/oops-mini-project
 */

import java.io.IOException;
import individuals.Varun;
import utils.database.DataBaseModifier;
import utils.database.DatabaseCreator;
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

            case "-up":
                    if(args.length < 4) {
                        System.out.println("Invalid number of arguments for updating password");
                        Helps.updatePasswordHelp();
                        return;
                    }
                    Varun.updatePassword(args);
                    break;

            case "-s":
                    if(args.length < 3) {
                        System.out.println("Invalid number of arguments for searching");
                        Helps.searchUsingUserNameHelp();
                        return;
                    }
                    Varun.searchUser(args);
                    return;

            case "-d":
                    if(args.length < 3) {
                        System.out.println("Invalid number of arguments");
                        Helps.deleteAccountHelp();
                        return;
                    }
                    DataBaseModifier.deleteAccount(args);
                    break;

            case "-tr":
                    if(args.length < 2) {
                        System.out.println("Invalid number of arguments");
                        Helps.transactionHelp();
                        return;
                    }
                    Varun.executeTransaction(args);
                    break;

            case "--init":
                    DatabaseCreator.programInit(args);
                    break;

            default:
                    Helps.printHelp();
                    break;
        }
    }
}