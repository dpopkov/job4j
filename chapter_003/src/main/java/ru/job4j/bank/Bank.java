package ru.job4j.bank;

import java.util.*;

/**
 * Represents bank with the ability to add users, add accounts and transfer money.
 */
public class Bank {
    private final Map<User, List<Account>> usersAccounts = new HashMap<>();

    /**
     * Adds new user with an empty list of accounts if such a user is not in the bank.
     * @param user new user
     */
    public void addUser(User user) {
        usersAccounts.putIfAbsent(user, new ArrayList<>());
    }

    /**
     * Deletes the specified user
     * @param user user
     */
    public void deleteUser(User user) {
        usersAccounts.remove(user);
    }

    /**
     * Adds the specified account to existing user.
     * @param passport passport of the existing user
     * @param account new account
     */
    public void addAccountToUser(String passport, Account account) {
        User user = findUserByPassport(passport);
        if (user != null) {
            List<Account> accounts = usersAccounts.get(user);
            accounts.add(account);
        }
    }

    /**
     * Deletes one existing user account.
     * @param passport passport of the existing user
     * @param account account to delete
     * @throws AccountNotFoundException when the specified account not found
     */
    public void deleteAccountFromUser(String passport, Account account) throws AccountNotFoundException {
        User user = findUserByPassport(passport);
        if (user != null) {
            List<Account> accounts = usersAccounts.get(user);
            int index = accounts.indexOf(account);
            if (index < 0) {
                throw new AccountNotFoundException();
            }
            accounts.remove(index);
        }
    }

    /**
     * Gets list of user accounts.
     * @param passport passport of the existing user
     * @return list of accounts, or empty list if user not found or has no accounts
     */
    public List<Account> getUserAccounts(String passport) {
        User user = findUserByPassport(passport);
        return user != null ? usersAccounts.get(user) : Collections.emptyList();
    }

    /**
     * Transfers the specified amount of money from source account to destination account.
     * @param srcPassport passport of source account owner
     * @param srcRequisite requisites of source account
     * @param destPassport passport of destination account owner
     * @param destRequisite requisites of destination account
     * @param amount amount of money to transfer
     * @return true if transfer was successful,
     *         false if one of accounts not found or there isn't sufficient value in the account.
     */
    public boolean transferMoney(String srcPassport, String srcRequisite,
                                 String destPassport, String destRequisite, double amount) {
        Account source = findAccount(srcPassport, srcRequisite);
        Account destination = findAccount(destPassport, destRequisite);
        if (source == null || destination == null) {
            return false;
        }
        return source.withdraw(amount) && destination.deposit(amount);
    }

    /**
     * Finds account using owner's passport and specified account requisites.
     * @param passport passport of account owner
     * @param requisite requisites of account
     * @return found account, or null if owner or account was not found
     */
    private Account findAccount(String passport, String requisite) {
        User user = findUserByPassport(passport);
        if (user == null) {
            return null;
        }
        return usersAccounts.get(user).stream()
                .filter(account -> account.getRequisites().equals(requisite))
                .findFirst().orElse(null);
    }

    /**
     * Finds user by passport.
     * @param passport passport of user
     * @return found user, or null if user with specified passport not found
     */
    private User findUserByPassport(String passport) {
        return usersAccounts.keySet().stream()
                .filter(user -> user.getPassport().equals(passport))
                .findFirst().orElse(null);
    }
}
