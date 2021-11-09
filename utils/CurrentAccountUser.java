package utils;

import java.io.FileWriter;
import java.io.IOException;

import utils.helpers.Account;
import utils.helpers.CurrentAccount;

public class CurrentAccountUser {
    private String username;
    private String password;
    private CurrentAccount account = new CurrentAccount();

    public CurrentAccountUser(String name, String gender, String username, String password, double balance) throws IOException {
        this.username = username;
        this.password = password;
        this.account.setAccountNumber(Account.generateAccountNumber());
        this.account.setBalance(balance);
        this.account.setName(name);
        this.account.setGender(gender);

        addUserToFile();
    }

    private void addUserToFile() throws IOException {
        String fileName = "data/currentAccountUser.csv";
        String entry = this.getUsername() +"," + this.getPassword() + "," + this.account.getAccountNumber() + "," + this.account.getName() + "," + this.account.getGender() + "\n";
        FileWriter writer = new FileWriter(fileName, true);
        writer.write(entry);
        writer.close();
        System.out.println("Account created successfully");
        System.out.println(this);
    }

    @Override
    public String toString() {
        return "Name : " + this.account.getName()
                 + "\nUserName : " + this.getUsername()
                 + "\nAccount Number : " + this.account.getAccountNumber()
                 + ", Gender : " + this.account.getGender() + "\n";
    }

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
