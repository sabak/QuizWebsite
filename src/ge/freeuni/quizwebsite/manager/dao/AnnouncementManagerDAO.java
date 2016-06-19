package ge.freeuni.quizwebsite.manager.dao;

import ge.freeuni.quizwebsite.manager.AnnouncementManager;
import ge.freeuni.quizwebsite.model.Account;
import ge.freeuni.quizwebsite.model.Announcement;

import javax.sql.DataSource;
import java.util.List;

/**
 * Created by Reaper on 19-06-2016.
 */
public class AnnouncementManagerDAO extends AbstractManagerDAO implements AnnouncementManager {

    public static final String ATTRIBUTE_NAME = "announcement_manager";

    public AnnouncementManagerDAO(DataSource dataSource) {
        super(dataSource);
    }

    @Override
    public Announcement getAnnouncement(Integer id) {
        return null;
    }

    @Override
    public void createAnnouncement(Announcement announcement) {

    }

    @Override
    public List<Announcement> getAnnouncements(Account admin, int limit) {
        return getAnnouncements(admin, 0, limit);
    }

    @Override
    public List<Announcement> getAnnouncements(Account admin, int limitFrom, int limitTo) {
        return null;
    }

    @Override
    public List<Announcement> getAnnouncements(int limit) {
        return getAnnouncements(0, limit);
    }

    @Override
    public List<Announcement> getAnnouncements(int limitFrom, int limitTo) {
        return null;
    }

    @Override
    public void deleteAnnouncements(Announcement announcement) {

    }

}
