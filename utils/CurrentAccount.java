package utils;

public class CurrentAccount extends Account{
    
    private double interestRate = 12;

    public CurrentAccount(String accountNumber, double balance) {
        super(accountNumber, balance);
    }

    public CurrentAccount() {
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
