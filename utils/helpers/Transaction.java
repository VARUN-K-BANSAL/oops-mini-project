package utils.helpers;

public class Transaction {
    private String transactionId;
    private String date;
    private int amount;
    private Account senderAccount;
    private Account receiverAccount;

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

    public Account getSenderAccount() {
        return senderAccount;
    }

    public void setSenderAccount(Account senderAccount) {
        this.senderAccount = senderAccount;
    }

    public Account getReceiverAccount() {
        return receiverAccount;
    }

    public void setReceiverAccount(Account receiverAccount) {
        this.receiverAccount = receiverAccount;
    }

    public Transaction(String transactionId, String date, int amount, Account senderAccount,
            Account receiverAccount) {
        this.transactionId = transactionId;
        this.date = date;
        this.amount = amount;
        this.senderAccount = senderAccount;
        this.receiverAccount = receiverAccount;
    }
    @Override
    public String toString() {
        return "[Sender : " + this.senderAccount.getAccountNumber()
                + ", Receiver : " + this.receiverAccount.getAccountNumber() 
                + ", Transaction ID : " + this.getTransactionId()
                + ", Date : " + this.getDate()
                + ", Amount : " + this.getAmount() + "]";
    }
}
