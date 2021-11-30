package utils.helpers;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * This is Transaction class which will represent every type of transaction
 * Everything is private here to enhance abstraction
 */
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

    /**
     * In this method we used an extra argument isPrinting boolean because
     * We need this function two times
     * 1 - when storing data in database then we need this function to return 1 char ('W' or 'D' or 'T')
     * 2 - when printing the transaction we need this function to return a word
     */
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

    /**
     * This Constructor is made for the case when we are retrieving data from database
     * because in the other constructor the date is generated according to the system date and time
     */
    public Transaction(String transactionId, String date, int amount, String sender, String receiver,
            String typeOfTransaction) {
        this.transactionId = transactionId;
        this.date = date;
        this.amount = amount;
        this.sender = sender;
        this.receiver = receiver;
        this.typeOfTransaction = typeOfTransaction;
    }

    /**
     * This Constructor is made for the case when we are storing data in database
     * because we need date and time here which we are taking current date and time of the system
     */
    public Transaction(int amount, String sender, String receiver, String type) {
        this.date = getDateTime();
        this.amount = amount;
        this.sender = sender;
        this.receiver = receiver;
        this.typeOfTransaction = type;
    }

    /**
     * Private method to get the current date and time in the required format
     */
    private String getDateTime() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        return dtf.format(now);
    }

    /**
     * Overridden toString method to beautifully print the transaction record
     */
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
