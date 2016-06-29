package ge.freeuni.quizwebsite.manager.dao;

import ge.freeuni.quizwebsite.manager.AccountManager;
import ge.freeuni.quizwebsite.manager.FriendManager;
import ge.freeuni.quizwebsite.manager.dao.db.DbContract;
import ge.freeuni.quizwebsite.model.Account;
import ge.freeuni.quizwebsite.model.message.FriendRequest;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Saba on 19-06-2016.
 */
public class FriendManagerDAO extends AbstractManagerDAO implements FriendManager {

    public static final String ATTRIBUTE_NAME = "friend_manager";

    private AccountManager accountManager;

    public FriendManagerDAO(DataSource dataSource, AccountManager accountManager) {
        super(dataSource);
        this.accountManager = accountManager;
    }

    @Override
    public FriendRequest getFriendRequest(Integer id) {
        FriendRequest friendRequest = null;
        try {
            Connection con = dataSource.getConnection();
            String query = "SELECT * FROM " + DbContract.FriendRequest.TABLE_NAME + " WHERE "
                    + DbContract.FriendRequest.COLUMN_NAME_REQUEST_ID + " = ?;";
            PreparedStatement statement = con.prepareStatement(query);
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                Integer senderId = rs.getInt(DbContract.FriendRequest.COLUMN_NAME_SENDER_ID);
                Integer receiverId = rs.getInt(DbContract.FriendRequest.COLUMN_NAME_RECEIVER_ID);
                Timestamp date = rs.getTimestamp(DbContract.FriendRequest.COLUMN_NAME_SEND_DATE);

                Account sender = accountManager.getAccount(senderId);
                Account receiver = accountManager.getAccount(receiverId);

                friendRequest = new FriendRequest(id, sender, receiver, date);
            }
            con.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return friendRequest;
    }

    @Override
    public List<FriendRequest> getFriendRequests(Account account) {
        List<FriendRequest> friendRequests = new ArrayList<>();
        try (Connection con = dataSource.getConnection()) {
            String query = "SELECT " + DbContract.FriendRequest.COLUMN_NAME_REQUEST_ID
                    + " FROM " + DbContract.FriendRequest.TABLE_NAME + " WHERE " +
                    DbContract.FriendRequest.COLUMN_NAME_RECEIVER_ID + " = ? ORDER BY "
                    + DbContract.Message.COLUMN_NAME_SEND_DATE + " DESC;";
            PreparedStatement statement = con.prepareStatement(query);
            statement.setInt(1, account.getId());
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                FriendRequest friendRequest = getFriendRequest(rs.getInt(DbContract.FriendRequest.COLUMN_NAME_REQUEST_ID));
                friendRequests.add(friendRequest);
            }
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return friendRequests;
    }

    @Override
    public void requestFriendship(Account sender, Account receiver) {
        try (Connection con = dataSource.getConnection()) {
            String query = "INSERT INTO " + DbContract.FriendRequest.TABLE_NAME + " (" +
                    DbContract.FriendRequest.COLUMN_NAME_SENDER_ID + ", " +
                    DbContract.FriendRequest.COLUMN_NAME_RECEIVER_ID + ", " +
                    DbContract.FriendRequest.COLUMN_NAME_SEND_DATE + ") VALUES (?, ?, ?);";
            PreparedStatement statement = con.prepareStatement(query);

            statement.setInt(1, sender.getId());
            statement.setInt(2, receiver.getId());
            statement.setTimestamp(3, getCurrentTimestamp());
            statement.executeUpdate();

            con.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Override
    public void confirmFriendship(FriendRequest request) {
        addFriend(request.getSender(), request.getTarget());
        addFriend(request.getTarget(), request.getSender()); // naive solution
        deleteRequest(request);
    }

    private void addFriend(Account sender, Account target) {
        try (Connection con = dataSource.getConnection()) {
            String query = "INSERT INTO " + DbContract.Friend.TABLE_NAME + " (" +
                    DbContract.Friend.COLUMN_NAME_ACCOUNT_1 + ", " +
                    DbContract.Friend.COLUMN_NAME_ACCOUNT_2 + ") VALUES (?, ?);";
            PreparedStatement statement = con.prepareStatement(query);

            statement.setInt(1, sender.getId());
            statement.setInt(2, target.getId());
            statement.executeUpdate();

            con.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Override
    public void declineFriendship(FriendRequest request) {
        deleteRequest(request);
    }

    private void deleteRequest(FriendRequest request) {
        try (Connection con = dataSource.getConnection()) {
            String query = "DELETE FROM " + DbContract.FriendRequest.TABLE_NAME + " WHERE "
                    + DbContract.FriendRequest.COLUMN_NAME_REQUEST_ID + " = ?;";
            PreparedStatement statement = con.prepareStatement(query);
            statement.setInt(1, request.getId());
            statement.executeUpdate();

            con.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Override
    public void removeFriend(Account account, Account toRemove) {
        deleteFriend(account, toRemove);
        deleteFriend(toRemove, account);
    }

    private void deleteFriend(Account account1, Account account2) {
        try (Connection con = dataSource.getConnection()) {
            String query = "DELETE FROM " + DbContract.Friend.TABLE_NAME + " WHERE "
                    + DbContract.Friend.COLUMN_NAME_ACCOUNT_1 + " = ? AND "
                    + DbContract.Friend.COLUMN_NAME_ACCOUNT_2 + " = ?;";
            PreparedStatement statement = con.prepareStatement(query);
            statement.setInt(1, account1.getId());
            statement.setInt(2, account2.getId());
            statement.executeUpdate();

            con.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Override
    public List<Account> getFriends(Account account) {
        List<Account> friends = new ArrayList<>();
        try (Connection con = dataSource.getConnection()) {
            String query = "SELECT " + DbContract.Friend.COLUMN_NAME_ACCOUNT_2
                    + " FROM " + DbContract.Friend.TABLE_NAME + " WHERE " +
                    DbContract.Friend.COLUMN_NAME_ACCOUNT_1 + " = ?;";
            PreparedStatement statement = con.prepareStatement(query);
            statement.setInt(1, account.getId());
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Account friend = accountManager.getAccount(rs.getInt(DbContract.Friend.COLUMN_NAME_ACCOUNT_2));
                friends.add(friend);
            }
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return friends;
    }

}
