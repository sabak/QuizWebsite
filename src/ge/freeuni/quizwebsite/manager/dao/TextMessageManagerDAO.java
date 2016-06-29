package ge.freeuni.quizwebsite.manager.dao;

import ge.freeuni.quizwebsite.manager.AccountManager;
import ge.freeuni.quizwebsite.manager.TextMessageManager;
import ge.freeuni.quizwebsite.manager.dao.db.DbContract;
import ge.freeuni.quizwebsite.model.Account;
import ge.freeuni.quizwebsite.model.message.TextMessage;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Saba on 19-06-2016.
 */
public class TextMessageManagerDAO extends AbstractManagerDAO implements TextMessageManager {

    public static final String ATTRIBUTE_NAME = "text_msg_manager";

    private AccountManager accountManager;

    public TextMessageManagerDAO(DataSource dataSource, AccountManager accountManager) {
        super(dataSource);
        this.accountManager = accountManager;
    }

    @Override
    public TextMessage getMessage(Integer id) {
        TextMessage msg = null;
        try {
            Connection con = dataSource.getConnection();
            String query = "SELECT * FROM " + DbContract.Message.TABLE_NAME + " WHERE "
                    + DbContract.Message.COLUMN_NAME_MESSAGE_ID + " = ?;";
            PreparedStatement statement = con.prepareStatement(query);
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                Integer senderId = rs.getInt(DbContract.Message.COLUMN_NAME_SENDER_ID);
                Integer receiverId = rs.getInt(DbContract.Message.COLUMN_NAME_RECEIVER_ID);
                String text = rs.getString(DbContract.Message.COLUMN_NAME_MESSAGE_TEXT);
                String dateStr = rs.getString(DbContract.Message.COLUMN_NAME_SEND_DATE);
                boolean isRead = rs.getBoolean(DbContract.Message.COLUMN_NAME_IS_READ);

                Account sender = accountManager.getAccount(senderId);
                Account receiver = accountManager.getAccount(receiverId);

                msg = new TextMessage(id, text, sender, receiver, Timestamp.valueOf(dateStr), isRead);
            }
            con.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return msg;
    }

    @Override
    public List<TextMessage> getSentMessages(Account account) {
        return getMessages(account, true);
    }

    @Override
    public List<TextMessage> getReceivedMessages(Account account) {
        return getMessages(account, false);
    }

    private List<TextMessage> getMessages(Account account, boolean isSender) {
        List<TextMessage> messages = new ArrayList<>();
        try (Connection con = dataSource.getConnection()) {
            String col = isSender ? DbContract.Message.COLUMN_NAME_SENDER_ID : DbContract.Message.COLUMN_NAME_RECEIVER_ID;
            String query = "SELECT " + DbContract.Message.COLUMN_NAME_MESSAGE_ID
                    + " FROM " + DbContract.Message.TABLE_NAME + " WHERE " +
                    col + " = ? ORDER BY " + DbContract.Message.COLUMN_NAME_SEND_DATE + " DESC;";
            PreparedStatement statement = con.prepareStatement(query);
            statement.setInt(1, account.getId());
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                TextMessage msg = getMessage(rs.getInt(DbContract.Message.COLUMN_NAME_MESSAGE_ID));
                messages.add(msg);
            }
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return messages;
    }

    @Override
    public List<Account> getConversationsMember(Account account) {
        // SELECT DISTINCT (id) FROM ((SELECT sender_id AS id, send_date FROM message WHERE receiver_id = 2)
        // UNION (SELECT receiver_id AS id, send_date FROM message WHERE sender_id = 2) ORDER BY send_date DESC) AS unique_members;
        List<Account> accounts = new ArrayList<>();
        try (Connection con = dataSource.getConnection();) {
            String unionColName = "id";
            String unionTableName = "unique_members";
            String firstSelect = "SELECT " +
                    DbContract.Message.COLUMN_NAME_SENDER_ID + " AS " + unionColName +
                    ", " + DbContract.Message.COLUMN_NAME_SEND_DATE + " FROM " +
                    DbContract.Message.TABLE_NAME + " WHERE " + DbContract.Message.COLUMN_NAME_RECEIVER_ID +
                    " = ?";
            String secondSelect = "SELECT " +
                    DbContract.Message.COLUMN_NAME_RECEIVER_ID + " AS " + unionColName +
                    ", " + DbContract.Message.COLUMN_NAME_SEND_DATE + " FROM " +
                    DbContract.Message.TABLE_NAME + " WHERE " + DbContract.Message.COLUMN_NAME_SENDER_ID +
                    " = ?";
            String union = "((" + firstSelect + ") UNION (" + secondSelect + ") ORDER BY " +
                    DbContract.Message.COLUMN_NAME_SEND_DATE + " DESC) AS " + unionTableName + ";";
            String query = "SELECT DISTINCT (" + unionColName + ") FROM " + union;
            PreparedStatement statement = con.prepareStatement(query);
            statement.setInt(1, account.getId());
            statement.setInt(2, account.getId());
            System.out.println(statement.toString());
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Account member = accountManager.getAccount(rs.getInt(unionColName));
                accounts.add(member);
            }
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return accounts;
    }

