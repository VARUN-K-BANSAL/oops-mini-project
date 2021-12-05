package utils.helpers;

public class Helps {

    public static void printHelp() {
        System.out.println("-------------------------------------------------------------------");
        System.out.println("IIIT Sri City Bank, version 1.0.0");
        System.out.println("--init\tfor initialising the program followed by your MYSQL username and password");
        System.out.println("  -c\tfor creating a new account");
        System.out.println("  -up\tfor updating password");
        System.out.println("  -s\tfor searching details about users, transactions etc");
        System.out.println("  -d\tfor deleting the account");
        System.out.println("  -tr\tfor doing the transaction");
        System.out.println("  -sort\tfor printing users in sorted order of their name");
        System.out.println("  -l\tfor loan purpose");
        System.out.println("  -sl\tfor searching loan details");
        System.out.println("  -h\tfor the commands help");
        System.out.println("  \tTo get help of any command just enter the command");
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

    public static void createLoanAccountHelp() {
        System.out.println("  -l\tfor creating new loan account");
        System.out.println("  \t\t\tArguments");
        System.out.println("  \t\t1. Do you have an account in bank? (Y/N)");
        System.out.println();
        System.out.println("  \t\tIf 'Yes(Y)' then");

        System.out.println("  \t\t2. Username of your account");
        System.out.println("  \t\t3. Loan Type {'EL' for Education Loan, 'AL' for Agriculture Loan, 'PL' for Personal Loan}");
        System.out.println("  \t\t4. Loan Amount");
        System.out.println();

        System.out.println("  \t\tIf 'No(N)' then");
        System.out.println("  \t\tCreate an account first.");
        System.out.println("  \t\t2.Name of account holder(Borrower) (use \"\" if there is a space in name)");
        System.out.println("  \t\t3.Gender M (Male) or F (Female)");
        System.out.println("  \t\t4.Unique username to be used");
        System.out.println("  \t\t5.Password to be used");
        System.out.println("  \t\t6.Loan Type");
        System.out.println("  \t\t7.Loan Amount");
    }

    public static void searchLoanHelp() {
        System.out.println("  -sl\tfor searching loan details");
        System.out.println("  \t\tArguments");
        System.out.println("  \t\t'-u' followed by username for searching using username.");
        System.out.println("  \t\t'-id' followed by loan_id for searching using loan_id.");
    }

    public static void updatePasswordHelp() {
        System.out.println("  -up\tfor updating password");
        System.out.println("  \t\t\tArguments");
        System.out.println("  \t\t1. Username");
        System.out.println("  \t\t2. Current password");
        System.out.println("  \t\t3. New password");

    }

    public static void searchHelp() {
        System.out.println("  -s\tfor searching details");
        System.out.println("  \t\t\tArguments");
        System.out.println("  \t\t-u for searching using username followed by username of the account");
        System.out.println("  \t\t\talso add -d after username for detailed description of user");
        System.out.println("  \t\t-a for searching using account number followed by account number");
        System.out.println("  \t\t-n for searching using name followed by name of the account holder");
        System.out.println("  \t\t-t for tracking a transaction followed by transaction Id");
    }

    public static void transactionHelp() {
        System.out.println("  -tr\tfor doing trasactions");
        System.out.println("  \t\t\tArguments");
        System.out.println("  \t\t1. -w for withdrawing, -d for deposit, -t for transferring");
        System.out.println("  \t\t2. Username of the account");
        System.out.println("  \t\t3. Password of the account (no need for deposit ignore it)");
        System.out.println("  \t\t4. Amount of the account");
        System.out.println("  \t\t5. Username of reciever if applicable");
    }

    public static void sortHelp() {
        System.out.println("  -sort\tfor printing customer names and account number in sorted order");
        System.out.println("\t\tNo arguments required");
    }
}
