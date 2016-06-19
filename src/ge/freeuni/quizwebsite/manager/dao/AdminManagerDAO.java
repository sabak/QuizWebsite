package ge.freeuni.quizwebsite.manager.dao;

import ge.freeuni.quizwebsite.manager.AdminManager;
import ge.freeuni.quizwebsite.model.Account;

import javax.sql.DataSource;
import java.util.List;

/**
 * Created by Saba on 19-06-2016.
 */
public class AdminManagerDAO extends AbstractManagerDAO implements AdminManager {

    public static final String ATTRIBUTE_NAME = "admin_manager";

    public AdminManagerDAO(DataSource dataSource) {
        super(dataSource);
    }

    @Override
    public boolean isAdmin(Account account) {
        return false;
    }

    @Override
    public void addAdmin(Account account) {

    }

    @Override
    public void removeAdmin(Account account) {

    }

    @Override
    public List<Account> getAdmins(int limit) {
        return getAdmins(0, limit);
    }

    @Override
    public List<Account> getAdmins(int limitFrom, int limitTo) {
        return null;
    }

}
