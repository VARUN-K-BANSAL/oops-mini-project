package utils.helpers;

/**
 * CurrentAccount class extending Account class
 */
public class CurrentAccount extends Account{
    
    private final static double interestRate = 8;
    private final static int maxLoanAmount = 4000;

    public static double getInterestrate() {
        return interestRate;
    }

    public static int getMaxloanamount() {
        return maxLoanAmount;
    }    

    /**
     * Constructor with arguments
     */
    public CurrentAccount(String accountNumber, double balance, String name, String gender) {
        super(accountNumber, balance, name, gender);
    }

    /**
     * Constructor without arguments overloaded method
     * This is called when we need a blank Object of type CurrentAccount
     * like in CurrentAccountUser class so that we can modify the data accordingly
     */
    public CurrentAccount() {
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
        this.setBalance(getBalance() + (this.getBalance()*interestRate)/100);
    }
}
