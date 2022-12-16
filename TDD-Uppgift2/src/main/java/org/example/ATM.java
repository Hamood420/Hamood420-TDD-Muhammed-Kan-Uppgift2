package org.example;

import java.util.Scanner;

public class ATM {

    static Bank bank = new Bank();
    static Scanner scanner = new Scanner(System.in);

    public static String getAnswer() {
        Scanner scanner1 = new Scanner(System.in);
        return scanner1.nextLine();
    }

    public static void main(String[] args) {
        System.out.println("Enter 0 insert your card");
        String answer = getAnswer();
        bank.bankAccess(answer);
    }
}
