package ge.freeuni.quizwebsite.manager.dao;

import ge.freeuni.quizwebsite.manager.AchievementManager;
import ge.freeuni.quizwebsite.model.Account;
import ge.freeuni.quizwebsite.model.Achievement;
import ge.freeuni.quizwebsite.model.AchievementType;

import javax.sql.DataSource;
import java.util.List;

/**
 * Created by Reaper on 19-06-2016.
 */
public class AchievementManagerDAO extends AbstractManagerDAO implements AchievementManager {

    public static final String ATTRIBUTE_NAME = "achievement_manager";

    public AchievementManagerDAO(DataSource dataSource) {
        super(dataSource);
    }

    @Override
    public Achievement getAchievementManager(Integer id) {
        return null;
    }

    @Override
    public void unlockAchievement(Account acc, AchievementType type) {

    }

    @Override
    public void checkForAchievements(Account acc) {

    }

    @Override
    public List<Account> getAchievements(Account acc) {
        return null;
    }
}
