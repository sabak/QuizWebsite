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
            String query = "SELECT * FROM " + DbContract.Account.TABLE_NAME + " WHERE "
                    + DbContract.Account.COLUMN_NAME_ACCOUNT_ID + " = ?;";
            PreparedStatement statement = con.prepareStatement(query);
            statement.setInt(1, id);
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
            String query = "SELECT * FROM " + DbContract.Account.TABLE_NAME + " WHERE " +
                    DbContract.Account.COLUMN_NAME_USERNAME + " = ?;";
            PreparedStatement statement = con.prepareStatement(query);
            statement.setString(1, username);
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
            String query = "SELECT * FROM " + DbContract.Account.TABLE_NAME + " WHERE " +
                    DbContract.Account.COLUMN_NAME_USERNAME + " = ?;";
            PreparedStatement statement = con.prepareStatement(query);
            statement.setString(1, username);

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
            String query = "INSERT INTO " + DbContract.Account.TABLE_NAME + " (" +
                    DbContract.Account.COLUMN_NAME_USERNAME + ", " +
                    DbContract.Account.COLUMN_NAME_HASHED_PASSWORD + ", " +
                    DbContract.Account.COLUMN_NAME_EMAIL + ", " +
                    DbContract.Account.COLUMN_NAME_FIRST_NAME + ", " +
                    DbContract.Account.COLUMN_NAME_LAST_NAME + ") VALUES (?, ?, ?, ?, ?);";
            PreparedStatement statement = con.prepareStatement(query);

            statement.setString(1, account.getUsername());
            statement.setString(2, account.getHashedPassword());
            statement.setString(3, account.getEmail());
            statement.setString(4, account.getFirstName());
            statement.setString(5, account.getLastName());
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
            String query = "DELETE FROM " + DbContract.Account.TABLE_NAME + " WHERE "
                    + DbContract.Account.COLUMN_NAME_USERNAME + " = ?;";
            PreparedStatement statement = con.prepareStatement(query);
            statement.setString(1, account.getUsername());
            statement.executeUpdate();

            con.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Override
    public Account changeHashedPassword(Account account, String newHashedPassword) {
        try (Connection con = dataSource.getConnection()) {
            String query = "UPDATE " + DbContract.Account.TABLE_NAME + " SET "
                    + DbContract.Account.COLUMN_NAME_HASHED_PASSWORD + " = ? WHERE "
                    + DbContract.Account.COLUMN_NAME_USERNAME + " = ?;";
            PreparedStatement statement = con.prepareStatement(query);
            statement.setString(1, newHashedPassword);
            statement.setString(2, account.getUsername());
            statement.executeUpdate();

            con.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return getAccount(account.getId());
    }

    @Override
    public Account changeEmail(Account account, String newEmail) {
        try (Connection con = dataSource.getConnection()) {
            String query = "UPDATE " + DbContract.Account.TABLE_NAME + " SET "
                    + DbContract.Account.COLUMN_NAME_EMAIL + " = ? WHERE "
                    + DbContract.Account.COLUMN_NAME_USERNAME + " = ?;";
            PreparedStatement statement = con.prepareStatement(query);
            statement.setString(1, newEmail);
            statement.setString(2, account.getUsername());
            statement.executeUpdate();

            con.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return getAccount(account.getId());
    }

    @Override
    public Account changeFirstName(Account account, String newFirstName) {
        try (Connection con = dataSource.getConnection()) {
            String query = "UPDATE " + DbContract.Account.TABLE_NAME + " SET "
                    + DbContract.Account.COLUMN_NAME_FIRST_NAME + " = ? WHERE "
                    + DbContract.Account.COLUMN_NAME_USERNAME + " = ?;";
            PreparedStatement statement = con.prepareStatement(query);
            statement.setString(1, newFirstName);
            statement.setString(2, account.getUsername());
            statement.executeUpdate();

            con.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return getAccount(account.getId());
    }

    @Override
    public Account changeLastName(Account account, String newLastName) {
        try (Connection con = dataSource.getConnection()) {
            String query = "UPDATE " + DbContract.Account.TABLE_NAME + " SET "
                    + DbContract.Account.COLUMN_NAME_LAST_NAME + " = ? WHERE "
                    + DbContract.Account.COLUMN_NAME_USERNAME + " = ?;";
            PreparedStatement statement = con.prepareStatement(query);
            statement.setString(1, newLastName);
            statement.setString(2, account.getUsername());
            statement.executeUpdate();

            con.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return getAccount(account.getId());
    }

    @Override
    public List<Account> getAccounts(int limit) {
        return getAccounts(0, limit);
    }

    @Override
    public List<Account> getAccounts(int limitFrom, int limitTo) {
        List<Account> accounts = new ArrayList<>();
        try (Connection con = dataSource.getConnection();) {
            String query = "SELECT " + DbContract.Account.COLUMN_NAME_USERNAME
                    + " FROM " + DbContract.Account.TABLE_NAME + " LIMIT ?, ?;";
            PreparedStatement statement = con.prepareStatement(query);
            statement.setInt(1, limitFrom);
            statement.setInt(2, limitTo);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Account acc = getAccount(rs.getString(DbContract.Account.COLUMN_NAME_USERNAME));
                accounts.add(acc);
            }
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return accounts;
    }

    @Override
    public int getAccountsQuantity() {
        int result = 0;
        try {
            Connection con = dataSource.getConnection();
            String query = "SELECT COUNT(" + DbContract.Account.COLUMN_NAME_USERNAME
                    + ") FROM " + DbContract.Account.TABLE_NAME + ";";
            PreparedStatement statement = con.prepareStatement(query);
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
