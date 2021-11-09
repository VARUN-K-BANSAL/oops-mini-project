package utils;

import java.io.IOException;

import utils.helpers.Account;
import utils.helpers.SavingAccount;

public class SavingAccountUser {
    private String username;
    private String password;
    private SavingAccount account = new SavingAccount();

    public SavingAccountUser(String name, String gender, String username, String password, double balance) throws IOException {
        this.username = username;
        this.password = password;
        this.account.setAccountNumber(Account.generateAccountNumber());
        this.account.setBalance(balance);
        this.account.setName(name);
        this.account.setGender(gender);
    }

    @Override
    public String toString() {
        return "-------------------------------------------------------------------------\n" 
                 +"Name : " + this.account.getName()
                 + "\nUserName : " + this.getUsername()
                 + "\nAccount Number : " + this.account.getAccountNumber()
                 + "\nGender : " + this.account.getGender() 
                 + "\n-------------------------------------------------------------------------";
    }

    public SavingAccount getAccount() {
        return account;
    }

    public void setAccount(SavingAccount account) {
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
