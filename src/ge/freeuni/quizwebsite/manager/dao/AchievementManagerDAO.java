package ge.freeuni.quizwebsite.manager.dao;

import ge.freeuni.quizwebsite.manager.AchievementManager;
import ge.freeuni.quizwebsite.manager.dao.db.DbContract;
import ge.freeuni.quizwebsite.model.Account;
import ge.freeuni.quizwebsite.model.Achievement;
import ge.freeuni.quizwebsite.model.AchievementType;
import ge.freeuni.quizwebsite.model.message.TextMessage;

import javax.sql.DataSource;
import java.sql.*;
import java.util.List;

/**
 * Created by Saba on 19-06-2016.
 */
public class AchievementManagerDAO extends AbstractManagerDAO implements AchievementManager {

    public static final String ATTRIBUTE_NAME = "achievement_manager";

    public AchievementManagerDAO(DataSource dataSource) {
        super(dataSource);
    }

    @Override
    public void unlockAchievement(Account account, AchievementType type) {

    }

    @Override
    public void checkForAchievements(Account account) {

    }

    @Override
    public List<Achievement> getAchievements(Account account) {
        return null;
    }

}
