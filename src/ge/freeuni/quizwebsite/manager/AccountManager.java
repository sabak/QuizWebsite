package ge.freeuni.quizwebsite.manager;

import ge.freeuni.quizwebsite.model.Account;

import java.util.List;

/**
 * Created by Saba on 6/17/2016.
 */
public interface AccountManager {

    public Account getAccount(Integer id);

    public Account getAccount(String username);

    public boolean usernameExists(String username);

    public Account createAccount(Account acc);

    public void removeAccount(Account acc);

    public void changeHashedPassword(Account acc, String newHashedPassword);

    public void changeEmail(Account acc, String newEmail);

    public void changeFirstName(Account acc, String newFirstName);

    public void changeLastName(Account acc, String newLastName);

    public List<Account> getAccounts(int limit);

    public List<Account> getAccounts(int limitFrom, int limitTo);

    public int getAccountsQuantity();

}
