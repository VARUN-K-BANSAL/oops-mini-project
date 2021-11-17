package utils.helpers;

public class CurrentAccount extends Account{
    
    private final static double interestRate = 8;
    private final static int maxLoanAmount = 4000;

    public static double getInterestrate() {
        return interestRate;
    }

    public static int getMaxloanamount() {
        return maxLoanAmount;
    }    

    public CurrentAccount(String accountNumber, double balance, String name, String gender) {
        super(accountNumber, balance, name, gender);
    }

    public CurrentAccount() {
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
