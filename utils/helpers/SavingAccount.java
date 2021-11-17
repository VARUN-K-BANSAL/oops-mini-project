package utils.helpers;

public class SavingAccount extends Account{
    
    private final static double interestRate = 5;
    private final static int maxLoanAmount = 2000;

    public static double getInterestrate() {
        return interestRate;
    }

    public static int getMaxloanamount() {
        return maxLoanAmount;
    }

    public SavingAccount(String accountNumber, double balance, String name, String gender) {
        super(accountNumber, balance, name, gender);
    }

    public SavingAccount() {
        super("", 0, "", "");
    }

    public double getInterestRate() {
        return interestRate;
    }

    @Override
    public void addInterest() {
        this.setBalance(getBalance() + (this.getBalance()*interestRate)/100);
    }
}
