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
     * Returns achievement by given unique identifier.
     *
     * @param id Unique identifier
     * @return Achievement object
     */
    Achievement getAchievementManager(Integer id);

    /**
     * Unlocks desired achievement for given account
     *
     * @param account Target user account
     * @param type    Desired achievement
     */
    void unlockAchievement(Account account, AchievementType type);

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
    List<Account> getAchievements(Account account);

}
