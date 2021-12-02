package utils.helpers;

/**
 * CurrentAccount class extending Account class
 */
public class SavingAccount extends Account {

    private final static double interestRate = 5;
    private final static int maxLoanAmount = 2000;

    public static double getInterestrate() {
        return interestRate;
    }

    public static int getMaxloanamount() {
        return maxLoanAmount;
    }

    /**
     * Constructor with arguments
     */
    public SavingAccount(String accountNumber, double balance, String name, String gender) {
        super(accountNumber, balance, name, gender);
    }

    /**
     * Constructor without arguments overloaded method
     * This is called when we need a blank Object of type SavingAccount
     * like in SavingAccountUser class so that we can modify the data accordingly
     */
    public SavingAccount() {
        super("", 0, "", "");
    }

    public double getInterestRate() {
        return interestRate;
    }

    /**
     * That abstract method of Account class is overridden here
     */
    @Override
    public void addInterest() {
        this.setBalance(getBalance() + (this.getBalance() * interestRate) / 100);
    }
}
