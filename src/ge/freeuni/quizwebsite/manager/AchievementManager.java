package ge.freeuni.quizwebsite.manager;

import ge.freeuni.quizwebsite.model.Account;
import ge.freeuni.quizwebsite.model.Achievement;
import ge.freeuni.quizwebsite.model.AchievementType;

import java.util.List;

/**
 * Created by Saba on 6/17/2016.
 */
public interface AchievementManager {
    public Achievement getAchievementManager(Integer id);

    public void unlockAchievement(Account acc, AchievementType type);

    public void checkForAchievements(Account acc);

    public List<Account> getAchievements(Account acc);

}
