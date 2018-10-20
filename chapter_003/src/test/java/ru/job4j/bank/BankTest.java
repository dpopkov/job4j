package ru.job4j.bank;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.number.IsCloseTo.closeTo;
import static org.junit.Assert.*;

public class BankTest {
    public static final double DELTA = 1e-15;

    private Bank bank;

    @Before
    public void setUp() {
        bank = new Bank();
    }

    @Test
    public void whenNonExistingPassportThenHasNoAccounts() {
        List<Account> list = bank.getUserAccounts("123");
        assertThat(list.size(), is(0));
    }

    @Test
    public void whenAddingUserWithoutAccountThenHasNoAccounts() {
        User user = new User("Denis", "pass1");
        bank.addUser(user);
        List<Account> list = bank.getUserAccounts("pass1");
        assertThat(list.size(), is(0));
    }

    @Test
    public void whenAddingUserAndAccountThenReturnAccount() {
        bank.addUser(new User("Denis", "pass1"));
        Account account = new Account("requisites", 0);
        bank.addAccountToUser("pass1", account);
        List<Account> list = bank.getUserAccounts("pass1");
        assertThat(list.get(0), is(account));
    }

    @Test
    public void whenAddingSameUserThenAccountsNotLost() {
        User user = new User("Denis", "pass1");
        bank.addUser(user);
        Account account = new Account("requisites", 0);
        bank.addAccountToUser("pass1", account);
        User user2 = new User("Denis", "pass1");
        bank.addUser(user2);
        List<Account> list = bank.getUserAccounts("pass1");
        assertThat(list.size(), is(1));
        assertThat(list.get(0), is(account));
    }

    @Test
    public void whenDeleteUserThenHasNoAccounts() {
        User user = new User("Denis", "pass1");
        bank.addUser(user);
        bank.addAccountToUser("pass1", new Account("requisites", 0));
        bank.deleteUser(user);
        List<Account> list = bank.getUserAccounts("pass1");
        assertThat(list.size(), is(0));
    }

    @Test
    public void whenDeleteAccountThenHasNoSuchAccount() {
        bank.addUser(new User("Denis", "pass1"));
        Account first = new Account("requisites1", 0);
        bank.addAccountToUser("pass1", first);
        bank.addAccountToUser("pass1", new Account("requisites2", 0));
        assertThat(bank.getUserAccounts("pass1").size(), is(2));
        bank.deleteAccountFromUser("pass1", first);
        List<Account> list = bank.getUserAccounts("pass1");
        assertThat(list.size(), is(1));
        assertThat(list.get(0).getRequisites(), is("requisites2"));
    }

    @Test
    public void whenTransferringToSameUserAccountThenSuccess() {
        bank.addUser(new User("Denis", "pass1"));
        Account fromAccount = new Account("reqFrom", 100.0);
        Account toAccount = new Account("reqTo", 0);
        bank.addAccountToUser("pass1", fromAccount);
        bank.addAccountToUser("pass1", toAccount);
        assertThat(fromAccount.getValue(), closeTo(100.0, DELTA));
        assertThat(toAccount.getValue(), closeTo(0.0, DELTA));
        boolean result = bank.transferMoney("pass1", "reqFrom",
                "pass1", "reqTo", 90.0);
        assertThat(result, is(true));
        assertThat(fromAccount.getValue(), closeTo(10.0, DELTA));
        assertThat(toAccount.getValue(), closeTo(90.0, DELTA));
    }

    @Test
    public void whenTransferringToOtherUserAccountThenSuccess() {
        bank.addUser(new User("Denis", "passFrom"));
        bank.addUser(new User("Sasha", "passTo"));
        Account fromAccount = new Account("reqFrom", 100.0);
        Account toAccount = new Account("reqTo", 1000.0);
        bank.addAccountToUser("passFrom", fromAccount);
        bank.addAccountToUser("passTo", toAccount);
        assertThat(fromAccount.getValue(), closeTo(100.0, DELTA));
        assertThat(toAccount.getValue(), closeTo(1000.0, DELTA));
        boolean result = bank.transferMoney("passFrom", "reqFrom",
                "passTo", "reqTo", 80.0);
        assertThat(result, is(true));
        assertThat(fromAccount.getValue(), closeTo(20.0, DELTA));
        assertThat(toAccount.getValue(), closeTo(1080.0, DELTA));
    }

    @Test
    public void whenTransferringFromNonExistingUserThenReturnFalse() {
        bank.addUser(new User("Sasha", "passTo"));
        Account toAccount = new Account("reqTo", 1000.0);
        bank.addAccountToUser("passTo", toAccount);
        boolean result = bank.transferMoney("passFrom", "reqFrom",
                "passTo", "reqTo", 80.0);
        assertThat(result, is(false));
        assertThat(toAccount.getValue(), closeTo(1000.0, DELTA));
    }

    @Test
    public void whenTransferringToNonExistingUserThenReturnFalse() {
        bank.addUser(new User("Sasha", "passFrom"));
        Account fromAccount = new Account("reqFrom", 1000.0);
        bank.addAccountToUser("passFrom", fromAccount);
        boolean result = bank.transferMoney("passFrom", "reqFrom",
                "passTo", "reqTo", 80.0);
        assertThat(result, is(false));
        assertThat(fromAccount.getValue(), closeTo(1000.0, DELTA));
    }

    @Test
    public void whenTransferringFromNonExistingAccountThenReturnFalse() {
        bank.addUser(new User("Denis", "passFrom"));
        bank.addUser(new User("Sasha", "passTo"));
        Account fromAccount = new Account("reqFrom", 100.0);
        Account toAccount = new Account("reqTo", 1000.0);
        bank.addAccountToUser("passFrom", fromAccount);
        bank.addAccountToUser("passTo", toAccount);
        boolean result = bank.transferMoney("passFrom", "reqNonExisting",
                "passTo", "reqTo", 80.0);
        assertThat(result, is(false));
        assertThat(toAccount.getValue(), closeTo(1000.0, DELTA));
    }

    @Test
    public void whenTransferringToNonExistingAccountThenReturnFalse() {
        bank.addUser(new User("Denis", "passFrom"));
        bank.addUser(new User("Sasha", "passTo"));
        Account fromAccount = new Account("reqFrom", 100.0);
        Account toAccount = new Account("reqTo", 1000.0);
        bank.addAccountToUser("passFrom", fromAccount);
        bank.addAccountToUser("passTo", toAccount);
        boolean result = bank.transferMoney("passFrom", "reqFrom",
                "passTo", "reqNonExisting", 80.0);
        assertThat(result, is(false));
        assertThat(fromAccount.getValue(), closeTo(100.0, DELTA));
    }

    @Test
    public void whenNotEnoughMoneyThenReturnFalse() {
        bank.addUser(new User("Denis", "passFrom"));
        bank.addUser(new User("Sasha", "passTo"));
        Account fromAccount = new Account("reqFrom", 100.0);
        Account toAccount = new Account("reqTo", 1000.0);
        bank.addAccountToUser("passFrom", fromAccount);
        bank.addAccountToUser("passTo", toAccount);
        boolean result = bank.transferMoney("passFrom", "reqFrom",
                "passTo", "reqTo", 101.0);
        assertThat(result, is(false));
        assertThat(fromAccount.getValue(), closeTo(100.0, DELTA));
        assertThat(toAccount.getValue(), closeTo(1000.0, DELTA));
    }
}
