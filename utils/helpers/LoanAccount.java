package utils.helpers;

/**
 * LoanAccount class extending Account class
 */

public class LoanAccount extends Account {
    private String username;
    private String borrower_name;
    private String loan_type;
    private double loan_amount;
    private final static double interestRateEL = 4;
    private final static double interestRateAL = 6;
    private final static double interestRatePL = 8;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getBorrower_name() {
        return borrower_name;
    }

    public void setBorrower_name(String borrower_name) {
        this.borrower_name = borrower_name;
    }

    public String getLoan_type() {
        return loan_type;
    }

    public void setLoan_type(String loan_type) {
        this.loan_type = loan_type;
    }

    public double getLoan_amount() {
        return loan_amount;
    }

    public void setLoan_amount(double loan_amount) {
        this.loan_amount = loan_amount;
    }

    public static double getInterestRateEL() {
        return interestRateEL;
    }

    public static double getInterestRateAL() {
        return interestRateAL;
    }

    public static double getInterestRatePL() {
        return interestRatePL;
    }

    public LoanAccount(String accountNumber, double balance, String name, String gender) {
        super(accountNumber, balance, name, gender);
        // TODO
    }

    public LoanAccount(String username, String borrower_name, String loan_type, double loan_amount) {
        super("", 0, "", "");
        this.username = username;
        this.borrower_name = borrower_name;
        this.loan_type = loan_type;
        this.loan_amount = loan_amount;
    }

    public LoanAccount(String accountNumber, double balance, String name, String gender, String username,
            String borrower_name, String loan_type, double loan_amount) {
        super(accountNumber, balance, name, gender);
        this.username = username;
        this.borrower_name = borrower_name;
        this.loan_type = loan_type;
        this.loan_amount = loan_amount;
    }

    public LoanAccount(String[] data) {
        super("", 0, "", "");
        this.username = data[0];
        this.borrower_name = data[1];
        this.loan_type = data[2];
        this.loan_amount = Double.valueOf(data[3]);

    }

    @Override
    public void addInterest() {
        if (this.loan_type.equals("EL")) {
            this.setLoan_amount(getLoan_amount() + this.getLoan_amount() * interestRateEL);
        } else if (this.loan_type.equals("AL")) {
            this.setLoan_amount(getLoan_amount() + this.getLoan_amount() * interestRateAL);
        } else if (this.loan_type.equals("PL")) {
            this.setLoan_amount(getLoan_amount() + this.getLoan_amount() * interestRatePL);
        }
    }

    @Override
    public String toString() {
        return " LoanAccount " + "\n"
                + " username = " + username + "\n"
                + " borrower_name = " + borrower_name + "\n"
                + " loan_type = " + loan_type + "\n"
                + " loan_amount = " + loan_amount;
    }

}
