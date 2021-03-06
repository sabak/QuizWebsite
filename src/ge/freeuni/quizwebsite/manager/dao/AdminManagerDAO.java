package ge.freeuni.quizwebsite.manager.dao;

import ge.freeuni.quizwebsite.manager.AccountManager;
import ge.freeuni.quizwebsite.manager.AdminManager;
import ge.freeuni.quizwebsite.manager.dao.db.DbContract;
import ge.freeuni.quizwebsite.model.Account;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Saba on 19-06-2016.
 */
public class AdminManagerDAO extends AbstractManagerDAO implements AdminManager {

    public static final String ATTRIBUTE_NAME = "admin_manager";

    private AccountManager accountManager;

    public AdminManagerDAO(DataSource dataSource, AccountManager accountManager) {
        super(dataSource);
        this.accountManager = accountManager;
    }

    @Override
    public boolean isAdmin(Account account) {
        boolean isAdmin = false;
        try (Connection con = dataSource.getConnection()) {
            String query = "SELECT * FROM " + DbContract.Admin.TABLE_NAME + " WHERE "
                    + DbContract.Admin.COLUMN_NAME_ADMIN_ID + " = ?;";
            PreparedStatement statement = con.prepareStatement(query);
            statement.setInt(1, account.getId());

            ResultSet result = statement.executeQuery();
            isAdmin = result.next();
            con.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return isAdmin;
    }

    @Override
    public void addAdmin(Account account) {
        try (Connection con = dataSource.getConnection()) {
            String query = "INSERT INTO " + DbContract.Admin.TABLE_NAME + " VALUES (?);";
            PreparedStatement statement = con.prepareStatement(query);
            statement.setInt(1, account.getId());
            statement.executeUpdate();

            con.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Override
    public void removeAdmin(Account account) {
        try (Connection con = dataSource.getConnection()) {
            String query = "DELETE FROM " + DbContract.Admin.TABLE_NAME + " WHERE " +
                    DbContract.Admin.COLUMN_NAME_ADMIN_ID + " = ?;";
            PreparedStatement statement = con.prepareStatement(query);
            statement.setInt(1, account.getId());
            statement.executeUpdate();

            con.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Override
    public List<Account> getAdmins(int limit) {
        return getAdmins(0, limit);
    }

    @Override
    public List<Account> getAdmins(int limitFrom, int limitTo) {
        List<Account> accounts = new ArrayList<>();
        try (Connection con = dataSource.getConnection();) {
            String query = "SELECT " + DbContract.Admin.COLUMN_NAME_ADMIN_ID
                    + " FROM " + DbContract.Admin.TABLE_NAME + " LIMIT ?, ?;";
            PreparedStatement statement = con.prepareStatement(query);
            statement.setInt(1, limitFrom);
            statement.setInt(2, limitTo);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Account acc = accountManager.getAccount(rs.getInt(DbContract.Admin.COLUMN_NAME_ADMIN_ID));
                accounts.add(acc);
            }
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return accounts;
    }

}
