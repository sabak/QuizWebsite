package ge.freeuni.quizwebsite.model;

import ge.freeuni.quizwebsite.manager.AccountManager;

import java.util.List;

/**
 * Created by user on 6/27/2016.
 */
public class AccountManagerStub implements AccountManager {

    @Override
    public Account getAccount(Integer id) {
        return null;
    }

    @Override
    public Account getAccount(String username) {
        return null;
    }

    @Override
    public boolean usernameExists(String username) {
        return false;
    }

    @Override
    public boolean createAccount(Account account) {
        return false;
    }

    @Override
    public void removeAccount(Account account) {

    }

    @Override
    public Account changeHashedPassword(Account account, String newHashedPassword) {
        return null;
    }

    @Override
    public Account changeEmail(Account account, String newEmail) {
        return null;
    }

    @Override
    public Account changeFirstName(Account account, String newFirstName) {
        return null;
    }

    @Override
    public Account changeLastName(Account account, String newLastName) {
        return null;
    }

    @Override
    public List<Account> getAccounts(int limit) {
        return null;
    }

    @Override
    public List<Account> getAccounts(int limitFrom, int limitTo) {
        return null;
    }

    @Override
    public int getAccountsQuantity() {
        return 0;
    }
}