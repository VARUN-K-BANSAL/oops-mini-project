package utils;

import utils.helpers.Account;
import utils.helpers.CurrentAccount;

/**
 * CurrentAccountUser class
 * It has a username, password and an blank Object of type CurrentAccount
 */
public class CurrentAccountUser {
    private String username;
    private String password;
    private CurrentAccount account = new CurrentAccount();

    /**
     * Constructor with arguments
     */
    public CurrentAccountUser(String name, String gender, String username, String password, double balance) {
        this.username = username;
        this.password = password;
        this.account.setAccountNumber(Account.generateAccountNumber());
        this.account.setBalance(balance);
        this.account.setName(name);
        this.account.setGender(gender);
    }

    /**
     * Overridden toString method for beautifully printing the Object
     */
    @Override
    public String toString() {
        return "-------------------------------------------------------------------------\n"
                + "Name : " + this.account.getName()
                + "\nUserName : " + this.getUsername()
                + "\nAccount Number : " + this.account.getAccountNumber()
                + "\nCurrent balance : " + this.account.getBalance()
                + "\nType of Account : Current account"
                + "\nGender : " + this.account.getGender()
                + "\n-------------------------------------------------------------------------";
    }

    /**
     * Getters and Setters
     */
    public CurrentAccount getAccount() {
        return account;
    }

    public void setAccount(CurrentAccount account) {
        this.account = account;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
