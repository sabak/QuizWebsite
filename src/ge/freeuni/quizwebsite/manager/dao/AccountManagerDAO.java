package ge.freeuni.quizwebsite.manager.dao;

import ge.freeuni.quizwebsite.manager.AccountManager;
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
            String query = "SELECT * FROM account WHERE id = ?;";
            PreparedStatement statement = con.prepareStatement(query);
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                String user = rs.getString("username");
                String pwd = rs.getString("hashed_password");
                String email = rs.getString("email_address");
                String firstName = rs.getString("first_name");
                String lastName = rs.getString("last_name");

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
            String query = "SELECT * FROM account WHERE username = ?;";
            PreparedStatement statement = con.prepareStatement(query);
            statement.setString(1, username);
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                Integer id = rs.getInt("id");
                String pwd = rs.getString("hashed_password");
                String email = rs.getString("email_address");
                String firstName = rs.getString("first_name");
                String lastName = rs.getString("last_name");

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
            String query = "SELECT * FROM account WHERE username=?;";
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
            String query = "INSERT INTO account VALUES(?, ?, ?, ?, ?);";
            PreparedStatement statement = con.prepareStatement(query);
            statement.setString(1, account.getUsername());
            statement.setString(2, account.getHashedPassword());
            statement.setString(3, account.getEmail());
            statement.setString(4, account.getFirstName());
            statement.setString(4, account.getLastName());
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
            String query = "DELETE FROM account WHERE username = ?;";
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
    public void changeHashedPassword(Account account, String newHashedPassword) {
        try (Connection con = dataSource.getConnection()) {
            String query = "UPDATE account SET hashed_password = ? WHERE username = ?;";
            PreparedStatement statement = con.prepareStatement(query);
            statement.setString(1, newHashedPassword);
            statement.setString(2, account.getUsername());
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
            String query = "UPDATE account SET email_address = ? WHERE username = ?;";
            PreparedStatement statement = con.prepareStatement(query);
            statement.setString(1, newEmail);
            statement.setString(2, account.getUsername());
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
            String query = "UPDATE account SET first_name = ? WHERE username = ?;";
            PreparedStatement statement = con.prepareStatement(query);
            statement.setString(1, newFirstName);
            statement.setString(2, account.getUsername());
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
            String query = "UPDATE account SET last_name = ? WHERE username = ?;";
            PreparedStatement statement = con.prepareStatement(query);
            statement.setString(1, newLastName);
            statement.setString(2, account.getUsername());
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
        List<Account> accounts = new ArrayList<Account>();
        Connection con = dataSource.getConnection();
        String query = "SELECT username FROM Accounts LIMIT ?, ?;";
        PreparedStatement statement = con.prepareStatement(query);
        statement.setInt(1, limitFrom);
        statement.setInt(2, limitTo);
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
            String query = "SELECT COUNT(username) FROM account";
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
