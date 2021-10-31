package utils;

public abstract class Account {
    private String accountNumber;
    private double balance;

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public Account(String accountNumber, double balance) {
        this.accountNumber = accountNumber;
        this.balance = balance;
    }

    public abstract void addInterest();

    public boolean withdraw(int amount) {
        if(this.balance > amount) {
            this.setBalance(this.getBalance() - amount);
            return true;
        } else {
            System.out.println("No enough balance | Available balance : " + this.getBalance());
            return false;
        }
    }

}
