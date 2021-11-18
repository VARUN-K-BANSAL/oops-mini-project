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

    public String getTypeOfTransaction() {
        return typeOfTransaction;
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
     * string for getting Date Time
     * DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        System.out.println(dtf.format(now));
     */
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
                + ", Receiver : " + this.getReceiver()
                + ", Transaction ID : " + this.getTransactionId()
                + ", Date : " + this.getDate()
                + ", Amount : " + this.getAmount()
                + "\n-------------------------------------------------------------------";
    }
}
