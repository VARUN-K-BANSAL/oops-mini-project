package utils.helpers;

public class Helps {

    public static void printHelp() {
        System.out.println("-------------------------------------------------------------------");
        System.out.println("Record Manager, version 1.0.0");
        System.out.println("--init\tfor initialising the program followed by your MYSQL username and password");
        System.out.println("  -c\tfor creating a new account");
        System.out.println("  -u\tfor updating password");
        System.out.println("  -su\tfor searching details using username");
        System.out.println("  -sa\tfor searching using account number");
        System.out.println("  -sn\tfor searching details using name");
        System.out.println("  -d\tfor deleting the account");
        System.out.println("  -h\tfor the commands help");
        // createNewAccountHelp();
        // updatePasswordHelp();
        System.out.println("-------------------------------------------------------------------");
    }

    public static void deleteAccountHelp() {
        System.out.println("  -d\tfor deleting the account");
        System.out.println("  \t\t\tArguments");
        System.out.println("  \t\t1. username of the account");
        System.out.println("  \t\t2. Password of the account");
    }

    public static void createNewAccountHelp() {
        System.out.println("  -c\tfor creating new account");
        System.out.println("  \t\t\tArguments");
        System.out.println("  \t\t1. Type of account SA (savings) or CA (current)");
        System.out.println("  \t\t2. Name of account holder (use \"\" if there is a space in name)");
        System.out.println("  \t\t3. Gender M (Male) or F (Female)");
        System.out.println("  \t\t4. Username to be used");
        System.out.println("  \t\t5. Password to be used");
        System.out.println("  \t\t6. Opening balance");
    }

    public static void updatePasswordHelp() {
        System.out.println("  -u\tfor updating password");
        System.out.println("  \t\t\tArguments");
        System.out.println("  \t\t1. Username");
        System.out.println("  \t\t2. Current password");
        System.out.println("  \t\t3. New password");
    }

    public static void searchUsingAccountNumberHelp() {
        System.out.println("  -sa\tfor searching details using account number");
        System.out.println("  \t\t\tArguments");
        System.out.println("  \t\t1. Account number of the account");
    }
    
    public static void searchUsingUserNameHelp() {
        System.out.println("  -su\tfor searching details using username");
        System.out.println("  \t\t\tArguments");
        System.out.println("  \t\t1. Username of the account");
    }

    public static void searchUsingNameHelp() {
        System.out.println("  -su\tfor searching details using name");
        System.out.println("  \t\t\tArguments");
        System.out.println("  \t\t1. Name of the account holder");
    }
}
