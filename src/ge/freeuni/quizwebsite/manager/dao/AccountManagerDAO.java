package ge.freeuni.quizwebsite.manager.dao;

import ge.freeuni.quizwebsite.manager.AccountManager;
import ge.freeuni.quizwebsite.manager.dao.db.DbContract;
import ge.freeuni.quizwebsite.model.Account;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AccountManagerDAO extends AbstractManagerDAO implements AccountManager {

    public static final String ATTRIBUTE_NAME = "account_manager";

    public AccountManagerDAO(DataSource dataSource) {
        super(dataSource);
    }

    @Override
    public Account getAccount(Integer id) {
        Account account = null;
        try {
            Connection con = dataSource.getConnection();
            String query = "SELECT * FROM ? WHERE ? = ?;";
            PreparedStatement statement = con.prepareStatement(query);
            statement.setString(1, DbContract.Account.TABLE_NAME);
            statement.setString(2, DbContract.Account.COLUMN_NAME_ACCOUNT_ID);
            statement.setInt(3, id);
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                String user = rs.getString(DbContract.Account.COLUMN_NAME_USERNAME);
                String pwd = rs.getString(DbContract.Account.COLUMN_NAME_HASHED_PASSWORD);
                String email = rs.getString(DbContract.Account.COLUMN_NAME_EMAIL);
                String firstName = rs.getString(DbContract.Account.COLUMN_NAME_FIRST_NAME);
                String lastName = rs.getString(DbContract.Account.COLUMN_NAME_LAST_NAME);

                account = new Account(id, user, pwd, email, firstName, lastName);
            }
            con.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return account;
    }

    @Override
    public Account getAccount(String username) {
        Account account = null;
        try {
            Connection con = dataSource.getConnection();
            String query = "SELECT * FROM ? WHERE ? = ?;";
            PreparedStatement statement = con.prepareStatement(query);
            statement.setString(1, DbContract.Account.TABLE_NAME);
            statement.setString(2, DbContract.Account.COLUMN_NAME_USERNAME);
            statement.setString(3, username);
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                Integer id = rs.getInt(DbContract.Account.COLUMN_NAME_ACCOUNT_ID);
                String pwd = rs.getString(DbContract.Account.COLUMN_NAME_HASHED_PASSWORD);
                String email = rs.getString(DbContract.Account.COLUMN_NAME_EMAIL);
                String firstName = rs.getString(DbContract.Account.COLUMN_NAME_FIRST_NAME);
                String lastName = rs.getString(DbContract.Account.COLUMN_NAME_LAST_NAME);

                account = new Account(id, username, pwd, email, firstName, lastName);
            }
            con.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return account;
    }

    @Override
    public boolean usernameExists(String username) {
        boolean exists = false;
        try (Connection con = dataSource.getConnection()) {
            String query = "SELECT * FROM ? WHERE ? = ?;";
            PreparedStatement statement = con.prepareStatement(query);
            statement.setString(1, DbContract.Account.TABLE_NAME);
            statement.setString(2, DbContract.Account.COLUMN_NAME_USERNAME);
            statement.setString(3, username);

            ResultSet result = statement.executeQuery();
            exists = result.next();
            con.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return exists;
    }

    @Override
    public boolean createAccount(Account account) {
        // username and password are mandatory fields
        if (account.getUsername() == null || account.getHashedPassword() == null)
            return false;

        if (usernameExists(account.getUsername()))
            return false;

        try (Connection con = dataSource.getConnection()) {
            String query = "INSERT INTO ? VALUES(?, ?, ?, ?, ?);";
            PreparedStatement statement = con.prepareStatement(query);
            statement.setString(1, DbContract.Account.TABLE_NAME);
            statement.setString(2, account.getUsername());
            statement.setString(3, account.getHashedPassword());
            statement.setString(4, account.getEmail());
            statement.setString(5, account.getFirstName());
            statement.setString(6, account.getLastName());
            statement.executeUpdate();

            con.close();
            return true;
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public void removeAccount(Account account) {
        try (Connection con = dataSource.getConnection()) {
            String query = "DELETE FROM ? WHERE ? = ?;";
            PreparedStatement statement = con.prepareStatement(query);
            statement.setString(1, DbContract.Account.TABLE_NAME);
            statement.setString(2, DbContract.Account.COLUMN_NAME_USERNAME);
            statement.setString(3, account.getUsername());
            statement.executeUpdate();

            con.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Override
    public void changeHashedPassword(Account account, String newHashedPassword) {
        try (Connection con = dataSource.getConnection()) {
            String query = "UPDATE ? SET ? = ? WHERE ? = ?;";
            PreparedStatement statement = con.prepareStatement(query);
            statement.setString(1, DbContract.Account.TABLE_NAME);
            statement.setString(2, DbContract.Account.COLUMN_NAME_HASHED_PASSWORD);
            statement.setString(3, newHashedPassword);
            statement.setString(4, DbContract.Account.COLUMN_NAME_USERNAME);
            statement.setString(5, account.getUsername());
            statement.executeUpdate();

            con.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Override
    public void changeEmail(Account account, String newEmail) {
        try (Connection con = dataSource.getConnection()) {
            String query = "UPDATE ? SET ? = ? WHERE ? = ?;";
            PreparedStatement statement = con.prepareStatement(query);
            statement.setString(1, DbContract.Account.TABLE_NAME);
            statement.setString(2, DbContract.Account.COLUMN_NAME_EMAIL);
            statement.setString(3, newEmail);
            statement.setString(4, DbContract.Account.COLUMN_NAME_USERNAME);
            statement.setString(5, account.getUsername());
            statement.executeUpdate();

            con.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Override
    public void changeFirstName(Account account, String newFirstName) {
        try (Connection con = dataSource.getConnection()) {
            String query = "UPDATE ? SET ? = ? WHERE ? = ?;";
            PreparedStatement statement = con.prepareStatement(query);
            statement.setString(1, DbContract.Account.TABLE_NAME);
            statement.setString(2, DbContract.Account.COLUMN_NAME_FIRST_NAME);
            statement.setString(3, newFirstName);
            statement.setString(4, DbContract.Account.COLUMN_NAME_USERNAME);
            statement.setString(5, account.getUsername());
            statement.executeUpdate();

            con.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Override
    public void changeLastName(Account account, String newLastName) {
        try (Connection con = dataSource.getConnection()) {
            String query = "UPDATE ? SET ? = ? WHERE ? = ?;";
            PreparedStatement statement = con.prepareStatement(query);
            statement.setString(1, DbContract.Account.TABLE_NAME);
            statement.setString(2, DbContract.Account.COLUMN_NAME_LAST_NAME);
            statement.setString(3, newLastName);
            statement.setString(4, DbContract.Account.COLUMN_NAME_USERNAME);
            statement.setString(5, account.getUsername());
            statement.executeUpdate();

            con.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Override
    public List<Account> getAccounts(int limit) throws SQLException {
        return getAccounts(0, limit);
    }

    @Override
    public List<Account> getAccounts(int limitFrom, int limitTo) throws SQLException {
        List<Account> accounts = new ArrayList<>();
        Connection con = dataSource.getConnection();
        String query = "SELECT ? FROM ? LIMIT ?, ?;";
        PreparedStatement statement = con.prepareStatement(query);
        statement.setString(1, DbContract.Account.COLUMN_NAME_USERNAME);
        statement.setString(2, DbContract.Account.TABLE_NAME);
        statement.setInt(3, limitFrom);
        statement.setInt(4, limitTo);
        ResultSet rs = statement.executeQuery();
        while (rs.next()) {
            Account acc = getAccount(rs.getString(1));
            accounts.add(acc);
        }
        con.close();
        return accounts;
    }

    @Override
    public int getAccountsQuantity() {
        int result = 0;
        try {
            Connection con = dataSource.getConnection();
            String query = "SELECT COUNT(?) FROM ?";
            PreparedStatement statement = con.prepareStatement(query);
            statement.setString(1, DbContract.Account.COLUMN_NAME_USERNAME);
            statement.setString(2, DbContract.Account.TABLE_NAME);
            ResultSet rs = statement.executeQuery();
            if (rs.next())
                result = rs.getInt(1);
            con.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return result;
    }

}
