package utils.helpers;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Transaction {
    private String transactionId;
    private String date;
    private int amount;
    private String sender;
    private String receiver;
    private String typeOfTransaction;

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getTypeOfTransaction(boolean isPrinting) {
        if(isPrinting) {
            if(this.typeOfTransaction.equals("W")) {
                return "Withdraw";
            } else if(this.typeOfTransaction.equals("D")) {
                return "Deposit";
            } else if(this.typeOfTransaction.equals("T")) {
                return "Transfer";
            }
        } else {
            return this.typeOfTransaction;
        }
        return null;
    }

    public String getTypeOfTransaction() {
        return this.typeOfTransaction;
    }

    public void setTypeOfTransaction(String typeOfTransaction) {
        this.typeOfTransaction = typeOfTransaction;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    /**This Constructor is made for the case when we are retrieving data from database */
    public Transaction(String transactionId, String date, int amount, String sender, String receiver,
            String typeOfTransaction) {
        this.transactionId = transactionId;
        this.date = date;
        this.amount = amount;
        this.sender = sender;
        this.receiver = receiver;
        this.typeOfTransaction = typeOfTransaction;
    }

    /**This Constructor is made for the case when we are storing data in database */
    public Transaction(int amount, String sender, String receiver, String type) {
        this.date = getDateTime();
        this.amount = amount;
        this.sender = sender;
        this.receiver = receiver;
        this.typeOfTransaction = type;
    }
    private String getDateTime() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        return dtf.format(now);
    }

    @Override
    public String toString() {
        return "-------------------------------------------------------------------\n"
                + "Sender : " + this.getSender()
                + "\nReceiver : " + this.getReceiver()
                + "\nTransaction ID : " + this.getTransactionId()
                + "\nDate : " + this.getDate()
                + "\nAmount : " + this.getAmount()
                + "\nType of transaction : " + this.getTypeOfTransaction(true)
                + "\n-------------------------------------------------------------------";
    }
}
