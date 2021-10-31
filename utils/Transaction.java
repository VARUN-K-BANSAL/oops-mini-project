package utils;

public class Transaction {
    String transactionId;
    String date;
    String status;
    Account senderAccount;
    Account receiverAccount;

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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public Transaction(String transactionId, String date, String status, Account senderAccount,
            Account receiverAccount) {
        this.transactionId = transactionId;
        this.date = date;
        this.status = status;
        this.senderAccount = senderAccount;
        this.receiverAccount = receiverAccount;
    }
}
