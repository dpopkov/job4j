package ru.job4j.bank;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.number.IsCloseTo.closeTo;
import static org.junit.Assert.*;

public class AccountTest {

    private static final double DELTA = 1e-15;

    @Test
    public void whenWithdrawAndEnoughMoney() {
        Account account = new Account("r123", 100.0);
        boolean result = account.withdraw(70.0);
        assertThat(result, is(true));
        assertThat(account.getValue(), closeTo(30.0, DELTA));
    }

    @Test
    public void whenWithdrawAndNotEnoughMoney() {
        Account account = new Account("r123", 100.0);
        boolean result = account.withdraw(170.0);
        assertThat(result, is(false));
        assertThat(account.getValue(), closeTo(100.0, DELTA));
    }

    @Test
    public void whenWithdrawNegativeAmountThenNoChange() {
        Account account = new Account("r123", 100.0);
        boolean result = account.withdraw(-10.0);
        assertThat(result, is(false));
        assertThat(account.getValue(), closeTo(100.0, DELTA));
    }

    @Test
    public void whenDepositPositiveAmountThenValueIncreases() {
        Account account = new Account("r123", 100.0);
        boolean result = account.deposit(1.0);
        assertThat(result, is(true));
        assertThat(account.getValue(), closeTo(101.0, DELTA));
    }

    @Test
    public void whenDepositNegativeAmountThenNoChange() {
        Account account = new Account("r123", 100.0);
        boolean result = account.deposit(-100.0);
        assertThat(result, is(false));
        assertThat(account.getValue(), closeTo(100.0, DELTA));
    }
}
