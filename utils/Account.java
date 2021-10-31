package utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

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

    public static String generateAccountNumber() throws IOException {
        long accNumber = Math.round(Math.random() * 1000000 + 1);
        String acc = Long.toString(accNumber);
        while(!((checkAccountNumber(acc, Paths.get("data/savingAccountUser.csv"))) && checkAccountNumber(acc, Paths.get("data/currentAccountUser.csv")))) {
            accNumber = Math.round(Math.random() * 1000000 + 1);
            acc = Long.toString(accNumber);
        }
        return acc;
    }

    private static boolean checkAccountNumber(String acc, Path path) throws IOException {
        if (Files.exists(path)) {
            List<String> lines = Files.readAllLines(path);
            for (String line : lines) {
                String creds[] = line.split(",");
                if (acc.equals(creds[2])) {
                    return false;
                }
            }
        }
        return true;
    }

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
