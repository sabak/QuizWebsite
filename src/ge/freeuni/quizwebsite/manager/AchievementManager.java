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
    public Achievement getAchievementManager(Integer id);

    /**
     * Unlocks desired achievement for given account
     *
     * @param acc  Target user account
     * @param type Desired achievement
     */
    public void unlockAchievement(Account acc, AchievementType type);

    /**
     * Checks whether user is eligible for any new achievements.
     *
     * @param acc Target user account
     */
    public void checkForAchievements(Account acc);

    /**
     * Lists all achievements unlocked by given user account.
     *
     * @param acc Target user account
     * @return List of specified achievements
     */
    public List<Account> getAchievements(Account acc);

}
