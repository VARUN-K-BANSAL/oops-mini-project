package utils;

public class SavingsAccount extends Account{
    
    private double interestRate = 8;

    public SavingsAccount(String accountNumber, double balance) {
        super(accountNumber, balance);
    }

    public SavingsAccount() {
        super("0", 0);
    }

    public double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(int interestRate) {
        this.interestRate = interestRate;
    }

    @Override
    public void addInterest() {
        this.setBalance(getBalance() + (this.getBalance()*interestRate)/100);
    }
}
