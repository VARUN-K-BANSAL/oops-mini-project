package individuals;

import java.sql.Connection;
import java.sql.PreparedStatement;

import utils.CurrentAccountUser;
import utils.SavingAccountUser;
import utils.database.ConnectionFactory;
import utils.database.DataBaseModifier;
import utils.database.SearchDataBase;
import utils.helpers.LoanAccount;

public class Amogh {
    
    /**
     * Method for creating new loan account
     * It adds data to the database by using dataBaseModifier.java file
     *
     */ 
    public static void createLoanAccount(String[] args) {
        /**
         * if user provides username i.e. already an account.
         * It will be linked will loan account.
         */ 
        if (args[1].equals("Y")){
            String [] data = new String[4];
            data[0] = args[2];  // username
            if((getAccountHolderName(args[2])) != null){
                args[3] = args[3].toUpperCase(); // loantype
                if(args[3].equals("EL") || args[3].equals("PL") || args[3].equals("AL")){
                    if(Double.valueOf(args[4]) <= 100000){
                        data[1] = Amogh.getAccountHolderName(args[2]);
                        data[2] = args[3];
                        data[3] = args[4];
                        DataBaseModifier.addDataToLoanTable(data);
                        LoanAccount user = new LoanAccount(data);
                        System.out.println(user); 
                        System.out.println("Your loan has been approved.");
                        System.out.println("Loan amount will be added to your account in a moment.");
                        addLoanAmount(args[2], Double.valueOf(args[4]));
                    } else {
                        System.out.println("Bank can grant maximum loan amount upto 1 Lakh only.");
                    }
                } else {
                    System.out.println("Invalid Loan Type.");
                }
            }
        } else if (args[1].equals("N")){          // If user don't have an account.
            String name = args[2];
            String gender = args[3];
            String username = args[4];
            String password = args[5];
            String openingBalance;
            Integer value = Integer.valueOf(args[7]);
                if(value <= 100000){
                    openingBalance = Integer.toString(value);
                } else {
                    openingBalance = Integer.toString(0);
                }

            if (Varun.authenticateUsername(username)) {
                System.out.println("Hi " + name + ", your account will be created in a moment");
                CurrentAccountUser user = new CurrentAccountUser(name, gender, username, password,
                        Integer.valueOf(openingBalance));
                DataBaseModifier.addDataToAccountTable(user);
                System.out.println("Account created successfully");
                System.out.println(user);

                String [] data = new String[4];
                data[0] = args[4];  // username
                args[6] = args[6].toUpperCase(); // loantype
                if(args[6].equals("EL") || args[6].equals("PL") || args[6].equals("AL")){
                    if(Double.valueOf(args[7]) <= 100000){
                        data[1] = args[2];
                        data[2] = args[6];
                        data[3] = args[7];
                        
                        DataBaseModifier.addDataToLoanTable(data);
                        LoanAccount loanUser = new LoanAccount(data);
                        System.out.println(loanUser); 
                        System.out.println("Your loan has been approved.");
                        System.out.println("Loan amount is added to your account");
                    } else {
                        System.out.println("Bank can grant maximum loan amount upto 1 Lakh only.");
                    }
                } else {
                    System.out.println("Invalid Loan Type.");
                }
            } else {
                System.out.println("This username already exists please enter another username");
            }

        } else {
            System.out.println("Invalid argument!");
        }
    }
    

    /**
     * This method returns account_holder_name 
     * using username provided by user.
     */

    private static String getAccountHolderName(String username) {
        String name = new String();
        Object obj = new Object();
        obj = SearchDataBase.searchUser(username);
        if(obj != null){
            if(obj.getClass().equals(CurrentAccountUser.class)){
                CurrentAccountUser user = (CurrentAccountUser) obj;
                name = user.getAccount().getName();  
            } else if(obj.getClass().equals(SavingAccountUser.class)){
                SavingAccountUser user = (SavingAccountUser) obj;
                name = user.getAccount().getName();
            } else {
                System.out.println("Cannot identify Account type.");
            }
        }      
        return name;
    }

    /**
     * Method for adding LoanAmount to the user's Account
     * username provided by user is used to find user's account and balance is updated.
     */
    private static void addLoanAmount(String username, double amount) {
        double balance = 0;
        Object obj = new Object();
        obj = SearchDataBase.searchUser(username);
        if(obj != null){
            if(obj.getClass().equals(CurrentAccountUser.class)){
                CurrentAccountUser user = (CurrentAccountUser) obj;
                balance = user.getAccount().getBalance();  
                ((CurrentAccountUser)obj).getAccount().setBalance(balance + amount);
            } else if(obj.getClass().equals(SavingAccountUser.class)){
                SavingAccountUser user = (SavingAccountUser) obj;
                balance = user.getAccount().getBalance();
                ((SavingAccountUser)obj).getAccount().setBalance(balance + amount);
            }

            Connection con = ConnectionFactory.getConnection();
            String query = "UPDATE account SET account_balance = ? WHERE username = ?";
            try (PreparedStatement stmt = con.prepareStatement(query)){
                stmt.setDouble(1,balance + amount); 
                stmt.setString(2,username);
                stmt.executeUpdate();
                System.out.println("Loan Amount is added to your account");
                Varun.addTransactionForLoan(obj, amount);
            } catch (Exception e) {
                if(Varun.inDevelopment){
                    e.printStackTrace();
                } else {
                    System.out.println("Unable to update balance.");
                }
            }
        }      
    }

}
