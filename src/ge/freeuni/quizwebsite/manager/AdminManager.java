package ge.freeuni.quizwebsite.manager;

import ge.freeuni.quizwebsite.manager.AccountManager;
import ge.freeuni.quizwebsite.model.Account;

import java.util.List;

/**
 * Created by Saba on 6/17/2016.
 */
public interface AdminManager {

    public boolean isAdmin(Account acc);

    public void addAdmin(Account acc);

    public void removeAdmin(Account acc);

    public List<Account> getAdmins(int limit);

    public List<Account> getAdmins(int limitFrom, int limitTo);

    public void setAccountManager(AccountManager manager);

}
