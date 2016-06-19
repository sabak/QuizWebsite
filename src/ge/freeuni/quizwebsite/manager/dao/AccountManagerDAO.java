package ge.freeuni.quizwebsite.manager.dao;

import ge.freeuni.quizwebsite.manager.AccountManager;
import ge.freeuni.quizwebsite.model.Account;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class AccountManagerDAO extends AbstractManagerDAO implements AccountManager {

    public static final String ATTRIBUTE_NAME = "account_manager";

    public AccountManagerDAO(DataSource dataSource) {
        super(dataSource);
    }

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
        try {
            Connection con = dataSource.getConnection();
            String query = "SELECT * FROM account WHERE username=?";
            PreparedStatement statement = con.prepareStatement(query);
            statement.setString(1, "saba");
            ResultSet result = statement.executeQuery();
            boolean contains = result.next();
            con.close();

            return contains;
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Account createAccount(Account account) {
        return null;
    }

    @Override
    public void removeAccount(Account account) {

    }

    @Override
    public void changeHashedPassword(Account account, String newHashedPassword) {

    }

    @Override
    public void changeEmail(Account account, String newEmail) {

    }

    @Override
    public void changeFirstName(Account account, String newFirstName) {

    }

    @Override
    public void changeLastName(Account account, String newLastName) {

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
