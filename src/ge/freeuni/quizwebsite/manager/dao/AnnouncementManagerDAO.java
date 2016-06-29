package ge.freeuni.quizwebsite.manager.dao;

import ge.freeuni.quizwebsite.manager.AccountManager;
import ge.freeuni.quizwebsite.manager.AnnouncementManager;
import ge.freeuni.quizwebsite.manager.dao.db.DbContract;
import ge.freeuni.quizwebsite.model.Account;
import ge.freeuni.quizwebsite.model.Announcement;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Saba on 19-06-2016.
 */
public class AnnouncementManagerDAO extends AbstractManagerDAO implements AnnouncementManager {

    public static final String ATTRIBUTE_NAME = "announcement_manager";

    private AccountManager accountManager;

    public AnnouncementManagerDAO(DataSource dataSource, AccountManager accountManager) {
        super(dataSource);
        this.accountManager = accountManager;
    }

    @Override
    public Announcement getAnnouncement(Integer id) {
        Announcement announcement = null;
        try {
            Connection con = dataSource.getConnection();
            String query = "SELECT * FROM " + DbContract.Announcement.TABLE_NAME + " WHERE "
                    + DbContract.Announcement.COLUMN_NAME_ANNOUNCEMENT_ID + " = ?;";
            PreparedStatement statement = con.prepareStatement(query);
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                Integer authorId = rs.getInt(DbContract.Announcement.COLUMN_NAME_ACCOUNT_ID);
                String text = rs.getString(DbContract.Announcement.COLUMN_NAME_ANNOUNCEMENT);
                Timestamp postDate = rs.getTimestamp(DbContract.Announcement.COLUMN_NAME_POST_DATE);

                Account author = accountManager.getAccount(authorId);

                announcement = new Announcement(id, text, author, postDate);
            }
            con.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return announcement;
    }

    @Override
    public boolean createAnnouncement(Announcement announcement) {
        try (Connection con = dataSource.getConnection()) {
            String query = "INSERT INTO " + DbContract.Announcement.TABLE_NAME + " (" +
                    DbContract.Announcement.COLUMN_NAME_ACCOUNT_ID + ", " +
                    DbContract.Announcement.COLUMN_NAME_ANNOUNCEMENT + ", " +
                    DbContract.Announcement.COLUMN_NAME_POST_DATE + ") VALUES (?, ?, ?);";
            PreparedStatement statement = con.prepareStatement(query);
            statement.setInt(1, announcement.getAuthor().getId());
            statement.setString(2, announcement.getText());
            Timestamp datePosted = announcement.getDatePosted();
            statement.setTimestamp(3, datePosted != null ? datePosted : getCurrentTimestamp());
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
    public List<Announcement> getAnnouncements(Account admin, int limit) {
        return getAnnouncements(admin, 0, limit);
    }

    @Override
    public List<Announcement> getAnnouncements(Account admin, int limitFrom, int limitTo) {
        List<Announcement> announcements = new ArrayList<>();
        try (Connection con = dataSource.getConnection()) {
            String query = "SELECT " + DbContract.Announcement.COLUMN_NAME_ANNOUNCEMENT_ID
                    + " FROM " + DbContract.Announcement.TABLE_NAME + " WHERE " +
                    DbContract.Announcement.COLUMN_NAME_ACCOUNT_ID + " = ? LIMIT ?, ?;";
            PreparedStatement statement = con.prepareStatement(query);
            statement.setInt(1, admin.getId());
            statement.setInt(2, limitFrom);
            statement.setInt(3, limitTo);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Announcement announcement = getAnnouncement(rs.getInt(DbContract.Announcement.COLUMN_NAME_ANNOUNCEMENT_ID));
                announcements.add(announcement);
            }
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return announcements;
    }

    @Override
    public List<Announcement> getAnnouncements(int limit) {
        return getAnnouncements(0, limit);
    }

    @Override
    public List<Announcement> getAnnouncements(int limitFrom, int limitTo) {
        List<Announcement> announcements = new ArrayList<>();
        try (Connection con = dataSource.getConnection()) {
            String query = "SELECT " + DbContract.Announcement.COLUMN_NAME_ANNOUNCEMENT_ID
                    + " FROM " + DbContract.Announcement.TABLE_NAME + " LIMIT ?, ?;";
            PreparedStatement statement = con.prepareStatement(query);
            statement.setInt(1, limitFrom);
            statement.setInt(2, limitTo);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Announcement announcement = getAnnouncement(rs.getInt(DbContract.Announcement.COLUMN_NAME_ANNOUNCEMENT_ID));
                announcements.add(announcement);
            }
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return announcements;
    }

    @Override
    public void deleteAnnouncements(Announcement announcement) {
        try (Connection con = dataSource.getConnection()) {
            String query = "DELETE FROM " + DbContract.Announcement.TABLE_NAME + " WHERE "
                    + DbContract.Announcement.COLUMN_NAME_ANNOUNCEMENT_ID + " = ?;";
            PreparedStatement statement = con.prepareStatement(query);
            statement.setInt(1, announcement.getId());
            statement.executeUpdate();

            con.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
