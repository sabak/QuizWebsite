package ge.freeuni.quizwebsite.manager;

import ge.freeuni.quizwebsite.model.Account;
import ge.freeuni.quizwebsite.model.Announcement;

import java.util.List;

/**
 * Created by Saba on 6/17/2016.
 */
public interface AnnouncementManager {

    /**
     * Returns announcement by given unique identifier.
     *
     * @param id Unique identifier
     * @return Found announcement, null if not found
     */
    Announcement getAnnouncement(Integer id);

    /**
     * Saves given announcement.
     *
     * @param announcement Announcement to be saved
     */
    void createAnnouncement(Announcement announcement);

    /**
     * Returns all announcements made by specified admin user account.
     * Query size limited by given parameter <code>limit</code>.
     *
     * @param admin Target admin user account
     * @param limit Number of consecutive announcements to return
     * @return List of announcements
     */
    List<Announcement> getAnnouncements(Account admin, int limit);

    /**
     * Returns all announcements made by specified admin user account.
     * Query size limited by given parameters <code>limitFrom</code> and
     * <codde>limitTo</codde>
     *
     * @param admin     Target admin user account
     * @param limitFrom Index to get announcements from this point onwards
     * @param limitTo   Stop index
     * @return List of announcements
     */
    List<Announcement> getAnnouncements(Account admin, int limitFrom, int limitTo);

    /**
     * Returns all announcements.
     * Query size limited by given parameter <code>limit</code>.
     *
     * @param limit Number of consecutive announcements to return
     * @return List of announcements
     */
    List<Announcement> getAnnouncements(int limit);

    /**
     * Returns all announcements.
     * Query size limited by given parameters <code>limitFrom</code> and
     * <codde>limitTo</codde>
     *
     * @param limitFrom Index to get announcements from this point onwards
     * @param limitTo   Stop index
     * @return List of announcements
     */
    List<Announcement> getAnnouncements(int limitFrom, int limitTo);

    /**
     * Removes given announcement.
     *
     * @param announcement Announcement object to be removed.
     */
    void deleteAnnouncements(Announcement announcement);

}
