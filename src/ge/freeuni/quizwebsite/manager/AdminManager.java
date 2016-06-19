package ge.freeuni.quizwebsite.manager;

import ge.freeuni.quizwebsite.model.Account;

import java.util.List;

/**
 * Created by Saba on 6/17/2016.
 */
public interface AdminManager {

    /**
     * Checks whether given user account is admin or not.
     *
     * @param account Target user account
     * @return true of account is admin, false otherwise
     */
    boolean isAdmin(Account account);

    /**
     * Gives admin privileges to given user account.
     *
     * @param account Target user account
     */
    void addAdmin(Account account);

    /**
     * Removes admin privileges from given user account.
     *
     * @param account Target user account
     */
    void removeAdmin(Account account);

    /**
     * Returns the list of all admins limited by given size.
     *
     * @param limit Number of consecutive admin accounts to return
     * @return List of admin accounts with the size <code>limit</code>
     */
    List<Account> getAdmins(int limit);

    /**
     * Returns the list of all admins, limited by given parameters.
     *
     * @param limitFrom Index to get accounts from this point onwards
     * @param limitTo   Stop index
     * @return List of admin acoounts
     */
    List<Account> getAdmins(int limitFrom, int limitTo);

}
