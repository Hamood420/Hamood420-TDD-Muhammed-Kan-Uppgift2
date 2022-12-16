package org.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class BankTest {

    Bank bank = new Bank();

    @Test
    public void CheckIfThereIsMoreThan5() {
        Assertions.assertTrue(bank.accountsList.size() < 5);
    }

    @Test
    public void CheckPinNumWhenItsValid() {
        Account account = new Account("Dahoud", "Süleyman", "453905695609", "1025", 1000, "Ohio");
        Assertions.assertTrue(bank.checkPinNum("1025", account));
    }

    @Test
    public void CheckPinNumIfItsNotValid(){
        Account account = new Account("Dahoud", "Süleyman", "453905695609", "1025", 1000, "Ohio");
        Assertions.assertFalse(bank.checkPinNum("1000", account));
    }

    @Test
    public void CheckIfTheCustomerLockedItselfAfter3InvalidTrys() {
        Account account = new Account("Dahoud", "Süleyman", "453905695609", "1025", 1000, "Ohio");

        Bank bank1 = Mockito.spy(bank);

        Mockito.doReturn("1111").when(bank1).getPinNum1();
        Mockito.doReturn("2222").when(bank1).getPinNum2();
        Mockito.doReturn("3333").when(bank1).getPinNum3();

        bank1.Bank(account);

        Assertions.assertTrue(account.isLocked());

        verify(bank1).getPinNum1();
        verify(bank1).getPinNum2();
        verify(bank1).getPinNum3();
    }

    @Test
    public void CheckIfTheCustomerDidNotGetItRightOnFirstTry() {
        Account account = new Account("Dahoud", "Süleyman", "453905695609", "1025", 1000, "Ohio");

        Bank bank = new Bank();

        Bank bank1 = Mockito.spy(bank);

        Mockito.doReturn("1024").when(bank1).getPinNum1();
        Mockito.doReturn("1025").when(bank1).getPinNum2();
        Mockito.doReturn(0).when(bank1).getChoice();

        Assertions.assertFalse(bank1.getPinNum1().equals(account.getCardPinNum()));
        Assertions.assertTrue(bank1.getPinNum2().equals(account.getCardPinNum()));

        verify(bank1).getPinNum1();
        verify(bank1).getPinNum2();

        bank1.Bank(account);
    }

    @Test
    public void CheckWhenOperationChoiceIsNotTrue() {
        Account account = new Account("Dahoud", "Süleyman", "453905695609", "1025", 1000, "Ohio");

        Bank bank = new Bank();

        Bank bank1 = Mockito.spy(bank);

        Mockito.doReturn("1025").when(bank1).getPinNum1();
        Mockito.doReturn(0).when(bank1).getChoice();

        Assertions.assertFalse(bank1.getChoice() == 1);

        bank1.Bank(account);
    }

    @Test
    public void CheckWhenOperationChoiceIsTrue() {
        Account account = new Account("Dahoud", "Süleyman", "453905695609", "1025", 1000, "Ohio");

        Bank bank = new Bank();

        Bank bank1 = Mockito.spy(bank);

        Mockito.doReturn("1025").when(bank1).getPinNum1();
        Mockito.doReturn(0).when(bank1).getChoice();

        Assertions.assertTrue(bank1.getChoice() == 0);

        verify(bank1).getChoice();

        bank1.Bank(account);
    }

    @Test
    public void CheckIfTheCustomerIsValidButPinNot() {
        Account account = new Account("Dahoud", "Süleyman", "453905695609", "1025", 1000, "Ohio");

        Bank bank = new Bank();

        Bank bank1 = Mockito.spy(bank);

        Mockito.doReturn("453905695609").when(bank1).getIDNum();
        Mockito.doReturn("1111").when(bank1).getPinNum1();
        Mockito.doReturn("2222").when(bank1).getPinNum2();
        Mockito.doReturn("3333").when(bank1).getPinNum3();

        Assertions.assertTrue(bank1.getIDNum().equals(account.getCardIdNum()));
        Assertions.assertFalse(bank1.getPinNum1().equals(account.getCardPinNum()));

        verify(bank1).getPinNum1();
        verify(bank1).getIDNum();

        bank1.bankAccess("0");

    }

    @Test
    public void CheckIfTheCustomerIsNotValidButPinIsValid() {
        Account account = new Account("Dahoud", "Süleyman", "453905695609", "1025", 1000, "Ohio");

        Bank bank = new Bank();

        Bank bank1 = Mockito.spy(bank);

        Mockito.doReturn("1111111").when(bank1).getIDNum();
        Mockito.doReturn("1025").when(bank1).getPinNum1();
        Mockito.doReturn("1024").when(bank1).getPinNum2();

        Assertions.assertFalse(bank1.getIDNum().equals(account.cardIDNum));
        Assertions.assertTrue(bank1.getPinNum1().equals(account.cardPinNum));

        verify(bank1).getPinNum1();
        verify(bank1).getIDNum();

        bank1.bankAccess("0");

    }

    @Test
    public void CheckIfTheCustomerGotItRightOn3rdTry() {
        Account account = new Account("Dahoud", "Süleyman", "453905695609", "1025", 1000, "Ohio");

        Bank bank = new Bank();

        Bank bank1 = Mockito.spy(bank);

        Mockito.doReturn("1011").when(bank1).getPinNum1();
        Mockito.doReturn("1022").when(bank1).getPinNum2();
        Mockito.doReturn("1025").when(bank1).getPinNum3();

        bank1.Bank(account);

        Assertions.assertTrue(bank.checkPinNum("1025", account));

        verify(bank1).getPinNum1();
        verify(bank1).getPinNum2();
        verify(bank1).getPinNum3();


    }

}