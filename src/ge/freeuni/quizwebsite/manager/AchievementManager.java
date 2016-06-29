package ge.freeuni.quizwebsite.manager;

import ge.freeuni.quizwebsite.model.Account;
import ge.freeuni.quizwebsite.model.Achievement;
import ge.freeuni.quizwebsite.model.AchievementType;

import java.util.List;

/**
 * Created by Saba on 6/17/2016.
 */
public interface AchievementManager {

    /**
     * Unlocks desired achievement for given account
     *
     * @param account     Target user account
     * @param achievement Desired achievement
     */
    void unlockAchievement(Account account, Achievement achievement);

    /**
     * Checks whether user is eligible for any new achievements.
     *
     * @param account Target user account
     */
    void checkForAchievements(Account account);

    /**
     * Lists all achievements unlocked by given user account.
     *
     * @param account Target user account
     * @return List of specified achievements
     */
    List<Achievement> getAchievements(Account account);

}
