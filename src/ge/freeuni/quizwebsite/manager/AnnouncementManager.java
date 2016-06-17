package ge.freeuni.quizwebsite.manager;

import ge.freeuni.quizwebsite.model.Account;
import ge.freeuni.quizwebsite.model.Announcement;

import java.util.List;

/**
 * Created by Saba on 6/17/2016.
 */
public interface AnnouncementManager {

    public Announcement getAnnouncement(Integer id);

    public void createAnnouncement(Account admin, Announcement announcement);

    public List<Announcement> getAnnouncements(Account admin, int limit);

    public List<Announcement> getAnnouncements(Account admin, int limitFrom, int limitTo);

    public List<Announcement> getAnnouncements(int limit);

    public List<Announcement> getAnnouncements(int limitFrom, int limitTo);

    public void deleteAnnouncements(Announcement announcement);

    public void setAdminManager(AdminManager manager);

}
