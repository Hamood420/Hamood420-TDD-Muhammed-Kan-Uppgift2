package org.example;

public class Account {
    String userFirstName;
    String userLastName;
    String cardIDNum;
    String cardPinNum;
    int balance;
    boolean isLocked;
    String bankName;

    public boolean isLocked() {
        return isLocked;
    }

    public void setLocked(boolean isLocked) {
        this.isLocked = isLocked;
    }

    public Account(String userFirstName, String userLastName, String cardIdNum, String cardPinNum, int balance, String bankName) {
        super();
        this.userFirstName = userFirstName;
        this.userLastName = userLastName;
        this.cardIDNum = cardIDNum;
        this.cardPinNum = cardPinNum;
        this.balance = balance;
        this.bankName = bankName;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getUserFirstName() {
        return userFirstName;
    }

    public void setUserFirstName(String userFirstName) {
        this.userFirstName = userFirstName;
    }

    public String getUserLastName() {
        return userLastName;
    }

    public void setUserLastName(String userLastName) {
        this.userLastName = userLastName;
    }

    public String getCardIdNum() {
        return cardIDNum;
    }

    public void setCardIDNum(String cardIdNum) {
        this.cardIDNum = cardIdNum;
    }

    public String getCardPinNum() {
        return cardPinNum;
    }

    public void setCardPinNum(String cardPinNum) {
        this.cardPinNum = cardPinNum;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

}