    @Override
    public List<TextMessage> getMessages(Account to, Account from) {
        List<TextMessage> messages = new ArrayList<>();
        try (Connection con = dataSource.getConnection()) {
            String query = "SELECT " + DbContract.Message.COLUMN_NAME_MESSAGE_ID
                    + " FROM " + DbContract.Message.TABLE_NAME + " WHERE (" +
                    DbContract.Message.COLUMN_NAME_SENDER_ID + " = ? AND " +
                    DbContract.Message.COLUMN_NAME_RECEIVER_ID + " = ?) OR (" +
                    DbContract.Message.COLUMN_NAME_SENDER_ID + " = ? AND " +
                    DbContract.Message.COLUMN_NAME_RECEIVER_ID + " = ?) ORDER BY " +
                    DbContract.Message.COLUMN_NAME_SEND_DATE + " DESC;";
            PreparedStatement statement = con.prepareStatement(query);
            statement.setInt(1, from.getId());
            statement.setInt(2, to.getId());
            statement.setInt(3, to.getId());
            statement.setInt(4, from.getId());
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                TextMessage msg = getMessage(rs.getInt(DbContract.Message.COLUMN_NAME_MESSAGE_ID));
                messages.add(msg);
            }
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return messages;
    }

    @Override
    public boolean sendMessage(TextMessage msg) {
        try (Connection con = dataSource.getConnection()) {
            String query = "INSERT INTO " + DbContract.Message.TABLE_NAME + " (" +
                    DbContract.Message.COLUMN_NAME_SENDER_ID + ", " +
                    DbContract.Message.COLUMN_NAME_RECEIVER_ID + ", " +
                    DbContract.Message.COLUMN_NAME_SEND_DATE + ", " +
                    DbContract.Message.COLUMN_NAME_MESSAGE_TEXT + ", " +
                    DbContract.Message.COLUMN_NAME_IS_READ + ") VALUES (?, ?, ?, ?, ?);";
            PreparedStatement statement = con.prepareStatement(query);

            statement.setInt(1, msg.getSender().getId());
            statement.setInt(2, msg.getTarget().getId());
            Timestamp dateSent = msg.getDateSent();
            statement.setString(3, dateSent != null ? dateSent.toString() : getCurrentTimestamp().toString());
            statement.setString(4, msg.getText());
            statement.setBoolean(5, msg.isRead());
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
    public TextMessage markRead(TextMessage msg) {
        try (Connection con = dataSource.getConnection()) {
            String query = "UPDATE " + DbContract.Message.TABLE_NAME + " SET "
                    + DbContract.Message.COLUMN_NAME_IS_READ + " = ? WHERE "
                    + DbContract.Message.COLUMN_NAME_MESSAGE_ID + " = ?;";
            PreparedStatement statement = con.prepareStatement(query);
            statement.setBoolean(1, true);
            statement.setInt(2, msg.getId());
            statement.executeUpdate();

            con.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return getMessage(msg.getId());
    }

    @Override
    public void deleteMessage(TextMessage msg) {
        try (Connection con = dataSource.getConnection()) {
            String query = "DELETE FROM " + DbContract.Message.TABLE_NAME + " WHERE "
                    + DbContract.Message.COLUMN_NAME_MESSAGE_ID + " = ?;";
            PreparedStatement statement = con.prepareStatement(query);
            statement.setInt(1, msg.getId());
            statement.executeUpdate();

            con.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
