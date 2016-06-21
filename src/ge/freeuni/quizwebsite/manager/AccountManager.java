package ge.freeuni.quizwebsite.manager;

import ge.freeuni.quizwebsite.model.Account;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Saba on 6/17/2016.
 */
public interface AccountManager {

    /**
     * Returns account by given unique identifier.
     *
     * @param id Unique identifier
     * @return Found account, null if not found
     */
    Account getAccount(Integer id);

    /**
     * Returns account by given unique username.
     *
     * @param username Unique username
     * @return Found account, null if not found
     */
    Account getAccount(String username);

    /**
     * Checks whether given username already exists
     *
     * @param username Username
     * @return true if exists, false otherwise
     */
    boolean usernameExists(String username) throws SQLException;


    /**
     * Creates (registers) account and returns updated
     * entry with unique identifier.
     *
     * @param account Account to be registered
     * @return true if created, false otherwise
     */
    boolean createAccount(Account account);

    /**
     * Removes account.
     *
     * @param account Account to be removed.
     */
    void removeAccount(Account account);

    /**
     * Updates password for given user account.
     *
     * @param account           Target account
     * @param newHashedPassword new hashed password
     * @return Updated account
     */
    Account changeHashedPassword(Account account, String newHashedPassword);

    /**
     * Updates e-mail for given user account.
     *
     * @param account  Target account
     * @param newEmail new e-mail
     * @return Updated account
     */
    Account changeEmail(Account account, String newEmail);

    /**
     * Updates first name for given user account.
     *
     * @param account      Target account
     * @param newFirstName new first name
     * @return Updated account
     */
    Account changeFirstName(Account account, String newFirstName);

    /**
     * Updates last name for given user account.
     *
     * @param account     Target account
     * @param newLastName new last name
     * @return Updated account
     */
    Account changeLastName(Account account, String newLastName);

    /**
     * Returns list of user accounts, limited by given parameter.
     *
     * @param limit Number of consecutive accounts to return
     * @return List of user accounts with length <code>limit</code>
     */
    List<Account> getAccounts(int limit) throws SQLException;

    /**
     * Returns list of user accounts, limited by given parameters.
     *
     * @param limitFrom Index to get accounts from this point onwards
     * @param limitTo   Stop index
     * @return List of user accounts
     */
    List<Account> getAccounts(int limitFrom, int limitTo) throws SQLException;

    /**
     * Returns total number of user accounts created.
     *
     * @return Number of total user accounts
     */
    int getAccountsQuantity();

}
