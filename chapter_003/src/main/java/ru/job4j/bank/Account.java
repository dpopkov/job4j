package ru.job4j.bank;

/**
 * Represents bank account.
 */
public class Account {
    /**
     * Amount of money in the account.
     */
    private double value;
    /**
     * Account details.
     */
    private final String requisites;

    /**
     * Constructs account with specified requisites and initial value.
     * @param requisites account details
     * @param value initial amount of money
     */
    public Account(String requisites, double value) {
        this.value = value;
        this.requisites = requisites;
    }

    /**
     * @return amount of money in the account
     */
    public double getValue() {
        return value;
    }

    /**
     * @return requisites of the account
     */
    public String getRequisites() {
        return requisites;
    }

    /**
     * Withdraws specified amount of money from the account if
     * value in the account is sufficient.
     * @param amount amount of money, should be positive
     * @return true if operation was success, false otherwise
     */
    public boolean withdraw(double amount) {
        if (amount < 0 || amount > value) {
            return false;
        }
        value -= amount;
        return true;
    }

    /**
     * Deposits specified amount of money into the account.
     * @param amount amount of money, should be positive
     * @return true if operation was success, false otherwise
     */
    public boolean deposit(double amount) {
        if (amount < 0) {
            return false;
        }
        value += amount;
        return true;
    }
}
