package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import static java.lang.System.in;

public class Bank {
    static Scanner scanner = new Scanner(in);

    List<Account> accountsList = new ArrayList<>();

    public static List<Account> getAccountList() {
        List<Account> accountList = new ArrayList<>();
        Account account = new Account("Dahoud", "Süleyman", "453905695609", "1025", 1000, "Ohio");
        Account account1 = new Account("Hamood", "Yılmaz", "395969314959", "3590", 6900, "Denver");
        Account account2 = new Account("Walid", "Parker", "20895795126", "7829", 1400, "Albuquerque");
        Account account3 = new Account("Mehmed", "Süleyman", "789625702324", "3335", 1900, "Albuquerque");

        accountList.add(account);
        accountList.add(account1);
        accountList.add(account2);
        accountList.add(account3);

        return accountList;
    }

    public void setAccountsList(List<Account> accountsList) {
        this.accountsList = accountsList;
    }

    public boolean checkPinNum(String pinNum, Account account) {
        boolean signedIn = false;
        Optional<Account> accountOptinal = getAccountList().stream().filter(b -> b.getUserFirstName().equals(account.getUserFirstName())).findFirst();

        if (accountOptinal.get().getCardPinNum().equals(pinNum)) {
            signedIn = true;
            System.out.println("Welcome back!");
        }

        return signedIn;
    }

    public String getPinNum1() {
        System.out.println("Please enter your Pin Number");
        String pinNum = scanner.nextLine();

        return pinNum;
    }

    public String getPinNum2() {
        System.out.println("Please re-enter your Pin Number");
        String pinNum2 = scanner.nextLine();

        return pinNum2;
    }

    public String getPinNum3() {
        System.out.println("Please enter your Pin Number for the third time");
        String pinNum3 = scanner.nextLine();

        return pinNum3;
    }

    public int getChoice() {
        System.out.println("*****");
        System.out.println("Enter 1 to check your balance");
        System.out.println("Enter 2 to withdraw");
        System.out.println("Enter 3 to deposit");
        System.out.println("Enter 0 to exit");
        int choice = Integer.parseInt(scanner.nextLine());

        return choice;
    }

    public void Bank(Account account) {
        System.out.println("*****");
        String pinNum = getPinNum1();
        int tryNum = 1;

        if (!checkPinNum(pinNum, account)) {
            String pinNum2 = getPinNum2();

            if (!checkPinNum(pinNum2, account)) {
                tryNum = 2;
            }

            if (tryNum == 2) {
                String pinNum3 = getPinNum3();
                if (!checkPinNum(pinNum3, account)) {
                    tryNum = 3;
                }
            }

            if (tryNum == 3) {
                account.setLocked(true);
                System.out.println("You have enter wrong Pin Number too many times. You are blocked. Please contact your bank");
            } else if (!account.isLocked()) {
                bankOperation(account);
            }
        }
    }

    private void bankOperation(Account account) {
        int choice = Integer.parseInt(String.valueOf(getChoice()));
        switch (choice) {
            case 0:
                System.out.println("Your current balance is: " + account.getBalance());
                bankOperation(account);
                break;

            case 1:
                System.out.println("Enter the amount you want to deposit");
                int depositAmount = scanner.nextInt();
                System.out.println("Are you sure you want to deposit?: "+ depositAmount + " (Yes/No)");
                String answer = scanner.next();

                if (answer.equalsIgnoreCase("Yes")) {
                    account.setBalance(account.getBalance() + depositAmount);
                    System.out.println("The deposit was successful. Your current balance is: " + account.getBalance());
                    bankOperation(account);
                } else if (answer.equalsIgnoreCase("No")) {
                    System.out.println("The deposit was unsuccessful. Therefore is your balance: " + account.getBalance());
                    bankOperation(account);
                }
                else {
                    System.out.println("Invalid answer. Please try again");
                    bankOperation(account);
                }
                break;

            case 2:
                System.out.println("Enter the amount you want to withdraw");
                int withDrawAmount = scanner.nextInt();

                if (account.getBalance() < withDrawAmount) {
                    System.out.println("You don't have enough money to complete your request");
                    bankOperation(account);
                } else if (account.getBalance() >= withDrawAmount) {
                    System.out.println("Do you want to continue the withdraw?: " + withDrawAmount + "(Yes/No)");
                    String answer1 = scanner.next();

                    if (answer1.equalsIgnoreCase("Yes")) {
                        account.setBalance(account.getBalance() - withDrawAmount);
                        System.out.println("Withdraw was successful. Your current balance is: " + account.getBalance());
                        bankOperation(account);
                    } else if (answer1.equalsIgnoreCase("No")) {
                        System.out.println("The withdraw was unsuccessful. Therefore your balance is: " + account.getBalance());
                        bankOperation(account);
                    }
                } else{
                    System.out.println("Invalid answer. Please try again");
                    bankOperation(account);
                }

                break;

            case 3:
                System.out.println("The process has ended. Have a nice day: " + account.getBankName());
                break;
        }
    }

    public Account checkUser(String IDNum) {
        Optional<Account> accountOptional = getAccountList().stream().filter(a -> a.cardIDNum.equals(IDNum)).findFirst();
        Account account = null;
        if (accountOptional.isPresent()) {
            System.out.println("User: " + accountOptional.get().getUserFirstName() + " " + accountOptional.get().getUserLastName());
            account = accountOptional.get();
            return account;
        } else {
            System.out.println("The specified ID is incorrect. Please try again");
            return account;
        }
    }

    public String getIDNum() {
        System.out.println("Please enter your ID Number");
        return scanner.nextLine();
    }

    public void bankAccess(String answer) {
        switch (answer) {
            case "0":
                String IDNum = getIDNum();
                Account account = checkUser(IDNum);
                if (account != null) {
                    Bank(account);
                }
                break;
        }
    }
}
