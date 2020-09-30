package Bank;

public class Account {
    private String credit_number; //credit number
    private String PIN; // Pin number
    private double balance; //current balance of the account

    //setter for credit number
    public void setcreditnumber(String credit_number) {
        this.credit_number = credit_number;
    }

    //getter for credit number
    public String getCreditnumber() {
        return credit_number;
    }

    //getter for PIN number
    public String getPIN() {
        return PIN;
    }

    //setter for PIN number
    public void setPIN(String PIN) {
        this.PIN = PIN;
    }

    //getter for current balance of the account
    public double getBalance() {
        return balance;
    }

    //setter for current balance of the account
    public void setBalance(double balance) {
        this.balance = balance;
    }
}
